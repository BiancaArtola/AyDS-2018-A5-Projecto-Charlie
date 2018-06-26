package ayds.dictionary.charlie.model.DataBase.room;

import android.arch.persistence.room.*;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ConceptDao {

  @Query("SELECT * FROM ConceptDB")
  List<ConceptDB> getAll();

  @Query("SELECT * FROM ConceptDB WHERE term LIKE :term AND source LIKE :source LIMIT 1")
  ConceptDB findByName(String term, int source);

  @Insert(onConflict = REPLACE)
  void insert(ConceptDB conceptDB);
}
