package ayds.dictionary.charlie.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ayds.dictionary.charlie.model.DataBase.DataBase;
import ayds.dictionary.charlie.model.Errors.ErrorHandler;
import ayds.dictionary.charlie.model.Services.ServicesDef;
import ayds.dictionary.charlie.model.TypesOfException.BadWordException;
import ayds.dictionary.charlie.model.TypesOfException.NetworkException;

class RepositoryImp implements Repository {

    private DataBase dataBase;
    private ServicesDef servicesDef;
    private ErrorHandler errorHandler;
    private final String prefijo = "[*] ";

    RepositoryImp(DataBase dataBase, ServicesDef servicesDef, ErrorHandler errorHandler) {
        this.dataBase = dataBase;
        this.servicesDef = servicesDef;
        this.errorHandler = errorHandler;
    }

    @Override
    public ArrayList<Concept> searchWord(String searchedWord) {
        ArrayList<Concept> conceptArrayList = new ArrayList<>();
        Map<Source,Exception> mapeoExceptions = new HashMap<>();
        Concept concept;
        for (Source source : servicesDef.getSources()) {
            try {
                concept = dataBase.getMeaning(searchedWord, source);
                if (concept != null) {
                    String newMeaning = prefijo + concept.getMeaning();
                    concept.setMeaning(newMeaning);
                } else {
                    String result = servicesDef.searchWord(searchedWord, source);
                    if (result != "" && result != null) {
                        concept = new Concept();
                        concept.setConcept(searchedWord);
                        concept.setMeaning(result);
                        concept.setSource(source);
                        dataBase.saveTerm(concept);
                    } else {
                        concept = new NullConcept();
                        concept.setSource(source);
                    }
                }
                conceptArrayList.add(concept);
            } catch (IOException e) {
                mapeoExceptions.put(source, new NetworkException());
            } catch (Exception e) {
                mapeoExceptions.put(source, e);
            }
        }
        if(!mapeoExceptions.isEmpty()){
            errorHandler.errorEvent(mapeoExceptions);
        }
        return conceptArrayList;
    }
}
