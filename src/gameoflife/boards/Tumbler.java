package gameoflife.boards;

public class Tumbler
        extends WrapAroundBoard{

    @SuppressWarnings("PointlessArithmeticExpression")
    public Tumbler(int size){
        super(size);
        int c = size/2;
        board[c-3][c+0] = true;
        board[c-3][c+1] = true;
        board[c-3][c+2] = true;
        board[c-2][c-3] = true;
        board[c-2][c-2] = true;
        board[c-2][c+2] = true;
        board[c-1][c-3] = true;
        board[c-1][c-2] = true;
        board[c-1][c-1] = true;
        board[c-1][c+0] = true;
        board[c-1][c+1] = true;

        board[c+3][c+0] = true;
        board[c+3][c+1] = true;
        board[c+3][c+2] = true;
        board[c+2][c-3] = true;
        board[c+2][c-2] = true;
        board[c+2][c+2] = true;
        board[c+1][c-3] = true;
        board[c+1][c-2] = true;
        board[c+1][c-1] = true;
        board[c+1][c+0] = true;
        board[c+1][c+1] = true;
    }
    
}