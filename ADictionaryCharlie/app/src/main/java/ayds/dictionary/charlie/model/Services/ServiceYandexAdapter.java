package ayds.dictionary.charlie.model.Services;


import services.Service;
import services.ServiceModule;

public class ServiceYandexAdapter implements Services {

    private static ServiceYandexAdapter instance;
    private Service yandexService;


    private ServiceYandexAdapter(){
        yandexService = ServiceModule.getInstance().getRemoteSource();
    }

    public static ServiceYandexAdapter getInstance(){
        if(instance == null) {
            instance =  new ServiceYandexAdapter();
        }
        return instance;
    }

    public String searchWord(String term) throws Exception{
        return yandexService.getMeaning(term);
    }
}
