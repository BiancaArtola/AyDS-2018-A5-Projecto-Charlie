package ayds.dictionary.charlie.fulllogic.controller;

import ayds.dictionary.charlie.fulllogic.model.UserModelModule;
import ayds.dictionary.charlie.fulllogic.view.EditUserView;
import ayds.dictionary.charlie.fulllogic.view.UserViewModule;

public class UserControllerModule {

    private static UserControllerModule instance;

    private UserControllerModule() { }

    public static UserControllerModule getInstance() {
        if (instance == null) {
            instance = new UserControllerModule();
        }
        return instance;
    }

    public void startApplication() {
        EditUserController controller = getEditUserController();

        EditUserView view = openEditUserWindowAndGetView(controller);

        controller.setEditUserView(view);
    }

    private EditUserController getEditUserController() {
        return new EditUserControllerImp(UserModelModule.getInstance().getUserModel());
    }

    private EditUserView openEditUserWindowAndGetView(EditUserController editUserController) {
        return UserViewModule.getInstance().openEditUserWindow(editUserController);
    }
}
