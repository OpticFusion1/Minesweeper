package game;

public class Tile {
	private Boolean searchFlag;
	private Boolean doneFlag;
	private int number;
	private Boolean opened;
	private Boolean flag;
	
	public Tile () {
		number = minesAround();
		searchFlag = false;
		doneFlag = false;
		opened = false;
		flag = false;
	}
	
	public void setFlag (Boolean n) {
		flag = n;
	}
	
	public Boolean getFlag() {
		return flag;
	}
	
	public int getNumber () {
		return number;
	}
	
	public void setNumber (int n) {
		number = n;
	}
	
	public Boolean getdoneFlag () {
		return doneFlag;
	}
	
	public Boolean getsearchFlag () {
		return searchFlag;
	}
	
	public void setDoneFlag (Boolean set) {
		doneFlag = set;
	}
	
	public void setSearchFlag (Boolean set) {
		searchFlag = set;
	}
	
	public void setOpened (Boolean n) {
		opened = n;
	}
	
	public Boolean getOpened () {
		return opened;
	}
	
	public int minesAround () {
		//do later
		return -2;
	}
}
