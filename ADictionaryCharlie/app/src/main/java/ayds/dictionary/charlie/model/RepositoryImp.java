package ayds.dictionary.charlie.fulllogic.model;

import ayds.dictionary.charlie.fulllogic.model.DataBase.DataBase;
import ayds.dictionary.charlie.fulllogic.model.Service.Service;

class RepositoryImp implements Repository{

    private DataBase dataBase;
    private Service service;
    private final String prefijo = "[*]";
    private final String noResult = "No Results";

    RepositoryImp(DataBase dataBase, Service service){
        this.dataBase = dataBase;
        this.service = service;
    }

    @Override
    public String searchWord(String searchedWord) throws APIConnectionException {
        String result = dataBase.getMeaning(searchedWord);
        if (result != null) { // exists in DB
            result = prefijo + result;
        } else {
            result = service.searchWord(searchedWord);
            if (!result.equals(noResult))
                dataBase.saveTerm(searchedWord,result);
        }
        return result;
    }
}
