package ayds.dictionary.charlie.fulllogic.model.Service;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

import ayds.dictionary.charlie.fulllogic.model.DataBase.DataBaseImp;
import ayds.dictionary.charlie.fulllogic.model.DataBase.Repository;
import ayds.dictionary.charlie.fulllogic.model.DataBase.RepositoryImp;
import ayds.dictionary.charlie.fulllogic.model.DataBase.WordsBighugelabsAPI;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ModelImp implements Model {

    private ModelListener listener;
    private Repository repository;

    public ModelImp(){
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
