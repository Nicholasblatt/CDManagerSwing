import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class is an implementation of DVDUserInterface that uses JOptionPane to
 * display the menu of command choices.
 */

public class DVDGUI implements DVDUserInterface {

	private DVDCollection dvdlist;

	private JPanel panel;

	private ImageIcon icon;

	private JOptionPane optionPane;

	public DVDGUI(DVDCollection dl) {
		dvdlist = dl;
	}

	public void processCommands() {
		String[] commands = { "Add/Modify DVD", "Remove DVD", "Get DVDs By Rating", "Get Total Running Time",
				"Show Your DVDs", "Exit and Save" };

		int choice;

		do {
			choice = JOptionPane.showOptionDialog(null,
					"Welcome to your DVD Stack!\nPlease Select what you would like to do with your DVDs.",
					"DVD Collection",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					new ImageIcon("src/images/newStack.png"), commands,
					commands[commands.length - 1]);

			switch (choice) {
			case 0:
				doAddOrModifyDVD();
				break;
			case 1:
				doRemoveDVD();
				break;
			case 2:
				doGetDVDsByRating();
				break;
			case 3:
				doGetTotalRunningTime();
				break;
			case 4:
				showDVDList();
				break;
			case 5:
				doSave();
				break;
			default: // do nothing
			}

		} while (choice != commands.length - 1);
		System.exit(0);
	}

	private void showDVDList() {
		JDialog dialog = null;
		optionPane = new JOptionPane();
		optionPane.setMessage("Your current DVDs: Click to edit or select OK to exit");
		optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		panel.setBackground(Color.LIGHT_GRAY);
		String[] buttonTxt = dvdlist.toStringArr();
		JButton[] buttons = new JButton[buttonTxt.length];
		for (int i = 0; i < buttonTxt.length; i++) {
			if (buttonTxt[i] != null) {
				buttons[i] = new JButton(buttonTxt[i]);
				int _i = i;
				String title = buttonTxt[_i];

				buttons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*
						 * String title = buttonTxt[_i]; JTextField ratingField = new JTextField();
						 * JTextField runtimeField = new JTextField(); Object[] fields = {
						 * "New Rating:", ratingField, "New Runtime:", runtimeField };
						 * JOptionPane.showConfirmDialog(panel, fields, title,
						 * JOptionPane.OK_CANCEL_OPTION); String myRating = ratingField.toString();
						 * String myRunTime = runtimeField.toString(); System.out.print(myRating +
						 * "\n"); System.out.print(myRunTime + "\n");
						 */
						// dvdlist.addOrModifyDVD(title, myRating, myRunTime);

						if (dvdlist.getRatingByTitle(title).equals("G")) {
							icon = new ImageIcon("src/images/BlueCD.png");
						}

						else if (dvdlist.getRatingByTitle(title).equals("PG")) {
							icon = new ImageIcon("src/images/YellowCD.png");
						}

						else if (dvdlist.getRatingByTitle(title).equals("PG-13")) {
							icon = new ImageIcon("src/images/PurpleCD.png");
						}

						else if (dvdlist.getRatingByTitle(title).equals("R")) {
							icon = new ImageIcon("src/images/RedCD.png");
						}

						else {
							icon = new ImageIcon("src/images/RainbowCD.png");
						}
						String myRating = "";// = ratingField.toString();
						String myRunTime = "";// = runtimeField.toString();
						String infoAboutDVD = dvdlist.getInfoByTitle(title);

						JOptionPane.showMessageDialog(panel, infoAboutDVD, title, JOptionPane.INFORMATION_MESSAGE,
								icon);
						myRating = JOptionPane.showInputDialog(panel, "Enter New Rating:", title,
								JOptionPane.INFORMATION_MESSAGE);
						// JOptionPane.showInpu
						myRunTime = JOptionPane.showInputDialog(panel, "Enter New Runtime:", title,
								JOptionPane.INFORMATION_MESSAGE);
						if (!(myRating.isEmpty()) && !(myRunTime.isEmpty())) {
							dvdlist.addOrModifyDVD(title, myRating, myRunTime);
						} else {
							JOptionPane.showMessageDialog(panel, "Invalid input not changing " + title);
						}

					}
				});

				panel.add(buttons[i]);
			} else {
				break;
			}
		}

		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
		optionPane.add(panel);
		dialog = optionPane.createDialog(panel, "DVD Manager");
		dialog.setVisible(true);
	}

	private void doAddOrModifyDVD() {

		// Request the title
		String title = JOptionPane.showInputDialog(panel, "Enter title", "Enter title",
				JOptionPane.INFORMATION_MESSAGE);
		if (title == null) {
			return; // dialog was cancelled
		}
		title = title.toUpperCase();

		// Request the rating
		String rating = JOptionPane.showInputDialog(panel, "Enter rating for " + title, "Enter Rating",
				JOptionPane.INFORMATION_MESSAGE);
		if (rating == null) {
			return; // dialog was cancelled
		}
		rating = rating.toUpperCase();

		// Request the running time
		String time = JOptionPane.showInputDialog(panel, "Enter running time for " + title, "Enter Runtime",
				JOptionPane.INFORMATION_MESSAGE);
		if (time == null) {
		}

		// Add or modify the DVD (assuming the rating and time are valid
		dvdlist.addOrModifyDVD(title, rating, time);

		// Display current collection to the console for debugging
		// System.out.println("Adding/Modifying: " + title + "," + rating + "," + time);
		// System.out.println(dvdlist);

		String myMessage = "Adding/Modifying: " + title + "," + rating + "," + time;
		JOptionPane.showMessageDialog(null, myMessage, title + " Added", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, dvdlist, title + " Added", JOptionPane.INFORMATION_MESSAGE);

	}

	private void doRemoveDVD() {

		// Request the title
		String title = JOptionPane.showInputDialog(panel, "Enter Title of Movie to be Removed:", "Movie Removal",
				JOptionPane.INFORMATION_MESSAGE);
		if (title == null) {
			return; // dialog was cancelled
		}
		title = title.toUpperCase();

		// Remove the matching DVD if found
		boolean removedDvd = dvdlist.removeDVD(title);

		// Display current collection to the console for debugging
		// System.out.println("Removing: " + title);
		// System.out.println(dvdlist);
		if (removedDvd) {
			String myMessage = "Removing: " + title;
			JOptionPane.showMessageDialog(null, myMessage, title + " Removed", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, dvdlist, title + " Removed", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void doGetDVDsByRating() {

		// Request the rating
		String rating = JOptionPane.showInputDialog(panel, "Enter Rating to View:", "Enter Rating",
				JOptionPane.INFORMATION_MESSAGE);
		if (rating == null) {
			return; // dialog was cancelled
		}
		rating = rating.toUpperCase();

		String results = dvdlist.getDVDsByRating(rating);
		// System.out.println("DVDs with rating " + rating);
		// System.out.println(results);

		String myMessage = "DVDs with rating " + rating + "\n" + results;
		JOptionPane.showMessageDialog(null, myMessage, "DVDs Rated " + rating, JOptionPane.INFORMATION_MESSAGE);
		// JOptionPane.showMessageDialog(null, results);
	}

	private void doGetTotalRunningTime() {

		int total = dvdlist.getTotalRunningTime();
		// System.out.println("Total Running Time of DVDs: ");
		// System.out.println(total);
		String myMessage = "Total Running Time of DVDs: " + total + "Min";

		JOptionPane.showMessageDialog(null, myMessage, "DVD Runtime", JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon("src/images/RunningTime.png"));

	}

	private void doSave() {

		dvdlist.save();

	}

}
