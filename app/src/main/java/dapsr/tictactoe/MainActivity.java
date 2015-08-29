package dapsr.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

    private int[][] GridID = new int[4][4]; // i.e. [0 to 3][0 to 3]

    private enum GridCode {
        BLANK, X, O
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button;
        int id;
        for (int r = 1; r <= 3; r++) {
            for (int c = 1; c <= 3; c++) {
                id = getResources().getIdentifier("btnGrid" + r + c, "id",
                        getPackageName());
                GridID[r][c] = id;
                button = (Button) findViewById(id);
                button.setText(""); // seems to be needed
                button.setOnClickListener(this);
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
        Button button = (Button) findViewById(R.id.btnReset);
        button.setText("");
        for (int r = 1; r <= 3; r++) {
            for (int c = 1; c <= 3; c++) {
                button = (Button) findViewById(GridID[r][c]);
                button.setText("");
            }
        }

    }

    public void onClick(View v) {
        Button button = (Button) v;

        if (button == (Button) findViewById(R.id.btnReset)) {
            if (gameHasEnded())
                resetGrid();
            return;
        }

        if (gameHasEnded() || button.getText() != "")
            return;

        button.setText("X");

        int xCountByRow[] = new int[4];
        int oCountByRow[] = new int[4];
        int xCountByColumn[] = new int[4];
        int oCountByColumn[] = new int[4];
        int xCountByDiagonal[] = new int[3];
        int oCountByDiagonal[] = new int[3];

        GridCode gc[][] = new GridCode[4][4];

        for (int r = 1; r <= 3; r++) {
            for (int c = 1; c <= 3; c++) {
                button = (Button) findViewById(GridID[r][c]);
                if (button.getText() == "X") {
                    gc[r][c] = GridCode.X;
                    xCountByRow[r]++;
                    xCountByColumn[c]++;
                    if (r == c)
                        xCountByDiagonal[1]++;
                    if (r + c == 4)
                        xCountByDiagonal[2]++;
                } else if (button.getText() == "O") {
                    gc[r][c] = GridCode.O;
                    oCountByRow[r]++;
                    oCountByColumn[c]++;
                    if (r == c)
                        oCountByDiagonal[1]++;
                    if (r + c == 4)
                        oCountByDiagonal[2]++;
                } else {
                    gc[r][c] = GridCode.BLANK;
                }
            }
        }

        // Have we lost?
        for (int r = 1; r <= 3; r++) {
            if (xCountByRow[r] == 3) {
                declareLoss();
                return;
            }
        }
        for (int c = 1; c <= 3; c++) {
            if (xCountByColumn[c] == 3) {
                declareLoss();
                return;
            }
        }
        for (int d = 1; d <= 2; d++) {
            if (xCountByDiagonal[d] == 3) {
                declareLoss();
                return;
            }
        }

        // Can we win?
        for (int r = 1; r <= 3; r++) {
            if (oCountByRow[r] == 2 && xCountByRow[r] == 0) {
                for (int c = 1; c <= 3; c++) {
                    if (gc[r][c] == GridCode.BLANK) {
                        button = (Button) findViewById(GridID[r][c]);
                        button.setText("O");
                        declareWin();
                        return;
                    }
                }
            }
        }
        for (int c = 1; c <= 3; c++) {
            if (oCountByColumn[c] == 2 && xCountByColumn[c] == 0) {
                for (int r = 1; r <= 3; r++) {
                    if (gc[r][c] == GridCode.BLANK) {
                        button = (Button) findViewById(GridID[r][c]);
                        button.setText("O");
                        declareWin();
                        return;
                    }
                }
            }
        }
        for (int d = 1; d <= 2; d++) {
            if (oCountByDiagonal[d] == 2 && xCountByDiagonal[d] == 0) {
                for (int r = 1; r <= 3; r++) {
                    int c = (d == 1) ? r : 4 - r;
                    if (gc[r][c] == GridCode.BLANK) {
                        button = (Button) findViewById(GridID[r][c]);
                        button.setText("O");
                        declareWin();
                        return;
                    }
                }
            }
        }


        // TODO:
        // Can we create a double threat?
        // Do we need to prevent a double threat?

        // Move randomly
        Button buttons[] = new Button[9];
        int buttonCount = 0;
        for (int r = 1; r <= 3; r++) {
            for (int c = 1; c <= 3; c++) {
                if (gc[r][c] == GridCode.BLANK) {
                    buttonCount++;
                    buttons[buttonCount] = (Button) findViewById(GridID[r][c]);
                }
            }
        }
        if (buttonCount == 0) {
            declareDraw();
            return;
        }

        Random random = new Random();
        Button randomButton = buttons[random.nextInt(buttonCount) + 1];
        randomButton.setText("O");
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
}