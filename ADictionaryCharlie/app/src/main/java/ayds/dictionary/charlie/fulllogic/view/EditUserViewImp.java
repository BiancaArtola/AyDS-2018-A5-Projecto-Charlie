package ayds.dictionary.charlie.fulllogic.view;

import android.text.Html;
import android.view.View;
import android.widget.TextView;
import ayds.dictionary.charlie.fulllogic.controller.EditUserController;
import ayds.dictionary.charlie.fulllogic.model.UserModel;
import ayds.dictionary.charlie.fulllogic.model.UserModelListener;

public class EditUserViewImp implements EditUserView {

    private EditUserController editUserController;
    private UserModel userModel;

    public EditUserViewImp(EditUserController editUserController, UserModel userModel) {
        this.editUserController = editUserController;
        this.userModel = userModel;
    }

    @Override
    public void updateResult() {
       /* String result = userModel.lastSearch();
        TextView panelResultado =  ;
        panelResultado.post(new Runnable() {
            @Override
            public void run() {
                panelResultado.setText(Html.fromHtml(result));
            }
        });*/
    }
}
