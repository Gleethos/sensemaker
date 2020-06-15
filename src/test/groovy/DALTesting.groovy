import sensemaker.businesslayer.Gatekeeper
import sensemaker.datalayer.API.DAL
import sensemaker.datalayer.assembly.DALFactory
import sensemaker.datalayer.db.SQLiteDAL
import sensemaker.datalayer.mock.MockDAL

import org.junit.Test
import sensemaker.gui.models.base.EXIFModel
import sensemaker.gui.models.base.IPTCModel
import sensemaker.gui.models.base.PhotographerModel
import sensemaker.gui.models.base.PictureModel
import sensemaker.gui.models.composits.DetailedPictureModel

import java.sql.Date

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
        // Composite Models:
        assert dal.access(DetailedPictureModel.class)!=null
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
        // Composite Models:
        assert dal.access(DetailedPictureModel.class)!=null
    }

    @Test
    void SQLiteDAL_has_expected_tables()
    {
        def factory = new DALFactory()
        factory.setDoMocking(false) //:= SQLiteDAL
        DAL dal = factory.produce()
        def tables = ((SQLiteDAL)dal).tablesSpace()

        assert "photographers" in tables
        assert "EXIFs" in tables
        assert "IPTCs" in tables
        assert "pictures" in tables
    }

    @Test
    void SQLiteDAL_has_correct_default_table_attributes()
    {
        def factory = new DALFactory()
        factory.setDoMocking(false) //:= SQLiteDAL
        DAL dal = factory.produce()
        def tables = ((SQLiteDAL)dal).tablesSpace()

        tables.each {assert "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT" in it.value}
        tables.each {assert "created DATE NOT NULL" in it.value}
        tables.each {assert "deleted DATE NULL" in it.value}
    }

    @Test
    void SQLiteDAL_has_correct_table_attributes()
    {
        def factory = new DALFactory()
        factory.setDoMocking(false) //:= SQLiteDAL
        DAL dal = factory.produce()
        def tables = ((SQLiteDAL)dal).tablesSpace()

        assert tables["photographers"].any{it.contains("forename TEXT")}
        assert tables["photographers"].any{it.contains("surname TEXT")}
        assert tables["EXIFs"].any{it.contains("orientation")}
        assert tables["EXIFs"].any{it.contains("shot DATE")}
        assert tables["IPTCs"].any{it.contains("title TEXT")}
        assert tables["IPTCs"].any{it.contains("description TEXT")}
        assert tables["IPTCs"].any{it.contains("copyright TEXT")}
        assert tables["IPTCs"].any{it.contains("keywords TEXT")}
    }

    @Test
    void test_findBy_in_SQLiteDAL_after_BL_sync()
    {
        def dal = new DALFactory().setDoMocking(false).produce()
        Gatekeeper gk = new Gatekeeper(dal)
        gk.syncImages("test_images")

        String date = new Date(System.currentTimeMillis()).toString()

        def stringify = { list ->
            return list.collect {
                it.getPictureModel().defaultInsertKeyValues(Object.class).toString() + "|" +
                        it.getEXIFModel().defaultInsertKeyValues(Object.class).toString() + "|" +
                        it.getIPTCModel().defaultInsertKeyValues(Object.class).toString() + "|" +
                        it.getPhotographerModel().defaultInsertKeyValues(Object.class).toString()
            }.join("\n")
        }

        def detailed = stringify(dal.access(DetailedPictureModel.class).findBy(
                new DetailedPictureModel(
                        new PictureModel(),
                        new EXIFModel(),
                        new IPTCModel(),
                        new PhotographerModel()
                )
        ))
        assert detailed.contains("test_images\\bang.png, EXIF_id:1,")
        assert detailed.contains("test_images\\beany.png, EXIF_id:2,")
        assert detailed.contains("test_images\\brumm.png, EXIF_id:3,")
        assert detailed.contains("id:1, IPTC_id:1, photographer_id:1]|[created:"+date.toString())
        assert detailed.contains("id:2, IPTC_id:2, photographer_id:2]|[created:"+date.toString())
        assert detailed.contains("id:3, IPTC_id:3, photographer_id:3]|[created:"+date.toString())
        assert detailed.contains("title:bang.png]|[forename:")

        def found = dal.access(DetailedPictureModel.class).findBy(new DetailedPictureModel(
                new PictureModel(),
                new EXIFModel(),
                new IPTCModel().setTitle("bea"),
                new PhotographerModel()
        ))

        detailed = stringify(found)
        def expectedString = "[path:D:\\development\\java\\studium\\sensemaker\\test_images\\beany.png, EXIF_id:2, " +
                "created:"+date.toString()+", id:2, IPTC_id:2, photographer_id:2]|[created:"+date.toString()+", id:2]|[created:"+date.toString()+", " +
                "id:2, title:beany.png]|[forename:Mr., created:"+date.toString()+", id:2]"

        assert detailed.equals(expectedString)

        found[0].getPhotographerModel().setSurname("Bean")
        dal.access(PhotographerModel.class).save(found[0].getPhotographerModel())

        assert stringify(dal.access(DetailedPictureModel.class).findBy(new DetailedPictureModel(
                new PictureModel(),
                new EXIFModel(),
                new IPTCModel().setTitle("bea"),
                new PhotographerModel()
        ))).equals(expectedString.replace("forename:Mr., created:","forename:Mr., surname:Bean, created:"))
    }

    @Test
    void test_delete_in_SQLiteDAL_after_BL_sync()
    {
        def dal = new DALFactory().setDoMocking(false).produce()
        Gatekeeper gk = new Gatekeeper(dal)
        gk.syncImages("test_images")

        String date = new Date(System.currentTimeMillis()).toString()

        def stringify = { list ->
            return list.collect {
                it.getPictureModel().defaultInsertKeyValues(Object.class).toString() + "|" +
                        it.getEXIFModel().defaultInsertKeyValues(Object.class).toString() + "|" +
                        it.getIPTCModel().defaultInsertKeyValues(Object.class).toString() + "|" +
                        it.getPhotographerModel().defaultInsertKeyValues(Object.class).toString()
            }.join("\n")
        }

        def found = dal.access(DetailedPictureModel.class).findBy(
                new DetailedPictureModel(
                        new PictureModel(),
                        new EXIFModel(),
                        new IPTCModel(),
                        new PhotographerModel()
                )
        )

        def detailed = stringify(found)

        //println detailed
        assert detailed.equals(
                "[path:D:\\development\\java\\studium\\sensemaker\\test_images\\bang.png, EXIF_id:1, created:"+date.toString()+", id:1, IPTC_id:1, photographer_id:1]|[created:"+date.toString()+", id:1]|[created:"+date.toString()+", id:1, title:bang.png]|[forename:Mr., created:"+date.toString()+", id:1]\n" +
                "[path:D:\\development\\java\\studium\\sensemaker\\test_images\\beany.png, EXIF_id:2, created:"+date.toString()+", id:2, IPTC_id:2, photographer_id:2]|[created:"+date.toString()+", id:2]|[created:"+date.toString()+", id:2, title:beany.png]|[forename:Mr., created:"+date.toString()+", id:2]\n" +
                "[path:D:\\development\\java\\studium\\sensemaker\\test_images\\brumm.png, EXIF_id:3, created:"+date.toString()+", id:3, IPTC_id:3, photographer_id:3]|[created:"+date.toString()+", id:3]|[created:"+date.toString()+", id:3, title:brumm.png]|[forename:Mr., created:"+date.toString()+", id:3]")

        dal.access(DetailedPictureModel.class).delete(found[1])

        found = dal.access(DetailedPictureModel.class).findBy(
                new DetailedPictureModel(
                        new PictureModel(),
                        new EXIFModel(),
                        new IPTCModel(),
                        new PhotographerModel()
                )
        )
        assert stringify(found).equals(
            "[path:D:\\development\\java\\studium\\sensemaker\\test_images\\bang.png, EXIF_id:1, created:"+date.toString()+", id:1, IPTC_id:1, photographer_id:1]|[created:"+date.toString()+", id:1]|[created:"+date.toString()+", id:1, title:bang.png]|[forename:Mr., created:"+date.toString()+", id:1]\n" +
            "[path:D:\\development\\java\\studium\\sensemaker\\test_images\\brumm.png, EXIF_id:3, created:"+date.toString()+", id:3, IPTC_id:3, photographer_id:3]|[created:"+date.toString()+", id:3]|[created:"+date.toString()+", id:3, title:brumm.png]|[forename:Mr., created:"+date.toString()+", id:3]")

    }

    @Test
    void test_findBy_with_second_getALL_in_SQLiteDAL_after_BL_sync()
    {
        def dal = new DALFactory().setDoMocking(false).produce()
        Gatekeeper gk = new Gatekeeper(dal)
        gk.syncImages("test_images")

        String date = new Date(System.currentTimeMillis()).toString()

        def stringify = { list ->
            return list.collect {
                it.getPictureModel().defaultInsertKeyValues(Object.class).toString() + "|" +
                        it.getEXIFModel().defaultInsertKeyValues(Object.class).toString() + "|" +
                        it.getIPTCModel().defaultInsertKeyValues(Object.class).toString() + "|" +
                        it.getPhotographerModel().defaultInsertKeyValues(Object.class).toString()
            }.join("\n")
        }

        def all = dal.access(DetailedPictureModel.class).getAll()
        def found = dal.access(DetailedPictureModel.class).findBy(all[1])

        assert  found.size()==1
        assert stringify(all).contains(stringify(found))
        assert stringify(found)==stringify([all[1]])
        assert stringify(found)=="[path:D:\\development\\java\\studium\\sensemaker\\test_images\\beany.png, EXIF_id:2, " +
                "created:"+date.toString()+", id:2, IPTC_id:2, photographer_id:2]|[" +
                "created:"+date.toString()+", id:2]|[created:"+date.toString()+", id:2, " +
                "title:beany.png]|[forename:Mr., created:"+date.toString()+", id:2]"
    }


    @Test
    void test_soft_findBy_after_BL_sync()
    {
        def dal = new DALFactory().setDoMocking(false).produce()
        Gatekeeper gk = new Gatekeeper(dal)
        gk.syncImages("test_images")

        String date = new Date(System.currentTimeMillis()).toString()

        def stringify = { list ->
            return list.collect {
                it.getPictureModel().defaultInsertKeyValues(Object.class).toString() + "|" +
                        it.getEXIFModel().defaultInsertKeyValues(Object.class).toString() + "|" +
                        it.getIPTCModel().defaultInsertKeyValues(Object.class).toString() + "|" +
                        it.getPhotographerModel().defaultInsertKeyValues(Object.class).toString()
            }.join("\n")
        }
        //Search parameter ONLY in IPTC
        def model = new DetailedPictureModel(
                new PictureModel(),
                new EXIFModel(),
                new IPTCModel().setTitle("bea").setCopyright("whatever"),
                new PhotographerModel()
        )
        // By default every search is "hard"
        def found = dal.access(DetailedPictureModel.class).findBy(model)

        // "bea" and "whatever" are queried by "AND" by default: ->No result!
        assert found.isEmpty()

        // Setting search to "Soft" (OR search)
        model.setIsFoundSoftly(false)
        model.getPhotographerModel().setIsFoundSoftly(true)
        model.getEXIFModel().setIsFoundSoftly(true)
        model.getIPTCModel().setIsFoundSoftly(true)
        model.getPictureModel().setIsFoundSoftly(true)

        found = dal.access(DetailedPictureModel.class).findBy(model)

        assert found.size()==1
        assert stringify(found)=="[path:D:\\development\\java\\studium\\sensemaker\\test_images\\beany.png, EXIF_id:2, " +
                "created:"+date.toString()+", id:2, IPTC_id:2, photographer_id:2]|[" +
                "created:"+date.toString()+", id:2]|[created:"+date.toString()+", id:2, " +
                "title:beany.png]|[forename:Mr., created:"+date.toString()+", id:2]"

        // Adding search parameter in a different sub-model than before:
        model.getEXIFModel().setOrientation("Some orientation")

        found = dal.access(DetailedPictureModel.class).findBy(model)

        // Nothing because super-mode (detailed) is still set to "hard"-search
        assert found.isEmpty()

        // Let's change this:
        model.setIsFoundSoftly(true)

        found = dal.access(DetailedPictureModel.class).findBy(model)

        // And now it should have worked again:
        assert found.size()==1
        assert stringify(found)=="[path:D:\\development\\java\\studium\\sensemaker\\test_images\\beany.png, EXIF_id:2, " +
                "created:"+date.toString()+", id:2, IPTC_id:2, photographer_id:2]|[" +
                "created:"+date.toString()+", id:2]|[created:"+date.toString()+", id:2, " +
                "title:beany.png]|[forename:Mr., created:"+date.toString()+", id:2]"


    }


}
