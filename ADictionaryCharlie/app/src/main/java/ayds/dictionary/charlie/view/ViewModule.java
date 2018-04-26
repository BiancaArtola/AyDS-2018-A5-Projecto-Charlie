package ayds.dictionary.charlie.view;

import android.content.Context;

import ayds.dictionary.charlie.controller.Controller;
import ayds.dictionary.charlie.controller.ControllerModule;
import ayds.dictionary.charlie.model.Model;
import ayds.dictionary.charlie.model.ModelModule;

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
