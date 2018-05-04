package ayds.dictionary.charlie.model.Errors;

class ErrorHandlerImp implements ErrorHandler{

    private ErrorListener errorListener;

    @Override
    public void setErrorListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public void errorEvent(Exception exception) {
        if (errorListener != null){
            errorListener.notifyError(exception);
        }
    }
}
