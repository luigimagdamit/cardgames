import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class FileManager {

	public void saveRecords(GameRecords records, String filename) {
    System.out.println("Saving records...");
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

    // create output streams
		try {
			fout = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(records);
			System.out.println("Done");
      // write the object to the file by output stream

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}
  public GameRecords getRecords(String filename) {
		GameRecords records = null;
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		try {
			fin = new FileInputStream(filename);
			ois = new ObjectInputStream(fin);
			records = (GameRecords) ois.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return records;

	}
  //function to read the serialized file and return the object contained in the file
}