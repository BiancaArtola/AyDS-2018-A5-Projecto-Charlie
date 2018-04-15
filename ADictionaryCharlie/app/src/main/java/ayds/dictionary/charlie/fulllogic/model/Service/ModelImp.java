package ayds.dictionary.charlie.fulllogic.model.Service;

import android.content.Context;

import ayds.dictionary.charlie.fulllogic.model.DataBase.Repository;
import ayds.dictionary.charlie.fulllogic.model.DataBase.RepositoryImp;


public class ModelImp implements Model {

    private ModelListener listener;
    private Repository repository;

    public ModelImp(){}

    @Override
    public void setListener(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void searchWord(String searchedWord){
        String result = repository.searchWord(searchedWord);
        notifyListener(result);
    }

    @Override
    public void createRepository(Context applicationContext) {
        initRepository(applicationContext);
    }

    private void initRepository(Context applicationContext){
        repository = new RepositoryImp(applicationContext);
    }

    private void notifyListener(String lastSearch) {
        if (listener != null) {
            listener.didUpdate(lastSearch);
        }
    }
}
