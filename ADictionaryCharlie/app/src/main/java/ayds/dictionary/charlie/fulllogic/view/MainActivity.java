package ayds.dictionary.charlie.fulllogic.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ayds.dictionary.charlie.R;
import ayds.dictionary.charlie.fulllogic.model.ModelListener;

public class MainActivity  extends AppCompatActivity {

    private EditText editText;
    private Button goButton;
    private TextView textView;
    private ViewModule viewModule;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initElements();
        initModule();
        initListeners();
    }

    private void initElements(){
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        goButton = findViewById(R.id.goButton);
        textView = findViewById(R.id.textView);
    }

    private void initModule(){
        viewModule = ViewModule.getInstance();
        viewModule.startApplication(getApplicationContext());
    }

    private void initListeners() {
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String searchedWord = editText.getText().toString();
                        viewModule.getController().onEventUpdate(searchedWord);
                    }
                }).start();
            }
        });

        viewModule.getModel().setListener(new ModelListener() {
            @Override
            public void didUpdate(String lastSearch) {
                updateResult(lastSearch);
            }
        });
    }

    private void updateResult(final String lastSearch){
        String searchedWord = editText.getText().toString();
        final String textToPrint = BoldText.textToHtml(lastSearch,searchedWord);
        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(Html.fromHtml(textToPrint));
            }
        });
    }
}
