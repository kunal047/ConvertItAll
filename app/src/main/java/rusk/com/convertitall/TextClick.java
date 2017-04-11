package rusk.com.convertitall;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kunal on 23-02-2017.
 */
public class TextClick extends AppCompatActivity implements View.OnClickListener{

    String red = "#ffff1e31";
    String blue = "#FF4F87FF";
    String yellow = "#FFFFF328";
    String orange = "#FFFF8334";
    String green = "FF34FF3A";
    String purple = "#FFAF58FF";

    Context c;
    static boolean result;
    static int win = 0;
    public TextClick(Context context) {
        this.c = context;
    }

    @Override
    public void onClick(View v) {
        TextView tv = (TextView) v;
        if (tv.getText() == "0") {
            tv.setText("1");
            tv.setBackgroundColor(Color.BLUE);
        }
        else if (tv.getText() == "1") {
            tv.setText("0");
            tv.setBackgroundColor(0xFF00FF00);
        }

        FragmentCells otb = new FragmentCells();

        for (int i = 0; i < 6; i++) {
            int count = 0;
            for (int j = 0; j < 6; j++) {
                char c[] = otb.binaryNums.get(i).toCharArray();
//                Log.d(TAG, "onClick: textviewarray is " + otb.textViewArray[i][j].getText().toString());
                if (c[j] == otb.textViewArray[i][j].getText().toString().charAt(0)){
                    count++;
                }
            }
            if (count == 6) {
//                int setColour = Color.parseColor(blue);
                otb.textViewArray[i][6].setBackgroundColor(Color.YELLOW);
            } else {
                otb.textViewArray[i][6].setBackgroundColor(0xFF00FF00);
            }
        }

        for (int i = 0; i < 6; i++) {
            int count = 0;
            for (int j = 0; j < 6; j++) {
                char c[] = otb.octalNums.get(i).toCharArray();
//                Log.d(TAG, "onClick: textviewarray is " + otb.textViewArray[i][j].getText().toString());
                if (c[j] == otb.textViewArray[j][i].getText().toString().charAt(0)){
                    count++;
                }
            }
            if (count == 6) {
//                int setColour = Color.parseColor(yellow);
                otb.textViewArray[6][i].setBackgroundColor(Color.YELLOW);
            } else {
                otb.textViewArray[6][i].setBackgroundColor(0xFF00FF00);
            }
        }
        result = checkResult();
        if (result){
            win++;
            Bundle args = new Bundle();
//            args.putInt("win_val", win);
            Fragment teamFragment = new FragmentCells();
            teamFragment.setArguments(args);
            Intent intent = new Intent(c, OctalToBinary.class);
            intent.putExtra("win_it", win);
            c.startActivity(intent);
        }
    }


    private boolean checkResult() {
        int c = 0;
        for (int i = 0; i < 6; i++) {
            FragmentCells otb = new FragmentCells();
//            ColorDrawable cdc = (ColorDrawable) otb.textViewArray[i][6].getBackground();
//            ColorDrawable cdr = (ColorDrawable) otb.textViewArray[6][i].getBackground();
//            int colorCodec = cdc.getColor();
//            int colorCoder = cdr.getColor();
            int colorcol = 0, colorrow = -1;
            Drawable colorc = otb.textViewArray[i][6].getBackground();
            if (colorc instanceof ColorDrawable) {
                colorcol = ((ColorDrawable) colorc).getColor();
//                Log.d(TAG, "checkResult: of coloridc is " + colorrow);
            }
            Drawable colorr = otb.textViewArray[i][6].getBackground();
            if (colorr instanceof ColorDrawable) {
                colorrow = ((ColorDrawable) colorr).getColor();
//                Log.d(TAG, "checkResult: of coloridr is " + colorrow);
            }
            if (colorrow == Color.YELLOW && colorcol == Color.YELLOW) {
//                Log.d(TAG, "checkResult:------------------------------------ in yellow part ");
                //go to the octal to binary activity
                c++;
            } else {
                return false;
            }
        }
        if (c == 6) {
            return true;
        } else {
            return false;
        }
    }

}
