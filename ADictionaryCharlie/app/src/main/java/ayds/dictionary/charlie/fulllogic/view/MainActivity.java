package ayds.dictionary.charlie.fulllogic.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ayds.dictionary.charlie.R;

public class MainActivity extends AppCompatActivity{

    private EditText editText;
    private Button goButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.textField1);
        goButton = findViewById(R.id.goButton);
        textView = findViewById(R.id.textPane1);
        ElementosGraficos elementosGraficos = new ElementosGraficos(editText,goButton,textView);
        UserViewModule.getInstance().startApplication(elementosGraficos, getApplicationContext());
    }
}
