package edu.lp.eom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
	
	//розпакування TOMCAT у вказану користувачем директорію
	//і відображення процесу розпакування
	public  void unzipFunction() {
		
		JFrame frameMSG = new JFrame("Unziping TOMCAT");
		frameMSG.setSize(500, 300);
		frameMSG.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMSG.setLocationRelativeTo(null);
		frameMSG.setLayout(new GridBagLayout());
		frameMSG.setResizable(false);
		frameMSG.setBackground(new Color(224,222,232));
		
		JButton buttonOk = new JButton("Ok");
		buttonOk.setEnabled(false);
		
		JTextArea JTextAreaEndUnzip = new JTextArea();
		JTextAreaEndUnzip.setFont(new Font("Courier", Font.BOLD + Font.ITALIC, 16));
		JScrollPane jScrollPaneEndUnzip = new JScrollPane(JTextAreaEndUnzip);
		jScrollPaneEndUnzip.setPreferredSize(new Dimension(100, 50));
		
		JTextArea area=new JTextArea();
		area.setSize(400, 300);
		JScrollPane jScrollPane = new JScrollPane(area);
		jScrollPane.setPreferredSize(new Dimension(400, 300));
		
		JPanel jPanel = new JPanel();
		jPanel.setBackground(Color.white);
		jPanel.setLayout(new GridBagLayout());
		jPanel.add(jScrollPaneEndUnzip,new GridBagConstraints(0, 0, 1, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		jPanel.add(buttonOk,new GridBagConstraints(0, 1, 1, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		Thread m_thr = new Thread() {
		       public void run() 
		       {
		        	 String msg="";
		 			File directory = new File(nameInstallDirectory);
		 	        
		 			if(!directory.exists()) 
		 				directory.mkdirs();

		 			byte[] buffer = new byte[2048];
		 	        int cou=1;
		 			try {
		 				FileInputStream fInput = new FileInputStream("apache-tomcat-8.5.23.zip");
		 				ZipInputStream zipInput = new ZipInputStream(fInput);
		 	            
		 				ZipEntry entry = zipInput.getNextEntry();
		 	            
		 				while(entry != null){
		 					String entryName = entry.getName();
		 					File file = new File(nameInstallDirectory + File.separator + entryName);
		 					
		 	                
		 					cou++;
		 					
		 					if(entry.isDirectory()) {
		 						File newDir = new File(file.getAbsolutePath());
		 						if(!newDir.exists()) {
		 							boolean success = newDir.mkdirs();
		 							msg=cou+": "+newDir.getName()+"\n";
		 							area.append(msg);
		 							JTextAreaEndUnzip.setForeground(Color.RED);
		 							JTextAreaEndUnzip.setText("Виконується розпакування...");
		 							if(success == false) {
		 								System.out.println("Problem creating Folder");
		 							}
		 						}
		 	                }
		 					else {
		 						FileOutputStream fOutput = new FileOutputStream(file);
		 						int count = 0;
		 						msg=cou+": "+file.getName()+"\n";
		 						area.append(msg);
		 						JTextAreaEndUnzip.setForeground(Color.RED);
		 						JTextAreaEndUnzip.setText("\nВиконується розпакування...\n");
		 						while ((count = zipInput.read(buffer)) > 0) {
		 							fOutput.write(buffer, 0, count);
		 						}
		 						fOutput.close();
		 						
		 					}
		 					zipInput.closeEntry();
		 					entry = zipInput.getNextEntry();
		 				}
		 				zipInput.closeEntry();
		 				Thread.interrupted();
		 				JTextAreaEndUnzip.setForeground(Color.GREEN);
		 				JTextAreaEndUnzip.setText("Розархівовування виконано... \nНатисніть Ok для продовження роботи");
		 			     buttonOk.setEnabled(true);

		 			} catch (IOException e) {
		 				e.printStackTrace();
		 			}
		        	 
		           
		           try {
		             Thread.sleep(500);
		           }
		           catch(Exception e) {}
		         }
		       
		     };
		     m_thr.start();
		     
		     
		    buttonOk.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					frameMSG.dispose();
					
				}
			});
		
		frameMSG.add(jScrollPane,new GridBagConstraints(0, 0, 1, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frameMSG.add(jPanel,new GridBagConstraints(0, 1, 1, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		
		
		frameMSG.pack();
		frameMSG.setVisible(true);
	}
           
	//встановлення environment-змінної
	public void setEnvironmentVariable(String exeCMD, String commandName, String nameVariable, String pathValue) throws IOException{
		ProcessBuilder builder = new ProcessBuilder(
				exeCMD, "/c", commandName+" "+nameVariable+"="+pathValue);
		System.out.println(commandName+" "+nameVariable+pathValue);
	        builder.redirectErrorStream(true);
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();
	            if (line == null) { break; }
	            System.out.println(line);
	        }
	        
	    	    
	}
	
	//копіювання war / jar файлу в папку work розпакованого TOMCAT
	public  void copyFileUsingStream(File source, File newFileName) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        
	        os = new FileOutputStream(getNameInstallDirectory()+"\\apache-tomcat-8.5.23\\work\\"+newFileName);
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
	
	//функція, що визначає систему Windows
	public  boolean isWindows(){

        String OSName = System.getProperty("os.name").toLowerCase();
        return (OSName.indexOf( "win" ) >= 0); 

    }

	//функція, що визначає систему UNIX(Linux)
    public  boolean isUnix (){

        String OSName = System.getProperty("os.name").toLowerCase();
        return (OSName.indexOf( "nix") >=0 || OSName.indexOf( "nux") >=0);

    }
	
    //функція. яка запускає інсталятор MySQL Server
	public void installMSQLServer(String patchToMYSQLInstaller){
		try {
 		   Runtime rf = Runtime.getRuntime(); 
 		   rf.exec("msiexec /i \"" + patchToMYSQLInstaller + "\"");    
 		} catch(Exception e) {                      
 		   e.printStackTrace();
 		} 
	}
	
}

	
	

