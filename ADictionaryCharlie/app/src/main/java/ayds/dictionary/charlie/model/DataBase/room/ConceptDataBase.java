package ayds.dictionary.charlie.model.DataBase.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ConceptDB.class}, version = 2)
public abstract class ConceptDataBase extends RoomDatabase {
  public abstract ConceptDao termDao();
}
