import java.io.IOException;

/**
 * 	Program to display and modify a simple DVD collection
 */

public class DVDManager {

	public static void main(String[] args) throws IOException {
		
		DVDUserInterface dlInterface;
		DVDCollection dl = new DVDCollection();
		
		dl.loadData("dvddata.txt");

		dlInterface = new DVDGUI(dl);
		dlInterface.processCommands();

		//Scanner scan = new Scanner(System.in);

		//System.out.println("Enter name of data file to load:");
		//String filename = scan.nextLine();			
		//dl.loadData("dvddata.txt");

		//System.out.println("Input interface type: C=Console, G=GUI");
		//String interfaceType = scan.nextLine();
		//if (interfaceType.equals("C")) {
		//	dlInterface = new DVDConsoleUI(dl);
		//	dlInterface.processCommands();
		//} else if (interfaceType.equals("G")) {
		//dlInterface = new DVDGUI(dl);
		//dlInterface.processCommands();
		//} else {
		//	System.out.println("Unrecognized interface type. Program exiting.");
		//	System.exit(0);
		//}
		
	}

}
