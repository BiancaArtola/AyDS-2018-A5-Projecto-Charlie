package ayds.dictionary.charlie.model.Services;


public class ServiceYandexAdapter implements Services {

    private static ServiceYandexAdapter instance;

    private ServiceYandexAdapter(){}

    public static ServiceYandexAdapter getInstance(){
        if(instance == null) {
            instance =  new ServiceYandexAdapter();
        }
        return instance;
    }

    public String searchWord(String term) throws Exception{
        return null;
    }
}
