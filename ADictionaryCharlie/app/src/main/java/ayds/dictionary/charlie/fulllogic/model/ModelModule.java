package ayds.dictionary.charlie.fulllogic.model;

import ayds.dictionary.charlie.fulllogic.model.DataBase.DataBase;
import ayds.dictionary.charlie.fulllogic.model.DataBase.DataBaseImp;
import ayds.dictionary.charlie.fulllogic.model.Service.Service;
import ayds.dictionary.charlie.fulllogic.model.Service.BighugelabsService;
import ayds.dictionary.charlie.fulllogic.model.Service.Transform;
import ayds.dictionary.charlie.fulllogic.model.Service.TransformToString;
import ayds.dictionary.charlie.fulllogic.view.ViewModule;

public class ModelModule {

    private static ModelModule instance;
    private Model model;

    private ModelModule() {
        DataBase dataBase = new DataBaseImp(ViewModule.getInstance().getApplicationContext());
        Transform transform = new TransformToString();
        Service service = new BighugelabsService(transform);
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
