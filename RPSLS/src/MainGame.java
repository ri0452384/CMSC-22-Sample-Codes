import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;


@SuppressWarnings("serial")
public class MainGame extends Frame {
	
	
	int playerScore;
	int computerScore;
	Dialog repeat;
	
	//private final String[] hand = new String[] {"Spock","Rock","Paper","Scissors","Lizard"};
	
	public MainGame(){
		
		Label lbl1 = new Label("Your Choice.");
		CheckboxGroup choices = new CheckboxGroup();
		add(new Checkbox("Rock",choices,true));
		add(new Checkbox("Paper",choices,true));
		add(new Checkbox("Scissors",choices,true));
		add(new Checkbox("Lizard",choices,true));
		add(new Checkbox("Spock",choices,true));
		TextArea txtResult = new TextArea();
		
		Button fight = new Button("RockPaperScissorsLizardSpock!");
		playerScore = 0;
		TextField pScore = new TextField(playerScore);
		computerScore = 0;
		TextField cScore = new TextField(computerScore);
		
		//adding components to the containers
		this.add(lbl1);
		this.add(txtResult);
		this.add(fight);
		this.add(new Label("Player Score: "));
		this.add(pScore);
		this.add(new Label("Computer Score: "));
		this.add(cScore);
		
		
		//adding the listeners
		
		fight.addActionListener(new ActionListener(){

			private String playerChoice;

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(choices.getSelectedCheckbox().getName());
				
				Random rnd = new Random();
				String compChoice = "";
				int computerSelection = rnd.nextInt(5);
				switch(computerSelection){
					case 0: {
						compChoice = "Spock";
						break;
					}case 1: {
						compChoice = "Rock";
						break;
					}case 2: {
						compChoice = "Paper";
						break;
					}case 3: {
						compChoice = "Scissors";
						break;
					}case 4: {
						compChoice = "Lizard";
						break;
					}
				
				}
				playerChoice = choices.getSelectedCheckbox().getName();
				txtResult.setText(compute(playerChoice,compChoice));
				cScore.setText(Integer.toString(computerScore));
				pScore.setText(Integer.toString(playerScore));
				
				
				if(playerScore ==5 || computerScore == 5){
					
					gameOver();
					playerScore = 0;
					computerScore =0;
					cScore.setText(Integer.toString(computerScore));
					pScore.setText(Integer.toString(playerScore));
				}
			}
			
		}
		);
		
		this.setLayout(new FlowLayout());
		this.setTitle("RockPaperScissorsLizardSpock!");
		this.setSize(450, 300);
		this.setVisible(true);
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
	
	}
	//helper method to determine the game logic and returns the string of the winning move
	
	protected void gameOver() {
		
		
		
		if(playerScore ==5){
			repeat = new Dialog(this, "Game Over! YOU WIN!");
		}else{
			repeat = new Dialog(this, "Game Over! COMPUTER WINS!");
		}

		repeat.addWindowListener(new WindowListener(){
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			repeat.dispose();
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	});
		repeat.setSize(300, 50);
		repeat.setVisible(true);
		computerScore = 0;
		playerScore = 0;
	}

	private String compute(String playerChoice, String compChoice) {
		String result = "";
		
		
		//ROCK
		if(playerChoice.equals("checkbox1")){
			playerChoice = "Rock";
			if(compChoice.equals("Paper") || compChoice.equals("Spock")){
				
				
				result = compChoice + " beats " + playerChoice;
				computerScore++;
				
			}else if(playerChoice.equals(compChoice)){
				result = "It's a tie for this round!";
			}else{
				result = playerChoice +" beats "+compChoice;
				playerScore++;
			}
		//PAPER
		}else if(playerChoice.equals("checkbox2")){
			playerChoice = "Paper";
			if(compChoice.equals("Scissors") || compChoice.equals("Lizard")){
				result = compChoice + " beats " + playerChoice;
				computerScore++;
			}else if(playerChoice.equals(compChoice)){
				result = "It's a tie for this round!";
			}else{
				result = playerChoice +" beats "+compChoice;
				playerScore++;
			}
		//SCISSORS
		}else if(playerChoice.equals("checkbox3")){
			playerChoice = "Scissors";
			if(compChoice.equals("Spock") || compChoice.equals("Rock")){
				result = compChoice + " beats " + playerChoice;
				computerScore++;
			}else if(playerChoice.equals(compChoice)){
				result = "It's a tie for this round!";
			}else{
				result = playerChoice +" beats "+compChoice;
				playerScore++;
			}
		//LIZARD
		}else if(playerChoice.equals("checkbox4")){
			playerChoice = "Lizard";
			if(compChoice.equals("Rock") || compChoice.equals("Scissors")){
				result = compChoice + " beats " + playerChoice;
				computerScore++;
			}else if(playerChoice.equals(compChoice)){
				result = "It's a tie for this round!";
			}else{
				result = playerChoice +" beats "+compChoice;
				playerScore++;
			}
		//SPOCK
		}else if(playerChoice.equals("checkbox0")){
			playerChoice = "Spock";
			if(compChoice.equals("Lizard") || compChoice.equals("Paper")){
				result = compChoice + " beats " + playerChoice;
				computerScore++;
			}else if(playerChoice.equals(compChoice)){
				result = "It's a tie for this round!";
			}else{
				result = playerChoice +" beats "+compChoice;
				playerScore++;
			}
		}else{
			result = "no choice selected!";
		}
		
		return result;
	}
	
	
	
	

 public static void main(String[] args){
		new MainGame();
	}
	
	
}
