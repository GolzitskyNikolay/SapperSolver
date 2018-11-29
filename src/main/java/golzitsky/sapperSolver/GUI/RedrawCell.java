package golzitsky.sapperSolver.GUI;

import golzitsky.sapperSolver.core.BotLogic;
import golzitsky.sapperSolver.core.Cell;
import golzitsky.sapperSolver.core.Field;
import golzitsky.sapperSolver.core.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.util.Queue;

class RedrawCell extends Cell {
    private BotLogic botLogic = new BotLogic();

    private Image foundBomb = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/foundBomb.png"));
    private Image bombed = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bombed.png"));
    private Image flag = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/flag.png"));
    private Image bomb = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bomb.png"));
    private Image zero = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/zero.png"));
    private Image num1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/num1.png"));
    private Image num2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/num2.png"));
    private Image num3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/num3.png"));
    private Image num4 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/num4.png"));
    private Image num5 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/num5.png"));
    private Image num6 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/num6.png"));
    private Image num7 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/num7.png"));
    private Image num8 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/num8.png"));

    /**
     * If Bot opened cell with bomb, then this method show all bombs in field.
     */
    private void showAllBombs(Field field, int i) {
        field.buttons[i].setIcon(new ImageIcon(bombed));
        field.allNumbersOfBombs.remove(i);
        field.allNumbersOfBombs.removeAll(field.knownNumbersOfFlags);
        for (Integer number : field.knownNumbersOfFlags) {
            field.buttons[number].setIcon(new ImageIcon(foundBomb));
        }
        for (Integer number : field.allNumbersOfBombs) {
            field.buttons[number].setIcon(new ImageIcon(bomb));
        }
    }

    /**
     * Change picture of cell, when Bot open this cell. If cell is empty, then call method
     * "openOrCountNotOpenedCellsOrFlagsAroundCell()" in BotLogic.
     */
    void openButton(Cell[] buttons, Field field, int i, int mapSize,
                    Queue<Integer> buttonsAroundEmptyButton, GameLogic gameLogic) {
        if (!buttons[i].isOpen()) {
            buttons[i].setOpen(true);
            field.quantityOfOpenButtons++;
            if (gameLogic.isLose(buttons[i])) showAllBombs(field, i);
            else if (buttons[i].countOfBombs == 0) {
                buttons[i].setIcon(new ImageIcon(zero));
                field.numbersOfEmptyButtons.add(i);
                botLogic.openOrCountNotOpenedCellsOrFlagsAroundCell(buttons, i, mapSize,
                        buttonsAroundEmptyButton, false, false);
            } else field.numbersOfOpenCellsWithDigit.add(i);
            if (buttons[i].countOfBombs == 1)
                buttons[i].setIcon(new ImageIcon(num1));
            else if (buttons[i].countOfBombs == 2)
                buttons[i].setIcon(new ImageIcon(num2));
            else if (buttons[i].countOfBombs == 3)
                buttons[i].setIcon(new ImageIcon(num3));
            else if (buttons[i].countOfBombs == 4)
                buttons[i].setIcon(new ImageIcon(num4));
            else if (buttons[i].countOfBombs == 5)
                buttons[i].setIcon(new ImageIcon(num5));
            else if (buttons[i].countOfBombs == 6)
                buttons[i].setIcon(new ImageIcon(num6));
            else if (buttons[i].countOfBombs == 7)
                buttons[i].setIcon(new ImageIcon(num7));
            else if (buttons[i].countOfBombs == 8)
                buttons[i].setIcon(new ImageIcon(num8));
        }
    }

    /**
     * When Bot found cell without bombs, it makes flag for this cell.
     */
    void makeFlag(Cell[] buttons, int i, Field field) {
        if (!buttons[i].isHasFlag()) {
            field.knownNumbersOfFlags.add(i);
            buttons[i].setIcon(new ImageIcon(flag));
            buttons[i].setFlag(true);
        }
    }

}