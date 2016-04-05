package gameoflife;


import gameoflife.boards.Board;
import gameoflife.exceptions.BoardFileFormatException;

import java.io.*;

/**
 * GameOfLife
 * Created by willne763
 * on 15/3/16.
 */
public class Saving_Loading{

    public static boolean fileWrite(File toWrite, Board board){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(toWrite));
            oos.writeObject(board);
            oos.close();
            return true;
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public static Board fileRead(File toRead)
            throws BoardFileFormatException{
        Board toReturn;

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(toRead));
            toReturn = (Board) ois.readObject();
            ois.close();
        } catch(IOException e){
            e.printStackTrace();
            return null;
        } catch(ClassNotFoundException e){
            e.printStackTrace();
            throw new BoardFileFormatException();
        }

        return toReturn;
    }

}
