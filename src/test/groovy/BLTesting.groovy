import org.junit.Test
import sensemaker.businesslayer.Gatekeeper
import sensemaker.datalayer.assembly.DALFactory
import sensemaker.gui.models.base.EXIFModel
import sensemaker.gui.models.base.IPTCModel
import sensemaker.gui.models.base.PhotographerModel
import sensemaker.gui.models.base.PictureModel
import sensemaker.gui.models.composits.DetailedPictureModel

import java.sql.Date

class BLTesting {

    @Test
    void bl_syncs_test_images()
    {
        def dal = new DALFactory().setDoMocking(true).produce()
        Gatekeeper gk = new Gatekeeper(dal)
        gk.syncImages("test_images")
        assert dal.access(PictureModel.class).getAll().size()==3
        assert dal.access(EXIFModel.class).getAll().size()==3
        assert dal.access(IPTCModel.class).getAll().size()==3
        assert dal.access(PhotographerModel.class).getAll().size()==3
    }

    @Test
    void bl_test_images_are_configured_properly_by_test_images()
    {
        def dal = new DALFactory().setDoMocking(true).produce()
        Gatekeeper gk = new Gatekeeper(dal)
        gk.syncImages("test_images")
        dal.access(PictureModel.class).getAll().each {
            assert it.getId()!=null
            assert it.getPath()!=null
            assert it.getPath().contains("test_images")
            assert it.getEXIFId()!=null
            assert it.getIPTCId()!=null
        }
        assert dal.access(IPTCModel.class).getAll().collect{it.getTitle()}.equals(["bang.png", "beany.png", "brumm.png"])
    }

    @Test
    void test_image_models_have_current_date_using_mock_dal()
    {
        def dal = new DALFactory().setDoMocking(true).produce()
        Gatekeeper gk = new Gatekeeper(dal)
        gk.syncImages("test_images")

        String date = new Date(System.currentTimeMillis()).toString()
        assert dal.access(PictureModel.class).getAll().collect{it.getCreated().toString()}.equals([date]*3)
        assert dal.access(EXIFModel.class).getAll().collect{it.getCreated().toString()}.equals([date]*3)
        assert dal.access(IPTCModel.class).getAll().collect{it.getCreated().toString()}.equals([date]*3)
        assert dal.access(PhotographerModel.class).getAll().collect{it.getCreated().toString()}.equals([date]*3)
    }

    @Test
    void test_image_models_have_current_date()
    {
        def dal = new DALFactory().setDoMocking(false).produce()
        Gatekeeper gk = new Gatekeeper(dal)
        gk.syncImages("test_images")

        String date = new Date(System.currentTimeMillis()).toString()
        assert dal.access(PictureModel.class).getAll().collect{it.getCreated().toString()}.equals([date]*3)
        assert dal.access(EXIFModel.class).getAll().collect{it.getCreated().toString()}.equals([date]*3)
        assert dal.access(IPTCModel.class).getAll().collect{it.getCreated().toString()}.equals([date]*3)
        assert dal.access(PhotographerModel.class).getAll().collect{it.getCreated().toString()}.equals([date]*3)

        def detailed = dal.access(DetailedPictureModel.class).getAll()
        detailed = detailed.collect {
            it.getPictureModel().defaultInsertKeyValues(Object.class).toString()+"|"+
            it.getEXIFModel().defaultInsertKeyValues(Object.class).toString()+"|"+
            it.getIPTCModel().defaultInsertKeyValues(Object.class).toString()+"|"+
            it.getPhotographerModel().defaultInsertKeyValues(Object.class).toString()
        }.join("\n")
        assert detailed.contains("test_images\\bang.png, EXIF_id:1,")
        assert detailed.contains("test_images\\beany.png, EXIF_id:2,")
        assert detailed.contains("test_images\\brumm.png, EXIF_id:3,")
        assert detailed.contains("id:1, IPTC_id:1, photographer_id:1]|[created:"+date.toString())
        assert detailed.contains("id:2, IPTC_id:2, photographer_id:2]|[created:"+date.toString())
        assert detailed.contains("id:3, IPTC_id:3, photographer_id:3]|[created:"+date.toString())
        assert detailed.contains("title:bang.png]|[forename:")
    }


}
