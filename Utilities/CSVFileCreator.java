package Utilities;

import java.io.FileWriter;
import java.io.IOException;

public class CSVFileCreator {
	 public static void main(String[] args) {
	        // Path to save the empty CSV file
	        String csvFile = "C:\\Users\\oyadavx\\git\\Intel_ISVC_R4C\\Intel_ISVC_R4C\\src\\test\\java\\TestData\\RMABuilkCaseSearch.csv"; // Update with your path
	        FileWriter writer = null;

	        try {
	            // Create the file (it will be empty since no data is written)
	            writer = new FileWriter(csvFile);

	            System.out.println("Empty CSV file created successfully!");

	        } catch (IOException e) {
	            System.err.println("Error creating CSV file: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            try {
	                if (writer != null) {
	                    writer.flush();  // Flush any data (though none in this case)
	                    writer.close();  // Close the writer to release resources
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
