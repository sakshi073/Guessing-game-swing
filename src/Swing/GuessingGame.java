package Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame {

    private JTextField guessField;
    private JLabel feedbackLabel;
    private JButton guessButton;
    private JButton hintButton;
    private int randomNumber;
    private int attempts;
    private JLabel attemptsLabel;

    public GuessingGame() {
        setTitle("Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        generateRandomNumber();
        attempts = 0;

        createUI();

        setVisible(true);
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
    }

    private void createUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel promptLabel = new JLabel("Guess a number between 1 and 100:");
        panel.add(promptLabel);

        guessField = new JTextField();
        panel.add(guessField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessAction());
        panel.add(guessButton);

        hintButton = new JButton("Hint");
        hintButton.addActionListener(new HintAction());
        panel.add(hintButton);

        feedbackLabel = new JLabel("");
        panel.add(feedbackLabel);
        
        attemptsLabel = new JLabel("Attempts: 0");
        panel.add(attemptsLabel);

        add(panel);
    }

    private class GuessAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                if (guess < 1 || guess > 100) {
                    feedbackLabel.setText("Please enter a number between 1 and 100.");
                } else if (guess < randomNumber) {
                    feedbackLabel.setText("Too low. Try again.");
                } else if (guess > randomNumber) {
                    feedbackLabel.setText("Too high. Try again.");
                } else {
                    feedbackLabel.setText("Correct! You guessed the number in " + attempts + " attempts.");
                    guessButton.setEnabled(false);
                    hintButton.setEnabled(false);
                }
                attemptsLabel.setText("Attempts: " + attempts);
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
        }
    }

    private class HintAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int lowerBound = randomNumber - 10 > 0 ? randomNumber - 10 : 1;
            int upperBound = randomNumber + 10 <= 100 ? randomNumber + 10 : 100;
            feedbackLabel.setText("Hint: The number is between " + lowerBound + " and " + upperBound + ".");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessingGame());
    }
}
