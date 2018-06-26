package ayds.dictionary.charlie.model.Services;

import java.util.Map;

import ayds.dictionary.charlie.model.Source;

abstract class ServiceAbstractFactory {

    protected Map<Source, Services> mapeo;

    public Services getService(Source source){
        return mapeo.get(source);
    }

}
