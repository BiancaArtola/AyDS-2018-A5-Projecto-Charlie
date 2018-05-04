package ayds.dictionary.charlie.model;

import ayds.dictionary.charlie.model.DataBase.DataBase;
import ayds.dictionary.charlie.model.DataBase.DataBaseModule;
import ayds.dictionary.charlie.model.Errors.ErrorHandler;
import ayds.dictionary.charlie.model.Errors.ErrorModule;
import ayds.dictionary.charlie.model.Service.Service;
import ayds.dictionary.charlie.model.Service.ServiceModule;

public class ModelModule {

    private static ModelModule instance;
    private TermModel termModel;

    private ModelModule() {
        DataBase dataBase = DataBaseModule.getInstance().getDataBase();
        Service service = ServiceModule.getInstance().getService();
        ErrorHandler errorHandler = ErrorModule.getInstance().getErrorHandler();
        Repository repository = new RepositoryImp(dataBase,service,errorHandler);
        termModel =  new TermModelImp(repository,errorHandler);
    }

    public static ModelModule getInstance() {
        if(instance == null) {
            instance =  new ModelModule();
        }
        return instance;
    }

    public TermModel getTermModel() {
        return termModel;
    }
}
