package ayds.dictionary.charlie.fulllogic.controller;

import ayds.dictionary.charlie.fulllogic.model.Service.Model;

public interface Controller {
    void onEventUpdate(String searchedWord);
    void setModel(Model model);
}
