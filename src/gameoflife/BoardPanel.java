package gameoflife;

import gameoflife.boards.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

public class BoardPanel
        extends JComponent{
    
    private int size;
    private int speed;
    private Color selectedColor;
    private Board board;
    
    private Rectangle2D[][] rects;
    
    public BoardPanel(){
        this.size = 65;
        this.speed = 0;
        board = new WrapAroundBoard(size);
        rects = new Rectangle2D[size][size];
        selectedColor = Color.RED;
        prepBoard();
    }
    
    public BoardPanel(int size){
        this.size = size;
        board = new WrapAroundBoard(size);
        rects = new Rectangle2D[size][size];
        selectedColor = Color.RED;
        prepBoard();
    }
    
    private void prepBoard(){
        for(int x = 0; x<size; x++){
            for(int y = 0; y<size; y++){
                rects[x][y] = new Rectangle2D.Double(x*10, y*10, 10, 10);
            }
        }
    }
    
    public void clicked(MouseEvent evt){
        for(int x = 0; x<size; x++){
            for(int y = 0; y<size; y++){
                if(rects[x][y].contains(evt.getPoint())){
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
    
    @Override
    public void paintComponent(Graphics g){
        for(int x = 0; x<size; x++){
            for(int y = 0; y<size; y++){
                Rectangle2D rect = rects[x][y];
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