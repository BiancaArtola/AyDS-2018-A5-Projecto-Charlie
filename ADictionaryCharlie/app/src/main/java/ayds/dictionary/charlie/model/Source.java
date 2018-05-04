package ayds.dictionary.charlie.model;

public class Source {

    public int getMySource(String source){
        int value = 0;
        switch (source){
            case "WIKIPEDIA" : value = 1;
                                break;
        }
        return value;
    }
}
