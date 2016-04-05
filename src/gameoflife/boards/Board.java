package gameoflife.boards;

import gameoflife.exceptions.NotOnBoardException;

import java.io.Serializable;

/**
 * GameOfLife
 * Created by willne763
 * on 8/3/16.
 */
public abstract class Board
        implements Serializable{

    protected boolean[][] board;
    protected int iterations;
    protected int size;

    public int getIterations(){
        return iterations;
    }

    public void setIterations(int setTo){
        iterations = setTo;
    }

    public void setPos(int x, int y, boolean setTo){
        try{
            board[x][y] = setTo;
        } catch (ArrayIndexOutOfBoundsException e){
            throw new NotOnBoardException(e.getMessage());
        }
    }

    public boolean getPos(int x, int y){
        if(x == -1)
            x = board.length - 1;
        else if(x == board.length)
            x = 0;
        else if(x < -1 || x > board.length)
            throw new NotOnBoardException("X was " + Integer.toString(x));

        if(y == -1)
            y = board.length - 1;
        else if(y == board.length)
            y = 0;
        else if(y < -1 || y > board.length)
            throw new NotOnBoardException("Y was " + Integer.toString(y));

        return board[x][y];
    }

    public int checkRound(int x, int y){
        int count = 0;

        for(int xC = -1; xC <= 1; xC++)
            for(int yC = -1; yC <= 1; yC++)
                if(getPos(x + xC, y + yC))
                    count++;

        if(getPos(x, y))
            count--;

        return count;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    public void iterate(){
        boolean[][] newBoard = new boolean[size][size];

        for(int x = 0; x<size; x++){
            System.arraycopy(board[x], 0, newBoard[x], 0, size);
        }

        for(int x = 0; x < size; x++)
            for(int y = 0; y < size; y++){
                int amount = checkRound(x, y);
                if(amount == 3)
                    newBoard[x][y] = true;
                else if(amount != 2) {
                    newBoard[x][y] = false;
                } else {
                    newBoard[x][y] = getPos(x, y);
                }

            }
        iterations++;
        board = newBoard;
    }
}
