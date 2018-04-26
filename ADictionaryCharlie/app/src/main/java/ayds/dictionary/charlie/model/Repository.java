package ayds.dictionary.charlie.model;

public interface Repository {
    String searchWord(String searchedWord) throws APIConnectionException;
}
