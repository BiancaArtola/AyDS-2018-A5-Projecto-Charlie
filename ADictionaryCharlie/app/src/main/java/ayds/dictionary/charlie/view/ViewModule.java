package ayds.dictionary.charlie.fulllogic.view;

import android.content.Context;

import ayds.dictionary.charlie.fulllogic.controller.Controller;
import ayds.dictionary.charlie.fulllogic.controller.ControllerModule;
import ayds.dictionary.charlie.fulllogic.model.Model;
import ayds.dictionary.charlie.fulllogic.model.ModelModule;

public class ViewModule {

    private static ViewModule instance;
    private Controller controller;
    private Model model;
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
        model = ModelModule.getInstance().getModel();
    }

    private void loadController(){
        controller = ControllerModule.getInstance().getController();
    }

    public Controller getController() {
        return controller;
    }

    public Model getModel() {
        return model;
    }

    public Context getApplicationContext() {
        return applicationContext;
    }
}
