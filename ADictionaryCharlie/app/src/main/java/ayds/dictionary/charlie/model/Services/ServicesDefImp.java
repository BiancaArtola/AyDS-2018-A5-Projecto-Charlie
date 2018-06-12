package ayds.dictionary.charlie.model.Services;

import java.io.IOException;

import ayds.dictionary.charlie.model.Source;

public class ServicesDefImp implements ServicesDef {

    private ServiceBigHugeLabsAdapter bigHugeLabsAdapter;
    private ServiceWikipediaAdapter wikipediaAdapter;
    private ServiceYandexAdapter yandexAdapter;

    protected ServicesDefImp(ServiceBigHugeLabsAdapter bigHugeLabsAdapter, ServiceWikipediaAdapter wikipediaAdapter, ServiceYandexAdapter yandexAdapter){
        this.bigHugeLabsAdapter = bigHugeLabsAdapter;
        this.wikipediaAdapter = wikipediaAdapter;
        this.yandexAdapter = yandexAdapter;
    }

    @Override
    public Source[] getSources() {
        return Source.values();  // Source.values().length;
    }

    @Override
    public String searchWord(String term, Source source) throws IOException {
        switch (source.toString()){
            case "BigHugeLabs": return bigHugeLabsAdapter.searchWord(term);
            case "Wikipedia" : return wikipediaAdapter.searchWord(term);
            default: return yandexAdapter.searchWord(term);
        }
    }
    // Chequea cual es la source en su lista, y le pide el termino al adapter correspondiente
}
