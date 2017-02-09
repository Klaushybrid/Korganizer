package main;
import ir.klaushayan.Korganizer.Foldering;
import ir.klaushayan.Korganizer.KFiles;
import ir.klaushayan.Korganizer.Purification;

public class Main {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		KFiles kf = new KFiles();
		Purification p = new Purification(kf.getFiles());
		Foldering foldering = new Foldering(kf.getFileLoc(), kf.getRootLoc(), p.Purify(true));
		foldering.Do();
		
		
		
		
		
	}

	
	
}
