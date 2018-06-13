package ayds.dictionary.charlie.model.Services;

import wikipedia.service.WikipediaService;
import wikipedia.service.WikipediaServiceModule;

public class ServiceWikipediaAdapter implements Services {

    private static ServiceWikipediaAdapter instance;
    private WikipediaService wikipediaService;

    private ServiceWikipediaAdapter(){
        wikipediaService = WikipediaServiceModule.getInstance().getService();
    }

    public static ServiceWikipediaAdapter getInstance(){
        if(instance == null) {
            instance =  new ServiceWikipediaAdapter();
        }
        return instance;
    }

    public String searchWord(String term) throws Exception{
        return wikipediaService.getMeaning(term);
    }
}
