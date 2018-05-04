package ayds.dictionary.charlie.model.Service;

import ayds.dictionary.charlie.model.TypesOfException.APIConnectionException;

public interface Service {
    String searchWord(String searchedWord) throws APIConnectionException;
    int getSource();  // PREGUNTAR !!!!!!!!!
}
