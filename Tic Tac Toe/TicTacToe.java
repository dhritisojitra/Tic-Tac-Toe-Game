import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener{
    private JButton[][] buttons = new JButton[3][3];
    private boolean xTurn = true;

    public TicTacToe(){
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++){
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial Black", Font.BOLD, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JButton clickedButton = (JButton) e.getSource();
        if(!clickedButton.getText().equals("")){
            return;
        }
        clickedButton.setText(xTurn ? "x" : "o");

        if(CheckWinner()){
            JOptionPane.showMessageDialog(this, (xTurn ? "x" : "o") + " Wins! ");
            resetGame();
        }else if(isBoardFull()){
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }    
        xTurn = !xTurn;
    
    }

    private boolean CheckWinner(){
        for(int i = 0; i<3; i++){
            if(CheckLine(buttons[i][0], buttons[i][1], buttons[i][2]) || CheckLine(buttons[0][i], buttons[1][i], buttons[2][i])){
                return true;
            }
        }
        return CheckLine(buttons[0][0], buttons[1][1], buttons[2][2]) || CheckLine(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private boolean CheckLine(JButton b1, JButton b2, JButton b3){
        return !b1.getText().equals("") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    private boolean isBoardFull(){
        for(JButton[] row: buttons){
            for(JButton button : row){
                if(button.getText().equals("")){
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame(){
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setText("");
            }
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe().setVisible(true));
    }

}