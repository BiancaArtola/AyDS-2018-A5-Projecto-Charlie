package ayds.dictionary.charlie.controller;

import ayds.dictionary.charlie.view.ViewModule;

public class ControllerModule {

    private static ControllerModule instance;
    private Controller controller;

    private ControllerModule() { controller = new ControllerImp(ViewModule.getInstance().getModel());}

    public static ControllerModule getInstance() {
        if (instance == null) {
            instance = new ControllerModule();
        }
        return instance;
    }

    public Controller getController() {
        return controller;
    }
}
