import sensemaker.datalayer.API.DAL
import sensemaker.datalayer.assembly.DALFactory
import sensemaker.datalayer.db.SQLiteDAL
import sensemaker.datalayer.mock.MockDAL

import org.junit.Test
import sensemaker.gui.models.EXIFModel
import sensemaker.gui.models.IPTCModel
import sensemaker.gui.models.PhotographerModel
import sensemaker.gui.models.PictureModel

public class DALTesting
{

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

    @Test
    void DALFactory_mock_has_Access_mocks()
    {
        def factory = new DALFactory()
        factory.setDoMocking(true)
        DAL dal = factory.produce()
        assert dal instanceof MockDAL
        assert dal.access(PhotographerModel.class)!=null
        assert dal.access(PictureModel.class)!=null
        assert dal.access(IPTCModel.class)!=null
        assert dal.access(EXIFModel.class)!=null
    }

    @Test
    void SQLDal_has_all_accessors()
    {
        def factory = new DALFactory()
        factory.setDoMocking(false) //:= SQLiteDAL
        DAL dal = factory.produce()
        assert dal instanceof SQLiteDAL
        assert dal.access(PhotographerModel.class)!=null
        assert dal.access(PictureModel.class)!=null
        assert dal.access(IPTCModel.class)!=null
        assert dal.access(EXIFModel.class)!=null
    }









}
