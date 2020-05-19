import datalayer.DAL
import datalayer.DALFactory
import datalayer.mock.MockDAL;
import org.junit.Test;

public class DALTesting {

    @Test
    void DALFactory_produces_mock(){

        def factory = new DALFactory()
        factory.setDoMocking(true)
        DAL dal =factory.produce()

        assert dal instanceof MockDAL

    }







}
