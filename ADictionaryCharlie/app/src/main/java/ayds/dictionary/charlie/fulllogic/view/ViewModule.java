package ayds.dictionary.charlie.fulllogic.view;

import android.content.Context;

import ayds.dictionary.charlie.fulllogic.controller.Controller;
import ayds.dictionary.charlie.fulllogic.controller.ControllerImp;
import ayds.dictionary.charlie.fulllogic.controller.ControllerModule;
import ayds.dictionary.charlie.fulllogic.model.Service.Model;

import ayds.dictionary.charlie.fulllogic.model.Service.ModelModule;

public class ViewModule {

    private static ViewModule instance;
    private Controller controller;
    private Model model;


    private ViewModule() {}

    public static ViewModule getInstance() {
        if (instance == null) {
            instance = new ViewModule();
        }
        return instance;
    }

    public void startApplication(Context applicationContext) {
        loadModel();
        loadController();
        loadDatabase(applicationContext);
    }

    private void loadModel(){
        model = ModelModule.getInstance().getModel();
    }

    private void loadController(){
        ControllerModule.getInstance().getController().setModel(model);
        controller = ControllerModule.getInstance().getController();
    }
    private void loadDatabase(Context applicationContext) {
        model.createRepository(applicationContext);
    }

    public Controller getController() {
        return controller;
    }

    public Model getModel() {
        return model;
    }
}
