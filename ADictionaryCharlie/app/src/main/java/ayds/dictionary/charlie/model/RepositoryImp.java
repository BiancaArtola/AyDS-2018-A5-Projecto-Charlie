package ayds.dictionary.charlie.model;

import java.io.IOException;
import java.util.ArrayList;

import ayds.dictionary.charlie.model.DataBase.DataBase;
import ayds.dictionary.charlie.model.Errors.ErrorHandler;
import ayds.dictionary.charlie.model.Services.ServicesDef;
import ayds.dictionary.charlie.model.TypesOfException.BadWordException;
import ayds.dictionary.charlie.model.TypesOfException.NetworkException;

class RepositoryImp implements Repository{

    private DataBase dataBase;
    private ServicesDef servicesDef;
    private ErrorHandler errorHandler;
    private final String prefijo = "[*]";

    RepositoryImp(DataBase dataBase, ServicesDef servicesDef, ErrorHandler errorHandler){
        this.dataBase = dataBase;
        this.servicesDef = servicesDef;
        this.errorHandler = errorHandler;
    }

    @Override
    public ArrayList<Concept> searchWord(String searchedWord) {
        ArrayList<Concept> conceptArrayList = new ArrayList<>();
        try {
            boolean isCorrect = checkWordWithoutSymbols(searchedWord);
            if (isCorrect) {
                Concept concept;
                for(Source source: servicesDef.getSources()){
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
                }
            }
        } catch (IOException e) {
            errorHandler.errorEvent(new NetworkException());
        } catch (Exception exception){
            errorHandler.errorEvent(exception);
        }
        return conceptArrayList;
    }

    private boolean checkWordWithoutSymbols(String searchWord) throws BadWordException {
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
        return isWordWithoutSymbols;
    }
}
