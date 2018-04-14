package ayds.dictionary.charlie.fulllogic.view;

import ayds.dictionary.charlie.fulllogic.controller.EditUserController;
import ayds.dictionary.charlie.fulllogic.model.UserModel;

public class EditUserViewImp implements EditUserView {

    private EditUserController editUserController;
    private UserModel userModel;

    public EditUserViewImp(EditUserController editUserController, UserModel userModel) {
        this.editUserController = editUserController;
        this.userModel = userModel;
    }

    @Override
    public void updateResult() {
        // Hace lo que esta en MainActivity
    }
}
