package ayds.dictionary.charlie.model.DataBase;

public interface DataBase {
    String getMeaning(String searchedWord);
    void saveTerm(String term, String meaning);
}
