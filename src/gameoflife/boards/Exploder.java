package gameoflife.boards;

public class Exploder
        extends WrapAroundBoard{
    
    public Exploder(int size){
        super(size);
        int c = size/2;
        board[c-2][c-2] = true;
        board[c-2][c-1] = true;
        board[c-2][c]   = true;
        board[c-2][c+1] = true;
        board[c-2][c+2] = true;
        board[c]  [c-2] = true;
        board[c]  [c+2] = true;
        board[c+2][c-2] = true;
        board[c+2][c-1] = true;
        board[c+2][c]   = true;
        board[c+2][c+1] = true;
        board[c+2][c+2] = true;
    }
    
}