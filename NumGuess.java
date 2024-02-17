package GuessingGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class NumGuess extends JFrame {
    private static final long serialVersionUID = 1L;
    private int randomNumber;
    private int score = 0;
    private int attempts = 0;
    private int guessesLeft = 5;
    private JTextField UserGuess;
    private JTextArea Outcome;
    private JLabel scoreLabel;
    private JLabel attemptsLabel;

    public NumGuess() {
    	getContentPane().setBackground(new Color(175, 238, 238));
    	scoreLabel = new JLabel("Score: 0");
        attemptsLabel = new JLabel("Guesses left: " + guessesLeft);
        setTitle("Number Guessing Game");
        setSize(685, 607);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        randomNumber = (int) (Math.random() * 100) + 1;

        JPanel panel = new JPanel();
        panel.setBackground(new Color(176, 196, 222));
        panel.setBounds(43, 143, 578, 393);

        Container contentPane = getContentPane();
        getContentPane().setLayout(null);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel GuessHere = new JLabel("Write your guess here");
        GuessHere.setHorizontalAlignment(SwingConstants.LEFT);
        GuessHere.setFont(new Font("SimSun", Font.BOLD, 20));
        GuessHere.setBounds(10, 8, 558, 106);
        panel.add(GuessHere);
        JButton GUESS = new JButton();
        GUESS.setIcon(new ImageIcon("C:\\Users\\dell\\Downloads\\icons8-google-web-search-100.png"));
        GUESS.setBounds(277, 124, 96, 79);
        panel.add(GUESS);
        UserGuess = new JTextField(10);
        UserGuess.setFont(new Font("Tahoma", Font.PLAIN, 30));
        UserGuess.setBounds(245, 24, 306, 79);
        panel.add(UserGuess);
        Outcome = new JTextArea(5, 20);
        Outcome.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, new Color(0, 139, 139)));
        Outcome.setFont(new Font("MV Boli", Font.BOLD, 20));
        Outcome.setBounds(24, 213, 527, 170);
        panel.add(Outcome);
        Outcome.setEditable(false);
        
        JButton RESET = new JButton();
        RESET.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		resetGame();
        	}
        });
        RESET.setIcon(new ImageIcon("C:\\Users\\dell\\Downloads\\icons8-reset-50.png"));
        RESET.setBounds(171, 124, 96, 79);
        panel.add(RESET);
        
        JLabel Heading = new JLabel("Number Guessing Game");
        Heading.setBackground(new Color(255, 255, 255));
        Heading.setHorizontalAlignment(SwingConstants.CENTER);
        Heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        Heading.setBounds(54, 10, 521, 39);
        getContentPane().add(Heading);
        
        JLabel GuessNumber = new JLabel(":::::::: Guess Number Between 1 to 100 ::::::::");
        GuessNumber.setForeground(new Color(128, 0, 0));
        GuessNumber.setHorizontalAlignment(SwingConstants.CENTER);
        GuessNumber.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        GuessNumber.setBounds(71, 75, 467, 39);
        getContentPane().add(GuessNumber);
        
                GUESS.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        checkGuess();
                    }
                });
                
    }
    
    

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(UserGuess.getText());
            attempts++;
            guessesLeft--;

            if (guess == randomNumber) {
                score += guessesLeft * 10 + 50;
                Outcome.append("CONGRATULATIONS!!!!!! YOU   WIN.\n");
                Outcome.append("Score for this guess: " + (guessesLeft * 10 + 50) + "\n");
                UserGuess.setEditable(false);
            } else {
                if (guessesLeft == 0) {
                    Outcome.append("Game over! The correct number was " + randomNumber + ".\n");
                    UserGuess.setEditable(false);
                } else {
                    String text = guess < randomNumber ? "Try higher number  " : "Try lower number";
                    Outcome.append(text + ": Attempts left: " + guessesLeft + "\n");
                }
            }
        } catch (NumberFormatException ex) {
            Outcome.append("Please enter a valid number.\n");
        }

        UserGuess.setText("");
        updateLabels();
    }
    
    private void updateLabels() {
        scoreLabel.setText("Score: " + score);
        attemptsLabel.setText("Guesses left: " + guessesLeft);
    }
    private void resetGame() {
        randomNumber = (int) (Math.random() * 100) + 1;
        guessesLeft = 10;
        score = 0;
        attempts = 0;
        UserGuess.setEditable(true);
        Outcome.setText("");
        updateLabels();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumGuess().setVisible(true);
            }
        });
    }
}
