/*
 Book Cricket Game
 User can play against the computer.
 Coin toss and runs are generated using secure random numbers.
 The player who wins the coin toss gets to bat first.
 If 0 or 8 is generated as a run the current player is out.
 * */
import java.security.SecureRandom;
import java.util.Scanner;

public class Cricket {
	private int bookPages;
	private int overs;
	private int userTossCall;
	private int tossResult;
	private int userScore;
	private int computerScore;
	private int [] runsPerOver;
	Cricket(int bookPages, int userTossCall,int overs){
		this.bookPages = bookPages;
		this.userTossCall = userTossCall;
		this.overs= overs;
		runsPerOver = new int[this.overs];
	}
	private enum Player{USER, COMPUTER}
	
	
	public void start() {
		Player p =coinToss();
		gamePlay(p);
	}
	
	private void gamePlay(Player p) {
		Player opponent;
		if(p == Player.USER) {
			opponent = Player.COMPUTER;
		}else {
			opponent = Player.USER;
		}
		
		int score1 = batting(p);
		int score2 = batting(opponent);
		if(p==Player.USER) {
			userScore = score1;
			computerScore=score2;
			result(userScore,computerScore);
			
		}else {
			computerScore= score1;
			userScore = score2;
			result(userScore,computerScore);
		}
		matchStats();
		
	}

	private void result(int userScore, int computerScore) {
		System.out.println("Your Runs: "+userScore+" Computer Runs: "+computerScore);
		if (userScore==computerScore){
			System.out.println("Match Draw");
		}
		
		else if(userScore>computerScore) {
			System.out.println("You Win");
		}else {
			System.out.println("Computer Wins");
		}
		
	}

	private int batting(Player p) {
		if(p==Player.USER) {
			System.out.println("You Start Batting");
		}else {
			System.out.println("Computer Starts Batting");
		}
		int score=0;
		SecureRandom random = new SecureRandom();
		int runs = (2*random.nextInt(bookPages))%10;
		int i=0;
		while(runs==2||runs==4||runs==6) {
			System.out.println(runs+" runs");
			score+=runs;
			if(p==Player.USER) {runsPerOver[i/6]+=runs;}
			runs=(2*random.nextInt(bookPages))%10;
			i++;
			if(i/6==overs) {
				break;
			}
		}
		if(runs==0||runs==8) {
			System.out.println(runs+" Out!!!");
		}
		return score;
	}

	private Player coinToss() {
		SecureRandom random = new SecureRandom();
		tossResult = random.nextInt(2);
		if(userTossCall == tossResult) {
			System.out.println("You won the toss");
			return Player.USER;
		}else {
			System.out.println("Computer wins the toss");
			return Player.COMPUTER;
		}
		
	}
	private void matchStats() {
		System.out.println("Your Runs Statistics");
		for(int i=0;i<overs;i++) {
			System.out.println("Over "+(i+1)+" : "+runsPerOver[i]+" runs");
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Hello Welcome To Book Cricket");
		System.out.println("Rules are simple you play against the computer");
		System.out.println("If you win coin toss you get to bat first");
		System.out.println("If you hit a 0 or 8 you are out");
		
		System.out.println("Ënter the number of pages in your book");
		int pages = s.nextInt();
		System.out.println("Enter Total Overs For the Match");
		int overs=s.nextInt();
		System.out.println("Toss Time 0 for head and 1 for tail");
		int tossCall = s.nextInt();
		
		Cricket game = new Cricket(pages, tossCall,overs);
		game.start();
		s.close();
	}

}
/*
 Sample Output
 
Hello Welcome To Book Cricket
Rules are simple you play against the computer
If you win coin toss you get to bat first
If you hit a 0 or 8 you are out
Ënter the number of pages in your book
300
Enter Total Overs For the Match
2
Toss Time 0 for head and 1 for tail
0
Computer wins the toss
Computer Starts Batting
2 runs
0 Out!!!
You Start Batting
2 runs
2 runs
0 Out!!!
Your Runs: 4 Computer Runs: 2
You Win
Your Runs Statistics
Over 1 : 4 runs
Over 2 : 0 runs
 */