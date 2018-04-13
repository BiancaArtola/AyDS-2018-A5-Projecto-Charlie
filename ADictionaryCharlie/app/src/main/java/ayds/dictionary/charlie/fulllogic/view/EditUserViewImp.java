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
    private ElementosGraficos elementosGraficos;

    EditUserViewImp(EditUserController editUserController, UserModel userModel, ElementosGraficos elementosGraficos) {
        this.editUserController = editUserController;
        this.userModel = userModel;
        this.elementosGraficos = elementosGraficos;

        initListeners();
    }


    private void initListeners() {
        elementosGraficos.getGoButton().setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                String palabraBuscada = elementosGraficos.getTextField().getText().toString();
                editUserController.onEventUpdate(palabraBuscada);
            }
        });

        userModel.setListener(new UserModelListener() {
            @Override
            public void didUpdateUser() {
                updateResult();
            }
        });
    }

    @Override
    public void updateResult() {
        final String result = userModel.lastSearch();
        final TextView panelResultado = elementosGraficos.getTextPanel();
        panelResultado.post(new Runnable() {
            @Override
            public void run() {
                panelResultado.setText(Html.fromHtml(result));
            }
        });
    }
}
