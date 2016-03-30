package alan.kai.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button one;
    Button two, three; //Row 1
    Button four, five, six; //Row 2
    Button seven, eight, nine; //Row 3
    Button reset;
    TicTac ticTac;
    CommHandler commHandler;
    Boolean turn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = (Button)findViewById(R.id.one);
        one.setOnClickListener(this);
        two = (Button)findViewById(R.id.two);
        two.setOnClickListener(this);
        three = (Button)findViewById(R.id.three);
        three.setOnClickListener(this);
        four = (Button)findViewById(R.id.four);
        four.setOnClickListener(this);
        five = (Button)findViewById(R.id.five);
        five.setOnClickListener(this);
        six = (Button)findViewById(R.id.six);
        six.setOnClickListener(this);
        seven = (Button)findViewById(R.id.seven);
        seven.setOnClickListener(this);
        eight = (Button)findViewById(R.id.eight);
        eight.setOnClickListener(this);
        nine = (Button)findViewById(R.id.nine);
        nine.setOnClickListener(this);
        reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(this);
        ticTac = new TicTac();
        commHandler = new CommHandler(this);
        turn = true;
    }
    @Override
    public void onClick(View view) {
        int num = 0;
        if(turn == true) {
            if (view.getId() == one.getId()) {
                if (ticTac.setBoard(0, 0, 'X')) {
                    one.setText("X");
                    num = 1;
                } else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == two.getId()) {
                if (ticTac.setBoard(1, 0, 'X')) {
                    two.setText("X");
                    num = 2;
                } else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == three.getId()) {
                if (ticTac.setBoard(2, 0, 'X')) {
                    three.setText("X");
                    num = 3;
                } else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == four.getId()) {
                if (ticTac.setBoard(0, 1, 'X')) {
                    four.setText("X");
                    num = 4;
                } else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == five.getId()) {
                if (ticTac.setBoard(1, 1, 'X')) {
                    five.setText("X");
                    num = 5;
                } else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == six.getId()) {
                if (ticTac.setBoard(2, 1, 'X')) {
                    six.setText("X");
                    num = 6;
                } else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == seven.getId()) {
                if (ticTac.setBoard(0, 2, 'X')) {
                    seven.setText("X");
                    num = 7;
                } else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == eight.getId()) {
                if (ticTac.setBoard(1, 2, 'X')) {
                    eight.setText("X");
                    num = 8;
                } else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
            } else if (view.getId() == nine.getId()) {
                if (ticTac.setBoard(2, 2, 'X')) {
                    nine.setText("X");
                    num = 9;
                } else
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
            }

            check();
            turn = false;
        }
        else if (view.getId() == reset.getId()) {
            resetBoard();
            num = 10;
        }
        else if (!turn){
            Toast.makeText(getApplicationContext(), "Not your turn!", Toast.LENGTH_SHORT).show();
        }

        updateBoard(num);
    }
    public void check() {
        if (ticTac.checkForWin()) {
            Toast.makeText(getApplicationContext(), "Winner!", Toast.LENGTH_SHORT).show();
            //resetBoard();
        }
        else if(ticTac.isBoardFull()) {
            updateBoard(13);
            Toast.makeText(getApplicationContext(), "Draw!", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(getApplicationContext(), "IT GETS HERE", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putCharArray("ARRAY", ticTac.get1D());
        outState.putString("ONE", (String) one.getText());
        outState.putString("TWO", (String) two.getText());
        outState.putString("THREE", (String) three.getText());
        outState.putString("FOUR", (String) four.getText());
        outState.putString("FIVE", (String) five.getText());
        outState.putString("SIX", (String) six.getText());
        outState.putString("SEVEN", (String) seven.getText());
        outState.putString("EIGHT", (String) eight.getText());
        outState.putString("NINE", (String) nine.getText());
        outState.putBoolean("TURN", turn);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        ticTac = new TicTac();
        ticTac.resetBoard();
        char[] saved = savedInstanceState.getCharArray("ARRAY");
        ticTac.restore(saved);
        one.setText(savedInstanceState.getString("ONE"));
        two.setText(savedInstanceState.getString("TWO"));
        three.setText(savedInstanceState.getString("THREE"));
        four.setText(savedInstanceState.getString("FOUR"));
        five.setText(savedInstanceState.getString("FIVE"));
        six.setText(savedInstanceState.getString("SIX"));
        seven.setText(savedInstanceState.getString("SEVEN"));
        eight.setText(savedInstanceState.getString("EIGHT"));
        nine.setText(savedInstanceState.getString("NINE"));
        turn = savedInstanceState.getBoolean("TURN");
        super.onRestoreInstanceState(savedInstanceState);
    }
    public void resetBoard() {
        ticTac.resetBoard();
        turn = true;
        one.setText("");
        two.setText("");
        three.setText("");
        four.setText("");
        five.setText("");
        six.setText("");
        seven.setText("");
        eight.setText("");
        nine.setText("");
    }
    public void watchClick(int buttonId)  {
        int num = 100;
        if(turn == false) {
            if (buttonId == 1) {
                if (ticTac.setBoard(0, 0, 'O')) {
                    one.setText("O");
                    num = 0;
                } else {
                    num = -1;
                }
            } else if (buttonId == 2) {
                if (ticTac.setBoard(1, 0, 'O')) {
                    two.setText("O");
                    num = 0;
                } else {
                    num = -1;
                }
            } else if (buttonId == 3) {
                if (ticTac.setBoard(2, 0, 'O')) {
                    three.setText("O");
                    num = 0;
                } else {
                    num = -1;
                }
            } else if (buttonId == 4) {
                if (ticTac.setBoard(0, 1, 'O')) {
                    four.setText("O");
                    num = 0;
                } else {
                    num = -1;
                }
            } else if (buttonId == 5) {
                if (ticTac.setBoard(1, 1, 'O')) {
                    five.setText("O");
                    num = 0;
                } else {
                    num = -1;
                }
            } else if (buttonId == 6) {
                if (ticTac.setBoard(2, 1, 'O')) {
                    six.setText("O");
                    num = 0;
                } else {
                    num = -1;
                }
            } else if (buttonId == 7) {
                if (ticTac.setBoard(0, 2, 'O')) {
                    seven.setText("O");
                    num = 0;
                } else {
                    num = -1;
                }
            } else if (buttonId == 8) {
                if (ticTac.setBoard(1, 2, 'O')) {
                    eight.setText("O");
                    num = 0;
                } else {
                    num = -1;
                }
            } else if (buttonId == 9) {
                if (ticTac.setBoard(2, 2, 'O')) {
                    nine.setText("O");
                    num = 0;
                } else {
                    num = -1;
                }
            }

            turn = true;
            check();
        }
        else if(turn == true){
            num = -2; //Out of turn
        }
        else if (buttonId == -1) {
            resetBoard();
            num = 10;
        }
        updateBoard(num);

    }
    public void updateBoard(int num) {
        commHandler.sendNumber(num);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isFinishing()){
            resetBoard();
            updateBoard(10); //Send signal to Watch
        }
    }
}
