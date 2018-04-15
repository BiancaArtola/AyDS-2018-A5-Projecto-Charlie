package ayds.dictionary.charlie.fulllogic.controller;

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
}
