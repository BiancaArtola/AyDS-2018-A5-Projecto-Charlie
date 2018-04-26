package ayds.dictionary.charlie.controller;

import ayds.dictionary.charlie.model.Model;

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
