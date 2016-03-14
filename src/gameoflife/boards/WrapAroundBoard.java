package gameoflife.boards;

/**
 * Test to see if the principal of the board will work;
 *
 * @author willne763
 */
public class WrapAroundBoard
        extends Board{

    public WrapAroundBoard(int size){
        this.size = size;
        board = new boolean[size][size];
        for(int x = 0; x < size; x++)
            for(int y = 0; y < size; y++)
                board[x][y] = false;
        iterations = 0;
    }
}
