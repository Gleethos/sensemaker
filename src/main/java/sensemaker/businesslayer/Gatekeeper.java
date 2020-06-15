package sensemaker.businesslayer;

import sensemaker.datalayer.API.DAL;
import sensemaker.gui.models.base.EXIFModel;
import sensemaker.gui.models.base.IPTCModel;
import sensemaker.gui.models.base.PhotographerModel;
import sensemaker.gui.models.base.PictureModel;
import sensemaker.gui.models.composits.DetailedPictureModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Gatekeeper {

    private final DAL _dal;

    public Gatekeeper(DAL dal){
        _dal = dal;
    }

    public Date syncImages(String relativePath)
    {
        final String dir = System.getProperty("user.dir").replace("\\","/");
        String folderPath = dir+"/"+relativePath;
        System.out.println("current dir = " + folderPath);
        try (Stream<Path> walk = Files.walk(Paths.get(folderPath))) {

            List<String> result = walk
                    .map(Path::toString)
                    .filter(f -> f.endsWith(".png"))
                    .collect(Collectors.toList()
                    );

            result.forEach( p ->
            {
                File f = new File(p);
                EXIFModel exif = new EXIFModel();
                IPTCModel iptc = new IPTCModel().setTitle(f.getName());
                PhotographerModel pg = new PhotographerModel();

                _dal.access(EXIFModel.class).save(exif); // Creates new ids!
                _dal.access(IPTCModel.class).save(iptc);
                _dal.access(PhotographerModel.class).save(pg);

                assert exif.getId()!=null && iptc.getId()!=null && pg.getId()!=null;

                PictureModel pic = new PictureModel().setPath(p);
                pic.setEXIFId(exif.getId());
                pic.setIPTCId(iptc.getId());
                pic.setPhotographerId(pg.getId());

                _dal.access(PictureModel.class).save(pic);

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DetailedPictureModel> searchForPictures(DetailedPictureModel model)
    {
        return _dal.access(DetailedPictureModel.class).findBy(model);
    }


}
