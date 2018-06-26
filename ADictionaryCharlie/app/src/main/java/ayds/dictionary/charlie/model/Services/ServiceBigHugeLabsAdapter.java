package ayds.dictionary.charlie.model.Services;

import ayds.dictionary.charlie.model.TypesOfException.BadWordException;
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
        checkWordWithoutSymbols(term);
        return bighugelabsService.searchWord(term);
    }

    private void checkWordWithoutSymbols(String searchWord) throws BadWordException {
        char letterOfSearchWord = ' ';
        boolean isWordWithoutSymbols = true;
        for(int i=0; i < searchWord.length() && isWordWithoutSymbols; i++){
            letterOfSearchWord = searchWord.charAt(i);
            if(!Character.isLetter(letterOfSearchWord)){
                isWordWithoutSymbols = false;
            }
        }
        if(!isWordWithoutSymbols){
            throw new BadWordException();
        }
    }
}
