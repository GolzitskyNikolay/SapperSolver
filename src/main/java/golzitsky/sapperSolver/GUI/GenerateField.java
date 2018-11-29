package golzitsky.sapperSolver.GUI;

import golzitsky.sapperSolver.core.*;

import javax.swing.*;
import java.awt.*;

class GenerateField extends Field {
    private GameLogic gameLogic;
    private BotLogic botLogic = new BotLogic();
    private Field field;
    private int mapSize;
    private int numberOfOpenButton;
    private Cell[] buttons;

    int getNumberOfOpenButton() {
        return numberOfOpenButton;
    }

    private Image closed = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/closed.png"));

    /**
     * Create array of Cells.
     */
    void createEmptyField(JPanel panel, Field ourField, GameLogic gameLogic) {
        field = ourField;
        buttons = field.buttons;
        mapSize = field.mapSize;
        this.gameLogic = gameLogic;
        for (int i = 0; i < mapSize * mapSize; i++) {
            buttons[i] = new RedrawCell();
            buttons[i].setPreferredSize(new Dimension(50, 50));
            buttons[i].setIcon(new ImageIcon(closed));
            panel.add(buttons[i]);
        }
        generateFieldByFirstOpenButton();
    }

    /**
     * Start generate field, open random cell, this cell hasn't bomb. Then each cell become or empty,
     * or with bomb, or with digit, that shows number of bombs around this cell.
     */
    private void generateFieldByFirstOpenButton() {
        numberOfOpenButton = botLogic.numberOfRandomOpenButton(mapSize);
        buttons[numberOfOpenButton].firstButtonHasntBomb();
        for (int j = 0; j < mapSize * mapSize; j++) {
            if (j != numberOfOpenButton) {
                buttons[j].chanceOfBomb(field, buttons[j]);
                if (buttons[j].isHasBomb()) {
                    field.numberOfBombs++;
                    field.allNumbersOfBombs.add(j);
                }
            }
        }
        for (int j = 0; j < mapSize * mapSize; j++) {
            int numberOfBombs = -1;
            if (!buttons[j].isHasBomb()) {
                numberOfBombs = gameLogic.countBombsAroundCell(buttons, j, mapSize);
            }
            buttons[j].countOfBombs(numberOfBombs);
        }
    }
}