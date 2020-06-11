import org.junit.Test;
import sensemaker.businesslayer.Gatekeeper;
import sensemaker.datalayer.assembly.DALFactory;

public class BLTesting {

    @Test
    void bl_syncs_images(){

        def dal = new DALFactory().setDoMocking(true).produce()
        Gatekeeper gk = new Gatekeeper(dal)
        gk.syncImages("images")

    }


}
