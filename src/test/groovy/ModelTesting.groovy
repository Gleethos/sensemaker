import org.junit.Test
import sensemaker.gui.models.base.EXIFModel
import sensemaker.gui.models.base.IPTCModel
import sensemaker.gui.models.base.PhotographerModel
import sensemaker.gui.models.base.PictureModel
import sensemaker.gui.models.composits.DetailedPictureModel

import java.sql.Date

class ModelTesting
{
    @Test
    void picture_model_generates_expected_prepare_statement_map()
    {
        PictureModel m = new PictureModel()

        def map = m.generatePreparedSQLKeyValues(Integer.class)
        assert map.isEmpty()
        assert m.generatePreparedSQLKeyValues(Object.class).isEmpty()
        m.setId(4)
        map = m.generatePreparedSQLKeyValues(Integer.class)
        assert map.any {it.key.contains("pictures.id = ?") && it.value==4}
        assert map.size()==1
        assert m.generatePreparedSQLKeyValues(Date.class).isEmpty()

        Date date = new Date(System.currentTimeMillis())
        m.setCreated(date)
        map = m.generatePreparedSQLKeyValues(Date.class)
        assert map.any {it.key.contains("pictures.created = ?") && it.value==date}
        assert m.generatePreparedSQLKeyValues(Object.class).size()==2
        m.setEXIFId(4)
        map = m.generatePreparedSQLKeyValues(Object.class)
        assert map.size()==3
        assert map.any {it.key.contains("pictures.EXIF_id = ?")&&it.value==4}
    }

    @Test
    void detailed_picture_model_generates_expected_prepared_key_values()
    {
        Date date = new Date(System.currentTimeMillis())
        EXIFModel exif = new EXIFModel()
                .setId(1)
                .setCreated(date)
                .setOrientation("Hochformat")
                .setShot(date)
        IPTCModel iptc = new IPTCModel()
                .setId(3)
                .setCreated(date)
                .setDescription("Pretty nice")
                .setKeywords("#Mr#Bean#Teddy")
                .setCopyright("royalty free")
        PhotographerModel photographer = new PhotographerModel()
                .setId(5)
                .setCreated(date)
        PictureModel picture = new PictureModel()
                .setId(9)
                .setPath("my/awesome/path.png")
                .setEXIFId(1)
                .setIPTCId(3)
                .setPhotographerId(5)
        DetailedPictureModel detailed = new DetailedPictureModel(picture, exif, iptc, photographer)

        def map = detailed.generatePreparedSQLKeyValues(Object.class)
        assert map.toString()=="[IPTCs.keywords = ?:#Mr#Bean#Teddy, pictures.path = ?:my/awesome/path.png, EXIFs.created = ?:"+date.toString()+", EXIFs.id = ?:1, " +
                "IPTCs.copyright = ?:royalty free, EXIFs.orientation = ?:Hochformat, pictures.EXIF_id = ?:1, " +
                "IPTCs.id = ?:3, pictures.id = ?:9, IPTCs.description = ?:Pretty nice, pictures.IPTC_id = ?:3, " +
                "EXIFs.shot = ?:"+date.toString()+", pictures.photographer_id = ?:5, IPTCs.created = ?:"+date.toString()+"]"

    }

    @Test
    void detailed_picture_model_generates_expected_insert_key_values()
    {
        Date date = new Date(System.currentTimeMillis())
        EXIFModel exif = new EXIFModel()
                .setId(1)
                .setCreated(date)
                .setOrientation("Hochformat")
                .setShot(date)
        IPTCModel iptc = new IPTCModel()
                .setId(3)
                .setCreated(date)
                .setDescription("Pretty nice")
                .setKeywords("#Mr#Bean#Teddy")
                .setCopyright("royalty free")
        PhotographerModel photographer = new PhotographerModel()
                .setId(5)
                .setCreated(date)
        PictureModel picture = new PictureModel()
                .setId(9)
                .setPath("my/awesome/path.png")
                .setEXIFId(1)
                .setIPTCId(3)
                .setPhotographerId(5)
        DetailedPictureModel detailed = new DetailedPictureModel(picture, exif, iptc, photographer)

        def map = detailed.defaultInsertKeyValues(Object.class)
        assert map.toString().equals("[path:my/awesome/path.png, orientation:Hochformat, copyright:royalty free, keywords:#Mr#Bean#Teddy, EXIF_id:1, created:"+date.toString()+", description:Pretty nice, id:9, IPTC_id:3, photographer_id:5, shot:"+date.toString()+"]")
        assert !map.toString().contains("deleted")

        map = detailed.generateSoftPreparedSQLKeyValues(Object.class)
        print map

    }


}
