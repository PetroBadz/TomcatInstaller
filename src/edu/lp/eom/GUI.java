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
	
	//������� ���� ��������(��� ��������� ��������� war / jar ����� � ������������ TOMCAT
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
				+ "\n   �� ������ ���� ���������� ��������� ����� ������� *.war ��� *.jar � ������ ���������."
				+ "\n   ��� ��������� ����� � ������ ��������� ������� �������� ��� 䳿:"
				+ "\n     * ��������� �� ������ '������� ����'"
				+ "\n     * � ��� �� ��������� ������� ����, ���� ������� ���������."
				+ "\n     * ��������� ������ 'Open'."
				+ "\n     * ϳ��� ���� �� ������������� ���� ������, ��������� ������ '���������'."
				+ "\n     * ������������ ������ ���������, ���� ����� ������ ���."
				+ "\n     * ϳ��� ���� �� �������� ���� ���������� ����� ��������� ���� ������ �����, ��������� ��� ������� �� ��� 䳿,"
				+ "\n        �� ������ ����."
				+ "\n     * � ��������� ��� �������� ������ '���������', ���� ���� ����������� ������ ���� ��������-�����������."
				+ "\n   (��'� �����, �� ������� ��������� ����� ����� ��������� ������ � ���������� ��� �����.)"
			
				);
		areaAboutThisWindow.setEditable(false);
		JScrollPane panelInfo = new JScrollPane(areaAboutThisWindow);
		panelInfo.setPreferredSize(new Dimension(500, 200));
		
		JButton buttonNext = new JButton("���������");
		buttonNext.setEnabled(false);
		JLabel jLabelPathToFile = new JLabel("������ ���� �� ����� ���� ������� ���������:");
		
		JTextField jTextFieldPathToFile = new JTextField(50);
		
		JButton buttonChoseFile = new JButton("������� ����");
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
		
		
		JButton buttonExecuteCopy = new JButton("���������");
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
	
	//������� ���� ��������(���������� �� ������������ MySQL Server)
	public void installMYSQLServerWindow() {
		JFrame frame = new JFrame("TOMCAT");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());
		frame.setResizable(false);
		frame.setBackground(new Color(224,222,232));
		
		JButton buttonNext = new JButton("���");
		
		JTextArea areaAboutThisWindow = new JTextArea();
		areaAboutThisWindow.setText(""
				+ "\n   �� ������ ���� ���������� ��������� �� ������������ MySQL Server �� ������ ��."
				+ "\n   ������������ ���� ����� ������� �� ���� ����������:"
				+ "\n\thttps://dev.mysql.com/downloads/installer/"
				+ "\n   ��� ��������� ������� �������� ��� 䳿:"
				+ "\n     * ��������� �� ������ '������� ����'"
				+ "\n     * � ��� �� ��������� ������� ���� ��� ������������."
				+ "\n     * ��������� ������ 'Open'."
				+ "\n     * ϳ��� ���� �� ������������� ���� ������, ��������� ������ '����������'."
				+ "\n     * ������������ ������ ���������, ���� ����� ������ ���."
				+ "\n     * � ��������� ��� �������� ������ '���' ��� �������� �� ��������� ���� ���������."
				+ "\n   (���� �� �������������� ����� ����� ����� ��������� ������ � ���������� ��� �����.)"
			
				);
		areaAboutThisWindow.setEditable(false);
		JScrollPane panelInfo = new JScrollPane(areaAboutThisWindow);
		panelInfo.setPreferredSize(new Dimension(500, 200));
		
		JLabel jLabelPathToMySQLInstaller = new JLabel("������ ���� �� �������������� ����� MySQL Server:");
		
		JTextField jTextFieldPathMySQLInst = new JTextField(50);
		
		JButton buttonChoseFile = new JButton("������� ����");
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
		
		JButton buttonExecuteInstall = new JButton("�����������");
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
	
	//������� ��������� ���� �������� (� ����� ���������� ������ �񳺿 ��������
	// � ���������� ����������� TOMCAT � ������� ������������ ���������
	public void homeWindow() throws IOException{
		JFrame frame = new JFrame("TOMCAT");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());
		frame.setResizable(false);
		frame.setBackground(new Color(224,222,232));
		
		JTextField pathHomeDir = new JTextField(50);
		JLabel jLabel = new JLabel("��'� �������� ��� ������������:");
		JLabel labelCopyStart = new JLabel();
		
		JButton jButton = new JButton("�������������");
		JButton jButtonNext = new JButton("���");
		jButtonNext.setEnabled(false);
		
		JButton buttonChoseFile = new JButton("������� ���������");
		buttonChoseFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 JFileChooser chooser = new JFileChooser();
		         chooser.setDialogTitle("������� ��������� ��� ������������");
		         chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		         chooser.setAcceptAllFileFilterUsed(false);

		         if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		          
		        	 pathHomeDir.setText(""+chooser.getSelectedFile());          
		         } 
				
			}
		});
		
		
		JLabel homeLabel = new JLabel("    ��������� ��� ������������  ");
		JLabel label = new JLabel("            ���� ����� ������ ��'� �������� ��� ������������");
		
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
				+ "\n   �� ������ ���� ���������� ��������� �� ������������ Apache Tomcat �� ������ ��."
				+ "\n   ��� ����� ������� �������� ��� 䳿:"
				+ "\n     * ��������� �� ������ '������� ���������', ���� ���� ����������� Apache Tomcat."
				+ "\n     * � ��� �� ��������� ������� ��������� ��� ������������."
				+ "\n     * ��������� ������ 'Open'."
				+ "\n     * ϳ��� ���� �� ��������� ��� ������������ �������, ��������� ������ '�������������'."
				+ "\n     * ������������ ������ ���������, ���� ����� ������ ���."
				+ "\n     * � ��� ������ ��������� �������� ����������� ��� �� ����������."
				+ "\n     * � ������������ ��� ��������� ������ '��'"
				+ "\n     * � ��������� ��� �������� ������ '���' ��� �������� �� ��������� ���� ���������."
				+ "\n   (��'� �������� ��� ������������ ����� ����� ��������� ������ � ���������� ��� �����.)"
			
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
