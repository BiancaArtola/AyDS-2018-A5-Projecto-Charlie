package ayds.dictionary.charlie.fulllogic.model.Service;

import retrofit2.Response;

public interface Transform {
    String JSONtoString(Response<String> callResponse , String searchedWord);
}
