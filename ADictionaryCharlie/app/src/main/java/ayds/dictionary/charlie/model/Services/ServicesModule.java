package ayds.dictionary.charlie.model.Services;

import ayds.dictionary.charlie.model.Source;

public class ServicesModule {
    private static ServicesModule instance;
    private ServicesDef servicesDef;

    private ServicesModule(){
        ServiceBigHugeLabsAdapter bigHugeLabsAdapter = new ServiceBigHugeLabsAdapter();
        ServiceWikipediaAdapter wikipediaAdapter = new ServiceWikipediaAdapter();
        ServiceYandexAdapter yandexAdapter = new ServiceYandexAdapter();
        servicesDef = new ServicesDefImp(bigHugeLabsAdapter, wikipediaAdapter, yandexAdapter);
    }

    public static ServicesModule getInstance(){
        if(instance == null) {
            instance =  new ServicesModule();
        }
        return instance;
    }

    public ServicesDef getServicesDef() {
        return servicesDef;
    }
}
