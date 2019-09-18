import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;


public class SimpleCalculator {

    // creating a button handler and the actions of each of the buttons in the user interface
    public static class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // identifying which event was fired
            String command = ((Button) event.getSource()).getActionCommand();

            // assigning the actions when the numerical buttons are pushed
            for (int i = 0; i<eNames.length; i++) {
                if (command.equals(eNames[i])) {
                    window.setText(window.getText()+i);
                    if (operation.equals("=")) {
                        window.setText("");
                        operation = "";
                        window.setText(window.getText()+i);
                    }
                }
            }
            if (operation.equals("=")) operation = "";

            // assigning the actions for the function buttons
            switch (command) {
                case "Reset":
                    window.setText("");
                    break;
                case "Delete":
                    String prevString = window.getText();
                    String newString = prevString.substring(0, prevString.length()-1);
                    window.setText(newString); // need to change this
                    break;
                case "+":
                    window.setText(window.getText()+"+");
                    operation = "+";
                    break;
                case "-":
                    window.setText(window.getText()+"-");
                    operation = "-";
                    break;
                case "*":
                    window.setText(window.getText()+"*");
                    operation = "*";
                    break;
                case "/":
                    window.setText(window.getText()+"/");
                    operation = "/";
                    break;
                case "=":
                    String equation = window.getText();
                    for (int i = 0; i < equation.length(); i++) {
                        if (equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '*' || equation.charAt(i) == '/') {
                            if (i == 0) {
                                expStack.push(Double.toString(result));
                                expStack.push(equation.substring(i+1));
                            }
                            else {
                                expStack.push(equation.substring(0, i));
                                expStack.push(equation.substring(i + 1));
                            }
                        }
                    }
                    Double second = Double.parseDouble(expStack.pop());
                    Double first = Double.parseDouble(expStack.pop());
                    switch (operation) {
                        case "+":
                            result = first + second;
                            break;
                        case "-":
                            result = first - second;
                            break;
                        case "*":
                            result = first * second;
                            break;
                        case "/":
                            result = first / second;
                            break;
                    }
                    window.setText( Double.toString(result));
                    operation = "=";
                break;
            }
        }
    }

    // creating the variables that are shared by the main and ButtonHandler
    private static String[] eNames;
    private static Label window;
    private static Frame calculator;
    private static Stack<String> expStack;
    private static String operation;
    private static Double result;

    public static void main(String[] args) {
        Button b0, b1, b2, b3, b4, b5 ,b6, b7, b8, b9;
        Button bReset, bDelete, bAdd, bSubtract, bMultiply, bDivide, bEquals;
        // instantiating the number buttons
        b0 = new Button("0");
        b0.setPreferredSize(new Dimension(6,20));
        b1 = new Button("1");
        b1.setPreferredSize(new Dimension(6,20));
        b2 = new Button("2");
        b2.setPreferredSize(new Dimension(6,20));
        b3 = new Button("3");
        b3.setPreferredSize(new Dimension(6,20));
        b4 = new Button("4");
        b4.setPreferredSize(new Dimension(6,20));
        b5 = new Button("5");
        b5.setPreferredSize(new Dimension(6,20));
        b6 = new Button("6");
        b6.setPreferredSize(new Dimension(6,20));
        b7 = new Button("7");
        b7.setPreferredSize(new Dimension(6,20));
        b8 = new Button("8");
        b8.setPreferredSize(new Dimension(6,20));
        b9 = new Button("9");
        b9.setPreferredSize(new Dimension(6,20));

        // instantiating the function buttons
        bReset = new Button("Reset");
        bReset.setPreferredSize(new Dimension(6,20));
        bDelete = new Button("Delete");
        bDelete.setPreferredSize(new Dimension(6,20));
        bAdd = new Button("+");
        bAdd.setPreferredSize(new Dimension(6,20));
        bSubtract = new Button("-");
        bSubtract.setPreferredSize(new Dimension(6,20));
        bMultiply = new Button("*");
        bMultiply.setPreferredSize(new Dimension(6,20));
        bDivide = new Button("/");
        bDivide.setPreferredSize(new Dimension(6,20));
        bEquals = new Button("=");
        bEquals.setPreferredSize(new Dimension(3,10));
        // instantiating the variables
        eNames = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine"};
        String[] eNames2 = new String[]{"Reset","Delete","+","-","*","/","="};
        Button[] bArray = new Button[]{b0, b1,b2,b3,b4,b5,b6,b7,b8,b9};
        Button[] bArray2 = new Button[]{bReset, bDelete, bAdd, bSubtract, bMultiply, bDivide, bEquals};
        ButtonHandler Arithmetic = new ButtonHandler();
        window = new Label();
        calculator = new Frame();
        operation = "";
        expStack = new Stack<>();
        result = 0.0;

        // instantiating the buttons and naming the events for numerical buttons
        int arrayLength = bArray.length;
        for( int j = 0; j <arrayLength; j++) {
            bArray[j].setActionCommand(eNames[j]);
            bArray[j].addActionListener(Arithmetic);
        }

        // instantiating the buttons and naming the events for function buttons
        int arrayLength2 = eNames2.length;
        for (int j = 0; j<arrayLength2; j++) {
            bArray2[j].setActionCommand(eNames2[j]);
            bArray2[j].addActionListener(Arithmetic);
        }

        // creating and instantiate the panels that will hold the buttons
        Panel panel1 = new Panel();
        Panel panel2 = new Panel();
        Panel panel3 = new Panel();
        Panel panel4 = new Panel();
        Panel panel5 = new Panel();

        // defining and setting the layout for each of the panels
        BoxLayout layout1 = new BoxLayout(panel1, BoxLayout.X_AXIS);
        panel1.setLayout(layout1);
        // for buttons 7,8,9 and +
        BoxLayout layout2 = new BoxLayout(panel2, BoxLayout.X_AXIS);
        panel2.setLayout(layout2);
        // for buttons 4,5,6 and -
        BoxLayout layout3 = new BoxLayout(panel3, BoxLayout.X_AXIS);
        panel3.setLayout(layout3);
        // for buttons 1,2,3 and *
        BoxLayout layout4 = new BoxLayout(panel4,BoxLayout.X_AXIS);
        panel4.setLayout(layout4);
        // for buttons Reset, 0, Delete and /
        BoxLayout layout5 = new BoxLayout(panel5,BoxLayout.X_AXIS);
        panel5.setLayout(layout5);

        // adding the Label window and = button into the panel1
        panel1.add(window);
        panel1.add(bEquals);
        // adding the buttons 7,8,9 and + into the panel2
        panel2.add(b7);
        panel2.add(b8);
        panel2.add(b9);
        panel2.add(bAdd);
        // adding the buttons 4,5,6 and -  into the panel3
        panel3.add(b4);
        panel3.add(b5);
        panel3.add(b6);
        panel3.add(bSubtract);
        // adding the buttons 1,2,3 and *  into the panel4
        panel4.add(b1);
        panel4.add(b2);
        panel4.add(b3);
        panel4.add(bMultiply);
        // adding the buttons Reset, 0, Delete and / into the panel5
        panel5.add(bReset);
        panel5.add(b0);
        panel5.add(bDelete);
        panel5.add(bDivide);

        // adding the panels into the calculator frame
        calculator.setPreferredSize(new Dimension(400,400));
        calculator.setLayout(new GridLayout(5,1));
        calculator.add(panel1);
        calculator.add(panel2);
        calculator.add(panel3);
        calculator.add(panel4);
        calculator.add(panel5);
        calculator.pack();
        calculator.show();

        // register the window closing event
        calculator.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event){
                calculator.dispose();
                System.exit(0);
            }
        });
    }
}
