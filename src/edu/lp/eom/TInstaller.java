package edu.lp.eom;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.xml.ws.Holder;

import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

public class TInstaller {
	
	private String nameInstallDirectory;

	public TInstaller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TInstaller(String nameInstallDirectory) {
		super();
		this.nameInstallDirectory = nameInstallDirectory;
	}

	public String getNameInstallDirectory() {
		return nameInstallDirectory;
	}

	public void setNameInstallDirectory(String nameInstallDirectory) {
		this.nameInstallDirectory = nameInstallDirectory;
	}
	
	public  void unzipFunction() {
		File directory = new File(nameInstallDirectory);
        
		// if the output directory doesn't exist, create it
		if(!directory.exists()) 
			directory.mkdirs();

		// buffer for read and write data to file
		byte[] buffer = new byte[2048];
        int cou=1;
		try {
			FileInputStream fInput = new FileInputStream("apache-tomcat-8.5.23.zip");
			ZipInputStream zipInput = new ZipInputStream(fInput);
            
			ZipEntry entry = zipInput.getNextEntry();
            
			while(entry != null){
				String entryName = entry.getName();
				File file = new File(nameInstallDirectory + File.separator + entryName);
                
//				System.out.println("Unzip file " + entryName + " to " + file.getAbsolutePath());
				System.out.println(cou++);
                
				// create the directories of the zip directory
				if(entry.isDirectory()) {
					File newDir = new File(file.getAbsolutePath());
					if(!newDir.exists()) {
						boolean success = newDir.mkdirs();
						if(success == false) {
							System.out.println("Problem creating Folder");
						}
					}
                }
				else {
					FileOutputStream fOutput = new FileOutputStream(file);
					int count = 0;
					while ((count = zipInput.read(buffer)) > 0) {
						// write 'count' bytes to the file output stream
						fOutput.write(buffer, 0, count);
					}
					fOutput.close();
				}
				// close ZipEntry and take the next one
				zipInput.closeEntry();
				entry = zipInput.getNextEntry();
			}
            
			// close the last ZipEntry
			zipInput.closeEntry();
            
			zipInput.close();
			fInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setEnvironmentVariable(String nameVariable, String pathValue) throws IOException{
		ProcessBuilder builder = new ProcessBuilder(
	            "cmd.exe", "/c", "set "+nameVariable+"="+pathValue);
	        builder.redirectErrorStream(true);
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();
	            if (line == null) { break; }
	            System.out.println(line);
	        }
	        
//	        System.out.println("___________________________________________________");
//	    	ProcessBuilder pb = new ProcessBuilder("C:\\apache-tomcat-8.5.23\\bin"); // or any other program you want to run
//	    	
//	    	        Map<String, String> envMap = pb.environment();
//	    	
//	    	        Set<String> keys = envMap.keySet();
//	    	        for(String key:keys){
//	    	            System.out.println(key+" ==> "+envMap.get(key));
//	    	        }
	    	    
	}
	
	public  void copyFileUsingStream(File source, File newFileName) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(nameInstallDirectory+"\\apache-tomcat-8.5.23\\"+newFileName);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	
}

	
	

