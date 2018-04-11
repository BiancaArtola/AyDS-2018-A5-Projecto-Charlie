package ayds.dictionary.charlie.fulllogic.model;

public class UserModelModule {

    private static UserModelModule instance;
    private UserModel userModel;

    private UserModelModule() {
        userModel =  new UserModelImp();
    }

    public static UserModelModule getInstance() {
        if(instance == null) {
            instance =  new UserModelModule();
        }
        return instance;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
