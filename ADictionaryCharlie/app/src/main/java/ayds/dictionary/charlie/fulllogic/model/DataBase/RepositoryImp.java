package ayds.dictionary.charlie.fulllogic.model.DataBase;

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

public class RepositoryImp implements Repository{

    private WordsBighugelabsAPI wikiAPI;
    private DataBase dataBase;

    public RepositoryImp(Context applicationContext){
        createRetrofit();
        createDatabase(applicationContext);
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

    private void createDatabase(Context applicationContext){
        dataBase = new DataBaseImp(applicationContext);
    }

    @Override
    public String searchWord(String searchedWord) {
        String result = dataBase.getMeaning(searchedWord);

        if (result != null) { // exists in db
            result = "[*]" + result;
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
                    searchVerbs(jobj, extract);

                    result = extract.toString().replace("\\n", "<br>");
                    result = textToHtml(result, searchedWord);

                    // save term to DB
                    dataBase.saveTerm(searchedWord,result);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }

    private JsonObject createJSON(Response<String> callResponse){
        Gson gson = new Gson();
        return gson.fromJson(callResponse.body(), JsonObject.class);
    }

    private void searchNouns(JsonObject jobj, StringBuilder extract){
        // nouns
        if (jobj.get("noun") != null) {
            JsonObject noun = jobj.get("noun").getAsJsonObject();
            JsonArray syn = noun.get("syn").getAsJsonArray();

            extract.append("<b>Nouns:</b><br>");
            for (JsonElement element : syn) {
                extract.append(element.getAsString()).append(", ");
            }

            extract.append("<br><br>");
        }
    }

    private void searchVerbs(JsonObject jobj, StringBuilder extract){
        // verbs
        if (jobj.get("verb") != null) {
            JsonObject verb = jobj.get("verb").getAsJsonObject();
            JsonArray syn = verb.get("syn").getAsJsonArray();

            extract.append("<b>Verbs:</b><br>");
            for (JsonElement element : syn) {
                extract.append(element.getAsString()).append(", ");
            }
            extract.append("<br>");
        }
    }

    private String textToHtml(String result, String searchedWord){
        StringBuilder builder = new StringBuilder();
        String textWithBold = result.replaceAll("(?i)" + searchedWord, "<b>" + searchedWord + "</b>");
        builder.append(textWithBold);
        return builder.toString();
    }
}
