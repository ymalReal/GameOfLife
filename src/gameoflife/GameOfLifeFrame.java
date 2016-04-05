package gameoflife;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * GameOfLife
 * Created by willne763
 * on 8/3/16.
 *
 * The overall frame and control for the <code>BoardPanel</code>
 */
@SuppressWarnings("FieldCanBeLocal")
public class GameOfLifeFrame
        extends JFrame{

    /**
     * Constructor that initializes all components and the timer for iteration.
     */
    public GameOfLifeFrame(){
        model = new DefaultComboBoxModel<>(BoardTypes.values());
        initComponents();
        int delay = 100;
        timer = new Timer(delay, e -> {
            boardPanel.iterate();
            jLabelIterator.setText(Integer.toString(boardPanel.getIterations()));
        });
        timer.stop();
    }

    /**
     * Initializes and creates every portion of the UI
     */
    protected void initComponents(){
        jPanelIteratorHolder = new JPanel();
        jLabelIteratorLabel = new JLabel();
        jLabelIterator = new JLabel();
        jPanelSliderHolder = new JPanel();
        jSliderSpeed = new JSlider();
        jPanelBottom = new JPanel();
        jLabelSliderLabel = new JLabel();
        jComboBoxSelector = new JComboBox<>();
        boardPanel = new BoardPanel();
        jMenuBar = new JMenuBar();
        jMenuFile = new JMenu();
        jMenuFileLoad = new JMenuItem();
        jMenuFileSave = new JMenuItem();
        jMenuEdit = new JMenu();
        jMenuFileSave = new JMenuItem();
        jMenuEditColor = new JMenu();
        jMenuItemClear = new JMenuItem();
        jMenuItemRed = new JMenuItem();
        jMenuItemBlue = new JMenuItem();
        jMenuItemGreen = new JMenuItem();
        jMenuItemBlack = new JMenuItem();
        jMenuItemRandomColor = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(740, 730));

        jPanelIteratorHolder.setLayout(new java.awt.GridLayout(2, 1));

        jLabelIteratorLabel.setText("Iterations:");
        jLabelIteratorLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        jPanelIteratorHolder.add(jLabelIteratorLabel);

        jLabelIterator.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabelIterator.setText("0");
        jLabelIterator.setVerticalAlignment(SwingConstants.TOP);
        jPanelIteratorHolder.add(jLabelIterator);

        getContentPane().add(jPanelIteratorHolder, java.awt.BorderLayout.EAST);

        jPanelSliderHolder.setLayout(new java.awt.GridLayout(1, 2));

        jSliderSpeed.setMaximum(200);
        jSliderSpeed.setValue(0);
        jSliderSpeed.addChangeListener(evt -> {
            if(!jSliderSpeed.getValueIsAdjusting()){
                if(!(jSliderSpeed.getValue()==0)){
                    timer.setDelay(10000/jSliderSpeed.getValue());
                    timer.start();
                } else
                    timer.stop();
            }
        });
        jPanelSliderHolder.add(jSliderSpeed);

        jPanelBottom.setLayout(new java.awt.GridLayout(1, 2));

        jLabelSliderLabel.setText("<- Speed");
        jPanelBottom.add(jLabelSliderLabel);

        jComboBoxSelector.setModel(model);
        jComboBoxSelector.addActionListener(evt -> {
            boardPanel.setBoard((BoardTypes) jComboBoxSelector.getSelectedItem());
            jLabelIterator.setText("0");
        });
        jPanelBottom.add(jComboBoxSelector);

        jPanelSliderHolder.add(jPanelBottom);

        getContentPane().add(jPanelSliderHolder, java.awt.BorderLayout.SOUTH);

        boardPanel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                boardPanel.clicked(e);
                jLabelIterator.setText("0");
            }
        });
        getContentPane().add(boardPanel, java.awt.BorderLayout.CENTER);

        jMenuFile.setText("File");

        jMenuFileSave.setText("Save Board...");
        jMenuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_MASK));
        jMenuFileSave.addActionListener(e -> boardPanel.save());
        jMenuFile.add(jMenuFileSave);

        jMenuFileLoad.setText("Load Board...");
        jMenuFileLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.META_MASK));
        jMenuFileLoad.addActionListener(e -> {
            boardPanel.load();
            jLabelIterator.repaint();
        });
        jMenuFile.add(jMenuFileLoad);


        jMenuBar.add(jMenuFile);

        jMenuEdit.setText("Edit");

        jMenuItemClear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.META_MASK));
        jMenuItemClear.setText("Clear Board");
        jMenuItemClear.addActionListener(e -> {
            boardPanel.clear();
            jLabelIterator.setText("0");
        });
        jMenuEdit.add(jMenuItemClear);

        jMenuEditColor.setText("Set Cell Color");

        jMenuItemRed.setText("Red");
        jMenuItemRed.addActionListener(e -> {
            boardPanel.setSelectedColor(Color.RED);
            boardPanel.repaint();
        });
        jMenuEditColor.add(jMenuItemRed);

        jMenuItemBlue.setText("Blue");
        jMenuItemBlue.addActionListener(e -> {
            boardPanel.setSelectedColor(Color.BLUE);
            boardPanel.repaint();
        });
        jMenuEditColor.add(jMenuItemBlue);

        jMenuItemGreen.setText("Green");
        jMenuItemGreen.addActionListener(e -> {
            boardPanel.setSelectedColor(Color.GREEN);
            boardPanel.repaint();
        });
        jMenuEditColor.add(jMenuItemGreen);

        jMenuItemBlack.setText("Black");
        jMenuItemBlack.addActionListener(e -> {
            boardPanel.setSelectedColor(Color.BLACK);
            boardPanel.repaint();
        });
        jMenuEditColor.add(jMenuItemBlack);

        jMenuItemRandomColor.setText("Random");
        jMenuItemRandomColor.addActionListener(e -> {
            boardPanel.setSelectedColor(new Color(
                    (int) (Math.random() * 155) + 100,
                    (int) (Math.random() * 155) + 100,
                    (int) (Math.random() * 155) + 100
            ));
            boardPanel.repaint();
        });
        jMenuEditColor.add(jMenuItemRandomColor);

        jMenuEdit.add(jMenuEditColor);

        jMenuBar.add(jMenuEdit);

        setJMenuBar(jMenuBar);

        pack();
    }

    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(() -> {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            new GameOfLifeFrame().setVisible(true);
        });
    }

    protected gameoflife.BoardPanel boardPanel;
    protected JComboBox<BoardTypes> jComboBoxSelector;
    protected JLabel jLabelIteratorLabel;
    protected JLabel jLabelSliderLabel;
    protected JLabel jLabelIterator;
    protected JMenu jMenuFile;
    protected JMenuItem jMenuFileSave;
    protected JMenuItem jMenuFileLoad;
    protected JMenuBar jMenuBar;
    protected JMenu jMenuEdit;
    protected JMenu jMenuEditColor;
    protected JMenuItem jMenuItemBlack;
    protected JMenuItem jMenuItemBlue;
    protected JMenuItem jMenuItemClear;
    protected JMenuItem jMenuItemGreen;
    protected JMenuItem jMenuItemRandomColor;
    protected JMenuItem jMenuItemRed;
    protected JPanel jPanelIteratorHolder;
    protected JPanel jPanelSliderHolder;
    protected JPanel jPanelBottom;
    protected JSlider jSliderSpeed;
    protected Timer timer;
    protected DefaultComboBoxModel<BoardTypes> model;
}
