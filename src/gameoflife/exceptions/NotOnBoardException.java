package gameoflife.exceptions;

public class NotOnBoardException 
        extends RuntimeException{
    
    public NotOnBoardException(){
        super();
    }
    
    public NotOnBoardException(String reason){
        super(reason);
    }
}