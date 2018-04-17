package ayds.dictionary.charlie.fulllogic.model.Service;

import android.util.Log;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BighugelabsService implements Service {

    private WordsBighugelabsAPI wikiAPI;
    private Transform transform;

    public BighugelabsService(Transform transform){
        this.transform = transform;
        createRetrofit();
    }

    private void createRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://words.bighugelabs.com/api/2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        createAPI(retrofit);
    }

    private void createAPI(Retrofit retrofit){
        wikiAPI = retrofit.create(WordsBighugelabsAPI.class);
    }

    @Override
    public String searchWord(String searchedWord) {
        String result = "";
        Response<String> callResponse;
        try{
            callResponse = wikiAPI.getTerm(searchedWord).execute();

            Log.e("**", "JSON: " + callResponse.body());

            if (callResponse.body() == null) {
                result = "No Results";
            } else {
                result = transform.JSONtoString(callResponse,searchedWord);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return result;
    }
}
