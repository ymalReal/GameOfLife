package gameoflife.boards;

public class FallOffBoard
        extends Board {

    public FallOffBoard(int size){
        this.size = size;
        board = new boolean[size][size];
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                board[x][y] = false;
            }
        }
    }

    @Override
    public boolean getPos(int x, int y) {
        if(x == board.length || y == board.length ||
                x == -1 || y == -1)
            return false;
        return super.getPos(x, y);
    }
}
