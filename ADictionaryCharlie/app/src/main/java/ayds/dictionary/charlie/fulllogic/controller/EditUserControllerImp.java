package ayds.dictionary.charlie.fulllogic.controller;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

import ayds.dictionary.charlie.fulllogic.model.UserModel;
import ayds.dictionary.charlie.fulllogic.view.EditUserView;
import retrofit2.Response;

public class EditUserControllerImp implements EditUserController {

    private UserModel userModel;
    private EditUserView editUserView;

    public EditUserControllerImp(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public void onEventUpdate(final String palabraBuscada) {
        new Thread(new Runnable() {
            public void run() {
                String resultado = userModel.getResult(palabraBuscada);
                checkResult(palabraBuscada,resultado);
            }
        }).start();
    }

    private void checkResult(String palabraBuscada, String resultado){
        if (resultado != null) { // exists in db
            resultado = "[*]" + resultado;
        } else {
            Response<String> callResponse;
            try{
                callResponse = userModel.getAPI().getTerm(palabraBuscada).execute();

                Log.e("**", "JSON: " + callResponse.body());

                if (callResponse.body() == null) {
                    resultado = "No Results";
                } else {
                    Gson gson = new Gson();
                    JsonObject jobj = gson.fromJson(callResponse.body(), JsonObject.class);

                    // nouns
                    JsonObject noun = jobj.get("noun").getAsJsonObject();
                    JsonArray syn = noun.get("syn").getAsJsonArray();
                    StringBuilder extract = new StringBuilder();
                    extract.append("<b>Nouns:</b><br>");
                    for (JsonElement element : syn) {
                        extract.append(element.getAsString()).append(", ");
                    }

                    extract.append("<br><br>");
                    extract.append("<b>Verbs:</b><br>");

                    // verbs
                    JsonObject verb = jobj.get("verb").getAsJsonObject();
                    JsonArray syn2 = verb.get("syn").getAsJsonArray();

                    for (JsonElement element : syn2) {
                        extract.append(element.getAsString()).append(", ");
                    }

                    extract.append("<br>");

                    resultado = extract.toString().replace("\\n", "<br>");
                    resultado = textToHtml(resultado, palabraBuscada);

                    // save to DB  <o/
                    userModel.saveTerm(palabraBuscada,resultado);
            }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private String textToHtml(String resultado, String palabraBuscada){
        StringBuilder builder = new StringBuilder();
        String textWithBold = resultado.replaceAll("(?i)" + palabraBuscada, "<b>" + palabraBuscada + "</b>");
        builder.append(textWithBold);
        return builder.toString();
    }

    @Override
    public void setEditUserView(EditUserView editUserView) {
        this.editUserView = editUserView;
    }
}
