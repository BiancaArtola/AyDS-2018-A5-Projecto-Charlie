package ayds.dictionary.charlie.fulllogic.model.DataBase;

import android.content.Context;

import ayds.dictionary.charlie.fulllogic.view.ViewModule;

public class DataBaseModule {
    private static DataBaseModule instance;
    private DataBase dataBase;

    private DataBaseModule() {
        Context context = ViewModule.getInstance().getApplicationContext();
        dataBase = new DataBaseImp(context);
    }

    public static DataBaseModule getInstance() {
        if(instance == null) {
            instance =  new DataBaseModule();
        }
        return instance;
    }

    public DataBase getDataBase() {
        return dataBase;
    }
}
