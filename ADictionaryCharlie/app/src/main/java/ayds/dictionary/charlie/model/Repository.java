package ayds.dictionary.charlie.model;

import java.util.ArrayList;

public interface Repository {
    ArrayList<Concept> searchWord(String searchedWord);
}
