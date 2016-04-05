package gameoflife;

import gameoflife.boards.*;
import gameoflife.exceptions.BoardFileFormatException;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BoardPanel
        extends JComponent{
    
    public final static int DEFAULT_SIZE = 65;

    private int size;
    private int speed;
    private Color selectedColor;
    private Board board;
    
    private Rectangle2D[][] rectangles;
    
    public BoardPanel(){
        this.size = DEFAULT_SIZE;
        this.speed = 0;
        board = new WrapAroundBoard(size);
        rectangles = new Rectangle2D[size][size];
        selectedColor = Color.RED;
        prepBoard();
    }
    
    public BoardPanel(int size){
        this.size = size;
        board = new WrapAroundBoard(size);
        rectangles = new Rectangle2D[size][size];
        selectedColor = Color.RED;
        prepBoard();
    }
    
    private void prepBoard(){
        for(int x = 0; x<size; x++){
            for(int y = 0; y<size; y++){
                rectangles[x][y] = new Rectangle2D.Double(x*10, y*10, 10, 10);
            }
        }
    }
    
    public void clicked(MouseEvent evt){
        for(int x = 0; x<size; x++){
            for(int y = 0; y<size; y++){
                if(rectangles[x][y].contains(evt.getPoint())){
                    board.setIterations(0);
                    board.setPos(x, y, !board.getPos(x, y));
                    repaint();
                    return;
                }
            }
        }
    }
    
    public void iterate(){
        board.iterate();
        repaint();
    }
    
    public void clear(){
        board = new WrapAroundBoard(size);
        repaint();
    }
    
    public void setBoard(BoardTypes b){
        switch(b){
            case Empty:
                board = new WrapAroundBoard(size);
                break;
            case SmallExploder:
                board = new SmallExploder(size);
                break;
            case Glider:
                board = new Glider(size);
                break;
            case Exploder:
                board = new Exploder(size);
                break;
            case RowOf10:
                board = new RowOf10(size);
                break;
            case LightSpaceship:
                board = new LightSpaceship(size);
                break;
            case Tumbler:
                board = new Tumbler(size);
                break;
        }
        repaint();
    }
    
    public void setSpeed(int newSpeed){
        if(newSpeed<=100 && newSpeed>=0){
            speed=newSpeed;
        }
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public int getIterations(){
        return board.getIterations();
    }
    
    public void setSelectedColor(Color newColor){
        selectedColor = newColor;
    }

    public void save(){
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("'.board's only", "board");
        chooser.setFileFilter(filter);

        int option = chooser.showSaveDialog(this);

        File toSave = null;
        if(option == JFileChooser.APPROVE_OPTION){
            File selectedFile = chooser.getSelectedFile();
            toSave = new File(selectedFile.getAbsolutePath());
        }

        if(toSave != null){
            Saving_Loading.fileWrite(toSave, board);
            JOptionPane.showMessageDialog(this, "Saved at: " + toSave.toString(), "Save successful.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Saving Failed", "Save unsuccessful.", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void load(){

        JFileChooser chooser = new JFileChooser();

        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("'.board's only", "board");
        chooser.setFileFilter(filter);

        int option = chooser.showOpenDialog(this);

        File toLoad = null;
        if(option == JFileChooser.APPROVE_OPTION){
            File selectedFile = chooser.getSelectedFile();
            toLoad = new File(selectedFile.getAbsolutePath());
        }

        Board potentialBoard = null;
        try{
            potentialBoard = Saving_Loading.fileRead(toLoad);
        } catch(BoardFileFormatException e){
            JOptionPane.showMessageDialog(null, "Incorrect File Type. \nPlease choose a valid File", "Incorrect Format", JOptionPane.ERROR_MESSAGE);
        }

        if(potentialBoard != null){
            board = potentialBoard;
            repaint();
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        for(int x = 0; x<size; x++){
            for(int y = 0; y<size; y++){
                Rectangle2D rect = rectangles[x][y];
                if(board.getPos(x, y)){
                    g.setColor(selectedColor);
                    g.fillRect((int) rect.getX(), (int) rect.getY(),
                            (int) rect.getWidth(), (int) rect.getHeight());
                    g.setColor(Color.BLACK);
                }
                g.drawRect((int) rect.getX(), (int) rect.getY(),
                        (int) rect.getWidth(), (int) rect.getHeight());
            }
        }
    }
}