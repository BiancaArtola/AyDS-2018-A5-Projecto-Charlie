package ayds.dictionary.charlie.fulllogic.model;

public interface Model {
    void setListener(ModelListener listener);
    void searchWord(String searchedWord);
}
