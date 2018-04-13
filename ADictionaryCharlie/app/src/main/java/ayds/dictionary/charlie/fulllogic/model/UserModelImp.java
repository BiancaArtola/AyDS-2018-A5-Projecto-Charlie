package ayds.dictionary.charlie.fulllogic.model;

import android.content.Context;
import android.util.Log;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UserModelImp implements UserModel {

    private UserModelListener listener;
    private WordsBighugelabsAPI wikiAPI;
    private String lastSearch;

    public UserModelImp(){
        crearRetrofit();
    }

    private void crearRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://words.bighugelabs.com/api/2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        crearAPI(retrofit);
    }

    private void crearAPI(Retrofit retrofit){
        wikiAPI = retrofit.create(WordsBighugelabsAPI.class);
    }

    @Override
    public WordsBighugelabsAPI getAPI(){
        return wikiAPI;
    }

    @Override
    public String lastSearch() {
        return lastSearch;
    }

    @Override
    public void setListener(UserModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void createDatabase(final Context applicationContext) {
        new Thread(new Runnable() {
            @Override public void run() {
                DataBase.createNewDatabase(applicationContext);
                DataBase.saveTerm("test", "sarasa");

                Log.e("**", "" + DataBase.getMeaning("test"));
                Log.e("**", "" + DataBase.getMeaning("nada"));
            }
        }).start();
    }
    @Override
    public String getResult(String palabraBuscada){
        return DataBase.getMeaning(palabraBuscada);
    }

    @Override
    public void saveTerm(String palabraBuscada, String resultado) {
        DataBase.saveTerm(palabraBuscada,resultado);
        lastSearch = resultado;
        notifyListener();
    }

    private void notifyListener() {
        if (listener != null) {
            listener.didUpdateUser();
        }
    }

}
