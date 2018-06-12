package ayds.dictionary.charlie.model.DataBase;

import ayds.dictionary.charlie.model.Concept;
import ayds.dictionary.charlie.model.Source;

public interface DataBase {
    Concept getMeaning(String searchedWord, Source source);
    void saveTerm(Concept myConcept);
}
