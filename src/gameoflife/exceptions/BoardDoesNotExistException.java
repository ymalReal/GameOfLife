package gameoflife.exceptions;

public class BoardDoesNotExistException
        extends Exception{

    public BoardDoesNotExistException(){
        super();
    }

    public BoardDoesNotExistException(String message){
        super(message);
    }
}