package ayds.dictionary.charlie.model.Services;

import java.util.HashMap;

import ayds.dictionary.charlie.model.Source;

class ServiceFactory extends ServiceAbstractFactory {

    ServiceFactory(Services bigHugeLabsAdapter, Services wikipediaAdapter, Services yandexAdapter) {
        mapeo = new HashMap<>();
        mapeo.put(Source.BigHugeLabs, bigHugeLabsAdapter);
        mapeo.put(Source.Wikipedia, wikipediaAdapter);
        mapeo.put(Source.Yandex, yandexAdapter);
    }
}
