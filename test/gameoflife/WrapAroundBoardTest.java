package gameoflife;

import gameoflife.boards.WrapAroundBoard;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author willne763
 */
public class WrapAroundBoardTest{
    
    @Test
    public void setPosTest(){
        WrapAroundBoard wrapAroundBoard = new WrapAroundBoard(3);
        
        wrapAroundBoard.setPos(1, 1, true);
        assertTrue(wrapAroundBoard.getPos(1, 1));
        
        wrapAroundBoard.setPos(1, 1, false);
        assertFalse(wrapAroundBoard.getPos(1, 1));
    }
    
    @Test
    public void getPosTest(){
        WrapAroundBoard wrapAroundBoard = new WrapAroundBoard(3);
        
        wrapAroundBoard.setPos(1, 1, true);
        assertTrue(wrapAroundBoard.getPos(1, 1));
        
        wrapAroundBoard.setPos(1, 1, false);
        assertFalse(wrapAroundBoard.getPos(1, 1));
        
        wrapAroundBoard.setPos(2, 2, true);
        assertTrue(wrapAroundBoard.getPos(2, 2));
        assertTrue(wrapAroundBoard.getPos(-1, -1));
        
        wrapAroundBoard.setPos(0, 0, true);
        assertTrue(wrapAroundBoard.getPos(0, 0));
        assertTrue(wrapAroundBoard.getPos(3, 3));
    }
    
    @Test
    public void checkRoundTest(){
        WrapAroundBoard wrapAroundBoard = new WrapAroundBoard(4);
        WrapAroundBoard wrapAroundBoard1 = new WrapAroundBoard(4);
        
        wrapAroundBoard.setPos(0, 0, true);
        wrapAroundBoard.setPos(1, 1, true);
        wrapAroundBoard.setPos(3, 2, true);
        wrapAroundBoard.setPos(0, 3, true);
        wrapAroundBoard.setPos(2, 3, true);
        
        wrapAroundBoard1.setPos(0, 0, true);
        wrapAroundBoard1.setPos(1, 1, true);
        wrapAroundBoard1.setPos(2, 2, true);
        wrapAroundBoard1.setPos(3, 2, true);
        wrapAroundBoard1.setPos(0, 3, true);
        wrapAroundBoard1.setPos(2, 3, true);
        
        assertEquals(3, wrapAroundBoard.checkRound(2, 2));
        assertEquals(4, wrapAroundBoard.checkRound(3, 3));
        assertEquals(2, wrapAroundBoard.checkRound(0, 0));
        
        assertEquals(3, wrapAroundBoard1.checkRound(2, 2));
        assertEquals(5, wrapAroundBoard1.checkRound(3, 3));
        assertEquals(2, wrapAroundBoard1.checkRound(0, 0));
    }
    
    @Test
    public void testIterate(){
        WrapAroundBoard wrapAroundBoard = new WrapAroundBoard(4);
        
        wrapAroundBoard.setPos(0, 0, true);
        wrapAroundBoard.setPos(1, 1, true);
        wrapAroundBoard.setPos(3, 2, true);
        wrapAroundBoard.setPos(0, 3, true);
        wrapAroundBoard.setPos(2, 3, true);
        
        wrapAroundBoard.iterate();
        
        assertTrue(wrapAroundBoard.getPos(0, 0));
        assertFalse(wrapAroundBoard.getPos(1, 0));
        assertFalse(wrapAroundBoard.getPos(2, 0));
        assertTrue(wrapAroundBoard.getPos(3, 0));
        assertTrue(wrapAroundBoard.getPos(0, 1));
        assertFalse(wrapAroundBoard.getPos(1, 1));
        assertFalse(wrapAroundBoard.getPos(2, 1));
        assertFalse(wrapAroundBoard.getPos(3, 1));
        assertTrue(wrapAroundBoard.getPos(0, 2));
        assertTrue(wrapAroundBoard.getPos(1, 2));
        assertTrue(wrapAroundBoard.getPos(2, 2));
        assertTrue(wrapAroundBoard.getPos(3, 2));
        assertTrue(wrapAroundBoard.getPos(0, 3));
        assertTrue(wrapAroundBoard.getPos(1, 3));
        assertFalse(wrapAroundBoard.getPos(2, 3));
        assertFalse(wrapAroundBoard.getPos(3, 3));
    }
}
