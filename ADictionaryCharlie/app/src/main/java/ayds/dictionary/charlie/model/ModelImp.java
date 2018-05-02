package ayds.dictionary.charlie.model;

import ayds.dictionary.charlie.model.Errors.ErrorHandler;
import ayds.dictionary.charlie.model.Errors.ErrorListener;

class ModelImp implements Model {

    private NewTermListener listener;
    private Repository repository;
    private ErrorHandler errorHandler;


    ModelImp(Repository repository, ErrorHandler errorHandler){
        this.repository = repository;
        this.errorHandler = errorHandler;
    }

    @Override
    public void setNewTermListener(NewTermListener listener) {
        this.listener = listener;
    }

    @Override
    public void setErrorListener(ErrorListener errorListener) {
        errorHandler.setErrorListener(errorListener);
    }

    @Override
    public void searchWord(String searchedWord){
        String result = repository.searchWord(searchedWord);
        notifyListener(result);
    }

    private void notifyListener(String lastSearch) {
        if (listener != null) {
            listener.didUpdate(lastSearch);
        }
    }
}
