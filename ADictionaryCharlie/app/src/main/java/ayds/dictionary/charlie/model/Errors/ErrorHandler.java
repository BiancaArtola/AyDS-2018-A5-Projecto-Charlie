package ayds.dictionary.charlie.model.Errors;

import java.util.Map;

import ayds.dictionary.charlie.model.Source;

public interface ErrorHandler {
    void setErrorListener(ErrorListener errorListener);
    void errorEvent(Map<Source,Exception> mapeoException);
}
