package ayds.dictionary.charlie.fulllogic.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ayds.dictionary.charlie.R;
import ayds.dictionary.charlie.fulllogic.model.UserModelListener;

public class MainActivity extends AppCompatActivity{

    private EditText editText;
    private Button goButton;
    private TextView textView;
    private UserViewModule userViewModule;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        goButton = findViewById(R.id.goButton);
        textView = findViewById(R.id.textView);

        userViewModule = UserViewModule.getInstance();
        userViewModule.startApplication(getApplicationContext());

        initListeners();
    }

    private void initListeners() {
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String palabraBuscada = editText.getText().toString();
                        userViewModule.getEditUserController().onEventUpdate(palabraBuscada);
                    }
                }).start();
            }
        });

        userViewModule.getUserModel().setListener(new UserModelListener() {
            @Override
            public void didUpdate() {
                //userViewModule.getEditUserView().updateResult();
                updateResult();
            }
        });
    }

    private void updateResult(){
        final String result = userViewModule.getUserModel().lastSearch();
        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(Html.fromHtml(result));
            }
        });
    }
}
