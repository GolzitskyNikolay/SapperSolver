package golzitsky.sapperSolver.GUI;

import java.awt.*;

public class NewJFrame32 extends javax.swing.JFrame{
    private Image empty;
    private Image oneBomb;
    private Image twoBombs;
    private Image threeBombs;
    private Image fourBombs;
    private Image fiveBombs;
    private Image sixBombs;
    private Image sevenBombs;
    private Image eightBombs;

    public NewJFrame32(){
//        initComponents();
        empty = getToolkit().getImage(getClass().getResource("src\\main\\resources\\images\\closed.png"));
        setIconImage(empty);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g = rootPane.getGraphics();
        g.drawImage(empty, 10, 40, this);
    }

    public static void main(String[] args) {
    }
}
