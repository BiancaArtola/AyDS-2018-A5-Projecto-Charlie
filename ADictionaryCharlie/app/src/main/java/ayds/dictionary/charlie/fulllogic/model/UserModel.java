package ayds.dictionary.charlie.fulllogic.model;

import android.content.Context;

public interface UserModel {
    String lastSearch();
    void setListener(UserModelListener listener);
    void createDatabase(Context applicationContext);
    String getResult(String palabraBuscada);
    void saveTerm(String palabraBuscada, String resultado);
    WordsBighugelabsAPI getAPI();
}
