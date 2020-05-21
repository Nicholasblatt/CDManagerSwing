import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class DVDCollection {

	// Data fields
	/** The current number of DVDs in the array */
	private int numdvds;
	
	/** The array to contain the DVDs */
	private DVD[] dvdArray;
	
	private String currentFile;
	
	/** The name of the data file that contains dvd data */
	private String sourceName;
	
	/** Boolean flag to indicate whether the DVD collection was
	    modified since it was last saved. */
	private boolean modified;
	
	/**
	 *  Constructs an empty directory as an array
	 *  with an initial capacity of 7. When we try to
	 *  insert into a full array, we will double the size of
	 *  the array first.
	 */
	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[20]; // make it like 20 or you can leave it
	}
	
	public String toString() {
		// Return a string containing all the DVDs in the
		// order they are stored in the array along with
		// the values for numdvds and the length of the array.
		// See homework instructions for proper format.
		String a = "";// "numdvds = " + numdvds + "\n"
		// + "dvdArray.length = " + dvdArray.length + "\n";
		for(int i = 0; i < numdvds; i++) { 
			a = a + "Movie #" + (i + 1) + ": " + dvdArray[i].toString() + "\n";
		}



		return a;
	}
	
	public int getNumDVDs() {
		return numdvds;
	}

	public void addOrModifyDVD(String title, String rating, String runningTime) {
		// NOTE: Be careful. Running time is a string here
		// since the user might enter non-digits when prompted.
		// If the array is full and a new DVD needs to be added,
		// double the size of the array first.
		int fixedRT = Integer.parseInt(runningTime);
		if (doesHaveTitle(title)) {
			int j = 0;
			boolean found = false;
			while (j < numdvds && !found) {
				if (dvdArray[j].getTitle() == title) {
					dvdArray[j].setRating(rating);
					dvdArray[j].setRunningTime(fixedRT);
					found = true;
				}
				j++;
			}
		} else {
			if (numdvds == dvdArray.length) {
				dvdArray = Arrays.copyOf(dvdArray, numdvds * 2);
			}
			// int fixedRT = Integer.parseInt(runningTime);
			DVD myDVD = new DVD(title, rating, fixedRT);
			dvdArray[numdvds] = myDVD;
			numdvds++;
		}
	}
	
	public boolean removeDVD(String title) {
		int i = 0;
		boolean found = false;
		while(!found && i < numdvds) {
			if(dvdArray[i].getTitle().equals(title)) {
				found = true;
				for(int j = i; j < numdvds; j++) {
					dvdArray[j] = dvdArray[j+1];
				}
				numdvds--;
			}
			i++;
		}
		if(!found) {
			//System.out.print("There is no movie with the title: " + title + "\n");

            String myMessage = "There is no movie with the title: " + title;
            JOptionPane.showMessageDialog(null, myMessage);
		}
		return found;

	}
	
	public String getDVDsByRating(String rating) {
		String s = "";
		for(int i = 0; i < numdvds; i++)
		{
			if(dvdArray[i].getRating().equals(rating))
			{
				s += dvdArray[i].toString() + "\n";
			}
		}
		return s;
	}

	public int getTotalRunningTime() {
		
		int a = 0;
		for(int i = 0; i < numdvds; i++) {
			a += dvdArray[i].getRunningTime();
		}
		return a;
	}

	
	public void loadData(String filename) throws IOException
	{
		 currentFile = filename;
		File f = new File(filename);
		if(f.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(f));
			if(f.canRead() && f.isFile()) {
				try {
				    //StringBuilder sb = new StringBuilder();
					int temp = 0;
					int j = 0;
				    String line = br.readLine();
				    String myName;
				    String myRating;
				    String myRT;
				    while(line != null) {
				    	//System.out.print(line);
				    	temp = 0;
				        j = 0;
				        while(line.charAt(j) != ',') {
				        	j++;
				        }
				        j++;
				        myName = line.substring(0, j - 1);
				        temp = j;
				        while(line.charAt(j) != ',') {
				        	j++;
				        }
				        myRating = line.substring(temp, j);
				        myRT = line.substring(j + 1);
				        this.addOrModifyDVD(myName, myRating, myRT);
				        line = br.readLine();
				    }
				    br.close();
				    //String everything = sb.toString();
				}
				catch (FileNotFoundException myFNF){
					myFNF.printStackTrace();
				}
				finally {
				    br.close();
				}
			}
		}
		else {
			System.out.print("File Not Found");
			File file = new File(filename);
			  
			//Create the file
			if (file.createNewFile())
			{
			    System.out.println("File is created!");
			} else {
			    System.out.println("File already exists.");
			}
		}

		
	}
	
	public void save() {
		/*File f = new File(currentFile);
		FileWriter writer = new FileWriter(currentFile);
		if(f.exists()) {
			BufferedWriter bw = new BufferedWriter(writer);
			
		}
		else {
			
		}*/
		try {
			//FileWriter writer = new FileWriter(currentFile, true);

			FileWriter writer = new FileWriter(currentFile);
			BufferedWriter bw = new BufferedWriter(writer);
			for(int i = 0; i < numdvds; i++) {
				String concat;
				concat = (dvdArray[i].getTitle() + "," + dvdArray[i].getRating() + "," + dvdArray[i].getRunningTime());
				bw.write(concat);
				bw.write("\r\n");
			}
			bw.close();
			System.exit(0);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public String [] toStringArr(){
		String s = null;
		String [] myDvds = new String [100];
		for(int i = 0; i < numdvds; i++) {
			s = dvdArray[i].getTitle();
			myDvds[i] = s;
		}
		return myDvds;
	}
	
	public boolean doesHaveTitle(String myTitle)
	{
		for(int i = 0; i < numdvds; i++) {
			if(dvdArray[i].getTitle() == myTitle) {
				return true;
			}
		}
		return false;
		
	}

	public String getInfoByTitle(String myTitle) {
		for (int i = 0; i < numdvds; i++) {
			if (dvdArray[i].getTitle() == myTitle) {
				return dvdArray[i].getTitle() + "\nCurrent Rating: " + dvdArray[i].getRating() + "\nCurrent Runtime: "
						+ dvdArray[i].getRunningTime();
			}
		}
		return "";
	}

	public String getRatingByTitle(String myTitle) {
		for (int i = 0; i < numdvds; i++) {
			if (dvdArray[i].getTitle() == myTitle) {
				return dvdArray[i].getRating();
			}
		}
		return "";
	}

	// Additional private helper methods go here:



	
}
