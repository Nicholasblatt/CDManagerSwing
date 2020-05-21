public class DVD {

	// Fields:
	
	// we have 6 files to look at
	// first one is just gonna hold info about the DVD
	// DVD userInterface describes operation required for any user
	// dont have to worry about the GUI
	// finish DVD and DVD collection
	//

	private String title;		// Title of this DVD
	private String rating;		// Rating of this DVD
	private int runningTime;	// Running time of this DVD in minutes

	public DVD(String dvdTitle, String dvdRating, int dvdRunningTime) 
	{
		this.title = dvdTitle;
		this.rating = dvdRating;
		this.runningTime = dvdRunningTime;
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public String getRating() 
	{
		return rating;
	}
	
	public int getRunningTime() 
	{
		return runningTime;
	}

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	public void setRating(String newRating) {
		this.rating = newRating;
	}

	public void setRunningTime(int newRunningTime) {
		this.runningTime = newRunningTime;
	}

	public String toString() {
		return title + "/" + rating + "/" + runningTime;
	}
	
	
}
