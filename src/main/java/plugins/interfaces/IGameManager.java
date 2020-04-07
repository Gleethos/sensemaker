package plugins.interfaces;

public interface IGameManager {
    /**
     * Returns a list of all plugins. Never returns null.
     * TODO: Refactor to List<component.IPlugin>, Enumeration is deprecated
     * @return
     */
	Iterable<IGame> getGames();
	
	
    /**
     * Adds a new game. If the game was already added, nothing will happen.
     * @param game
     */
    void add(IGame game);
    
    /**
     * Adds a new plugin by class name. If the plugin was already added, nothing will happen.
     * Throws an exception, when the type cannot be resoled or the class does not implement component.IPlugin.
     * @param game
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    void add(String game) throws Exception;


    
    /**
     * Clears all plugins
     */
    void clear();
}
