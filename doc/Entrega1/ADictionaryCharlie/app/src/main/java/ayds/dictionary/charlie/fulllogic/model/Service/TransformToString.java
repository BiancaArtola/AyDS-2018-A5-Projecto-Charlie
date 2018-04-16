package ayds.dictionary.charlie.fulllogic.model.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Response;

public class TransformToString implements Transform{

    public TransformToString(){}

    @Override
    public String JSONtoString(Response<String> callResponse, String searchedWord) {
        JsonObject jobj = createJSON(callResponse);
        StringBuilder extract = new StringBuilder();

        searchNouns(jobj, extract);
        searchVerbs(jobj, extract);

        String result = "";
        result = extract.toString().replace("\\n", "<br>");
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
}
