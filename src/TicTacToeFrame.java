import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame implements ActionListener {


    private TicTacToeTile[][] board = new TicTacToeTile[3][3];
    private String[][] gameBoard = new String[3][3];

    private String player = "X";
    private int moveCount = 0;

    private JButton quitButton;

    public TicTacToeFrame() {

        setTitle("Tic Tac Toe");
        setSize(400,400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3,3));

        for(int row=0; row<3; row++){
            for(int col=0; col<3; col++){

                board[row][col] = new TicTacToeTile(row,col);
                board[row][col].setFont(new Font("Arial",Font.BOLD,40));
                board[row][col].setText(" ");
                board[row][col].addActionListener(this);

                boardPanel.add(board[row][col]);

                gameBoard[row][col] = " ";
            }
        }

        add(boardPanel,BorderLayout.CENTER);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        add(quitButton,BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        TicTacToeTile tile = (TicTacToeTile) e.getSource();

        int row = tile.getRow();
        int col = tile.getCol();

        if(!gameBoard[row][col].equals(" "))
        {
            JOptionPane.showMessageDialog(this,"Illegal move!");
            return;
        }

        gameBoard[row][col] = player;
        tile.setText(player);

        moveCount++;

        if(moveCount >= 5 && isWin(player))
        {
            JOptionPane.showMessageDialog(this,"Player "+player+" wins!");
            resetGame();
            return;
        }

        if(moveCount >= 7 && isTie())
        {
            JOptionPane.showMessageDialog(this,"It's a tie!");
            resetGame();
            return;
        }

        if(player.equals("X")) {
            player = "O";
        }
        else {
            player = "X";
        }
    }

    private void resetGame()
    {
        int response = JOptionPane.showConfirmDialog(this, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);

        if(response == JOptionPane.NO_OPTION)
        {
            System.exit(0);
        }

        player = "X";
        moveCount = 0;

        for(int row=0; row<3; row++)
        {
            for(int col=0; col<3; col++)
            {
                gameBoard[row][col] = " ";
                board[row][col].setText(" ");
            }
        }
    }

    private boolean isWin(String player)
    {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private boolean isRowWin(String player)
    {
        for(int row=0; row<3; row++) {
            if (gameBoard[row][0].equals(player) && gameBoard[row][1].equals(player) && gameBoard[row][2].equals(player)){
                return true;
            }
        }
        return false;
    }

    private boolean isColWin(String player)
    {
        for(int col=0; col<3; col++)
        {
            if(gameBoard[0][col].equals(player) && gameBoard[1][col].equals(player) && gameBoard[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String player)
    {
        if(gameBoard[0][0].equals(player) && gameBoard[1][1].equals(player) && gameBoard[2][2].equals(player)) {
            return true;
        }

        if(gameBoard[0][2].equals(player) && gameBoard[1][1].equals(player) && gameBoard[2][0].equals(player)) {
            return true;
        }

        return false;
    }

    private boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;

        // Check rows
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                if(gameBoard[row][col].equals("X"))
                    xFlag = true;
                if(gameBoard[row][col].equals("O"))
                    oFlag = true;
            }

            if(!(xFlag && oFlag)) {
                return false;
            }

            xFlag = false;
            oFlag = false;
        }

        for(int col = 0; col < 3; col++)
        {
            for(int row = 0; row < 3; row++)
            {
                if(gameBoard[row][col].equals("X"))
                    xFlag = true;
                if(gameBoard[row][col].equals("O"))
                    oFlag = true;
            }

            if(!(xFlag && oFlag)) {
                return false;
            }

            xFlag = false;
            oFlag = false;
        }

        // Check main diagonal
        if(gameBoard[0][0].equals("X") || gameBoard[1][1].equals("X") || gameBoard[2][2].equals("X")){
            xFlag = true;
        }
        if(gameBoard[0][0].equals("O") || gameBoard[1][1].equals("O") || gameBoard[2][2].equals("O")) {
            oFlag = true;
        }

        if(!(xFlag && oFlag)) {
            return false;
        }

        xFlag = false;
        oFlag = false;

        if(gameBoard[0][2].equals("X") || gameBoard[1][1].equals("X") || gameBoard[2][0].equals("X")){
            xFlag = true;
        }
        if(gameBoard[0][2].equals("O") || gameBoard[1][1].equals("O") || gameBoard[2][0].equals("O")){
            oFlag = true;
        }

        if(!(xFlag && oFlag)) {
            return false;
        }

        return true;
    }
}