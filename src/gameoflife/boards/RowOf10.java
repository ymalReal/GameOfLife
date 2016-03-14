package gameoflife.boards;

public class RowOf10
        extends WrapAroundBoard{

    public RowOf10(int size){
        super(size);
        int c = size/2;
        for(int i=-4; i<6; i++){
            board[c+i][c] = true;
        }
    }
    
}