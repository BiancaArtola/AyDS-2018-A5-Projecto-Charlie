package ayds.dictionary.charlie.model.Service;

public class ServiceModule {
    private static ServiceModule instance;
    private Service service;

    private ServiceModule() {
        Transform transform = new JSONToString();

        service = new BighugelabsService(transform);
    }

    public static ServiceModule getInstance() {
        if(instance == null) {
            instance =  new ServiceModule();
        }
        return instance;
    }

    public Service getService() {
        return service;
    }
}
