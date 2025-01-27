import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PercentageCalculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PercentageCalculator::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Percentage Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create components
        JLabel instructionsLabel = new JLabel("Enter values and select a calculation type:");
        JTextField value1Field = new JTextField(10);
        JTextField value2Field = new JTextField(10);
        JComboBox<String> calculationType = new JComboBox<>(new String[]{
                "Calculate Percentage",
                "Percentage Increase",
                "Percentage Decrease",
                "Find Whole from Part and Percentage"
        });
        JButton calculateButton = new JButton("Calculate");
        JLabel resultLabel = new JLabel("Result: ");

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.add(instructionsLabel);
        panel.add(new JLabel("Value 1 (e.g., Part, Base, Original):"));
        panel.add(value1Field);
        panel.add(new JLabel("Value 2 (e.g., Percentage, Change):"));
        panel.add(value2Field);
        panel.add(calculationType);
        panel.add(calculateButton);
        panel.add(resultLabel);

        frame.add(panel);

        // Add action listener
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double value1 = Double.parseDouble(value1Field.getText());
                    double value2 = Double.parseDouble(value2Field.getText());
                    String selectedCalculation = (String) calculationType.getSelectedItem();
                    double result = 0;

                    switch (selectedCalculation) {
                        case "Calculate Percentage":
                            result = (value1 / value2) * 100;
                            break;
                        case "Percentage Increase":
                            result = ((value2 - value1) / value1) * 100;
                            break;
                        case "Percentage Decrease":
                            result = ((value1 - value2) / value1) * 100;
                            break;
                        case "Find Whole from Part and Percentage":
                            result = (value1 / value2) * 100;
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown calculation type.");
                    }

                    resultLabel.setText(String.format("Result: %.2f", result));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
