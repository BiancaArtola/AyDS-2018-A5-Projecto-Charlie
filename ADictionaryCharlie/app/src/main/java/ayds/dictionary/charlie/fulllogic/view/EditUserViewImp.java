package ayds.dictionary.charlie.fulllogic.view;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ayds.dictionary.charlie.R;
import ayds.dictionary.charlie.fulllogic.controller.EditUserController;
import ayds.dictionary.charlie.fulllogic.model.UserModel;

public class EditUserViewImp implements EditUserView {

    private EditText textField1;
    private Button goButton;
    private TextView textPane1;

    private EditUserController editUserController;
    private UserModel userModel;

    EditUserViewImp(EditUserController editUserController, UserModel userModel) {
        this.editUserController = editUserController;
        this.userModel = userModel;

        createFields();
    }

    private void createFields(){
        textField1 = findViewById(R.id.textField1);
        goButton = findViewById(R.id.goButton);
        textPane1 = findViewById(R.id.textPane1);
    }

    @Override
    public void updateText(String text) {
        //Actualizo el texto
    }
}
