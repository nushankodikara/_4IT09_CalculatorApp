import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame {
    private JTextField textField1;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton button4;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton button8;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton button12;
    private JButton a0Button;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JPanel mainPanel;
    private JButton button1;
    private JButton clearButton;
    private JButton clearAllButton;

    private String calculation = "";

    public SimpleCalculator() {
        ActionListener numberButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                String buttonText = button.getText();
                appendToTextField(buttonText);
            }
        };

        // Assign the shared listener to the number buttons
        a0Button.addActionListener(numberButtonListener);
        a1Button.addActionListener(numberButtonListener);
        a2Button.addActionListener(numberButtonListener);
        a3Button.addActionListener(numberButtonListener);
        a4Button.addActionListener(numberButtonListener);
        a5Button.addActionListener(numberButtonListener);
        a6Button.addActionListener(numberButtonListener);
        a7Button.addActionListener(numberButtonListener);
        a8Button.addActionListener(numberButtonListener);
        a9Button.addActionListener(numberButtonListener);

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendToTextField(" + ");
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendToTextField(" - ");
            }
        });

        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendToTextField(" * ");
            }
        });

        button16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendToTextField(" / ");
            }
        });

        button15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendToTextField(" % ");
            }
        });

        button14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendToTextField(".");
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = textField1.getText();
                if (!currentText.isEmpty()) {
                    textField1.setText(currentText.substring(0, currentText.length() - 1));
                }
            }
        });

        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
            }
        });
    }

    private void appendToTextField(String text) {
        textField1.setText(textField1.getText() + text);
    }

    private void calculate() {
        String expression = textField1.getText();
        String[] parts = expression.split(" ");
        if (parts.length % 2 == 1) {
            try {
                double result = Double.parseDouble(parts[0]);
                for (int i = 1; i < parts.length; i += 2) {
                    String operator = parts[i];
                    double num2 = Double.parseDouble(parts[i + 1]);

                    switch (operator) {
                        case "+":
                            result += num2;
                            break;
                        case "-":
                            result -= num2;
                            break;
                        case "*":
                            result *= num2;
                            break;
                        case "/":
                            result /= num2;
                            break;
                        case "%":
                            result %= num2;
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid operator");
                    }
                }

                textField1.setText(Double.toString(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid expression", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid expression", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new SimpleCalculator().mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
