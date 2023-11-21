
// Importing necessary packages for GUI components, events, and formatting
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

// Defining the main class that represents the currency converter application and extends JFrame
public class Currency_Converter extends JFrame {

    // Declaration of GUI components and variables
    private JLabel amountLabel, fromLabel, toLabel, resultLabel;
    private JTextField amountField;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton convertButton;
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    // Arrays to store currency codes and exchange rates
    private final String[] currencies = {"USD", "EUR", "JPY", "GBP", "CAD", "AUD", "CHF", "CNY","INR"};
    private double[] exchangeRates = {1.00, 0.84, 109.65, 0.72, 1.27, 1.30, 0.92, 6.47, 87.14};

    // Constructor method for initializing the GUI
    public Currency_Converter() {
        // Setting JFrame properties
        setTitle("Currency Converter");
        setLayout(new GridLayout(4, 2)); // Setting layout to a 4x2 grid

        // Creating and adding labels and text fields to the JFrame
        amountLabel = new JLabel("Amount:");
        add(amountLabel);

        amountField = new JTextField();
        add(amountField);

        fromLabel = new JLabel("From:");
        add(fromLabel);

        fromComboBox = new JComboBox<>(currencies);
        add(fromComboBox);

        toLabel = new JLabel("To:");
        add(toLabel);

        toComboBox = new JComboBox<>(currencies);
        add(toComboBox);

        // Adding a button to trigger the currency conversion
        convertButton = new JButton("Convert");
        add(convertButton);

        // Adding a label to display the result of the conversion
        resultLabel = new JLabel();
        add(resultLabel);

        // Adding an ActionListener to the Convert button to handle button clicks
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Getting input values and performing the currency conversion
                    double amount = Double.parseDouble(amountField.getText());
                    String fromCurrency = (String) fromComboBox.getSelectedItem();
                    String toCurrency = (String) toComboBox.getSelectedItem();
                    double exchangeRate = exchangeRates[getIndex(toCurrency)] / exchangeRates[getIndex(fromCurrency)];
                    double result = amount * exchangeRate;
                    // Displaying the result with proper formatting
                    resultLabel.setText(decimalFormat.format(result) + " " + toCurrency);
                } catch (Exception ex) {
                    // Handling invalid input by displaying an error message
                    resultLabel.setText("Invalid input");
                }
            }
        });

        // Setting JFrame size, making it visible, and defining close operation
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Utility method to get the index of a currency in the array
    private int getIndex(String currency) {
        for (int i = 0; i < currencies.length; i++) {
            if (currency.equals(currencies[i])) {
                return i;
            }
        }
        return -1; // Return -1 if the currency is not found
    }

    // Main method to start the application
    public static void main(String[] args) {
        new Currency_Converter(); // Creating an instance of the Currency_Converter class
    }
}
