package ayds.dictionary.charlie.fulllogic.view;

import android.content.Context;
import ayds.dictionary.charlie.fulllogic.controller.EditUserController;
import ayds.dictionary.charlie.fulllogic.controller.EditUserControllerImp;
import ayds.dictionary.charlie.fulllogic.model.UserModelModule;

public class UserViewModule {

    private static UserViewModule instance;

    private UserViewModule() { }

    public static UserViewModule getInstance() {
        if (instance == null) {
            instance = new UserViewModule();
        }
        return instance;
    }

    public void startApplication(ElementosGraficos elementosGraficos, Context applicationContext) {
        cargarBaseDeDatos(applicationContext);

        EditUserController controller = getEditUserController();

        EditUserView view = openEditUserWindowAndGetView(controller, elementosGraficos);

        controller.setEditUserView(view);
    }

    private EditUserController getEditUserController() {
        return new EditUserControllerImp(UserModelModule.getInstance().getUserModel());
    }

    private EditUserView openEditUserWindowAndGetView(EditUserController editUserController,ElementosGraficos elementosGraficos) {
        return UserViewModule.getInstance().openEditUserWindow(editUserController, elementosGraficos);
    }

    public EditUserView openEditUserWindow(EditUserController editUserController, ElementosGraficos elementosGraficos) {
        EditUserViewImp editUserView = new EditUserViewImp(editUserController, UserModelModule.getInstance().getUserModel(), elementosGraficos);
        return editUserView;
    }

    private void cargarBaseDeDatos(Context applicationContext){
        UserModelModule.getInstance().getUserModel().createDatabase(applicationContext);
    }

}
