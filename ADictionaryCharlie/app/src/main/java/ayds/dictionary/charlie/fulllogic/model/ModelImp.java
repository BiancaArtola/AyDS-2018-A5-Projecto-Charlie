package ayds.dictionary.charlie.fulllogic.model;

public class ModelImp implements Model {

    private ModelListener listener;
    private Repository repository;

    public ModelImp(Repository repository){
        this.repository = repository;
    }

    @Override
    public void setListener(ModelListener listener) {
        this.listener = listener;
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
