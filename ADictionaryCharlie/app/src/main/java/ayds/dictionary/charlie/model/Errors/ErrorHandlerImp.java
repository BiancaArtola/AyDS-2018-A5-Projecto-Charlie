package ayds.dictionary.charlie.model.Errors;

import java.util.Map;
import ayds.dictionary.charlie.model.Source;

class ErrorHandlerImp implements ErrorHandler{

    private ErrorListener errorListener;

    @Override
    public void setErrorListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public void errorEvent(Map<Source,Exception> mapeoException) {
        String errorText = "\n";
        if (errorListener != null) {
            for (Map.Entry<Source,Exception> entry:  mapeoException.entrySet()){
                errorText += entry.getKey().toString() + ": " + entry.getValue().getMessage() + "\n";
            }
            errorListener.notifyError(new Exception(errorText));
        }
    }
}
