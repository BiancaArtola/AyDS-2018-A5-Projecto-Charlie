package ayds.dictionary.charlie.fulllogic.controller;

public class UserControllerModule {

    private static UserControllerModule instance;

    private UserControllerModule() { }

    public static UserControllerModule getInstance() {
        if (instance == null) {
            instance = new UserControllerModule();
        }
        return instance;
    }
}
