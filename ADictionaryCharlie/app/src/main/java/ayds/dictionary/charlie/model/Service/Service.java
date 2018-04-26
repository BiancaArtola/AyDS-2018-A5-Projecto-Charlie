package ayds.dictionary.charlie.model.Service;

import ayds.dictionary.charlie.model.APIConnectionException;

public interface Service {
    String searchWord(String searchedWord) throws APIConnectionException;
}
