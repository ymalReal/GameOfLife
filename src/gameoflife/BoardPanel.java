package gameoflife;

import gameoflife.boards.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class BoardPanel
        extends JComponent{
    
    private int size;
    private int speed;
    private Color selectedColor;
    private Board board;
    private BoardType boardType;

    private GameTypes gameTypes;

    private Rectangle2D[][] rectangles;
    
    public BoardPanel(){
        this.size = 65;
        this.speed = 0;
        board = new WrapAroundBoard(size);
        rectangles = new Rectangle2D[size][size];
        selectedColor = Color.RED;
        gameTypes = GameTypes.STANDARD;
        prepBoard();
    }
    
    public BoardPanel(int size){
        this.size = size;
        board = new WrapAroundBoard(size);
        rectangles = new Rectangle2D[size][size];
        selectedColor = Color.RED;
        gameTypes = GameTypes.STANDARD;
        prepBoard();
    }

    public BoardPanel(int size, BoardType type){
        this.size = size;
        this.boardType = type;
        switch (type){
            case WrapAroundBoard -> board = new WrapAroundBoard(size);
            case FallOffBoard -> board = new FallOffBoard(size);
            case WalledOffBoard -> board = new WalledOffBoard(size);
        }
        rectangles = new Rectangle2D[size][size];
        selectedColor = Color.RED;
        gameTypes = GameTypes.STANDARD;
        prepBoard();
    }

    
    private void prepBoard(){
        for(int x = 0; x<size; x++){
            for(int y = 0; y<size; y++){
                rectangles[x][y] = new Rectangle2D.Double(x * 10, y * 10, 10, 10);
            }
        }
    }
    
    public void clicked(MouseEvent evt){
        for(int x = 0; x<size; x++){
            for(int y = 0; y<size; y++){
                if (rectangles[x][y].contains(evt.getPoint())) {
                    board.resetIterations();
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
        switch (boardType){
            case WrapAroundBoard -> board = new WrapAroundBoard(size);
            case FallOffBoard -> board = new FallOffBoard(size);
            case WalledOffBoard -> board = new WalledOffBoard(size);
        }
        repaint();
    }
    
    public void setBoard(BoardShapes b){
        GameTypes currType = board.getGameType();
        switch (b) {
            case Empty -> board = new WrapAroundBoard(size);
            case SmallExploder -> board = new SmallExploder(size);
            case Glider -> board = new Glider(size);
            case Exploder -> board = new Exploder(size);
            case RowOf10 -> board = new RowOf10(size);
            case LightSpaceship -> board = new LightSpaceship(size);
            case Tumbler -> board = new Tumbler(size);
        }
        board.setGameType(currType);
        repaint();
    }

    public void changeBoardSizeSquare(int newSize){
        this.size = newSize;
        board = new WrapAroundBoard(size);
        rectangles = new Rectangle2D[size][size];
        selectedColor = Color.RED;
        gameTypes = GameTypes.STANDARD;
        prepBoard();
    }

    public void fullScreen(){
        System.err.println("Before;;");
        System.err.println("Size: " + this.getSize());
        System.err.println("Preferred Size: " + this.getPreferredSize());
        System.err.println("Maximum Size: " + this.getMaximumSize());
        System.err.println("Minimum Size: " + this.getMinimumSize());
        Container parent = null;
        while(!(parent instanceof JFrame)){
            if (parent == null) parent = this.getParent();
            else parent = parent.getParent();
            System.err.println(parent.getClass());
        }
        JFrame topJFrame = (JFrame) parent;
        topJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        System.err.println("After;;");
        System.err.println("Size: " + this.getSize());
        System.err.println("Preferred Size: " + this.getPreferredSize());
        System.err.println("Maximum Size: " + this.getMaximumSize());
        System.err.println("Minimum Size: " + this.getMinimumSize());
    }

    public void setGameType(GameTypes type){
        board.setGameType(type);
    }

    public void setBoardType(BoardType type){
        this.boardType = type;
        clear();
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
        this.repaint();
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