package ayds.dictionary.charlie.model;

class ModelImp implements Model {

    private ModelListener listener;
    private ErrorListener errorListener;
    private Repository repository;


    ModelImp(Repository repository){
        this.repository = repository;
    }

    @Override
    public void setListener(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void setErrorListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public void searchWord(String searchedWord){
        try {
            String result = repository.searchWord(searchedWord);
            notifyListener(result);
        } catch (APIConnectionException e){
            notifyErrorListener();
        }
    }

    private void notifyListener(String lastSearch) {
        if (listener != null) {
            listener.didUpdate(lastSearch);
        }
    }

    private void notifyErrorListener(){
        if (errorListener != null){
            errorListener.notifyError();
        }
    }
}
