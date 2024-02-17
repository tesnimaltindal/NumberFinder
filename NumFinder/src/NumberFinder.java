import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;
//DOĞRU ALGORİTMA
//COUNT EKLE
public class NumberFinder extends JFrame implements ActionListener {
    private JComboBox<String> comboBox;
    private JLabel ValueByComputer;
    private JButton buttonSend;
    private int[] numbers;
    private int low;
    private int high;

    public NumberFinder() {
        this.setTitle("<< Number Finder >>");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        String[] Probabilities = {"MoreBIGGER", "MoreSMALLER", "TRUE"}; // array for probabilities
        comboBox = new JComboBox<>(Probabilities); // creating combo box
        comboBox.addActionListener(this);
        this.add(comboBox);

        // button for send
        buttonSend = new JButton("Send");
        buttonSend.addActionListener(this);
        this.add(buttonSend);

        // label for display guess
        ValueByComputer = new JLabel("First 50? ");
        this.add(ValueByComputer);

        // initialize array in the constructor
        numbers = IntStream.rangeClosed(1, 100).toArray();
        low = 0;
        high = numbers.length - 1;
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSend || e.getSource() == comboBox) {
            if (high >= low) {
                int middlePosition = (low + high) / 2;
                int currentGuess = numbers[middlePosition];

                if (comboBox.getSelectedItem().equals("MoreBIGGER")) {
                    // middle number<number to find
                    low = middlePosition + 1;
                } else if (comboBox.getSelectedItem().equals("MoreSMALLER")) {
                    // middle number>number to find
                    high = middlePosition - 1;
                } else {
                    // number to find == middle number
                    ValueByComputer.setText("I FOUND IT! It is " + currentGuess);
                    // Reset range after finding the number
                    low = 0;
                    high = numbers.length - 1;
                    return; // Exit the method to avoid updating the label twice
                }

                if (high >= low) {
                    // Display the current guess
                    ValueByComputer.setText("Is it " + numbers[(low + high) / 2] + " ?");
                } else {
                    ValueByComputer.setText("The number is not in the list.");
                }
            }
        }
    }

    public static void main(String[] args) {
        new NumberFinder();
    }
}
