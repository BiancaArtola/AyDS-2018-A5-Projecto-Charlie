package ayds.dictionary.charlie.model;

import ayds.dictionary.charlie.model.DataBase.DataBase;
import ayds.dictionary.charlie.model.DataBase.DataBaseModule;
import ayds.dictionary.charlie.model.Errors.ErrorHandler;
import ayds.dictionary.charlie.model.Errors.ErrorModule;
import ayds.dictionary.charlie.model.Services.ServicesDef;
import ayds.dictionary.charlie.model.Services.ServicesModule;

public class ModelModule {

    private static ModelModule instance;
    private TermModel termModel;

    private ModelModule() {
        DataBase dataBase = DataBaseModule.getInstance().getDataBase();
        //BighugelabsService service = BighugelabsServiceModule.getInstance().getBighugelabsService();
        ServicesDef servicesDef = ServicesModule.getInstance().getServicesDef();
        ErrorHandler errorHandler = ErrorModule.getInstance().getErrorHandler();
        Repository repository = new RepositoryImp(dataBase,servicesDef,errorHandler);
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
