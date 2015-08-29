package dapsr.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private int[][] GridID = new int[3][3]; // i.e. [0 to 3][0 to 3]
    private char[][] board = new char[4][4];
    public int[] random  = {1,2,3,4,5,6,7,8,9};
    public int[] randomId = {R.id.btnGrid11,R.id.btnGrid12,R.id.btnGrid13,R.id.btnGrid21,R.id.btnGrid22,R.id.btnGrid23,R.id.btnGrid31,R.id.btnGrid32,R.id.btnGrid33};

    boolean userclick = true , computerclick =  false;
    Button button, button1,button2,button3,button4,button5,button6,button7,button8,button9;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = (Button)findViewById(R.id.btnGrid11);
        button2 = (Button)findViewById(R.id.btnGrid12);
        button3 = (Button)findViewById(R.id.btnGrid13);
        button4 = (Button)findViewById(R.id.btnGrid21);
        button5 = (Button)findViewById(R.id.btnGrid22);
        button6 = (Button)findViewById(R.id.btnGrid23);
        button7 = (Button)findViewById(R.id.btnGrid31);
        button8 = (Button)findViewById(R.id.btnGrid32);
        button9 = (Button)findViewById(R.id.btnGrid33);

        button1.setText("");
        button1.setOnClickListener(this);
        button2.setText("");
        button2.setOnClickListener(this);
        button3.setText("");
        button3.setOnClickListener(this);
        button4.setText("");
        button4.setOnClickListener(this);
        button5.setText("");
        button5.setOnClickListener(this);
        button6.setText("");
        button6.setOnClickListener(this);
        button7.setText("");
        button7.setOnClickListener(this);
        button8.setText("");
        button8.setOnClickListener(this);
        button9.setText("");
        button9.setOnClickListener(this);


        for (int r = 1; r <= 3; r++) {
            for (int c = 1; c <= 3; c++) {
                board[r][c] = 'N';
            }
        }

        button = (Button) findViewById(R.id.btnReset);
        button.setText("");
        button.setOnClickListener(this);
    }



    private boolean gameHasEnded() {
        Button button = (Button) findViewById(R.id.btnReset);
        return button.getText() != "";
    }

    private void resetGrid() {

        button.setText("");

        for (int r = 1; r <= 3; r++) {
            for (int c = 1; c <= 3; c++) {
                button1.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                button8.setText("");
                button9.setText("");
            }
        }

    }


    public void randomclick(){

    }

    public void onClick(View v) {




        switch (v.getId()){

            case R.id.btnGrid11:

                    if(userclick)
                    {
                        button1.setText("X");
                        button1.setEnabled(false);
                        board[1][1] = 'X';
                        userclick = false;
                        button2.performClick();
                        onClick(v);
                    }else
                    {
                        button1.setText("O");
                        button1.setEnabled(false);
                        board[1][1] = 'O';
                        userclick = true;
                    }




                break;
            case R.id.btnGrid12:

                break;
            case R.id.btnGrid13:

                break;
            case R.id.btnGrid21:

                break;
            case R.id.btnGrid22:

                break;
            case R.id.btnGrid23:

                break;

            case R.id.btnGrid31:

                break;
            case R.id.btnGrid32:

                break;
            case R.id.btnGrid33:

                break;

        }



        // TODO:
        // Can we create a double threat?
        // Do we need to prevent a double threat?

        // Move randomly
//        Button buttons[] = new Button[9];
//        int buttonCount = 0;
//        for (int r = 1; r <= 3; r++) {
//            for (int c = 1; c <= 3; c++) {
//                if (board[r][c] == GridCode.BLANK) {
//                    buttonCount++;
//                    buttons[buttonCount] = (Button) findViewById(GridID[r][c]);
//                }
//            }
//        }
//        if (buttonCount == 0) {
//            declareDraw();
//            return;
//        }
//        Random random = new Random();
//        Button randomButton = buttons[random.nextInt(buttonCount) + 1];
//        randomButton.setText("O");
    }

    private void declareSomething(String something) {
        Button button = (Button) findViewById(R.id.btnReset);
        button.setText(something + "! \n(click to reset)");
    }

    private void declareLoss() {
        declareSomething("Congratulations! You win");
    }

    private void declareWin() {
        declareSomething("You Lose!Computer Win");
    }

    private void declareDraw() {
        declareSomething("Draw");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
