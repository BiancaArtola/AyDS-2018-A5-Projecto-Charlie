package ayds.dictionary.charlie.fulllogic.model.DataBase;

import android.arch.persistence.room.Room;
import android.content.Context;

import ayds.dictionary.charlie.fulllogic.model.DataBase.room.Concept;
import ayds.dictionary.charlie.fulllogic.model.DataBase.room.ConceptDataBase;

public class DataBaseImp implements DataBase {

  private static ConceptDataBase db;

  public DataBaseImp(Context context){
    createDatabase(context);
  }

  private void createDatabase(final Context applicationContext){
      new Thread(new Runnable() {
        @Override public void run() {
          createNewDatabase(applicationContext);
        }
      }).start();
  }

  private void createNewDatabase(Context context) {
    db = Room.databaseBuilder(context,
                              ConceptDataBase.class, "dictionary.db").build();
  }

  public void saveTerm(String term, String meaning) {
    Concept concept =  new Concept();
    concept.setTerm(term);
    concept.setMeaning(meaning);
    concept.setSource(1);
    db.termDao().insert(concept);
  }

  public String getMeaning(String term) {

    Concept concept = db.termDao().findByName(term);

    if (concept != null) {
      return concept.getMeaning();
    }
    return null;
  }
}
