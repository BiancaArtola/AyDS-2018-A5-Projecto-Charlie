package ayds.dictionary.charlie.model.Service;

import java.io.IOException;

import ayds.dictionary.charlie.model.Source;
import ayds.dictionary.charlie.model.TypesOfException.APIConnectionException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

class BighugelabsService implements Service {

    private WordsBighugelabsAPI wikiAPI;
    private Transform transform;
    private Source source; // PREGUNTAR !!!!!!!!!
    private final String baseUrl = "http://words.bighugelabs.com/api/2/";
    private final String noResult = "No Results";
    private final String connectionProblem = "No Connection Detected!";
    private final String myName = "WIKIPEDIA"; // PREGUNTAR !!!!!!!!!

    BighugelabsService(Transform transform){
        this.transform = transform;
        source = new Source();  // PREGUNTAR !!!!!!!!!!!!
        createRetrofit();
    }

    private void createRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        createAPI(retrofit);
    }

    private void createAPI(Retrofit retrofit){
        wikiAPI = retrofit.create(WordsBighugelabsAPI.class);
    }

    @Override
    public String searchWord(String searchedWord) throws APIConnectionException {
        String result = "";
        Response<String> callResponse;
        try{
            callResponse = wikiAPI.getTerm(searchedWord).execute();
            if (callResponse.body() == null) {
                result = noResult;
            } else {
                result = transform.ResultToString(callResponse.body(), searchedWord);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            throw new APIConnectionException(connectionProblem);
        }
        return result;
    }

    @Override
    public int getSource() {
        return source.getMySource(myName);
    }
}
