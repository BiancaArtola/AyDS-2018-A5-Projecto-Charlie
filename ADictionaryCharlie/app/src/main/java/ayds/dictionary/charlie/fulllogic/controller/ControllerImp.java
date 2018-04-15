package ayds.dictionary.charlie.fulllogic.controller;

import android.util.Log;

import ayds.dictionary.charlie.fulllogic.model.Service.Model;

public class ControllerImp implements Controller {

    private Model model;

    public ControllerImp() {}

    @Override
    public void onEventUpdate(String searchedWord) {
        model.searchWord(searchedWord);
    }

    public void setModel(Model model){
        this.model = model;
    }
}
