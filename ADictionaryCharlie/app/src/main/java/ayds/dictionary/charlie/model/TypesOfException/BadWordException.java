package ayds.dictionary.charlie.model.TypesOfException;

public class BadWordException extends ApplicationException {
    public BadWordException(){
        super("Incorrect Word!");
    }
}
