package ayds.dictionary.charlie.model;

import ayds.dictionary.charlie.model.DataBase.DataBase;
import ayds.dictionary.charlie.model.DataBase.DataBaseModule;
import ayds.dictionary.charlie.model.Service.Service;
import ayds.dictionary.charlie.model.Service.ServiceModule;

public class ModelModule {

    private static ModelModule instance;
    private Model model;

    private ModelModule() {
        DataBase dataBase = DataBaseModule.getInstance().getDataBase();
        Service service = ServiceModule.getInstance().getService();
        Repository repository = new RepositoryImp(dataBase,service);
        model =  new ModelImp(repository);
    }

    public static ModelModule getInstance() {
        if(instance == null) {
            instance =  new ModelModule();
        }
        return instance;
    }

    public Model getModel() {
        return model;
    }
}
