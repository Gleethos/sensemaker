package sensemaker.businesslayer;

import sensemaker.datalayer.API.DAL;
import sensemaker.gui.models.EXIFModel;
import sensemaker.gui.models.IPTCModel;
import sensemaker.gui.models.PhotographerModel;
import sensemaker.gui.models.PictureModel;

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

            result.forEach(System.out::println);

            result.forEach(p->{
                File f = new File(p);
                EXIFModel exif = new EXIFModel();
                IPTCModel iptc = new IPTCModel();
                PhotographerModel pg = new PhotographerModel();

                _dal.access(EXIFModel.class).save(exif); // Creates new ids!
                _dal.access(IPTCModel.class).save(iptc);
                _dal.access(PhotographerModel.class).save(pg);

                PictureModel pic = new PictureModel();
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


}
