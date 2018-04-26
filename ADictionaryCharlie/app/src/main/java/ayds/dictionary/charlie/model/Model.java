package ayds.dictionary.charlie.fulllogic.model;

public interface Model {
    void setListener(ModelListener listener);
    void setErrorListener(ErrorListener errorListener);
    void searchWord(String searchedWord);
}
