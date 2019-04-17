package game;

import java.util.Random;

public class Board {
	private int W = 16;
	private int H = 30;
	public Tile[][] board;
	private int flagCount ;
	
	public Board (int difficulty) {
		if (difficulty == 1) {
			W = 9;
			H = 10;
		}
		else if (difficulty == 2) {
			W = 16;
			H = 16;
		}
		else if (difficulty == 3) {
			W = 16;
			H = 30;
		}
		
		board = new Tile[W+2][H+2];
		for (int i = 0; i<W+2; i++) {
			for (int j = 0; j<H+2; j++) {
				board[i][j]=new Tile ();
			}
		}

	}
	
	public void initBoard (int difficulty) {
		for (int i = 0; i<W+2; i++) {
			for (int j = 0; j<H+2; j++) {
				board[i][j].setNumber(-2);;
				board [i][j].setDoneFlag(false);
				board[i][j].setOpened(false);
				board[i][j].setSearchFlag(false);
				board[i][j].setFlag(false);
			}
		}
		flagCount = 0;
		setBomb(difficulty);
		for (int i = 1; i<=W; i++) {
			for (int j = 1; j<=H; j++) {
				board[i][j].setNumber(setTile (i, j));
			}
		}
		showBoard();
	}
	
	public int getNumber (int locX, int locY) {
		return board[locX][locY].getNumber();
	}
	
	public void openNumber (int locX, int locY) {
		board[locX][locY].setOpened(true);
	}
	
	public void revealAll () {
		for (int i = 1; i<=W; i++) {
			for (int j = 1; j<=H; j++) {
				if (board[i][j].getNumber()==-1) System.out.print("M ");
				else System.out.print(board[i][j].getNumber() + " ");
				
			}
			System.out.println();
		}
	}
	
	public int setTile(int locX, int locY) {
		int mineCount = 0;
		if (board[locX][locY].getNumber()==-1) return -1;
		for (int i = locX-1; i<=locX+1; i++) {
			for (int j = locY-1; j<=locY+1; j++) {
				if (board[i][j].getNumber()==-1) {
					mineCount++;
				}
			}
		}
		return mineCount;
	}
	
	public Boolean checkWin () {
		for (int i = 1; i<=W; i++) {
			for (int j = 1; j<=H; j++) {
				//System.out.println (i + " " + j);
				if (board[i][j].getNumber()>=0 && board[i][j].getOpened()==false) {
					//System.out.println ("check win debug: "+ i + " " + j);
					return false;
				}
			}
		}
		return true;
	}
	
	public void setFlag (int locX, int locY) {
		if (!board[locX][locY].getOpened() && board[locX][locY].getFlag()) {
			board[locX][locY].setFlag(false);
			flagCount++;
		}
		else {
			board[locX][locY].setFlag(true);
			flagCount--;
		}
		
	}
	
	public int getFlag () {
		return flagCount;
	}
	
	public void setBomb (int difficulty) {
		//create Mines
		int x;
		int y;
		Random rn = new Random();
		int bombCount;
		if (difficulty == 1) {
			bombCount = 10;
		}
		else if (difficulty == 2) {
			bombCount = 40;
		}
		else {
			bombCount = 99;
		}
		
		while (flagCount < bombCount) {
			x = rn.nextInt(W)+1;
			y = rn.nextInt (H)+1;
			if (board[x][y].getNumber()!=-1) {
				board[x][y].setNumber(-1);
				flagCount++;
			}
		}
	}
	
	public void showBoard () {
		//System.out.println ("Type in the 'row column (optional f)', where f indicate if you want to set flag.  For example, 3 4 f set a flag at the coordinates (3, 4).");
		System.out.print("  ");
		for (int i = 1; i<10; i++) {
			System.out.print (i + " ");
		}
		if (H>10) {
			for (int i = 0; i < H-9; i++)
			{
			    System.out.print ((char) ('a' + i) + " ");
			}
		}
		
		System.out.println();
		for (int i = 1; i<=W; i++) {
			for (int j = 1; j<=H; j++) {
				if (j==1 && i<10) System.out.print(i+" ");
				if (j==1 && i>=10) System.out.print((char) ('a' + i-10) + " ");
				if (board[i][j].getOpened()) System.out.print(board[i][j].getNumber() + " ");
				else if (board[i][j].getFlag()) System.out.print("f ");
				else System.out.print("- ");
				//System.out.print(board[i][j].getNumber() + " ");
			}
			System.out.println();
		}
	}
	
	public void openNeighbors (int locX, int locY) {
		Boolean foundBlank = true;
		board[locX][locY].setSearchFlag(true);
		board[locX][locY].setOpened(true);
		
		while (foundBlank) {
			foundBlank = false;
			for (int i = 0; i<W+2; i++) {
				for (int j = 0; j<H+2; j++) {
					if (board [i][j].getsearchFlag()) {
						//done with the current tile
						board[i][j].setSearchFlag(false);
						board[i][j].setDoneFlag(true);
						//search 8 neighbors
						for (int k = i-1; k<=i+1; k++) {
							for (int m = j-1; m<=j+1; m++) {
								//System.out.println(board[k][m].getNumber() + " " + board[k][m].getsearchFlag() + " " + k + " " + j + " " + i " " + );
								//reveal numbers
								if (board[k][m].getNumber()==0 && !board [k][m].getsearchFlag() && !board[k][m].getdoneFlag()){
									board[k][m].setOpened(true); 
									board[k][m].setSearchFlag(true);
									foundBlank = true;
								}
								else if (board[k][m].getNumber()>0){
									//number tile
									board[k][m].setOpened(true);
								}
							}
						}
					}
				}
			}
			
		}
	}
}
