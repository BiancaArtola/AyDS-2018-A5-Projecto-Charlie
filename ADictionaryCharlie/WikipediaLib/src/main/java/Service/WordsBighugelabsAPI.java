package Service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

// https://words.bighugelabs.com/api.php
public interface WordsBighugelabsAPI {

  @GET("c2e025b35f5ab7a1881095185ceabff7/{word}/json")
  Call<String> getTerm(@Path("word") String word);

}