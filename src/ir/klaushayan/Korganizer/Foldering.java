package ir.klaushayan.Korganizer;

import java.nio.file.*;
import java.util.ArrayList;

public class Foldering implements Runnable {
	private ArrayList<String> StringArray = new ArrayList<>();
	private ArrayList<String> Destination = new ArrayList<>();
	private ArrayList<String> FileLoc = new ArrayList<>();
	private ArrayList<String> NamesListString = new ArrayList<>();

	public Foldering(ArrayList<String> FileLoc, ArrayList<String> Destination, ArrayList<String> StringArray) {
		this.StringArray = StringArray;
		this.Destination = Destination;
		this.FileLoc = FileLoc;
	}

	
	//CALL Do() INSTEAD OF run() !!!!!
	public void Do() {
		if (StringArray == null) {
		} else {
			Foldering foldering = new Foldering(FileLoc, Destination, StringArray);
			Thread t = new Thread(foldering, "threadrun");
			t.start();
		}

	}
	
	public void run() {

		for (int i = 0; i < StringArray.size(); i++) {
			int lastindex = FileLoc.get(i).toString().lastIndexOf("/");
			NamesListString
					.add(FileLoc.get(i).toString().replace(FileLoc.get(i).toString().substring(0, lastindex + 1), ""));
			Path source = Paths.get(FileLoc.get(i).trim());
			Path dir = Paths.get(Destination.get(i) + StringArray.get(i) + "/");
			Path dest = Paths.get(Destination.get(i) + StringArray.get(i) + "/" + NamesListString.get(i));
			try {
				Files.createDirectories(dir);
				Files.move(source, dest, StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}
