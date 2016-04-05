package gameoflife.exceptions;

/**
 * GameOfLife
 * Created by willne763
 * on 24/3/16.
 */
public class BoardFileFormatException
        extends Exception{

    public BoardFileFormatException(){
        super();
    }

    public BoardFileFormatException(String message){
        super(message);
    }
}
