package ayds.dictionary.charlie.model;

import java.io.IOException;

import ayds.dictionary.charlie.model.DataBase.DataBase;
import ayds.dictionary.charlie.model.Errors.ErrorHandler;
import Service.Service;
import ayds.dictionary.charlie.model.TypesOfException.BadWordException;
import ayds.dictionary.charlie.model.TypesOfException.NetworkException;

class RepositoryImp implements Repository{

    private DataBase dataBase;
    private Service service;
    private ErrorHandler errorHandler;
    private Source source;
    private final String prefijo = "[*]";

    RepositoryImp(DataBase dataBase, Service service, ErrorHandler errorHandler){
        this.dataBase = dataBase;
        this.service = service;
        this.errorHandler = errorHandler;
    }

    @Override
    public Concept searchWord(String searchedWord) {
        Concept concept = null;
        try {
            boolean isCorrect = checkWordWithoutSymbols(searchedWord);
            if (isCorrect) {
                concept = dataBase.getMeaning(searchedWord);
                if (concept != null) {
                    String newMeaning = prefijo + concept.getMeaning();
                    concept.setMeaning(newMeaning);
                } else {
                    String result = service.searchWord(searchedWord);
                    if (result != "") {
                        concept = new Concept();
                        concept.setConcept(searchedWord);
                        concept.setMeaning(result);
                        concept.setSource(Source.WIKIPEDIA);
                        dataBase.saveTerm(concept);
                    } else {
                        concept = new NullConcept();
                        concept.setSource(Source.WIKIPEDIA);
                    }
                }
            }
        } catch (IOException e) {
            errorHandler.errorEvent(new NetworkException());
        } catch (Exception exception){
            errorHandler.errorEvent(exception);
        }
        return concept;
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
