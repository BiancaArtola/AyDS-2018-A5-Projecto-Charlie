package Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


class JSONToString implements Transform{

    @Override
    public String ResultToString(String body, String searchedWord) {
        JsonObject jobj = createJSON(body);
        StringBuilder extract = new StringBuilder();

        searchNouns(jobj, extract);
        searchVerbs(jobj, extract);

        String result = "";
        result = extract.toString().replace("\\n", "<br>");
        return result;
    }

    private JsonObject createJSON(String body){
        Gson gson = new Gson();
        return gson.fromJson(body, JsonObject.class);
    }

    private void searchNouns(JsonObject jobj, StringBuilder extract){
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
