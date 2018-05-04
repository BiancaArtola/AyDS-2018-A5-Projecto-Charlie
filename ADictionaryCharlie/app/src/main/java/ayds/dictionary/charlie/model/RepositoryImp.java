package ayds.dictionary.charlie.model;

import ayds.dictionary.charlie.model.DataBase.DataBase;
import ayds.dictionary.charlie.model.Errors.ErrorHandler;
import ayds.dictionary.charlie.model.Service.Service;
import ayds.dictionary.charlie.model.TypesOfException.BadWordException;

class RepositoryImp implements Repository{

    private DataBase dataBase;
    private Service service;
    private ErrorHandler errorHandler;
    private final String prefijo = "[*]";
    private final String noResult = "No Results";
    private final String badWordProblem = "Incorrect Word!";

    RepositoryImp(DataBase dataBase, Service service, ErrorHandler errorHandler){
        this.dataBase = dataBase;
        this.service = service;
        this.errorHandler = errorHandler;
    }

    @Override
    public String searchWord(String searchedWord) {
        String result = "";
        try {
            boolean isCorrect = checkWordWithoutSymbols(searchedWord);
            if (isCorrect) {
                result = dataBase.getMeaning(searchedWord);
                if (result != null) { // exists in DB
                    result = prefijo + result;
                } else {
                    result = service.searchWord(searchedWord);
                    if (!result.equals(noResult))
                        dataBase.saveTerm(searchedWord, result, service.getSource());  // PREGUNTAR !!!!!!!!!
                }
            }
        } catch (Exception exception){
            errorHandler.errorEvent(exception);
        }
        return result;
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
            throw new BadWordException(badWordProblem);
        }
        return isWordWithoutSymbols;
    }
}
