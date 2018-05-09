package ayds.dictionary.charlie.model.TypesOfException;

public class BadWordException extends SystemException {
    public BadWordException(){
        super("Incorrect Word!");
    }
}
