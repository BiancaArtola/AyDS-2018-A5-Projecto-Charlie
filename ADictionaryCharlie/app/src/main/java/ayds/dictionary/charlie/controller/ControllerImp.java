package ayds.dictionary.charlie.controller;

import ayds.dictionary.charlie.model.TermModel;

class ControllerImp implements Controller {

    private TermModel termModel;

    ControllerImp(TermModel termModel) {
        this.termModel = termModel;
    }

    @Override
    public void onEventUpdate(String searchedWord) {
        termModel.searchWord(searchedWord);
    }
}
