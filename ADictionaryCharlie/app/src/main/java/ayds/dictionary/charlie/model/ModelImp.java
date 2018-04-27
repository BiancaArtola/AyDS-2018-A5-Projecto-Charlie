package ayds.dictionary.charlie.model;

import android.util.Log;

class ModelImp implements Model {

    private ModelListener listener;
    private ErrorListener errorListener;
    private Repository repository;


    ModelImp(Repository repository){
        this.repository = repository;
    }

    @Override
    public void setListener(ModelListener listener) {
        this.listener = listener;
    }

    @Override
    public void setErrorListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public void searchWord(String searchedWord){
        try {
            boolean isCorrect = checkWordWithoutSymbols(searchedWord);
            if (isCorrect){
                String result = repository.searchWord(searchedWord);
                notifyListener(result);
            }
        } catch (APIConnectionException e){
            notifyErrorListener();
        }
    }

    private boolean checkWordWithoutSymbols(String searchWord){
        char letterOfSearchWord=' ';
        boolean isWordWithoutSymbols = true;
        for(int i=0; i< searchWord.length() && isWordWithoutSymbols;i++){
            letterOfSearchWord = searchWord.charAt(i);
            if(!Character.isLetter(letterOfSearchWord)){
                isWordWithoutSymbols=false;
                Log.e("Letra", "--"+isWordWithoutSymbols+"");
            }
        }
        return isWordWithoutSymbols;
    }


    private void notifyListener(String lastSearch) {
        if (listener != null) {
            listener.didUpdate(lastSearch);
        }
    }

    private void notifyErrorListener(){
        if (errorListener != null){
            errorListener.notifyError();
        }
    }
}
