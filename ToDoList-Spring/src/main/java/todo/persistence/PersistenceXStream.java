package todo.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.thoughtworks.xstream.XStream;
import todo.model.*;
// The first type parameter is the domain type for wich we are creating the repository.
// The second is the key type that is used to look it up. This example will not use it.
@Repository
public class PersistenceXStream {

	private static XStream xstream = new XStream();
	private static String filename = "data.xml";

	// TODO create the RegistrationManager instance here (replace the void return value as well)
	public static TaskList initializeToDo(String fileName) {
		setFilename(fileName);
		TaskList td;
		//set alias using when saving the xml file
		xstream.alias("Catagory", Category.class);
		xstream.alias("Task", Task.class);
		xstream.alias("TaskList", TaskList.class);
		
		//if file exists, read rm from the file, else create a new file and save rm
		File file = new File(fileName);
		if(file.exists()) {
			td = (TaskList) loadFromXMLwithXStream();
		}else {
			try {
				file.createNewFile();
			}catch(Exception e){
				e.printStackTrace();
				System.exit(1);
			}
			td = new TaskList();
			saveToXMLwithXStream(td);
		}
		return td;
		
	}

	public static boolean saveToXMLwithXStream(Object obj) {
		xstream.setMode(XStream.ID_REFERENCES);
		String xml = xstream.toXML(obj); //save the object into XML file
		
		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(xml);
			writer.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		} //end try catch
		
	}

	public static Object loadFromXMLwithXStream() {
		xstream.setMode(XStream.ID_REFERENCES);
		
		try {
			FileReader fileReader = new FileReader(filename);
			return xstream.fromXML(fileReader); //read object from XML file
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void setAlias(String xmlTagName, Class<?> className) {
		xstream.alias(xmlTagName, className);
	}

	public static void setFilename(String fn) {
		filename = fn;
	}

	public static String getFilename() {
		return filename;
	}

	public static void clearData() {
		File myFoo = new File(filename);
		FileWriter fooWriter;
		try {
			fooWriter = new FileWriter(myFoo, false);
			fooWriter.write("");
			fooWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
