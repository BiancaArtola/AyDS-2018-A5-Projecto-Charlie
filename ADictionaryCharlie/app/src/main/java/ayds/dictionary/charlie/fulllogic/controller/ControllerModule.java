package ayds.dictionary.charlie.fulllogic.controller;

import ayds.dictionary.charlie.fulllogic.model.Service.Model;

public class ControllerModule {

    private static ControllerModule instance;
    private Controller controller;

    private ControllerModule() { controller = new ControllerImp();}

    public static ControllerModule getInstance() {
        if (instance == null) {
            instance = new ControllerModule();
        }
        return instance;
    }

    public Controller getController() {
        return controller;
    }

    public void setModel(Model model){
        controller.setModel(model);
    }
}
