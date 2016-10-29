package calculators;

/*
 * This calculator class only contains the four basic operations, modulo, and added a clear and backspace feature
 * Made by Rayven Ingles
 * All wrongs reversed 2016
 * 
 */

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.math.BigDecimal;

import javax.swing.SwingConstants;

public class CalcuMain {

	private JFrame frmCalculator;
	private JTextField textField;
	//scale variable is necessary to keep the formatting of the BigDecimal values from having too many decimal places
	private int scale;
	//BigDecimal numbers with scale zero are automatically treated as BigInteger values
	BigDecimal firstNumber;
	BigDecimal secondNumber;
	BigDecimal result;
	//string necessary for the equals operator to determine the type of operation
	String operations;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalcuMain window = new CalcuMain();
					window.frmCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		});
	}

	/**
	 * Create the application.
	 */
	public CalcuMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame. 
	 * most of the code here were automatically generated by the design template mode in Eclipse Luna
	 * only the Action Event codes were done manually
	 * each digit when pressed after the decimal point increment the scale of the number and pressing any operator resets the scale to zero
	 */
	private void initialize() {
		frmCalculator = new JFrame();
		frmCalculator.setTitle("Calculator");
		frmCalculator.setBounds(100, 100, 250, 375);
		frmCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculator.getContentPane().setLayout(null);
		frmCalculator.setResizable(false);
		
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(10, 11, 211, 60);
		frmCalculator.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		
		JButton buttonSeven = new JButton("7");
		buttonSeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("."))
					scale ++;
				textField.setText(textField.getText() + "7");
			}
		});
		buttonSeven.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonSeven.setBounds(10, 133, 47, 40);
		frmCalculator.getContentPane().add(buttonSeven);
		
		JButton buttonEight = new JButton("8");
		buttonEight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("."))
					scale ++;
				textField.setText(textField.getText() + "8");
			}
		});
		buttonEight.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonEight.setBounds(60, 133, 47, 40);
		frmCalculator.getContentPane().add(buttonEight);
		
		JButton buttonNine = new JButton("9");
		buttonNine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("."))
					scale ++;
				textField.setText(textField.getText() + "9");
			}
		});
		buttonNine.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonNine.setBounds(110, 133, 47, 40);
		frmCalculator.getContentPane().add(buttonNine);
		
		JButton button_6 = new JButton("\u00B1");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstNumber = new BigDecimal(Double.parseDouble(textField.getText()));
				firstNumber = firstNumber.negate();
				textField.setText(firstNumber.toString());
			}
		});
		button_6.setFont(new Font("Calibri", Font.BOLD, 16));
		button_6.setBounds(115, 286, 47, 40);
		frmCalculator.getContentPane().add(button_6);
		
		JButton buttonFour = new JButton("4");
		buttonFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("."))
					scale ++;
				textField.setText(textField.getText() + "4");
			}
		});
		buttonFour.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonFour.setBounds(10, 184, 47, 40);
		frmCalculator.getContentPane().add(buttonFour);
		
		JButton buttonFive = new JButton("5");
		buttonFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("."))
					scale ++;
				textField.setText(textField.getText() + "5");
			}
		});
		buttonFive.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonFive.setBounds(60, 184, 47, 40);
		frmCalculator.getContentPane().add(buttonFive);
		
		JButton buttonSix = new JButton("6");
		buttonSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("."))
					scale ++;
				textField.setText(textField.getText() + "6");
			}
		});
		buttonSix.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonSix.setBounds(110, 184, 47, 40);
		frmCalculator.getContentPane().add(buttonSix);
		
		JButton buttonDivide = new JButton("/");
		buttonDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstNumber = new BigDecimal(textField.getText());
				firstNumber.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
				scale = 0;
				textField.setText("");
				operations = "/";
			}
		});
		buttonDivide.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonDivide.setBounds(174, 82, 47, 40);
		frmCalculator.getContentPane().add(buttonDivide);
		
		JButton buttonOne = new JButton("1");
		buttonOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("."))
					scale ++;
				textField.setText(textField.getText() + "1");
			}
		});
		buttonOne.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonOne.setBounds(10, 235, 47, 40);
		frmCalculator.getContentPane().add(buttonOne);
		
		JButton buttonTwo = new JButton("2");
		buttonTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("."))
					scale ++;
				textField.setText(textField.getText() + "2");
			}
		});
		buttonTwo.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonTwo.setBounds(60, 235, 47, 40);
		frmCalculator.getContentPane().add(buttonTwo);
		
		JButton buttonThree = new JButton("3");
		buttonThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("."))
					scale ++;
				textField.setText(textField.getText() + "3");
			}
		});
		buttonThree.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonThree.setBounds(110, 235, 47, 40);
		frmCalculator.getContentPane().add(buttonThree);
		
		JButton buttonTimes = new JButton("*");
		buttonTimes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstNumber = new BigDecimal(textField.getText());
				firstNumber.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
				scale = 0;
				textField.setText("");
				operations = "*";
			}
		});
		buttonTimes.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonTimes.setBounds(174, 133, 47, 40);
		frmCalculator.getContentPane().add(buttonTimes);
		
		JButton buttonZero = new JButton("0");
		buttonZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains("."))
					scale ++;
				textField.setText(textField.getText() + "0");
			}
		});
		buttonZero.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonZero.setBounds(10, 286, 57, 40);
		frmCalculator.getContentPane().add(buttonZero);
		
		JButton buttonDecimal = new JButton(".");
		buttonDecimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contains(".")){
					//do nothing
				}
				else{
					textField.setText(textField.getText() + ".");
					scale = 0;
				}
			}
		});
		buttonDecimal.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonDecimal.setBounds(70, 289, 40, 35);
		frmCalculator.getContentPane().add(buttonDecimal);
		
		JButton buttonMinus = new JButton("-");
		buttonMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstNumber = new BigDecimal(textField.getText());
				firstNumber.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
				scale = 0;
				textField.setText("");
				operations = "-";
			}
		});
		buttonMinus.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonMinus.setBounds(174, 184, 47, 40);
		frmCalculator.getContentPane().add(buttonMinus);
		
		JButton buttonPlus = new JButton("+");
		buttonPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstNumber = new BigDecimal(textField.getText());
				firstNumber.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
				scale = 0;
				textField.setText("");
				operations = "+";
			}
		});
		buttonPlus.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonPlus.setBounds(174, 235, 47, 40);
		frmCalculator.getContentPane().add(buttonPlus);
		
		JButton buttonC = new JButton("C");
		buttonC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operations = "=";
				textField.setText("");
				firstNumber = BigDecimal.ZERO;
			}
		});
		buttonC.setFont(new Font("Calibri", Font.BOLD, 12));
		buttonC.setBounds(10, 82, 47, 40);
		frmCalculator.getContentPane().add(buttonC);
		
		JButton backspace = new JButton("<-");
		backspace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length() > 0){
					StringBuilder strb = new StringBuilder(textField.getText());
					strb.deleteCharAt(textField.getText().length() - 1);
					textField.setText(strb.toString());
				}
				
			}
		});
		backspace.setFont(new Font("Calibri", Font.BOLD, 10));
		backspace.setBounds(60, 82, 47, 40);
		frmCalculator.getContentPane().add(backspace);
		
		JButton buttonPercent = new JButton("%");
		buttonPercent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstNumber = new BigDecimal(Double.parseDouble(textField.getText()));
				textField.setText("");
				operations = "%";
				
			}
		});
		buttonPercent.setFont(new Font("Calibri", Font.BOLD, 10));
		buttonPercent.setBounds(115, 82, 47, 40);
		frmCalculator.getContentPane().add(buttonPercent);
		
		JButton buttonEquals = new JButton("=");
		buttonEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//this is where everything comes together first checks if the equals sign was pressed right away
				if(textField.getText().isEmpty())
					return;
				else if(operations ==null ){
					//should go here if no operators have been clicked yet, then assigns it to the equals operator
					operations = "=";
				}
			/*
			 * takes whatever is on the screen as the second number, 
			 * assuming there was already a first number entered before clicking any operator symbol
			 * 
			 */
				
				secondNumber = new BigDecimal(textField.getText());
				
				switch (operations){
					case "+":{
						result = firstNumber.add(secondNumber);
						break;
					}case "-":{
						result = firstNumber.subtract(secondNumber);
						break;
					}case "*":{
						result = firstNumber.multiply(secondNumber);
						break;
					}case "/":{
						if(secondNumber.equals(BigDecimal.ZERO)){
							textField.setText("Cannot divide by zero! Clear screen!");
							
							}
						else
							result = firstNumber.divide(secondNumber);
						break;
					}case "%":{
						if(secondNumber.equals(BigDecimal.ZERO)){
							textField.setText("cannot divide by zero!");
							break;
							}
						else							
						result = firstNumber.remainder(secondNumber);
						break;
					}case "=":{
												
						result = new BigDecimal(Double.parseDouble(textField.getText()));
						break;
					}
					default:
						break;
				}
				scale = (firstNumber.scale() > secondNumber.scale() ? firstNumber.scale() : secondNumber.scale());
				if(! (operations.equals("/") && secondNumber.equals(BigDecimal.ZERO)))
					textField.setText(result.setScale(scale,BigDecimal.ROUND_HALF_DOWN).toPlainString());
				
			}
		});
		buttonEquals.setFont(new Font("Calibri", Font.BOLD, 16));
		buttonEquals.setBounds(167, 286, 57, 40);
		frmCalculator.getContentPane().add(buttonEquals);
	}
}
