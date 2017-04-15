package ir.klaushayan.Korganizer;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JOptionPane;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Purification implements Callable<ArrayList<String>> {
	public final File XMLFile = new File(getpackname());
	private ArrayList<String> StringArray;
	private static ArrayList<String> LoweredString = new ArrayList<>();

	public Purification(ArrayList<String> StringArray) {
		this.StringArray = StringArray;

	}

	public ArrayList<String> Purify() {

		return Purify(false);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> Purify(boolean ToRemove) {
		getpackname();
		if (StringArray == null) {
			return null;
		}
		ListIterator litr = StringArray.listIterator();
		int i = 0;
		while (litr.hasNext()) {
			StringArray.set(i, StringArray.get(i).replace(",", " "));
			StringArray.set(i, StringArray.get(i).replace("-", " "));
			StringArray.set(i, StringArray.get(i).replace("_", " "));
			StringArray.set(i, StringArray.get(i).replace(
					StringArray.get(i).substring(StringArray.get(i).lastIndexOf("."), StringArray.get(i).length()),
					""));
			StringArray.set(i, StringArray.get(i).replace(".", " "));
			LoweredString.add(StringArray.get(i).toLowerCase());
			litr.next();
			i++;
		}
		if (ToRemove) {
			try {

				ExecutorService EXTSERV = Executors.newFixedThreadPool(1);
				Future<ArrayList<String>> future = EXTSERV.submit(new Purification(StringArray));
				StringArray = future.get();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

		return StringArray;
	}

	// Done
	private ArrayList<String> XMLArrayReader(File XMLFile) {
		SAXReader reader = new SAXReader();
		ArrayList<String> ToRemove = new ArrayList<>();
		try {
			Document doc = reader.read(XMLFile);
			Element rootel = doc.getRootElement();
			Iterator itr = rootel.elementIterator();
			Element itemel = null;
			while (itr.hasNext()) {
				itemel = (Element) itr.next();
				ToRemove.add(itemel.getTextTrim().toString());
			}
		} catch (Exception docE) {
			JOptionPane.showMessageDialog(null, "Purifier.xml does not exist, either create one or redownload");
			System.exit(0);

		}
		return ToRemove;
	}

	public ArrayList<String> call() {

		ListIterator litr = StringArray.listIterator();
		int i = 0;
		ArrayList<String> XMLString = new ArrayList<>();
		XMLString = XMLArrayReader(XMLFile);

		while (litr.hasNext()) {
			int j = 0;
			ListIterator litrXML = XMLString.listIterator();
			while (litrXML.hasNext()) {
				if (LoweredString.get(i).contains(XMLString.get(j))) {
					if ((LoweredString.get(i).contains("("))
							&& (LoweredString.get(i).indexOf("(") < LoweredString.get(i).indexOf(XMLString.get(j)))
							&& (LoweredString.get(i).indexOf(")") > LoweredString.get(i).indexOf(XMLString.get(j)))) {
						StringArray.set(i, StringArray.get(i).replace(StringArray.get(i)
								.substring(LoweredString.get(i).indexOf("("), StringArray.get(i).length()), ""));
						LoweredString.set(i, StringArray.get(i).toLowerCase());

					} else {

						StringArray.set(i, StringArray.get(i).replace(StringArray.get(i).substring(
								LoweredString.get(i).indexOf(XMLString.get(j)), StringArray.get(i).length()), ""));
						LoweredString.set(i, StringArray.get(i).toLowerCase());

					}
					StringArray.set(i, StringArray.get(i).trim());
				}

				j++;
				litrXML.next();
			}

			i++;

			litr.next();
		}

		return StringArray;

	}

	private String getpackname() {
		String string = (Purification.class.getPackage().getName().toString().replace(".", "/")
				+ "/resources/options/Purifier.xml");
		return Purification.class.getResource("/" + string).toString().replace("file:", "");
	}

}
