package ayds.dictionary.charlie.fulllogic.model.Service;

import ayds.dictionary.charlie.fulllogic.model.APIConnectionException;

public interface Service {
    String searchWord(String searchedWord) throws APIConnectionException;
}
