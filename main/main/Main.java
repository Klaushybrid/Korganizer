package main;
import ir.klaushayan.Korganizer.*;

//very simple example
public class Main {

	public static void main(String[] args) {
		KFiles kf = new KFiles();
		Purification p = new Purification(kf.getFiles());
		Foldering foldering = new Foldering(kf.getFileLoc(), kf.getRootLoc(), p.Purify(true));
		foldering.Do();
		
		
		
		
		
	}

	
	
}
