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

import static rusk.com.convertitall.OctalToBinary.win_rate;

/**
 * Created by kunal on 24-02-2017.
 */

public class FragmentCells extends Fragment {

    private static final String TAG = "" ;

    public static ArrayList<String> octalNums; // column answer binary
    public static ArrayList<String> colNums; // column answer
    public static ArrayList<String> finalNums;  // row answer
    public static ArrayList<String> binaryNums; // row answer binary

    public static TextView[][] textViewArray = new TextView[7][7];

    public Bundle bundle = null;
    static int win;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        bundle = this.getArguments();
        return inflater.inflate(R.layout.octal_to_binary, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        setContentView(R.layout.octal_to_binary);

        Log.d(TAG, "onActivityCreated: started @@@@@@@@@ ");

        final ProgressBar progressBar = (ProgressBar)getView().findViewById(R.id.progressBar);

        new CountDownTimer(20000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
//                Log.d(TAG, "onTick: starts");
                //this will be done every 1000 milliseconds ( 1 seconds )
                long timePassed = (20000 - millisUntilFinished)/1000;

                int progress = (int)(( timePassed/20.0)*100);
                progressBar.setProgress(progressBar.getMax()-progress);
            }

            @Override
            public void onFinish() {
                //the progressBar will be invisible after 60 000 miliseconds ( 1 minute)

                progressBar.setProgress(0);
//                Intent intent = getContext();
//                Intent intent = getIntent();
                if (win_rate != 0){
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(getActivity(), "Game Over !\nYou have scored " + win_rate + " points. ");
                } else {
                    ViewDialog alert = new ViewDialog();
                    win_rate = 0;
                    alert.showDialog(getActivity(), "Game Over !");
                }
            }
        }.start();

        octalNums = new ArrayList<>();
        binaryNums = new ArrayList<>();
        finalNums = new ArrayList<>();
        colNums = new ArrayList<>();

        for (int i = 0; i < 6; i++) {

            Random rand = new Random(System.nanoTime());
            int n = rand.nextInt(63) + 1;
//            int n = (int)(Math.random() * 63 + 1);
//            System.out.println("random dec num is " + n);
            int o = Integer.parseInt(Integer.toOctalString(n));
//            System.out.println("octal num is " + o);
            colNums.add(Integer.toString(o));
            String s = String.format("%6s", Integer.toBinaryString(n)).replace(' ', '0');
            binaryNums.add(s);
        }
        System.out.println("Column octal is " + colNums);
        System.out.println("binary of octal is " + binaryNums);
        String r;
        for (int i = 0; i < 6; i++) {
            r = "";
            for (int j = 0; j < 6; j++) {
                char[] n = binaryNums.get(j).toCharArray();
                r += n[i];
            }
            octalNums.add(r);
        }
//        System.out.println("final outcome is " + octalNums);
        for (int i = 0; i < 6; i++) {
//            int o = Integer.parseInt(Integer.toOctalString(Integer.parseInt(octalNums.get(i))));
            int d = Integer.parseInt(octalNums.get(i), 2);
            int o = Integer.parseInt(Integer.toOctalString(d));
            finalNums.add(Integer.toString(o));
        }
//        System.out.println("last final is " + finalNums);

        TableLayout tableLayout = (TableLayout)getView().findViewById(R.id.table_layout);
        TableRow tableRow[] = new TableRow[7];

        for (int i = 0; i < 7; i++) {
//            tableRow[i] = new TableRow(this);
            tableRow[i] = new TableRow(getActivity());
            tableRow[i].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow[i].setGravity(Gravity.CENTER);
            tableRow[i].setWeightSum(7); //total row weight

        }

        TableRow.LayoutParams params = new TableRow.LayoutParams(100, 100);
        params.setMargins(10, 10, 10, 10);

        int textViewCount = 7;


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
                if (j == 6 && i == 6) textViewArray[i][j].setText("~");
                else if (j == 6) {
                    textViewArray[i][j].setText(colNums.get(i));
                } else if (i == 6) {
                    textViewArray[i][j].setText(finalNums.get(j));
                } else {
                    textViewArray[i][j].setText("0");
                }
                textViewArray[i][j].setGravity(Gravity.CENTER);
                textViewArray[i][j].setTextSize(20);
                textViewArray[i][j].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                textViewArray[i][j].setPadding(15, 15, 15, 15);
                textViewArray[i][j].setBackground(gd);


            }

/* Add Button to row. */
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

