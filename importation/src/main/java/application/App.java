package application;

import java.text.ParseException;
import java.util.List;

import dao.JpaApp;
import jxlsimportation.Entry;
import jxlsimportation.JXLSReader;

public class App {
	
	public static final String INVALID_ENTRY_MARKER = "!INVALID";

	public static void main(String[] args) throws ParseException {
		

		JXLSReader jxlsreader = new JXLSReader();
		// Setting the paths for XML config file for reading XLS Data
		String xmlPath = "src//main//java//application//config.xml";
		// Setting the path for the XLS File to read
		String xlsPath = "sopra-modified.xlsx";
		// Getting data from XLS File
		List<Entry> entries = jxlsreader.xlsReadFromFile(xmlPath, xlsPath);
		// Calling the JPA Application to manipulate DB
		JpaApp jpaapp = new JpaApp();
		// Injecting data from file to DB
		jpaapp.dataInjector(entries);
	}
}
