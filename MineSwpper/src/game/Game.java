package game;

public class Game {
	public static Board testBoard;
	
	public static void main (String Args[]) {
		Boolean endGame = false;
		System.out.println("Welcome to minesweeper!  Choose your difficulty! \nLevel 1: Easy\nLevel 2: Medium\nLevel 3: Expert");
		int difficulty = TextIO.getInt();
		testBoard = new Board (difficulty);
		testBoard.initBoard(difficulty);
		playAGame();
	}
	
	
	private static void playAGame () {
		Boolean endGame = false;
		Boolean startGame = true;
		while (!endGame) {
			char flag;
			if (!startGame) {
				System.out.println("Type in the 'row column (optional f)', where f indicate if you want to set flag.  For example, 3 4 f set a flag at the coordinates (3, 4).");
				System.out.println("Number of Flags Left: " + testBoard.getFlag());
			}
			startGame = false;
			String input = TextIO.getlnString();
			int x = 0;
			int y = 0;
			try {
				x = Integer.parseInt(input.substring(0, 1));
			} catch (Exception e) {
				continue;
			}
			try {
				y = Integer.parseInt(input.substring(2, 3));
			} catch (Exception e) {
				continue;
			}
			
			try {
				flag = input.charAt(4);
			} catch (Exception e) {
				flag = ' ';
			}
			
			if (flag == 'f') {
				testBoard.setFlag (x, y);
				testBoard.showBoard();
			}
			else {
				if (testBoard.getNumber(x, y)==0) {
					testBoard.openNeighbors(x, y);
					testBoard.showBoard();
				}
				if (testBoard.getNumber(x, y)>0) {
					testBoard.openNumber(x, y);
					testBoard.showBoard();
				}
				if (testBoard.getNumber(x, y)==-1) {
					System.out.println("game over!");
					testBoard.revealAll();
					endGame = true;
				}
				if (testBoard.checkWin()) {
					endGame = true;
					testBoard.revealAll();
					System.out.println("you won");
				}
			}
		}
		return; 
	}
}
