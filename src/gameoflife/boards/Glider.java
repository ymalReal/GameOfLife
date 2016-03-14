package gameoflife.boards;

public class Glider
        extends WrapAroundBoard{

    public Glider(int size){
        super(size);
        int c=size/2;
        board[c-1][c+1] = true;
        board[c+0][c-1] = true;
        board[c+0][c+1] = true;
        board[c+1][c+0] = true;
        board[c+1][c+1] = true;
    }
    
}