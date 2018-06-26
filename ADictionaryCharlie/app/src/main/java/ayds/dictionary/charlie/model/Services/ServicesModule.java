package ayds.dictionary.charlie.model.Services;

public class ServicesModule {
    private static ServicesModule instance;
    private ServicesDef servicesDef;

    private ServicesModule(){
        Services bigHugeLabsAdapter = ServiceBigHugeLabsAdapter.getInstance();
        Services wikipediaAdapter = ServiceWikipediaAdapter.getInstance();
        Services yandexAdapter = ServiceYandexAdapter.getInstance();
        ServiceAbstractFactory factory = new ServiceFactory(bigHugeLabsAdapter, wikipediaAdapter, yandexAdapter);
        servicesDef = new ServicesDefImp(factory);
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
