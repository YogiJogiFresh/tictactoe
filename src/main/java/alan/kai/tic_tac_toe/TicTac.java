package alan.kai.tic_tac_toe;

/**
 * Created by weiak_000 on 3/20/2016.
 */
public class TicTac {
    char board[][];
    char items[];
    public TicTac() {
        board = new char[3][3];
        items = new char[9];
        resetBoard();
    }
    public boolean setBoard(int x, int y, char in) {
        if (board[x][y] == ' ') {
            board[x][y] = in;
            arrayify();
            return true;
        }
        else {
            return false;
        }

    }
    public void arrayify() {
        int k = 0;
        for(int i = 0; i < 3; i ++) {
            for(int j = 0; j < 3; j++) {
                items[k] = board[i][j];
                k++;
            }
        }
    }
    public void restore(char[] in) {
        int k = 0;
        for(int i = 0; i < 3; i ++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = in[k];
                k++;
            }
        }
    }
    public char get2D(int x, int y) {
        return board[x][y];
    }
    public char[] get1D() {
        return items;
    }
    public void resetBoard() {
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                items[k] =' ';
                k++;
            }
        }
    }
    public boolean isBoardFull() {
        boolean isFull = true;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i][j] == ' ') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }
    public boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
                return true;
            }
        }
        return false;
    }
    public boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != ' ') && (c1 == c2) && (c2 == c3));
    }
    public boolean checkColumnsForWin() {
        for(int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
                return true;
            }
        }
        return false;
    }
    public boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
    }
}