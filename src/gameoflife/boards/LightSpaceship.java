package gameoflife.boards;

public class LightSpaceship extends WrapAroundBoard{

    public LightSpaceship(int size){
        super(size);
        int c = size/2;
        board[c-2][c+0] = true;
        board[c-2][c+2] = true;
        board[c-1][c-1] = true;
        board[c+0][c-1] = true;
        board[c+1][c-1] = true;
        board[c+1][c+2] = true;
        board[c+2][c-1] = true;
        board[c+2][c+0] = true;
        board[c+2][c+1] = true;
        
    }

}
