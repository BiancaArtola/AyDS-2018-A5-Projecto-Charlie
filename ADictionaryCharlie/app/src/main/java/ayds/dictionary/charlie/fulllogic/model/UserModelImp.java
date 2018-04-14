package ayds.dictionary.charlie.fulllogic.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UserModelImp implements UserModel {

    private UserModelListener listener;
    private WordsBighugelabsAPI wikiAPI;
    private String lastSearch;

    public UserModelImp(){
        createRetrofit();
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
    public String lastSearch() {
        return lastSearch;
    }

    @Override
    public void searchWord(String searchedWord){
        String result = DataBase.getMeaning(searchedWord);

        if (result != null) { // exists in db
            result = "[*]" + result;
            lastSearch = result;
            notifyListener();
        } else {
            Response<String> callResponse;
            try{
                callResponse = wikiAPI.getTerm(searchedWord).execute();

                Log.e("**", "JSON: " + callResponse.body());

                if (callResponse.body() == null) {
                    result = "No Results";
                } else {
                    JsonObject jobj = createJSON(callResponse);
                    StringBuilder extract = new StringBuilder();

                    searchNouns(jobj, extract);
                    searchVerbs(jobj,extract);

                    result = extract.toString().replace("\\n", "<br>");
                    result = textToHtml(result, searchedWord);

                    // save term to DB
                    saveTerm(searchedWord,result);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private JsonObject createJSON(Response<String> callResponse){
        Gson gson = new Gson();
        return gson.fromJson(callResponse.body(), JsonObject.class);
    }

    private void searchNouns(JsonObject jobj, StringBuilder extract){
        // nouns
        JsonObject noun = jobj.get("noun").getAsJsonObject();
        JsonArray syn = noun.get("syn").getAsJsonArray();

        extract.append("<b>Nouns:</b><br>");
        for (JsonElement element : syn) {
            extract.append(element.getAsString()).append(", ");
        }

        extract.append("<br><br>");
        extract.append("<b>Verbs:</b><br>");
    }

    private void searchVerbs(JsonObject jobj, StringBuilder extract){
        // verbs
        JsonObject verb = jobj.get("verb").getAsJsonObject();
        JsonArray syn2 = verb.get("syn").getAsJsonArray();

        for (JsonElement element : syn2) {
            extract.append(element.getAsString()).append(", ");
        }

        extract.append("<br>");
    }

    private String textToHtml(String result, String searchedWord){
        StringBuilder builder = new StringBuilder();
        String textWithBold = result.replaceAll("(?i)" + searchedWord, "<b>" + searchedWord + "</b>");
        builder.append(textWithBold);
        return builder.toString();
    }

    private void saveTerm(String searchedWord, String result) {
        DataBase.saveTerm(searchedWord,result);
        lastSearch = result;
        notifyListener();
    }

    private void notifyListener() {
        if (listener != null) {
            listener.didUpdate();
        }
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
}
