package ayds.dictionary.charlie.fulllogic.view;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ElementosGraficos {

    private EditText editText;
    private Button goButton;
    private TextView textView;

    public ElementosGraficos(EditText editTextt, Button button, TextView textView){
        this.editText = editTextt;
        this.goButton = button;
        this.textView = textView;
    }

    public Button getGoButton() {
        return goButton;
    }

    public EditText getTextField() {
        return editText;
    }

    public TextView getTextPanel() {
        return textView;
    }
}
