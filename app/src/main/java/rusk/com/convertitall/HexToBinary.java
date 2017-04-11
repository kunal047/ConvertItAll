package rusk.com.convertitall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class HexToBinary extends AppCompatActivity {

    private static final String TAG = "";
    public static int win_rate_hex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_cell);
        Log.d(TAG, "onCreate: of Hex to binary called &&&&&&&&&&&&&&&&&&&&&&&&&&");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            win_rate_hex = bundle.getInt("win_it");
        }

        Fragment fragmentCellsHex = new FragmentCellsHex();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragmentCellsHex, fragmentCellsHex.getClass().getSimpleName()).addToBackStack(null).commit();

    }
}
