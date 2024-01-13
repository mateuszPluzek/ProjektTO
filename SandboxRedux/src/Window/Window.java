package Window;

import Particle.*;
import States.Window.*;
import memento.Memento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window {
    private JFrame frame = new JFrame();
    private ButtonState state;

    public Window(String title, int width, int height, Board board) {
        state = new DrawAirState();
//        basic setup
        this.frame.setTitle(title);
        this.frame.setSize(width, height);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      grid set up
        CustomPanel gridPanel = new CustomPanel(board, state, frame);
        this.frame.getContentPane().add(gridPanel);

        this.frame.setVisible(true);

    }

    private static class CustomPanel extends JPanel {
        private Board board;
        private ButtonState state;

        private boolean temperatureHUD;

        private JFrame frame;
        private boolean mousePressed = false;
        CustomPanel(Board board, ButtonState state, JFrame frame) {
            this.board = board;
            this.state = state;
            this.frame = frame;
            temperatureHUD = false;

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mousePressed = true;
                    mouseLoop();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    mousePressed = false;
                }
            });
//            BUTTON PANEL
//          main panel
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
//          element buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            JLabel elementsLabel = new JLabel("Elements");
            buttonPanel.add(elementsLabel);

            ButtonList bList = new ButtonList();
            for(int index = 0; index < bList.getSize();index++) {
                JButton tmp = new JButton(bList.getType(index));
                tmp.setBackground(bList.getcolor(index));
                ButtonState tmpState = bList.getState(index);

                tmp.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setState(tmpState);
                    }
                });

                buttonPanel.add(tmp);
            }
            // tool buttons
            JPanel toolsPanel = new JPanel();
            toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.Y_AXIS));
            JLabel toolsLabel = new JLabel("tools");
            JButton buttonHeat = new JButton("Heat");
            JButton buttonCool = new JButton("Cool");

            buttonHeat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setState(new ToolHeat());
                }
            });
            buttonCool.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setState(new ToolCool());
                }
            });

            buttonPanel.add(toolsLabel);
            buttonPanel.add(buttonHeat);
            buttonPanel.add(buttonCool);

            // reset buttons
            JPanel menuPanel = new JPanel();
            JButton buttonReset = new JButton("Reset");
            JButton buttonTemperature = new JButton("toggle temperature");
            buttonTemperature.setBackground(new Color(0xF3F3F3));
            JButton saveBackup = new JButton("Save");
            JButton loadBackup = new JButton("Load");
            //lista
            DefaultListModel<String> loadListModel = new DefaultListModel<>();
            JList<String> loadList = new JList<>();
            loadList.setModel(loadListModel);
            loadList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            buttonReset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i = 0; i < board.getRows(); i++) {
                        for(int j = 0; j < board.getCols(); j++) {
                            board.setParticle(i, j, new Air(board, j * board.getGridSize(), i * board.getGridSize()));
                            board.setTemp(i, j, 0);
                        }
                    }
                }
            });
            buttonTemperature.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    temperatureHUD = temperatureHUD ? false : true;
                    if(temperatureHUD)
                        buttonTemperature.setBackground(new Color(0x424242));
                    else
                        buttonTemperature.setBackground(new Color(0xF3F3F3));
                }
            });
            saveBackup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    board.getHistory().takeMemento(new Memento(board));
                    loadListModel.clear();
                    for(int i = 0; i < board.getHistory().getSavedHistory().size();i++) {
                        loadListModel.addElement("     "+String.valueOf(i)+"     ");
                    }
                }
            });
            loadBackup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    board.getHistory().loadHistory(Integer.parseInt(loadList.getSelectedValue().strip())).restore();
                }
            });

            menuPanel.add(buttonReset);
            menuPanel.add(buttonTemperature);
            menuPanel.add(saveBackup);
            menuPanel.add(loadBackup);
            menuPanel.add(new JScrollPane(loadList));

            mainPanel.add(buttonPanel);
            mainPanel.add(toolsPanel);
            mainPanel.add(menuPanel);
            frame.getContentPane().add(mainPanel, BorderLayout.EAST);
//          BUTTON PANEL END
        }

        private void mouseLoop() {
            new Thread(() -> {
                while (mousePressed) {
                    int MouseX = getMousePosition().x / board.getGridSize();
                    int MouseY = getMousePosition().y / board.getGridSize();

                    state.draw(board, MouseY, MouseX);

                    try {
                        Thread.sleep(1); // Adjust the sleep duration as needed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Particle tmp;

            for (int i = 0; i < board.getRows(); i++) {
                for(int j = 0; j < board.getCols(); j++) {
                    tmp = board.getParticle(i, j);
                    if(!temperatureHUD)
                        g.setColor(tmp.getColour());
                    else {
                        if (board.getTemp(i, j) == 0)
                            g.setColor(Color.BLACK);
                        else if (board.getTemp(i, j) > 0)
                            if(board.getTemp(i, j) <= 255)
                                g.setColor(new Color(board.getTemp(i, j), 3, 3));
                            else
                                g.setColor(new Color(255, 3, 3));
                        else if (board.getTemp(i, j) < 50)
                            if(board.getTemp(i, j) * -1 <= 255)
                                g.setColor(new Color(5, 5, board.getTemp(i, j) * -1));
                            else
                                g.setColor(new Color(5, 5, 255));
                    }
                    int x
                            = j * board.getGridSize();
                    int y = i * board.getGridSize();
                    g.fillRect(x, y, board.getGridSize(), board.getGridSize());
                }

            }
        }
        public void setState(ButtonState state) {
            this.state = state;
        }
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void setState(ButtonState state) {
        this.state = state;
    }
}
