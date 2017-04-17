package ir.klaushayan.Korganizer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

import javax.swing.JFileChooser;

public class KFiles {
	private ArrayList<String> Addresses = new ArrayList<>();
	private ArrayList<String> RootAddresses = new ArrayList<>();
	private ArrayList<String> NamesListString = new ArrayList<>();

	public ArrayList<String> getFiles() {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		int returnval = chooser.showOpenDialog(null);
		if (returnval == chooser.APPROVE_OPTION) {

			ArrayList<File> NamesList = new ArrayList<>(Arrays.asList(chooser.getSelectedFiles()));
			ArrayList<String> StringNamesList = new ArrayList<>();

			StringNamesList = FiletoString(NamesList);
			return StringNamesList;
		} else {
			return null;

		}

	}

	private ArrayList<String> FiletoString(ArrayList<File> NamesList) {
		ListIterator litr = NamesList.listIterator();
		int i = 0;

		while (litr.hasNext()) {
			int lastindex = NamesList.get(i).toString().lastIndexOf("/");
			NamesListString.add(
					NamesList.get(i).toString().replace(NamesList.get(i).toString().substring(0, lastindex + 1), ""));
			Addresses.add(NamesList.get(i).toString());
			RootAddresses.add(Addresses.get(i).replace(
					Addresses.get(i).toString().substring(lastindex + 1, Addresses.get(i).toString().length()), ""));
			litr.next();
			i++;

		}

		return NamesListString;

	}

	public ArrayList<String> getFileLoc() {
		return Addresses;
	}

	public ArrayList<String> getRootLoc() {
		return RootAddresses;

	}

	public ArrayList<String> getNames() {
		return NamesListString;
	}

}
