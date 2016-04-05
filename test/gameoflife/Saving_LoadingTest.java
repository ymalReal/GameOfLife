package gameoflife;

import static org.junit.Assert.*;

import gameoflife.boards.Board;
import gameoflife.boards.WrapAroundBoard;
import gameoflife.exceptions.BoardFileFormatException;
import org.junit.Test;
import org.junit.Before;

import java.io.File;
import java.io.FileNotFoundException;

public class Saving_LoadingTest{

    Board board;

    @Before
    public void Before(){
        board = new WrapAroundBoard(4);

        board.setPos(0, 1, true);
        board.setPos(2, 1, true);
        board.setPos(1, 3, true);
        board.setPos(3, 3, true);
    }

    @Test
    public void loadTest(){
        File loadTest = new File("test/gameoflife/loadTest.board");

        Board loadedBoard = null;
        try{
            loadedBoard = Saving_Loading.fileRead(loadTest);
        } catch(BoardFileFormatException e){
            fail(e.getMessage());
        }

        if(loadedBoard != null){
            for(int x = 0; x < 4; x++){
                for(int y = 0; y < 4; y++){
                    assertEquals(board.getPos(x, y), loadedBoard.getPos(x, y));
                }
            }
        } else {
            fail("loadedBoard is null.");
        }
    }

    @Test
    public void saveTest(){
        //This test is simply to tell if saving is also working
        //If loadTest fails then saveTest will also fail

        File saveTest = new File("test/gameoflife/saveTest.board");

        Saving_Loading.fileWrite(saveTest, board);

        Board loadedBoard = null;
        try{
            loadedBoard = Saving_Loading.fileRead(saveTest);
        } catch(BoardFileFormatException e){
            fail(e.getMessage());
        }

        if(loadedBoard != null){
            for(int x = 0; x < 4; x++){
                for(int y = 0; y < 4; y++){
                    assertEquals(board.getPos(x, y), loadedBoard.getPos(x, y));
                }
            }
        } else {
            fail("loadedBoard is null.");
        }
    }
}