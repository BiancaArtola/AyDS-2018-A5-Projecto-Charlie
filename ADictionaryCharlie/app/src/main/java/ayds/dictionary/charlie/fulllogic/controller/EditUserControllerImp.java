package ayds.dictionary.charlie.fulllogic.controller;

import ayds.dictionary.charlie.fulllogic.model.UserModel;

public class EditUserControllerImp implements EditUserController {

    private UserModel userModel;

    public EditUserControllerImp(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public void onEventUpdate(String searchedWord) {
        userModel.searchWord(searchedWord);
    }
}
