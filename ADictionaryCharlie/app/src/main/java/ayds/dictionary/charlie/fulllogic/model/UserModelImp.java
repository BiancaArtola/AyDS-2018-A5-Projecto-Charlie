package ayds.dictionary.charlie.fulllogic.model;


public class UserModelImp implements UserModel {
    private UserModelListener listener;

    public UserModelImp(){

    }

    @Override public void setListener(UserModelListener listener) {
        this.listener = listener;
    }

    private void notifyListener() {
        if (listener != null) {
            listener.didUpdateUser();
        }
    }

}
