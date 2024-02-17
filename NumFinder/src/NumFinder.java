import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;
//NumberFinder Class
//with the help of Binary Search TREE computer finds the number you have determine the number btw 1 and 100.
//cause of 2 based log100 is equal the 6,64385618977473 the algorithm generally founds in 7 tries.

public class NumFinder extends JFrame implements ActionListener {
    private JComboBox<String> comboBox;
    private JLabel ValueByComputer;
   // private JButton buttonSend;
    private int count;
    private int[] numbers;
    private int low;
    private int high;


    public NumFinder()
    {
        this.setTitle("<< Number Finder >> " + "(Beetween 1-100)" );
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        String[] Probabilities ={"MoreBIGGER","MoreSMALLER","TRUE"}; //array for probabilities
        comboBox = new JComboBox<>(Probabilities); //creating combo box
        comboBox.addActionListener(this);
        this.add(comboBox);
        //button for send //can be added but nıt necessary
      //  buttonSend = new JButton("Send");
      //  buttonSend.addActionListener(this);
      //  this.add(buttonSend);
        //label for display guess
        ValueByComputer = new JLabel("Firstly is it 50?");
        this.add(ValueByComputer);

        //initialize array in the constructor
        numbers= IntStream.rangeClosed(1,100).toArray();
        low=0;
        high=numbers.length-1;
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==comboBox) //can be added button code (but not necessary) : e.getSource()==buttonSend ||
        {
         if(high>=low)
            {
                int middlePosition= (low+high)/2;
                int currentGuess=numbers[middlePosition];

                if(comboBox.getSelectedItem().equals("MoreBIGGER"))
                {
                   // middle number<number to find
                    low=middlePosition+1;
                    count++;

                }
                else if (comboBox.getSelectedItem().equals("MoreSMALLER"))
                {
                 //middle number>number to find
                    high=middlePosition-1;
                    count++;

                }
                else {
                    count++;
                    //number to find== middle number
                    ValueByComputer.setText("I FOUND IT ! It is "+ currentGuess + " \n I have tried "+count +" times.");

                    //Reset range after finding the number
                    low=0;
                    high=numbers.length-1;
                    return;//Exit the method to avoid updating the label twice
                }


                ValueByComputer.setText("Is it "+ numbers[(low+high)/2]+" ?");
            }
         else {
             ValueByComputer.setText("The number is not in the list!!!");
            }
        }
    }
}
