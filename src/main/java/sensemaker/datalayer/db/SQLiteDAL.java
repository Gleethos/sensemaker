package sensemaker.datalayer.db;

import sensemaker.datalayer.API.Access;
import sensemaker.datalayer.API.DAL;
import sensemaker.gui.models.AbstractModel;
import sensemaker.gui.models.simple.EXIFModel;
import sensemaker.gui.models.simple.IPTCModel;
import sensemaker.gui.models.simple.PhotographerModel;
import sensemaker.gui.models.simple.PictureModel;
import sensemaker.gui.models.composites.DetailedPictureModel;

import java.sql.Date;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SQLiteDAL extends AbstractDatabaseConnection implements DAL {

    private Map<Class, Access> _accessors = new HashMap<>();

    public SQLiteDAL(){
        initialize();
    }

    @Override
    public void initialize()
    {
        //Close DB-Connection upon System-Shutdown!
        Runtime.getRuntime().addShutdownHook( new Thread(this::_close));
        _construct("jdbc:sqlite:C:/sqlite/db/SenseMakerDB", "", "");
        _executeFile("bootstrap.sql");
        _query("SELECT * FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%';");
        _accessors.put(
                PictureModel.class,
                new Access<PictureModel>()
                {
                    @Override public List<PictureModel> getAll()
                    {
                        return _getAll("SELECT * FROM pictures", null,
                                get -> new PictureModel()
                                    .setId((Integer) get.apply("id"))
                                    .setEXIFId((Integer) get.apply("EXIF_id"))
                                    .setIPTCId((Integer) get.apply("IPTC_id"))
                                    .setPhotographerId((Integer) get.apply("photographer_id"))
                                    .setCreated((Date) get.apply("created"))
                                    .setDeleted((Date) get.apply("deleted"))
                        );
                    }

                    @Override public List<PictureModel> findBy(PictureModel M) {
                        List<Object> prepValues = new ArrayList<>();
                        String joinedWhere = _genWhere(List.of(M) ,prepValues, " AND ");
                        return _getAll("SELECT * FROM pictures"+joinedWhere, prepValues,
                                get -> new PictureModel()
                                        .setId((Integer) get.apply("id"))
                                        .setEXIFId((Integer) get.apply("EXIF_id"))
                                        .setIPTCId((Integer) get.apply("IPTC_id"))
                                        .setPhotographerId((Integer) get.apply("photographer_id"))
                                        .setCreated((Date) get.apply("created"))
                                        .setDeleted((Date) get.apply("deleted"))
                        );
                    }

                    @Override public void save(PictureModel M) {
                        _execute(_generateSaveSQLFor(M));
                        if (M.getId()==null) M.setId(_lastInsertID());
                    }

                    @Override public void delete(PictureModel M) {
                        _execute("DELETE FROM "+M.getAsTableName()+" WHERE id = "+M.getId());
                    }
                });
        _accessors.put(
                EXIFModel.class,
                new Access<EXIFModel>()
                {
                    @Override public List<EXIFModel> getAll() {
                        return _getAll("SELECT * FROM EXIFs", null,
                                    get -> new EXIFModel()
                                            .setId((Integer) get.apply("id"))
                                            .setCreated((Date) get.apply("created"))
                                            .setDeleted((Date) get.apply("deleted"))
                                            .setShot((Date) get.apply("shot"))
                                            .setOrientation((String) get.apply("orientation"))
                            );
                    }
                    @Override public List<EXIFModel> findBy(EXIFModel M) {
                        List<Object> prepValues = new ArrayList<>();
                        String joinedWhere = _genWhere(List.of(M) ,prepValues, " AND ");
                        return _getAll("SELECT * FROM EXIFs"+joinedWhere, prepValues,
                                get -> new EXIFModel()
                                        .setId((Integer) get.apply("id"))
                                        .setCreated((Date) get.apply("created"))
                                        .setDeleted((Date) get.apply("deleted"))
                                        .setShot((Date) get.apply("shot"))
                                        .setOrientation((String) get.apply("orientation"))
                        );
                    }
                    @Override public void save(EXIFModel M) {
                        _execute(_generateSaveSQLFor(M));
                        if (M.getId()==null) M.setId(_lastInsertID());
                    }
                    @Override public void delete(EXIFModel M) {
                        _execute("DELETE FROM "+M.getAsTableName()+" WHERE id = "+M.getId());
                    }
                });
        _accessors.put(IPTCModel.class, new Access<IPTCModel>()
        {
            @Override
            public List<IPTCModel> getAll()
            {
                return _getAll(
                        "SELECT * FROM IPTCs", null,
                            get -> new IPTCModel()
                                    .setId((Integer) get.apply("id"))
                                    .setCreated((Date) get.apply("created"))
                                    .setDeleted((Date) get.apply("deleted"))
                                    .setTitle((String) get.apply("title"))
                                    .setCopyright((String) get.apply("copyright"))
                                    .setDescription((String) get.apply("description"))
                                    .setKeywords((String) get.apply("keywords"))
                    );
            }

            @Override
            public List<IPTCModel> findBy(IPTCModel M) {
                List<Object> prepValues = new ArrayList<>();
                String joinedWhere = _genWhere(List.of(M) ,prepValues, " AND ");
                return _getAll("SELECT * FROM IPTCs"+joinedWhere, prepValues,
                        get -> new IPTCModel()
                                .setId((Integer) get.apply("id"))
                                .setCreated((Date) get.apply("created"))
                                .setDeleted((Date) get.apply("deleted"))
                                .setTitle((String) get.apply("title"))
                                .setCopyright((String) get.apply("copyright"))
                                .setDescription((String) get.apply("description"))
                                .setKeywords((String) get.apply("keywords"))
                );
            }

            @Override
            public void save(IPTCModel M) {
                _execute(_generateSaveSQLFor(M));
                if (M.getId()==null) M.setId(_lastInsertID());
            }

            @Override
            public void delete(IPTCModel M) {
                _execute("DELETE FROM "+M.getAsTableName()+" WHERE id = "+M.getId());
            }
        });
        _accessors.put(PhotographerModel.class,
                new Access<PhotographerModel>()
                {
                    @Override
                    public List<PhotographerModel> getAll() {
                        return _getAll(
                                "SELECT * FROM photographers", null,
                                    get -> new PhotographerModel()
                                            .setId((Integer) get.apply("id"))
                                            .setCreated((Date) get.apply("created"))
                                            .setDeleted((Date) get.apply("deleted"))
                                            .setForename((String) get.apply("forename"))
                                            .setSurname((String) get.apply("surname"))
                            );
                    }

                    @Override
                    public List<PhotographerModel> findBy(PhotographerModel M) {
                        List<Object> prepValues = new ArrayList<>();
                        String joinedWhere = _genWhere(List.of(M) ,prepValues, " AND ");
                        return _getAll("SELECT * FROM photographers"+joinedWhere, prepValues,
                                get -> new PhotographerModel()
                                    .setId((Integer) get.apply("id"))
                                    .setCreated((Date) get.apply("created"))
                                    .setDeleted((Date) get.apply("deleted"))
                                    .setForename((String) get.apply("forename"))
                                    .setSurname((String) get.apply("surname"))
                        );
                    }

                    @Override
                    public void save(PhotographerModel M) {
                        _execute(_generateSaveSQLFor(M));
                        if (M.getId()==null) M.setId(_lastInsertID());
                    }
                    @Override
                    public void delete(PhotographerModel M) {
                            _execute("DELETE FROM "+M.getAsTableName()+" WHERE id = "+M.getId());
                    }
                });
        _accessors.put( DetailedPictureModel.class,
           new Access<DetailedPictureModel>()
           {
               private List<DetailedPictureModel> _getAllWhere(String where, List<Object> values)
               {
                   return _getAll(
                           "SELECT\n" +
                                   "pictures.id as 'pictures.id', pictures.created as 'pictures.created', pictures.deleted as 'pictures.deleted',\n" +
                                   "EXIFs.id as 'EXIFs.id', EXIFs.created as 'EXIFs.created', EXIFs.deleted as 'EXIFs.deleted',\n" +
                                   "IPTCs.id as 'IPTCs.id', IPTCs.created as 'IPTCs.created', IPTCs.deleted as 'IPTCs.deleted',\n" +
                                   "photographers.id as 'photographers.id', photographers.created as 'photographers.created', photographers.deleted as 'photographers.deleted',\n" +
                                   " * \n" +
                                   "FROM pictures\n" +
                                   "JOIN EXIFs ON pictures.EXIF_id = EXIFs.id\n" +
                                   "JOIN IPTCs ON pictures.IPTC_id = IPTCs.id\n" +
                                   "JOIN photographers ON pictures.photographer_id = photographers.id"+
                                   ((where==null||where.equals(""))?"":"\n"+where),
                           values,
                           get -> new DetailedPictureModel(
                                   new PictureModel()
                                           .setId((Integer)get.apply("pictures.id"))
                                           .setCreated((Date)get.apply("pictures.created"))
                                           .setDeleted((Date)get.apply("pictures.deleted"))
                                           .setEXIFId((Integer)get.apply("EXIF_id"))
                                           .setIPTCId((Integer)get.apply("IPTC_id"))
                                           .setPhotographerId((Integer)get.apply("photographer_id"))
                                           .setPath((String)get.apply("path")),
                                   new EXIFModel()
                                           .setId((Integer)get.apply("EXIFs.id"))
                                           .setCreated((Date)get.apply("EXIFs.created"))
                                           .setDeleted((Date)get.apply("EXIFs.deleted"))
                                           .setOrientation((String)get.apply("orientation"))
                                           .setShot((Date)get.apply("shot")),
                                   new IPTCModel()
                                           .setId((Integer)get.apply("IPTCs.id"))
                                           .setCreated((Date)get.apply("IPTCs.created"))
                                           .setDeleted((Date)get.apply("IPTCs.deleted"))
                                           .setCopyright((String)get.apply("copyright"))
                                           .setDescription((String)get.apply("description"))
                                           .setKeywords((String)get.apply("keywords"))
                                           .setTitle((String)get.apply("title")),
                                   new PhotographerModel()
                                           .setId((Integer)get.apply("photographers.id"))
                                           .setCreated((Date)get.apply("photographers.created"))
                                           .setDeleted((Date)get.apply("photographers.deleted"))
                                           .setForename((String)get.apply("forename"))
                                           .setSurname((String)get.apply("surname"))
                           )
                   );
               }

               @Override
               public List<DetailedPictureModel> getAll() {
                    return _getAllWhere(null, null);
               }

               @Override
               public List<DetailedPictureModel> findBy(DetailedPictureModel M)
               {
                   List<Object> prepValues = new ArrayList<>();
                   String joinedWhere = _genWhere(
                           List.of(
                                   M.getEXIFModel(),
                                   M.getPhotographerModel(),
                                   M.getIPTCModel(),
                                   M.getPictureModel()
                           ),
                           prepValues,
                           (M.isFoundSoftly())?" OR ":" AND "
                   );
                   return _getAllWhere(joinedWhere, prepValues);
               }

               @Override
               public void save(DetailedPictureModel M) {
                    access(PictureModel.class).save(M.getPictureModel());
                    access(EXIFModel.class).save(M.getEXIFModel());
                    access(IPTCModel.class).save(M.getIPTCModel());
                    access(PhotographerModel.class).save(M.getPhotographerModel());
               }

               @Override
               public void delete(DetailedPictureModel M) {
                   _execute("DELETE FROM "+M.getEXIFModel().getAsTableName()+" WHERE id = "+M.getEXIFModel().getId());
                   _execute("DELETE FROM "+M.getIPTCModel().getAsTableName()+" WHERE id = "+M.getIPTCModel().getId());
                   _execute("DELETE FROM "+M.getPhotographerModel().getAsTableName()+" WHERE id = "+M.getPhotographerModel().getId());
                   _execute("DELETE FROM "+M.getPictureModel().getAsTableName()+" WHERE id = "+M.getPictureModel().getId());
               }
           });
    }

    @Override
    public <T> Access<T> access(Class<T> type) {
        return _accessors.get(type);
    }

    @Override
    public void reset() {
        initialize();
    }

    //---------------

    private String _generateSaveSQLFor(AbstractModel m){
        if(m.getCreated()==null) m.setCreated(Date.valueOf(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString()));

        Map<String, Object> inserts = m.defaultInsertKeyValues(Object.class);
        List<String> attributes = new ArrayList<>(inserts.keySet());
        List<String> values = new ArrayList<>(inserts.values()).stream().map(o->"'"+o.toString()+"'").collect(Collectors.toList());

        if(m.getId()==null) {
            return "INSERT INTO "+m.getAsTableName()+"\n"+
                    "("+String.join(", ",attributes)+")\n"+
                    "VALUES\n"+
                    "("+String.join(",", values)+")";
        } else {
            List<String> pairs = new ArrayList<>();
            for (int i=0; i<attributes.size(); i++){
                pairs.add(attributes.get(i)+" = "+values.get(i));
            }
            return "UPDATE "+m.getAsTableName()+"\n"+
                   "SET\n" +
                        String.join(", ",pairs) +"\n" +
                   "WHERE id = "+m.getId();
        }

    }

    private <T> List<T> _getAll(String query, List<Object> values, Function<Function<String, Object>, T> get) {
        Map<String, List<Object>> table = _query(query, values);
        if(table.isEmpty()) return new ArrayList<>();
        int rows = table.values().toArray(new List[0])[0].size();
        List<T> list = new ArrayList<>();
        for(int i=0; i<rows; i++) {
            int finalI = i;
            Function<String, Object> fromTable = k -> table.get(k).get(finalI);
            list.add(get.apply(fromTable));
        }
        return list;
    }

    private String _genWhere(List<AbstractModel> models, List<Object> inValues, String outerJunctor)
    {
        List maps = models.stream().map(m->m.generateSoftPreparedSQLKeyValues(Object.class)).collect(Collectors.toList());

        List<String> joined = new ArrayList<>();
        // Joining by and/or :
        for(int i=0; i<maps.size(); i++) {
            String delimiter = (models.get(i).isFoundSoftly())?" OR ":" AND ";
            joined.add(String.join(delimiter, new ArrayList<>(((Map<String, Object>) maps.get(i)).keySet())));
            inValues.addAll(((Map<String, Object>) maps.get(i)).values());
        }
        joined = joined.stream().filter(s->!s.equals("")).collect(Collectors.toList());
        // add WHERE:
        String where = String.join(outerJunctor, joined);
        if (!where.equals("")) return "\nWHERE "+where;
        return where;

    }

}
