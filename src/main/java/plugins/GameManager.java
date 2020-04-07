package plugins;

import plugins.interfaces.IGame;
import plugins.interfaces.IGameManager;
import plugins.state.GameWrapper;
import plugins.state.imp.Known;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameManager implements IGameManager {

    private static final GameManager _instance;
    static { _instance = new GameManager(); }

    Map<String, IGame> _plugins = new HashMap<>();

    private GameManager(){
        this.add("MyGame");
    }
    private void _put(String name, IGame game){
        _plugins.put(name, new GameWrapper(new Known(game)));
    }

    public static synchronized GameManager instance(){
        return _instance;
    }

    public IGame get(String name){
        return _plugins.get(name);
    }

    @Override
    public Iterable<IGame> getGames() {
        return _plugins.values();
    }

    @Override
    public void add(IGame game) {
        if(!_plugins.containsKey(game.getClass().getName())) _put(game.getClass().getName(), game);
    }

    @Override
    public void add(String game){
        if(!_plugins.containsKey(game)){
            try {
                if(loadGame(game, "build/classes/java/main/comp/imp/plugins")){
                    if(loadGame(game, "build/classes/java/test/BIF/SWE1/unittests/mocks")){
                        if(loadGame(game, "plugins")) {
                            //throw new IllegalStateException("Plugin not found!");
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e){ e.printStackTrace(); }
        }
    }

    @Override
    public void clear() {
        _plugins = new HashMap<>();
    }

    public boolean loadGame(String gameName, String packagePath)
            throws MalformedURLException, IOException, ClassNotFoundException
    {
        File location = new File(packagePath);
        String packagePraefix = _resolvePackagePrefix(location.getPath());
        File[] pluginLocations = location.listFiles((File file)->file.getName().endsWith(".jar")||file.getName().endsWith(".class"));
        URL url = null;
        try { url = location.toURI().toURL(); } catch (MalformedURLException ignored) { }
        URL[] urls = new URL[]{ url };
        URLClassLoader classLoader = new URLClassLoader(urls);
        try {
            for(int i = 0; i < Objects.requireNonNull(pluginLocations).length; i++){
                String[] fragments = pluginLocations[i].toPath().getFileName().toString().split("\\.");
                String foundFileName = fragments[0];
                fragments = gameName.split("\\.");
                gameName = fragments[fragments.length-1];
                if(foundFileName.equals(gameName)){// Instantiate plugin:
                    //==============================================================================================\\
                    IGame target = (IGame)classLoader.loadClass(packagePraefix+foundFileName).newInstance();
                    //==============================================================================================\\
                    _put(foundFileName, target);
                    try { classLoader.close(); } catch (IOException ignored) { }
                    return false;
                }
            }
        } catch (Exception e) { }
        try { classLoader.close(); } catch (IOException e) { }
        return true;
    }

    private String _resolvePackagePrefix(String path){
        StringBuilder result = new StringBuilder();
        String[] parts = path.replace("\\", "/").split("/");
        boolean javaFound = false;
        for(String part : parts){
            result.append((javaFound && !part.equals("main") && !part.equals("test")) ? part + "." : "");
            javaFound = (part.equals("java")) || javaFound;
        }
        return result.toString();
    }




}
