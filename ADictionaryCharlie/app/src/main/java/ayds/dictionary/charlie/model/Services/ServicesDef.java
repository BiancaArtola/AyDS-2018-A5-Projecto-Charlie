package ayds.dictionary.charlie.model.Services;

import java.io.IOException;

import ayds.dictionary.charlie.model.Source;

public interface ServicesDef {
    Source[] getSources();
    String searchWord(String term, Source source) throws IOException;
}
