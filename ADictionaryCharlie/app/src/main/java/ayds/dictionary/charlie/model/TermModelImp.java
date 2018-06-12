package ayds.dictionary.charlie.model;

import java.util.ArrayList;

import ayds.dictionary.charlie.model.Errors.ErrorHandler;
import ayds.dictionary.charlie.model.Errors.ErrorListener;

class TermModelImp implements TermModel {

    private NewTermListener listener;
    private Repository repository;
    private ErrorHandler errorHandler;


    TermModelImp(Repository repository, ErrorHandler errorHandler){
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
        ArrayList<Concept> conceptArrayList = repository.searchWord(searchedWord);
        if (!conceptArrayList.isEmpty())
            notifyListener(conceptArrayList);
    }

    private void notifyListener(ArrayList<Concept> conceptArrayList) {
        if (listener != null) {
            listener.didUpdate(conceptArrayList);
        }
    }
}
