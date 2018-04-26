package ayds.dictionary.charlie.fulllogic.controller;

import ayds.dictionary.charlie.fulllogic.model.Model;

class ControllerImp implements Controller {

    private Model model;

    ControllerImp(Model model) {
        this.model = model;
    }

    @Override
    public void onEventUpdate(String searchedWord) {
        model.searchWord(searchedWord);
    }
}
