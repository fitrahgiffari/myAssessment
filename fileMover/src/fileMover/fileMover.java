package fileMover;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class fileMover {
	public static Date StartTime; //this for log file
	public static Date EndTime; // this for log file
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public static void main(String[] args) throws IOException {
		
		/** 1. Define the file location and source file*/
		
		String SourceFile = "D:\\Test\\SomeFile\\";
		String DestFile = "D:\\Test\\DestFile\\";
		
		File srcFile = new File(SourceFile);
		
		
		/** 2. List all the file from source file one by one */
		
		if(srcFile.isDirectory()) {
			File[] f = srcFile.listFiles();
			for (int i=0;i<f.length;i++) {
				File files = f[i];
				try {
					/** 3. Validate the file either is .pdf or .exls type file */
					
					if(files.getName().endsWith(".pdf") || files.getName().endsWith(".exls")) {
						StartTime = Calendar.getInstance().getTime(); 
						boolean temp = files.renameTo(new File(DestFile+files.getName()));
						/** Checking if there is a file move from source folder to destination folder */
						 if(temp) 
					        {	/**4. if files already move, system directly put the information in log file*/
							 
								FileHandler fh = new FileHandler("D:\\Test\\Logs\\fileMove.log",true);
								fh.setLevel(Level.FINE);
								logger.addHandler(fh);
							 	EndTime = Calendar.getInstance().getTime();
							 	logger.info( "\n" +
							 				"Start Time: " + StartTime + "\n" + 
							 				"File: " + files.getName() + "\n" +
							 				"End Time: " + EndTime);
					        } else{ 
					            System.out.println("Failed to move the file"); 
					        } 
					}else {
						System.out.println("the file that can be moved is pdf or exls format only");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
