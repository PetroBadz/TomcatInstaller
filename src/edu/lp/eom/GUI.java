package edu.lp.eom;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
	
	public GUI() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void homeWindow() throws IOException{
		JFrame frame = new JFrame("TOMCAT");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());
		frame.setResizable(false);
		frame.setBackground(new Color(224,222,232));
		
		JTextField pathHomeDir = new JTextField(50);
		JLabel jLabel = new JLabel("Ім'я дерикторії для встановлення:");
		JButton jButton = new JButton("Розархівувати");
		JButton jButtonNext = new JButton("Далі");
		jButtonNext.setEnabled(false);
		
		
		JLabel homeLabel = new JLabel("    Дерикторія для встановлення  ");
		JLabel label = new JLabel("            Будь ласка вкажіть ім'я дерикторії для встановлення");
		
		JPanel jPanel = new JPanel();
		jPanel.setSize(500,100);
		jPanel.setBackground(Color.WHITE);
		jPanel.add(homeLabel,new GridBagConstraints(0, 0, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		jPanel.add(label,new GridBagConstraints(0, 1, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		JPanel jPanel2 = new JPanel();
		jPanel2.setSize(600, 100);
		jPanel2.add( jButtonNext,BorderLayout.EAST);
		jPanel2.setBackground(Color.WHITE);
		
		
		JTextArea jTextAreaInfo = new JTextArea();
		jTextAreaInfo.setText("тут має бути опис функцій, які виконуються..."
			
				);
		jTextAreaInfo.setEditable(false);
		JScrollPane panelInfo = new JScrollPane(jTextAreaInfo);
		panelInfo.setPreferredSize(new Dimension(600, 200));
		
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TInstaller installer = new TInstaller();
				installer.setNameInstallDirectory(pathHomeDir.getText());
				try {
					installer.unzipFunction();
				} catch (Exception err) {
					err.printStackTrace();
					System.exit(1);
				}
				System.out.println(installer.getNameInstallDirectory()+"\\apache-tomcat-8.5.23\\bin");
				try {
					installer.setEnvironmentVariable("TOMCAT",installer.getNameInstallDirectory()+"\\apache-tomcat-8.5.23\\bin" );
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				jButtonNext.setEnabled(true);
				
				 File source = new File("C:\\Program Files\\Java\\jdk1.8.0_91\\bin\\java.exe");
			        File dest = new File("java.exe");
				try {
					installer.copyFileUsingStream(source, dest);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		

		
		
		
//		frame.add(jPanel,new GridBagConstraints(0, 0, 2, 1, 1, 1, 
//				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
//				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(panelInfo,new GridBagConstraints(0, 0, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(jLabel,new GridBagConstraints(0, 1, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(pathHomeDir,new GridBagConstraints(0, 2, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(jButton,new GridBagConstraints(0, 3, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(jPanel2,new GridBagConstraints(0, 4, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		frame.pack();
		frame.setVisible(true);
		
	}

}
