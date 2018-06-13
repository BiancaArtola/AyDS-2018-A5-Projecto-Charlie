package ayds.dictionary.charlie.model.Services;

import ayds.dictionary.charlie.model.Source;

public interface ServicesDef {
    Source[] getSources();
    String searchWord(String term, Source source) throws Exception;
}
