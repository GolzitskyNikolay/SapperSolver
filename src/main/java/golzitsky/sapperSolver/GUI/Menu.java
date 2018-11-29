package golzitsky.sapperSolver.GUI;

import golzitsky.sapperSolver.core.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Menu {

    static void createMenu(Field classField, JFrame jFrame, JPanel panel, BotMovies botMovies) {
        changeIcon(jFrame);
        JMenuBar menuBar = new JMenuBar();

        JMenu game = new JMenu("Game");
        addNewGame(game, classField, jFrame, panel, botMovies);
        addExit(game);

        JMenu settings = new JMenu("Settings");
        addSizeOfMap(settings, classField, jFrame, panel, botMovies);
        addDifficulty(settings, classField, jFrame, panel, botMovies);
        addSound(settings);

        JMenu stopOrStartTimer = new JMenu("Pause");
        stopOrStartTimer(stopOrStartTimer, botMovies);

        menuBar.add(game);
        menuBar.add(settings);
        menuBar.add(stopOrStartTimer);

        jFrame.add(menuBar);
        jFrame.setJMenuBar(menuBar);
    }

    private static void stopOrStartTimer(JMenu stopOrStartTimer, final BotMovies botMovies) {
        JMenuItem pause = new JMenuItem("stop/start");
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!botMovies.getPause()) {
                    botMovies.getTimer().stop();
                    botMovies.setPause(true);
                } else {
                    botMovies.setPause(false);
                    botMovies.getTimer().start();
                }
            }
        });
        stopOrStartTimer.add(pause);
    }

    private static void addNewGame(JMenu game, final Field classField, final JFrame jFrame,
                                   final JPanel panel, final BotMovies botMovies) {
        ImageIcon flag1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
                Menu.class.getResource("/images/reload.png")));
        JMenuItem newGame = new JMenuItem("Start New Game", flag1);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BotLauncher.startGame(classField, jFrame, panel, botMovies);
            }
        });
        game.add(newGame);
    }

    private static void addDifficulty(JMenu settings, final Field classField, final JFrame jFrame,
                                      final JPanel panel, final BotMovies botMovies) {
        ImageIcon flag2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
                Menu.class.getResource("/images/settings.png")));
        JMenuItem difficult = new JMenuItem("Difficulty", flag2);
        difficult.setToolTipText("You can choose number of chanceOfBombs");
        difficult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forDifficultAndMap(true, classField, jFrame, panel, botMovies);
            }
        });
        settings.add(difficult);
    }

    private static void addSizeOfMap(JMenu settings, final Field classField, final JFrame jFrame,
                                     final JPanel panel, final BotMovies botMovies) {
        ImageIcon flag3 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
                Menu.class.getResource("/images/field.png")));
        JMenuItem sizeOfMap = new JMenuItem("Size of map", flag3);
        sizeOfMap.setToolTipText("You can choose map size of the playing field");
        sizeOfMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forDifficultAndMap(false, classField, jFrame, panel, botMovies);
            }
        });
        settings.add(sizeOfMap);
    }

    private static void forDifficultAndMap(final Boolean isDifficult, final Field classField, final JFrame jFrame,
                                           final JPanel panel, final BotMovies botMovies) {
        final JDialog jDialog = new JDialog();
        jDialog.setModal(true);
        JPanel jPanel = new JPanel();
        final JSlider slider;
        if (!isDifficult) {
            slider = new JSlider(5, 15, classField.mapSize);
            slider.setMajorTickSpacing(1);
        } else {
            slider = new JSlider(5, 19, classField.chanceOfBombs);
            slider.setMajorTickSpacing(2);
        }
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setPaintLabels(true);

        JButton button = new JButton("Save changes");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isDifficult) classField.chanceOfBombs = slider.getValue();
                else {
                    int result = JOptionPane.showConfirmDialog(null,
                            "The current game will be over." + "\n" +
                                    "Do you want to start a new game?");
                    if (result == JOptionPane.YES_OPTION) {
                        classField.mapSize = slider.getValue();
                        BotLauncher.startGame(classField, jFrame, panel, botMovies);
                    }
                }
                jDialog.dispose();
            }
        });
        jPanel.add(slider);
        jPanel.add(button);

        jDialog.add(jPanel);
        jDialog.setBounds(465, 300, 450, 100);
        jDialog.setResizable(false);

        jDialog.setVisible(true);
    }

    private static void addExit(JMenu game) {
        ImageIcon flag4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
                Menu.class.getResource("/images/exit.png")));
        JMenuItem exit = new JMenuItem("Exit", flag4);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        game.add(exit);
    }

    private static void addSound(JMenu settings) {

        ImageIcon flag5 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
                Menu.class.getResource("/images/volume.png")));

        final JMenuItem sound = new JMenuItem("turn on/off Sound", flag5);

        if (PlaySound.playSound) sound.setToolTipText("Sound on");

        sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlaySound.playSound = !PlaySound.playSound;
                if (PlaySound.playSound) sound.setToolTipText("Sound on");
                else sound.setToolTipText("Sound off");
            }
        });
        settings.add(sound);
    }

    private static void changeIcon(JFrame jFrame) {
        Image icon = Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/images/bomb1.png"));
        jFrame.setIconImage(icon);
    }

}
