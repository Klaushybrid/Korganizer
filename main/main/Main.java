package main;
import ir.klaushayan.Korganizer.*;

//very simple example
public class Main {

	public static void main(String[] args) {
		FileChoosing kf = new FileChoosing();
		FileNameFixer p = new FileNameFixer(kf.getFiles());
		Foldering foldering = new Foldering(kf.getFileLoc(), kf.getRootLoc(), p.Purify(true));
		foldering.Do();
		
		
		
		
		
	}

	
	
}
