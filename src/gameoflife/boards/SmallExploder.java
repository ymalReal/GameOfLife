package gameoflife.boards;

public class SmallExploder 
        extends WrapAroundBoard{

    @SuppressWarnings("PointlessArithmeticExpression")
    public SmallExploder(int size){
        super(size);
        int center = size/2;
        board[center+0][center-1] = true;
        board[center-1][center+0] = true;
        board[center+0][center+0] = true;
        board[center+1][center+0] = true;
        board[center-1][center+1] = true;
        board[center+1][center+1] = true;
        board[center+0][center+2] = true;
    }

}
