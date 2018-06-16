package ayds.dictionary.charlie.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ayds.dictionary.charlie.R;
import ayds.dictionary.charlie.model.Concept;
import ayds.dictionary.charlie.model.Errors.ErrorListener;
import ayds.dictionary.charlie.model.NewTermListener;

public class MainActivity  extends AppCompatActivity {

    private EditText searchField;
    private Button goButton;
    private TextView resultField;
    private ViewModule viewModule;
    private ProgressBar progressBar;

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
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    private void initModule(){
        viewModule = ViewModule.getInstance();
        viewModule.startApplication(getApplicationContext());
    }

    private void initListeners() {
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String searchedWord = searchField.getText().toString();
                        viewModule.getController().onEventUpdate(searchedWord);
                    }
                }).start();
            }
        });

        viewModule.getTermModel().setNewTermListener(new NewTermListener() {
            @Override
            public void didUpdate(ArrayList<Concept> conceptArrayList) {
                updateResult(conceptArrayList);
            }
        });

        viewModule.getTermModel().setErrorListener(new ErrorListener() {
            @Override
            public void notifyError(Exception exception) {
                String msgToPrint = exception.getMessage();
                showErrorMessage(msgToPrint);
            }
        });
    }

    private void updateResult(ArrayList<Concept> conceptArrayList){
        String textToPrint = "";
        for (Concept concept: conceptArrayList){
            textToPrint += "<b>"+concept.getSource().toString()+"</b>";
            textToPrint += "<br>";
            textToPrint += concept.getMeaning();
            textToPrint += "<br><br>";
        }
        final String textToPrintFinal = textToPrint;
        resultField.post(new Runnable() {
            @Override
            public void run() {
                resultField.setText(Html.fromHtml(textToPrintFinal));
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void showErrorMessage(final String message){
        resultField.post(new Runnable() {
            @Override
            public void run() {
                resultField.setText("");
                progressBar.setVisibility(View.GONE);
                showPopUp(message);
            }
        });
    }

    private void showPopUp(String message) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(getApplicationContext(), message, duration);
        toast.show();
    }
}
