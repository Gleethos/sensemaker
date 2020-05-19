package businesslayer;

public class Gatekeeper {

    private static Gatekeeper _instance;
    static {
        _instance = new Gatekeeper();
    }



    private Gatekeeper(){ }




}
