package ayds.dictionary.charlie.fulllogic.controller;

import ayds.dictionary.charlie.fulllogic.model.Model;

public class ControllerImp implements Controller {

    private Model model;

    public ControllerImp(Model model) {
        this.model = model;
    }

    @Override
    public void onEventUpdate(String searchedWord) {
        model.searchWord(searchedWord);
    }
}
