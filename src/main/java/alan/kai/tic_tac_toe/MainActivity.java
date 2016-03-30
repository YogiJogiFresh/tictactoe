package alan.kai.tic_tac_toe;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends WearableActivity implements View.OnClickListener {

    private BoxInsetLayout mContainerView;

    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    CommHandler commHandler;
    Boolean valid = false;
    int prev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();
        mContainerView = (BoxInsetLayout) findViewById(R.id.container);

        one = (Button) findViewById(R.id.one);
        one.setOnClickListener(this);
        two = (Button) findViewById(R.id.two);
        two.setOnClickListener(this);
        three = (Button) findViewById(R.id.three);
        three.setOnClickListener(this);
        four = (Button) findViewById(R.id.four);
        four.setOnClickListener(this);
        five = (Button) findViewById(R.id.five);
        five.setOnClickListener(this);
        six = (Button) findViewById(R.id.six);
        six.setOnClickListener(this);
        seven = (Button) findViewById(R.id.seven);
        seven.setOnClickListener(this);
        eight = (Button) findViewById(R.id.eight);
        eight.setOnClickListener(this);
        nine = (Button) findViewById(R.id.nine);
        nine.setOnClickListener(this);

        commHandler = new CommHandler(this);
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
        } else {
            mContainerView.setBackground(null);

        }
    }

    public void updateBoard(int in) {
        valid = false;
        if (in == -1) {
            Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
        }
        else if (in == -2) {
            //Toast.makeText(getApplicationContext(), "Not your turn!", Toast.LENGTH_SHORT).show();
        }else if (in == 1) {
            one.setText("X");
        } else if (in == 2) {
            two.setText("X");
        } else if (in == 3) {
            three.setText("X");
        } else if (in == 4) {
            four.setText("X");
        } else if (in == 5) {
            five.setText("X");
        } else if (in == 6) {
            six.setText("X");
        } else if (in == 7) {
            seven.setText("X");
        } else if (in == 8) {
            eight.setText("X");
        } else if (in == 9) {
            nine.setText("X");
        } else if (in == 10) {
            prev = 0;
            one.setText("");
            two.setText("");
            three.setText("");
            four.setText("");
            five.setText("");
            six.setText("");
            seven.setText("");
            eight.setText("");
            nine.setText("");
        } else if (in == 0) {
            if (prev == 1) {
                one.setText("O");
            } else if (prev == 2) {
                two.setText("O");
            } else if (prev == 3) {
                three.setText("O");
            } else if (prev == 4) {
                four.setText("O");
            } else if (prev == 5) {
                five.setText("O");
            } else if (prev == 6) {
                six.setText("O");
            } else if (prev == 7) {
                seven.setText("O");
            } else if (prev == 8) {
                eight.setText("O");
            } else if (prev == 9) {
                nine.setText("O");
            }
        } else if (in == 12) {
            //Winner
        } else if (in == 13) {
            Toast.makeText(getApplicationContext(), "Draw!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == one.getId()) {
            prev = 1;
            commHandler.sendClickedButton(1, 0);

        } else if (v.getId() == two.getId()) {
            prev = 2;
            commHandler.sendClickedButton(2, 0);

        } else if (v.getId() == three.getId()) {
            prev = 3;
            commHandler.sendClickedButton(3, 0);

        } else if (v.getId() == four.getId()) {
            prev = 4;
            commHandler.sendClickedButton(4, 0);

        } else if (v.getId() == five.getId()) {
            prev = 5;
            commHandler.sendClickedButton(5, 0);

        } else if (v.getId() == six.getId()) {
            prev = 6;
            commHandler.sendClickedButton(6, 0);

        } else if (v.getId() == seven.getId()) {
            prev = 7;
            commHandler.sendClickedButton(7, 0);

        } else if (v.getId() == eight.getId()) {
            prev = 8;
            commHandler.sendClickedButton(8, 0);

        } else if (v.getId() == nine.getId()) {
            prev = 9;
            commHandler.sendClickedButton(9, 0);
        }
        valid = false;
    }

    @Override
    protected void onStop() {
        commHandler.sendClickedButton(-1, 0);
        super.onStop();
    }
}
