package ayds.dictionary.charlie.fulllogic.model.Service;

public class ModelModule {

    private static ModelModule instance;
    private Model model;

    private ModelModule() {
        model =  new ModelImp();
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
