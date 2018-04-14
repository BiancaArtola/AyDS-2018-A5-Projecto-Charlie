package ayds.dictionary.charlie.fulllogic.view;

import android.content.Context;

import ayds.dictionary.charlie.fulllogic.controller.EditUserController;
import ayds.dictionary.charlie.fulllogic.controller.EditUserControllerImp;
import ayds.dictionary.charlie.fulllogic.model.UserModel;
import ayds.dictionary.charlie.fulllogic.model.UserModelModule;

public class UserViewModule {

    private static UserViewModule instance;
    private EditUserController editUserController;
    private EditUserView editUserView;
    private UserModel userModel;


    private UserViewModule() {
    }

    public static UserViewModule getInstance() {
        if (instance == null) {
            instance = new UserViewModule();
        }
        return instance;
    }

    public void startApplication(Context applicationContext) {
        userModel = UserModelModule.getInstance().getUserModel();
        editUserController = createEditUserController();
        editUserView = openEditUserWindowAndGetView();
        cargarBaseDeDatos(applicationContext);
    }

    private EditUserController createEditUserController() {
        return new EditUserControllerImp(userModel);
    }

    private EditUserView openEditUserWindowAndGetView() {
        return UserViewModule.getInstance().openEditUserWindow();
    }

    private EditUserView openEditUserWindow() {
        return new EditUserViewImp(editUserController, userModel);
    }

    private void cargarBaseDeDatos(Context applicationContext) {
        userModel.createDatabase(applicationContext);
    }

    public EditUserController getEditUserController() {
        return editUserController;
    }

    public EditUserView getEditUserView() {
        return editUserView;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}