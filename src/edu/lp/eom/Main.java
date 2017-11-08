package edu.lp.eom;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws Exception {
//		TInstaller tomcatInstaller = new TInstaller("C:\\");
//		System.out.println(tomcatInstaller.getNameInstallDirectory());
//		
//		try {
//			tomcatInstaller.unzipFunction();
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.exit(1);
//		}

		GUI gui = new GUI();
		gui.homeWindow();
		
//		TInstaller installer = new TInstaller("D:\\apache-tomcat-8.5.23");
//		
//		 File source = new File("C:\\Program Files\\Java\\jdk1.8.0_91\\bin\\java.exe");
//	        File dest = new File("java.exe");
//		installer.copyFileUsingStream(source, dest);
//		
	}

}
