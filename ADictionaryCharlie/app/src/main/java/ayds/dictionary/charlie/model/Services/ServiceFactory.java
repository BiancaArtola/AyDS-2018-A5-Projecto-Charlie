package ayds.dictionary.charlie.model.Services;

import java.util.HashMap;
import java.util.Map;

import ayds.dictionary.charlie.model.Source;

public class ServiceFactory {

    Map<Source, Services> mapeo;

    public ServiceFactory(Services bigHugeLabsAdapter, Services wikipediaAdapter, Services yandexAdapter) {
        mapeo = new HashMap<>();
        mapeo.put(Source.BigHugeLabs, bigHugeLabsAdapter);
        mapeo.put(Source.Wikipedia, wikipediaAdapter);
        mapeo.put(Source.Yandex, yandexAdapter);
    }

    public Services getService(Source source){
        return mapeo.get(source);
    }
}
