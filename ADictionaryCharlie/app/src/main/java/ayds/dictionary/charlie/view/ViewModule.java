package ayds.dictionary.charlie.view;

import android.content.Context;

import ayds.dictionary.charlie.controller.Controller;
import ayds.dictionary.charlie.controller.ControllerModule;
import ayds.dictionary.charlie.model.TermModel;
import ayds.dictionary.charlie.model.ModelModule;

public class ViewModule {

    private static ViewModule instance;
    private Controller controller;
    private TermModel termModel;
    private Context applicationContext;


    private ViewModule() {}

    public static ViewModule getInstance() {
        if (instance == null) {
            instance = new ViewModule();
        }
        return instance;
    }

    public void startApplication(Context applicationContext) {
        this.applicationContext = applicationContext;
        loadModel();
        loadController();
    }

    private void loadModel(){
        termModel = ModelModule.getInstance().getTermModel();
    }

    private void loadController(){
        controller = ControllerModule.getInstance().getController();
    }

    public Controller getController() {
        return controller;
    }

    public TermModel getTermModel() {
        return termModel;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }
}
