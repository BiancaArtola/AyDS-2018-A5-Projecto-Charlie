package ayds.dictionary.charlie.model.Services;

public class ServiceWikipediaAdapter implements Services {

    private static ServiceWikipediaAdapter instance;
    private WikipediaService wikipediaService;

    private ServiceWikipediaAdapter(){

    }

    public static ServiceWikipediaAdapter getInstance(){
        if(instance == null) {
            instance =  new ServiceWikipediaAdapter();
        }
        return instance;
    }

    public String searchWord(String term) throws Exception{
        return wikipediaService.searchWord(term);
    }
}
