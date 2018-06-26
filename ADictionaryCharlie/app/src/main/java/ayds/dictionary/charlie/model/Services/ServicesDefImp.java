package ayds.dictionary.charlie.model.Services;

import ayds.dictionary.charlie.model.Source;

class ServicesDefImp implements ServicesDef {

    private ServiceAbstractFactory factory;

    ServicesDefImp(ServiceAbstractFactory factory){
        this.factory = factory;
    }

    @Override
    public Source[] getSources() {
        return Source.values();
    }

    @Override
    public String searchWord(String term, Source source) throws Exception {
        Services service = factory.getService(source);
        String result = service.searchWord(term);
        return result;
    }
}
