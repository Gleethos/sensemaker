package sensemaker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Environment
{
    private static final Map<Thread, Environment> _instances = new ConcurrentHashMap<>();
    private final Settings _settings;

    private Environment() {
        System.setProperty("log4j.properties", String.valueOf(this.getClass().getResource("log4j.properties")));
        _settings = Settings.instance();
    }

    public static Environment instance()
    {
        Thread thread = Thread.currentThread();
        if(_instances.containsKey(thread)) return _instances.get(thread);
        else {
            Environment instance = new Environment();
            _instances.put(thread, instance);
            //synchronized (Sensemaker.class) {
            //    instance.reset();
            //}
            return instance;
        }
    }

    public Settings settings(){
        return _settings;
    }

    static class Settings {

        private static final Settings _instance = new Settings();

        private Datalayer _datalayer;

        private Settings(){
            _datalayer = Datalayer._instance;
        }

        public static Settings instance(){
            return _instance;
        }

        public Datalayer datalayer(){
            return _datalayer;
        }

        static class Datalayer {

            private static Datalayer _instance = new Datalayer();

            private Datalayer() {}


        }

    }

    /**
     * Helper method which reads the file with the given name and returns
     * the contents of this file as a String. Will exit the application
     * if the file can not be read.
     *
     * @param path
     * @return The contents of the file
     */
    public String readResource(String path){
        InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while (line!=null) {
                line = br.readLine();
                if (line != null) sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }


}
