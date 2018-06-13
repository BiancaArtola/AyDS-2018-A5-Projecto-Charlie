package ayds.dictionary.charlie.model.Services;

import ayds.dictionary.charlie.service.BighugelabsService;
import ayds.dictionary.charlie.service.BighugelabsServiceModule;

public class ServiceBigHugeLabsAdapter implements Services {

    private static ServiceBigHugeLabsAdapter instance;
    private BighugelabsService bighugelabsService;

    private ServiceBigHugeLabsAdapter(){
        bighugelabsService = BighugelabsServiceModule.getInstance().getBighugelabsService();
    }

    public static ServiceBigHugeLabsAdapter getInstance(){
        if(instance == null) {
            instance =  new ServiceBigHugeLabsAdapter();
        }
        return instance;
    }

    public String searchWord(String term) throws Exception{
        return bighugelabsService.searchWord(term);
    }
}
