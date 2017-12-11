package GUI;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener{

    private Board board;
    private Timer timer;

    public Main(Board board) {
        this.board = board;
    }

    public static void main(String[] args) {
        Board board = new Board();

        JFrame frame = new BoardFrame(board);
        frame.setVisible(true);

        Main m = new Main(board);
        m.startGame();
    }

    public void startGame(){
        timer = new Timer(500, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (!board.makeTick()) {
        	timer.stop();
        }

    }
}