package ayds.dictionary.charlie.model.Services;

import java.io.IOException;

import ayds.dictionary.charlie.model.Source;

public class ServicesDefImp implements ServicesDef {

    private ServiceFactory factory;

    protected ServicesDefImp(ServiceFactory factory){
        this.factory = factory;
    }

    @Override
    public Source[] getSources() {
        return Source.values();
    }

    @Override
    public String searchWord(String term, Source source) throws IOException {
        Services service = factory.getService(source);
        return service.searchWord(term);
    }
}
