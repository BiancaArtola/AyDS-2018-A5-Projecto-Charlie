package ayds.dictionary.charlie.model;

import ayds.dictionary.charlie.model.Errors.ErrorListener;

public interface Model {
    void setNewTermListener(NewTermListener listener);
    void setErrorListener(ErrorListener errorListener);
    void searchWord(String searchedWord);
}
