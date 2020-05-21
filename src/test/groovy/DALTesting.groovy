import sensemaker.datalayer.API.DAL
import sensemaker.datalayer.assembly.DALFactory
import sensemaker.datalayer.db.SQLiteDAL
import sensemaker.datalayer.mock.MockDAL

import org.junit.Test;

public class DALTesting {

    @Test
    void DALFactory_produces_mock_and_default_dal()
    {
        def factory = new DALFactory()
        factory.setDoMocking(true)
        DAL dal = factory.produce()

        assert dal instanceof MockDAL

        factory.setDoMocking(false)

        dal = factory.produce()

        assert dal instanceof SQLiteDAL
    }







}
