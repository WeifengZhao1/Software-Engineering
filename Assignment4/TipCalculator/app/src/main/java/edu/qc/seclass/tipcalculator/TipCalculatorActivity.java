package edu.qc.seclass.tipcalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class TipCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = getResources().getString(R.string.app_name);
        Log.i("click",title);
    }


    public void CalTips(View view) {
        Log.i("click", "Onclick");
        TextView checkamount = findViewById(R.id.checkAmountValue);
        TextView partysize =  findViewById(R.id.partySizeValue);

        if(checkamount.getText().toString().length() == 0 || partysize.getText().toString().length() == 0){
            Toast.makeText(getApplicationContext(),"Empty or incorrect value(s)!", Toast.LENGTH_SHORT).show();
        }
        else{
            int CheckAmount = Integer.parseInt(checkamount.getText().toString());
            int PartySize = Integer.parseInt(partysize.getText().toString());
            int Average = CheckAmount / PartySize;

            // Create TextViews objects
            TextView Tip15 = findViewById(R.id.fifteenPercentTipValue);
            TextView Tip20 = findViewById(R.id.twentyPercentTipValue);
            TextView Tip25 = findViewById(R.id.twentyfivePercentTipValue);

            TextView Total15 = findViewById(R.id.fifteenPercentTotalValue);
            TextView Total20 = findViewById(R.id.twentyPercentTotalValue);
            TextView Total25 = findViewById(R.id.twentyfivePercentTotalValue);

            /*
               Find the Tip amount for 15%,20%,25%
               Put the Tip amount in their corresponding TextViews objects
             */
            Tip15.setText(String.valueOf(Tip(Average ,0.15)));
            Tip20.setText(String.valueOf(Tip(Average, 0.20)));
            Tip25.setText(String.valueOf(Tip(Average,0.25)));

            /*
             Find the Total amount of each person needs to pay
             Put the Total amount in their corresponding TextViews objects
             */
            Total15.setText(String.valueOf(Total(Average, 0.15)));
            Total20.setText(String.valueOf(Total(Average, 0.20)));
            Total25.setText(String.valueOf(Total(Average, 0.25)));
        }
    }

    private int Tip(int average, double percent) {
        int Tips = (int) Math.round(average * percent);
        return Tips;
    }

    private int Total(int average, double percent) {
        double Tips = (average * percent);
        int CheckTotal = (int) Math.round(average + Tips);
        return  CheckTotal;
    }

}