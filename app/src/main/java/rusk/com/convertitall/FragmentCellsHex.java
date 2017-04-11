package rusk.com.convertitall;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static rusk.com.convertitall.HexToBinary.win_rate_hex;

/**
 * Created by kunal on 08-03-2017.
 */

public class FragmentCellsHex extends Fragment {

    private static final String TAG = "";

    public static ArrayList<String> hexNums; // column answer binary
    public static ArrayList<String> colNumsHex; // column answer
    public static ArrayList<String> finalNumsHex;  // row answer
    public static ArrayList<String> binaryNumsHex; // row answer binary

    public static TextView[][] textViewArray = new TextView[9][9];

    public Bundle bundle = null;
    static int win;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        bundle = this.getArguments();
        return inflater.inflate(R.layout.hex_to_binary, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        setContentView(R.layout.octal_to_binary);

        Log.d(TAG, "onActivityCreated: started @@@@@@@@@ ");

        final ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);

        new CountDownTimer(20000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
//                Log.d(TAG, "onTick: starts");
                //this will be done every 1000 milliseconds ( 1 seconds )
                long timePassed = (20000 - millisUntilFinished) / 1000;

                int progress = (int) ((timePassed / 20.0) * 100);
                progressBar.setProgress(progressBar.getMax() - progress);
            }

            @Override
            public void onFinish() {
                //the progressBar will be invisible after 60 000 miliseconds ( 1 minute)

                progressBar.setProgress(0);
//                Intent intent = getContext();
//                Intent intent = getIntent();
                if (win_rate_hex != 0) {
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(getActivity(), "Game Over !\nYou have scored " + win_rate_hex + " points. ");
                } else {
                    ViewDialog alert = new ViewDialog();
                    win_rate_hex = 0;
                    alert.showDialog(getActivity(), "Game Over !");
                }
            }
        }.start();

        hexNums = new ArrayList<>();
        binaryNumsHex = new ArrayList<>();
        finalNumsHex = new ArrayList<>();
        colNumsHex = new ArrayList<>();

        for (int i = 0; i < 6; i++) {

            Random rand = new Random(System.nanoTime());
            int n = rand.nextInt(254) + 1;
//            int n = (int)(Math.random() * 63 + 1);
//            System.out.println("random dec num is " + n);
            String h = Integer.toHexString(n);
            System.out.println("hex num is " + h );
            int o = Integer.parseInt(h, 16);

            colNumsHex.add(Integer.toString(o));
            String s = String.format("%8s", Integer.toBinaryString(n)).replace(' ', '0');
            binaryNumsHex.add(s);
        }


        String r;
        for (int i = 0; i < 6; i++) {
            r = "";
            for (int j = 0; j < 6; j++) {
                char[] n = binaryNumsHex.get(j).toCharArray();
                r += n[i];
            }
            hexNums.add(r);
        }

        for (int i = 0; i < 6; i++) {
//            int o = Integer.parseInt(Integer.toOctalString(Integer.parseInt(octalNums.get(i))));
            int d = Integer.parseInt(hexNums.get(i), 2);
            int o = Integer.parseInt(Integer.toOctalString(d));
            finalNumsHex.add(Integer.toString(o));
        }

        TableLayout tableLayout = (TableLayout) getView().findViewById(R.id.table_layout);
        TableRow tableRow[] = new TableRow[7];

        for (int i = 0; i < 9; i++) {
//            tableRow[i] = new TableRow(this);
            tableRow[i] = new TableRow(getActivity());
            tableRow[i].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow[i].setGravity(Gravity.CENTER);
            tableRow[i].setWeightSum(9); //total row weight

        }
        TableRow.LayoutParams params = new TableRow.LayoutParams(100, 100);
        params.setMargins(10, 10, 10, 10);

        int textViewCount = 9;


        GradientDrawable gd = new GradientDrawable();
        gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, 0xFF000000);
        TextClick click = new TextClick(getActivity());
//        TextClick click = new TextClick(OctalToBinary.this);
        Log.d(TAG, "onActivityCreated: giving value to textviews *****************************");
        for(int i = 0; i < textViewCount; i++) {
            for (int j = 0; j < textViewCount; j++){
                textViewArray[i][j] = new TextView(getActivity());
                if (j == 8 && i == 8) textViewArray[i][j].setText("~");
                else if (j == 8) {
                    textViewArray[i][j].setText(colNumsHex.get(i));
                } else if (i == 8) {
                    textViewArray[i][j].setText(finalNumsHex.get(j));
                } else {
                    textViewArray[i][j].setText("0");
                }
                textViewArray[i][j].setGravity(Gravity.CENTER);
                textViewArray[i][j].setTextSize(20);
                textViewArray[i][j].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                textViewArray[i][j].setPadding(15, 15, 15, 15);
                textViewArray[i][j].setBackground(gd);


            }
        }
        for (int i = 0; i < textViewCount; i++) {
            for (int j = 0; j < textViewCount; j++) {
                tableRow[i].addView(textViewArray[i][j], params);
            }
            tableLayout.addView(tableRow[i]);

        }
        for (int i = 0; i < textViewCount; i++)
            for (int j = 0; j < textViewCount; j++)
                textViewArray[i][j].setOnClickListener(click);

    }
}

