package ayds.dictionary.charlie.fulllogic.model.Service;

import android.content.Context;

public interface Model {
    void createRepository(Context applicationContext);
    void setListener(ModelListener listener);
    void searchWord(String searchedWord);
}
