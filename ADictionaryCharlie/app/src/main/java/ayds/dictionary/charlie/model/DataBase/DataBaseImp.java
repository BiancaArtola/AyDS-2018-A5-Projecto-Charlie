package ayds.dictionary.charlie.model.DataBase;

import android.arch.persistence.room.Room;
import android.content.Context;

import ayds.dictionary.charlie.model.Concept;
import ayds.dictionary.charlie.model.DataBase.room.ConceptDB;
import ayds.dictionary.charlie.model.DataBase.room.ConceptDataBase;
import ayds.dictionary.charlie.model.Source;

class DataBaseImp implements DataBase {

  private static ConceptDataBase db;

  DataBaseImp(Context context){
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
                              ConceptDataBase.class, "dictionary.db")
        .fallbackToDestructiveMigration()
        .build();
  }

  public void saveTerm(Concept myConcept) {
    ConceptDB conceptDB =  new ConceptDB();
    conceptDB.setTerm(myConcept.getConcept());
    conceptDB.setMeaning(myConcept.getMeaning());
    conceptDB.setSource(myConcept.getSource().ordinal());
    db.termDao().insert(conceptDB);
  }

  public Concept getMeaning(String term) {

    ConceptDB conceptDB = db.termDao().findByName(term);

    if (conceptDB != null) {
      Concept concept = new Concept();
      concept.setConcept(conceptDB.getTerm());
      concept.setMeaning(conceptDB.getMeaning());
      concept.setSource(Source.values()[conceptDB.getSource()]);
      return concept;
    }
    return null;
  }
}
