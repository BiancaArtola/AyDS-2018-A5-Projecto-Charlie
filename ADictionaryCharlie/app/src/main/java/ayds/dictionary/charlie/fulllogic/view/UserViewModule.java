package ayds.dictionary.charlie.fulllogic.view;

import ayds.dictionary.charlie.R;
import ayds.dictionary.charlie.fulllogic.controller.EditUserController;
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

    public EditUserView openEditUserWindow(EditUserController editUserController) {
        EditUserViewImp editUserView = new EditUserViewImp(editUserController, UserModelModule.getInstance().getUserModel());
        setContentView(R.layout.activity_main);
        return editUserView;
    }

}
