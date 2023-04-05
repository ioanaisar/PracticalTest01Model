package ro.pub.cs.systems.eim.practicaltest01model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    private TextView numberFinal;

    private Button buttonOk;
    private Button buttonCancel;

    // listener
    private  OKButtonClickListener okButtonClickListener =  new  OKButtonClickListener();
    private class  OKButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            setResult(RESULT_OK, null);
            finish();
        }
    }

    private  CancelButtonClickListener CancelButtonClickListener =  new  CancelButtonClickListener();
    private class  CancelButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            setResult(RESULT_CANCELED, null);
            finish();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        numberFinal= (TextView) findViewById(R.id.valueNr);

        if(savedInstanceState==null){
            Bundle extras = getIntent().getExtras();
            if(extras==null) {
                numberFinal.setText("0");
            }else{
                Integer right = Integer.parseInt(extras.getString(Constants.RIGHT_TEXT));
                Integer left = Integer.parseInt(extras.getString(Constants.LEFT_TEXT));
                numberFinal.setText(String.valueOf(left+right));
                //numberFinal.setText(extras.getString(Constants.LEFT_TEXT) + " " + extras.getString(Constants.RIGHT_TEXT));
            }


        }else{
            numberFinal.setText(savedInstanceState.getString("numberFinal"));
        }

        // caut buton si setez listener pt el
        buttonOk = (Button)findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(okButtonClickListener);

        buttonCancel = (Button)findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(CancelButtonClickListener);


       // numberFinal.setText(getIntent().getStringExtra(Constants.LEFT_TEXT) + " " + getIntent().getStringExtra(Constants.RIGHT_TEXT));
    }


}