package ayds.dictionary.charlie.model.Errors;

public class ErrorModule {
    private static ErrorModule instance;
    private ErrorHandler errorHandler;

    private ErrorModule() {
        errorHandler = new ErrorHandlerImp();
    }

    public static ErrorModule getInstance() {
        if(instance == null) {
            instance =  new ErrorModule();
        }
        return instance;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }
}
