package ayds.dictionary.charlie.model.Errors;

import ayds.dictionary.charlie.model.TypesOfException.ApplicationException;

class ErrorHandlerImp implements ErrorHandler{

    private ErrorListener errorListener;

    @Override
    public void setErrorListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public void errorEvent(Exception exception) {
        if (errorListener != null) {
            if (exception instanceof ApplicationException) {
                errorListener.notifyError(exception);
            } else {
                Exception newException = new Exception("Ocurrio un error inesperado.");
                errorListener.notifyError(newException);
            }
        }
    }
}
