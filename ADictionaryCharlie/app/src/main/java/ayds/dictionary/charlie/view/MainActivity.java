package ayds.dictionary.charlie.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ayds.dictionary.charlie.R;
import ayds.dictionary.charlie.model.Errors.ErrorListener;
import ayds.dictionary.charlie.model.NewTermListener;

public class MainActivity  extends AppCompatActivity {

    private EditText searchField;
    private Button goButton;
    private TextView resultField;
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
        searchField = findViewById(R.id.searchField);
        goButton = findViewById(R.id.goButton);
        resultField = findViewById(R.id.resultField);
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
                        String searchedWord = searchField.getText().toString();
                        viewModule.getController().onEventUpdate(searchedWord);
                    }
                }).start();
            }
        });

        viewModule.getModel().setNewTermListener(new NewTermListener() {
            @Override
            public void didUpdate(String lastSearch) {
                updateResult(lastSearch);
            }
        });

        viewModule.getModel().setErrorListener(new ErrorListener() {
            @Override
            public void notifyError(Exception exception) {
                showErrorMessage(exception.getMessage());
            }
        });
    }

    private void updateResult(final String lastSearch){
        String searchedWord = searchField.getText().toString();
        final String textToPrint = ResultToBold.textToHtml(lastSearch,searchedWord);
        resultField.post(new Runnable() {
            @Override
            public void run() {
                resultField.setText(Html.fromHtml(textToPrint));
            }
        });
    }

    private void showErrorMessage(final String message){
        resultField.post(new Runnable() {
            @Override
            public void run() {
                resultField.setText(message);
            }
        });
    }
}
