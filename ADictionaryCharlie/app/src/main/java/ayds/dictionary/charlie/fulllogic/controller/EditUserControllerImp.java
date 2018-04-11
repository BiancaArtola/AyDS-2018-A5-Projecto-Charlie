package ayds.dictionary.charlie.fulllogic.controller;

import ayds.dictionary.charlie.fulllogic.model.UserModel;
import ayds.dictionary.charlie.fulllogic.view.EditUserView;

public class EditUserControllerImp implements EditUserController {

    private UserModel userModel;
    private EditUserView editUserView;

    public EditUserControllerImp(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public void setEditUserView(EditUserView editUserView) {
        this.editUserView = editUserView;
    }
}
