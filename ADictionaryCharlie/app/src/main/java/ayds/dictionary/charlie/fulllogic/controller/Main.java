package ayds.dictionary.charlie.fulllogic.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Main extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UserControllerModule.getInstance().startApplication();
    }
}
