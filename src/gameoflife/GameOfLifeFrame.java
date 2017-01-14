package gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
     *
     */
    private void initComponents(){
        jPanelIteratorHolder = new JPanel();
        jLabelIteratorLabel = new JLabel();
        jLabelIterator = new JLabel();
        jPanel2 = new JPanel();
        jSliderSpeed = new JSlider();
        jPanel3 = new JPanel();
        jLabel3 = new JLabel();
        jComboBoxSelector = new JComboBox<>();
        boardPanel = new BoardPanel();
        jMenuBar = new JMenuBar();
        jMenuFile = new JMenu();
        jMenuFileGame = new JMenu();
        jMenuFileGameStandard = new JMenuItem();
        jMenuFileGameCities = new JMenuItem();
        jMenuEdit = new JMenu();
        jMenuItemClear = new JMenuItem();
        jMenuItemPause = new JMenuItem();
        previousVal = jSliderSpeed.getMaximum();
        jMenuEditColor = new JMenu();
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

        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        jSliderSpeed.setMaximum(200);
        jSliderSpeed.setValue(0);
        jSliderSpeed.addChangeListener(evt -> {
            if(!jSliderSpeed.getValueIsAdjusting()){
                if (jSliderSpeed.getValue() != 0) {
                    timer.setDelay(10000/jSliderSpeed.getValue());
                    timer.start();
                    jMenuItemPause.setText("Pause");
                } else {
                    timer.stop();
                    jMenuItemPause.setText("Resume");
                }
            }
        });
        jPanel2.add(jSliderSpeed);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        jLabel3.setText("<- Speed");
        jPanel3.add(jLabel3);

        jComboBoxSelector.setModel(model);
        jComboBoxSelector.addActionListener(evt -> {
            boardPanel.setBoard((BoardTypes) jComboBoxSelector.getSelectedItem());
            jLabelIterator.setText("0");
        });
        jPanel3.add(jComboBoxSelector);

        jPanel2.add(jPanel3);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        boardPanel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                boardPanel.clicked(e);
                jLabelIterator.setText("0");
            }
        });
        getContentPane().add(boardPanel, java.awt.BorderLayout.CENTER);

        jMenuFile.setText("File");

        jMenuFileGame.setText("GameType");

        jMenuFileGameStandard.setText("Standard");
        jMenuFileGameStandard.addActionListener(e -> boardPanel.setGameType(GameTypes.STANDARD));
        jMenuFileGame.add(jMenuFileGameStandard);

        jMenuFileGameCities.setText("Cities");
        jMenuFileGameCities.addActionListener(e -> boardPanel.setGameType(GameTypes.CITIES));
        jMenuFileGame.add(jMenuFileGameCities);

        jMenuFile.add(jMenuFileGame);

        jMenuBar.add(jMenuFile);

        jMenuEdit.setText("Edit");

        jMenuItemClear.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.META_MASK));
        jMenuItemClear.setText("Clear WrapAroundBoard");
        jMenuItemClear.addActionListener(e -> {
            boardPanel.clear();
            jLabelIterator.setText("0");
            jSliderSpeed.setValue(0);
        });
        jMenuEdit.add(jMenuItemClear);

        jMenuItemPause.setText("Resume");
        jMenuItemPause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.META_MASK));
        jMenuItemPause.addActionListener((e) -> {
            if (jSliderSpeed.getValue() == 0) {
                jSliderSpeed.setValue(previousVal);
            } else {
                previousVal = jSliderSpeed.getValue();
                jSliderSpeed.setValue(0);
            }
        });
        jMenuEdit.add(jMenuItemPause);

        jMenuEditColor.setText("Set Cell Color");

        jMenuItemRed.setText("Red");
        jMenuItemRed.addActionListener(e -> boardPanel.setSelectedColor(Color.RED));
        jMenuEditColor.add(jMenuItemRed);

        jMenuItemBlue.setText("Blue");
        jMenuItemBlue.addActionListener(e -> boardPanel.setSelectedColor(Color.BLUE));
        jMenuEditColor.add(jMenuItemBlue);

        jMenuItemGreen.setText("Green");
        jMenuItemGreen.addActionListener(e -> boardPanel.setSelectedColor(Color.GREEN));
        jMenuEditColor.add(jMenuItemGreen);

        jMenuItemBlack.setText("Black");
        jMenuItemBlack.addActionListener(e -> boardPanel.setSelectedColor(Color.BLACK));
        jMenuEditColor.add(jMenuItemBlack);

        jMenuItemRandomColor.setText("Random");
        jMenuItemRandomColor.addActionListener(e -> boardPanel.setSelectedColor(
                new Color(
                        (int) (Math.random() * 155) + 100,
                        (int) (Math.random() * 155) + 100,
                        (int) (Math.random() * 155) + 100)
        ));
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

    private gameoflife.BoardPanel boardPanel;
    private JComboBox<BoardTypes> jComboBoxSelector;
    private JLabel jLabelIteratorLabel;
    private JLabel jLabel3;
    private JLabel jLabelIterator;
    private JMenu jMenuFile;
    private JMenu jMenuFileGame;
    private JMenuItem jMenuFileGameStandard;
    private JMenuItem jMenuFileGameCities;
    private JMenuBar jMenuBar;
    private JMenu jMenuEdit;
    private JMenu jMenuEditColor;
    private JMenuItem jMenuItemPause;
    private int previousVal;
    private JMenuItem jMenuItemBlack;
    private JMenuItem jMenuItemBlue;
    private JMenuItem jMenuItemClear;
    private JMenuItem jMenuItemGreen;
    private JMenuItem jMenuItemRandomColor;
    private JMenuItem jMenuItemRed;
    private JPanel jPanelIteratorHolder;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JSlider jSliderSpeed;
    private Timer timer;
    private DefaultComboBoxModel<BoardTypes> model;
}
