package ayds.dictionary.charlie.fulllogic.model;

import android.content.Context;

public interface UserModel {
    void setListener(UserModelListener listener);
    void createDatabase(Context applicationContext);
    void searchWord(String searchedWord);
    String lastSearch();
}
