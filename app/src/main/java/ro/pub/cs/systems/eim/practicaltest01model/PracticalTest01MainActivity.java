package ro.pub.cs.systems.eim.practicaltest01model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.IntentFilter;

public class PracticalTest01MainActivity extends AppCompatActivity {

    Object serviceStatus = Constants.SERVICE_STOPPED;
    private TextView number1;
    private TextView number2;
    private Button pressMe;
    private Button pressMe2;
    private Button secondActivity;

    Integer leftClicks = 0;
    Integer rightClicks = 0;

    BroadcastReceiver broadcastReceiver = new PracticalTest01BroadcastReceiver();

    private IntentFilter intentFilter = new IntentFilter();

    private class PracticalTest01BroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_EXTRA, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }


    private CallButtonClickListener1 callButtonListener1 = new CallButtonClickListener1();

    private class CallButtonClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            leftClicks++;
            myStartService();
            number1.setText(String.valueOf(leftClicks));

        }
    }


    private CallButtonClickListener2 callButtonListener2 = new CallButtonClickListener2();

    private class CallButtonClickListener2 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            rightClicks++;
            myStartService();
            number2.setText(String.valueOf(rightClicks));
        }
    }


    // porneste serviciu
    public void myStartService() {
        if(leftClicks+rightClicks>5 &&  serviceStatus == Constants.SERVICE_STOPPED){
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Service.class);
            intent.putExtra(Constants.LEFT_TEXT, number1.getText().toString());
            intent.putExtra(Constants.RIGHT_TEXT, number2.getText().toString());
            getApplicationContext().startService(intent);
            serviceStatus = Constants.SERVICE_STARTED;
        }

    }


    private CallButtonClickListenerSecond callButtonListenerSecond = new CallButtonClickListenerSecond();

    private class CallButtonClickListenerSecond implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
            intent.putExtra(Constants.LEFT_TEXT, number1.getText().toString());
            intent.putExtra(Constants.RIGHT_TEXT, number2.getText().toString());
            // astept rezultat
            startActivityForResult(intent, Constants.RequestCode);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // pereche key- value
        savedInstanceState.putString(Constants.LEFT_TEXT, number1.getText().toString());
        savedInstanceState.putString(Constants.RIGHT_TEXT, number2.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.LEFT_TEXT)) {
            // iau text salvat si il pun inapoi
            leftClicks = Integer.valueOf(savedInstanceState.getString(Constants.LEFT_TEXT));
            number1.setText(String.valueOf(leftClicks));
            //  TextView left = (TextView) findViewById(R.id.nr1);
            //  left.setText(savedInstanceState.getString(Constants.LEFT_TEXT));
        }
        if (savedInstanceState.containsKey(Constants.RIGHT_TEXT)) {
            rightClicks = Integer.valueOf(savedInstanceState.getString(Constants.RIGHT_TEXT));
            number2.setText(String.valueOf(rightClicks));
            // iau text salvat si il pun inapoi
            //   TextView right = (TextView) findViewById(R.id.nr1);
            //  right.setText(savedInstanceState.getString(Constants.RIGHT_TEXT));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);


        number1 = (TextView) findViewById(R.id.nr1);
        number2 = (TextView) findViewById(R.id.nr2);

        // caut buton si setez listener pt el
        pressMe = (Button) findViewById(R.id.button1);
        pressMe.setOnClickListener(callButtonListener1);

        // caut buton si setez listener pt el
        pressMe2 = (Button) findViewById(R.id.button2);
        pressMe2.setOnClickListener(callButtonListener2);

        // caut buton si setez listener pt el
        secondActivity = (Button) findViewById(R.id.navigate_second_activity);
        secondActivity.setOnClickListener(callButtonListenerSecond);

        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.RequestCode)
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "The activity returned with result OK", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "The activity returned with result CANCEL", Toast.LENGTH_LONG).show();
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Service.class);
        getApplicationContext().stopService(intent);
    }

    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }
}