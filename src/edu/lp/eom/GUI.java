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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI {
	private String nameCopeFile;
	
	public GUI() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getNameCopeFile() {
		return nameCopeFile;
	}

	public void setNameCopeFile(String nameCopeFile) {
		this.nameCopeFile = nameCopeFile;
	}


	TInstaller installer = new TInstaller();
	
	//розмітка вікна програми(для виконання копіювання war / jar файлів в розпакований TOMCAT
	public void copyFileWindow() {
		JFrame frame = new JFrame("TOMCAT");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());
		frame.setResizable(false);
		frame.setBackground(new Color(224,222,232));
		
		
		JTextArea areaAboutThisWindow = new JTextArea();
		areaAboutThisWindow.setText(""
				+ "\n   На даному етапі виконується копіювання файлів формату *.war або *.jar у робочу дерикторію."
				+ "\n   Для копіювання файлів у робочу дерикторію потрібно виконати такі дії:"
				+ "\n     * Натиснути на кнопку 'Вибрати файл'"
				+ "\n     * У вікні що відкриється вибрати файл, який потрібно скопіювати."
				+ "\n     * Натиснути кнопку 'Open'."
				+ "\n     * Після того як інсталяційний файл обрано, натиснути кнопку 'Скопіювати'."
				+ "\n     * Розпочнеться процес копіювання, який триває деякий час."
				+ "\n     * Після того як потрібний файл скопійовано можна скопіювати інші потрібні файли, виконавши для кожного із них дії,"
				+ "\n        які описані вище."
				+ "\n     * У головному вікні натискаєм кнопку 'Завершити', після чого завершується робота даної програми-інсталятора."
				+ "\n   (Ім'я файлів, які потрібно скопіювати також можна прописати вручну у відповідному полі вводу.)"
			
				);
		areaAboutThisWindow.setEditable(false);
		JScrollPane panelInfo = new JScrollPane(areaAboutThisWindow);
		panelInfo.setPreferredSize(new Dimension(500, 200));
		
		JButton buttonNext = new JButton("Завершити");
		buttonNext.setEnabled(false);
		JLabel jLabelPathToFile = new JLabel("Введіть шлях до файлу який потрібно скопіювати:");
		
		JTextField jTextFieldPathToFile = new JTextField(50);
		
		JButton buttonChoseFile = new JButton("Вибрати файл");
		buttonChoseFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
		    	   FileNameExtensionFilter filter = new FileNameExtensionFilter(
		                   "JAR & WAR File", "jar", "war");
		         chooser.setFileFilter(filter);

		         if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		        	  setNameCopeFile(""+chooser.getSelectedFile().getName());
		        	  
		        	 jTextFieldPathToFile.setText(""+chooser.getSelectedFile().getAbsolutePath());          
		         } 
				
			}
		});
		
		
		JButton buttonExecuteCopy = new JButton("Скопіювати");
		buttonExecuteCopy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				File source = new File(jTextFieldPathToFile.getText());
			
				File dest = new File(getNameCopeFile());
				
			try {
				installer.copyFileUsingStream(source, dest);
				buttonNext.setEnabled(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
		});
		JLabel jLabel = new JLabel("");
		JPanel jPanelExecuteInst = new JPanel();
		jPanelExecuteInst.setSize(500,200);
		jPanelExecuteInst.setBackground(Color.WHITE);
		jPanelExecuteInst.setLayout(new GridBagLayout());
		jPanelExecuteInst.add(jLabelPathToFile,new GridBagConstraints(0, 0, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		jPanelExecuteInst.add(jTextFieldPathToFile,new GridBagConstraints(0, 1, 1, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		jPanelExecuteInst.add(buttonChoseFile,new GridBagConstraints(1, 1, 1, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		jPanelExecuteInst.add(buttonExecuteCopy,new GridBagConstraints(0, 4, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		jPanelExecuteInst.add(jLabel,new GridBagConstraints(0, 5, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		
		
		buttonNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
		
		frame.add(panelInfo,new GridBagConstraints(0, 0, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(jLabel,new GridBagConstraints(0, 1, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(jPanelExecuteInst,new GridBagConstraints(0, 2, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(buttonNext,new GridBagConstraints(0, 3, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	//розмітка вікна програми(інсталяція та налаштування MySQL Server)
	public void installMYSQLServerWindow() {
		JFrame frame = new JFrame("TOMCAT");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());
		frame.setResizable(false);
		frame.setBackground(new Color(224,222,232));
		
		JButton buttonNext = new JButton("Далі");
		
		JTextArea areaAboutThisWindow = new JTextArea();
		areaAboutThisWindow.setText(""
				+ "\n   На даному етапі виконується установка та налаштування MySQL Server на Вашому ПК."
				+ "\n   Інсталяційни файл можна скачати на сайті розробника:"
				+ "\n\thttps://dev.mysql.com/downloads/installer/"
				+ "\n   Для установки потрібно виконати такі дії:"
				+ "\n     * Натиснути на кнопку 'Вибрати файл'"
				+ "\n     * У вікні що відкриється вибрати файл для встановлення."
				+ "\n     * Натиснути кнопку 'Open'."
				+ "\n     * Після того як інсталяційний файл обрано, натиснути кнопку 'Встановити'."
				+ "\n     * Розпочнеться процес установки, який триває деякий час."
				+ "\n     * У головному вікні натискаєм кнопку 'Далі' для переходу на наступний крок установки."
				+ "\n   (Шлях до інсталяційного файлу також можна прописати вручну у відповідному полі вводу.)"
			
				);
		areaAboutThisWindow.setEditable(false);
		JScrollPane panelInfo = new JScrollPane(areaAboutThisWindow);
		panelInfo.setPreferredSize(new Dimension(500, 200));
		
		JLabel jLabelPathToMySQLInstaller = new JLabel("Введіть шлях до інсталяційного файлу MySQL Server:");
		
		JTextField jTextFieldPathMySQLInst = new JTextField(50);
		
		JButton buttonChoseFile = new JButton("Вибрати файл");
		buttonChoseFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
		    	
		         if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		        	  setNameCopeFile(""+chooser.getSelectedFile().getName());
		        	  
		        	  jTextFieldPathMySQLInst.setText(""+chooser.getSelectedFile().getAbsolutePath());          
		         } 
				
			}
		});
		
		JButton buttonExecuteInstall = new JButton("Інсталювати");
		buttonExecuteInstall.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				installer.installMSQLServer(jTextFieldPathMySQLInst.getText());
				
				
			}
		});
		JLabel jLabel = new JLabel("");
		JPanel jPanelExecuteInst = new JPanel();
		jPanelExecuteInst.setSize(500,200);
		jPanelExecuteInst.setBackground(Color.WHITE);
		jPanelExecuteInst.setLayout(new GridBagLayout());
		jPanelExecuteInst.add(jLabelPathToMySQLInstaller,new GridBagConstraints(0, 0, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		jPanelExecuteInst.add(jTextFieldPathMySQLInst,new GridBagConstraints(0, 1, 1, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		jPanelExecuteInst.add(buttonChoseFile,new GridBagConstraints(1, 1, 1, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		jPanelExecuteInst.add(buttonExecuteInstall,new GridBagConstraints(0, 2, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		jPanelExecuteInst.add(jLabel,new GridBagConstraints(0, 3, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		
		buttonNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				copyFileWindow();
				frame.dispose();
				
			}
		});
		
		frame.add(panelInfo,new GridBagConstraints(0, 0, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(jLabel,new GridBagConstraints(0, 1, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(jPanelExecuteInst,new GridBagConstraints(0, 2, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(buttonNext,new GridBagConstraints(0, 3, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	//розмітка головного вікна програми (з нього починається робота всієї програми
	// і відбувається розпакуваня TOMCAT у вказану користувачем директорію
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
		JLabel labelCopyStart = new JLabel();
		
		JButton jButton = new JButton("Розархівувати");
		JButton jButtonNext = new JButton("Далі");
		jButtonNext.setEnabled(false);
		
		JButton buttonChoseFile = new JButton("Вибрати дерикторію");
		buttonChoseFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 JFileChooser chooser = new JFileChooser();
		         chooser.setDialogTitle("Виберіть директорію для встановлення");
		         chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		         chooser.setAcceptAllFileFilterUsed(false);

		         if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		          
		        	 pathHomeDir.setText(""+chooser.getSelectedFile());          
		         } 
				
			}
		});
		
		
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
		jTextAreaInfo.setText(" "
				+ "\n   На даному етапі виконується установка та налаштування Apache Tomcat на Вашому ПК."
				+ "\n   Для цього потрібно виконати такі дії:"
				+ "\n     * Натиснути на кнопку 'Вибрати дерикторію', куди буде встановлено Apache Tomcat."
				+ "\n     * У вікні що відкриється вибрати дерикторію для встановлення."
				+ "\n     * Натиснути кнопку 'Open'."
				+ "\n     * Після того як дерикторія для встановлення вибрана, натиснути кнопку 'Розархівувати'."
				+ "\n     * Розпочнеться процес установки, який триває деякий час."
				+ "\n     * У разі успішної установки отримаємо повідомлення про її завершення."
				+ "\n     * У вспливаючому вікні натиснути кнопку 'Ок'"
				+ "\n     * У головному вікні натискаєм кнопку 'Далі' для переходу на наступний крок установки."
				+ "\n   (Ім'я дерикторії для встановлення також можна прописати вручну у відповідному полі вводу.)"
			
				);
		jTextAreaInfo.setEditable(false);
		JScrollPane panelInfo = new JScrollPane(jTextAreaInfo);
		panelInfo.setPreferredSize(new Dimension(600, 200));
		
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				installer.setNameInstallDirectory(pathHomeDir.getText());
				try {
					installer.unzipFunction();
					
					
				} catch (Exception err) {
					err.printStackTrace();
					System.exit(1);
				}
				System.out.println(installer.getNameInstallDirectory()+"\\apache-tomcat-8.5.23\\bin");
				try {
					if(installer.isWindows() == true)
					{
						installer.setEnvironmentVariable("cmd.exe","set","TOMCAT=",installer.getNameInstallDirectory()
								+"\\apache-tomcat-8.5.23\\bin" );
						installer.setEnvironmentVariable("cmd.exe", "echo", "", "%TOMCAT%");
					}
					else if(installer.isUnix() == true){
						installer.setEnvironmentVariable("gnome-terminal","export","path","%path%;"+installer.getNameInstallDirectory()+
								"\\apache-tomcat-8.5.23\\bin" );
					}
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				jButtonNext.setEnabled(true);
				
				 
				
			}
		});
		
		jButtonNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				installMYSQLServerWindow();
				frame.dispose();
			}
		});
		
		

	
		frame.add(panelInfo,new GridBagConstraints(0, 0, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(jLabel,new GridBagConstraints(0, 1, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(pathHomeDir,new GridBagConstraints(0, 2, 1, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(buttonChoseFile,new GridBagConstraints(1, 2, 1, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(labelCopyStart,new GridBagConstraints(0, 3, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(jButton,new GridBagConstraints(0, 4, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		frame.add(jPanel2,new GridBagConstraints(0, 5, 2, 1, 1, 1, 
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	

}
