package rusk.com.convertitall;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;


public class OctalToBinary extends AppCompatActivity {

    private static final String TAG = "" ;
    public GridView gridView;
    ArrayList<String> data = new ArrayList<>();
    public static int win_rate = 0;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cell);
        Log.d(TAG, "onCreate: of octal to binary called &&&&&&&&&&&&&&&&&&&&&&&&&&");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            win_rate = bundle.getInt("win_it");
        }

       Fragment fragmentCells = new FragmentCells();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragmentCells, fragmentCells.getClass().getSimpleName()).addToBackStack(null).commit();

    }




}
