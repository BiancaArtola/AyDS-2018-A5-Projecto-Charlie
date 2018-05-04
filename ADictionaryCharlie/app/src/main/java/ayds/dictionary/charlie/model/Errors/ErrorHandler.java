package ayds.dictionary.charlie.model.Errors;

public interface ErrorHandler {
    void setErrorListener(ErrorListener errorListener);
    void errorEvent(Exception exception);
}
