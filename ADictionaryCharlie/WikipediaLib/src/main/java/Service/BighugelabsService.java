package Service;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

class BighugelabsService implements Service {

    private WordsBighugelabsAPI wikiAPI;
    private Transform transform;
    private final String baseUrl = "http://words.bighugelabs.com/api/2/";

    BighugelabsService(Transform transform){
        this.transform = transform;
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
    public String searchWord(String searchedWord) throws Exception {
        String result = "";
        Response<String> callResponse;
        callResponse = wikiAPI.getTerm(searchedWord).execute();
        if (callResponse.body() != null) {
            result = transform.ResultToString(callResponse.body(), searchedWord);
        }
        return result;
    }
}
