package ayds.dictionary.charlie.model.DataBase;

import ayds.dictionary.charlie.model.Concept;

public interface DataBase {
    Concept getMeaning(String searchedWord);
    void saveTerm(Concept myConcept);
}
