import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Sensemaker
{
    private static final Map<Thread, Sensemaker> _instances = new ConcurrentHashMap<>();
    private final Settings _settings;

    private Sensemaker() {
        _settings = Settings.instance();
    }

    public static Sensemaker instance(){
        Thread thread = Thread.currentThread();
        if(_instances.containsKey(thread)) return _instances.get(thread);
        else {
            Sensemaker instance = new Sensemaker();
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


}
