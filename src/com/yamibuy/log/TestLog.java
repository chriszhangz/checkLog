package com.yamibuy.log;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.Session;
import com.trilead.ssh2.StreamGobbler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.text.DefaultCaret;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class TestLog {

	private Connection conn;
	private Session sess;
	private Connection conn2;
	private Session sess2;
	private Thread m1;
	private Thread m2;
	
	private JFrame frame;
	private JButton btnNewButton;
	private JTextArea textPane;
	private JButton btnStop;

	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel2;
	private JScrollPane scrollPane;
	private JTextField logPath;
	private JPanel panel_1;
	private JButton btnShowErrorLog;
	private JButton button_1;
	private JTextField logPath2;
	private JScrollPane scrollPane_1;
	private JTextArea textPane_1;
	private JTextField IPField;
	private JTextField KeyField;
	private JCheckBox chckbxNewCheckBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestLog window = new TestLog();
					//window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//一定要设置关闭
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestLog() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stop();
				stop2();
			}
		});

		frame.setBounds(100, 100, 596, 355);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{110, 38, 422, 0};
		gridBagLayout.rowHeights = new int[]{27, 25, 283, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 3;
		gbc_tabbedPane.gridheight = 4;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);
		panel = new JPanel();

		
		tabbedPane.addTab("Log 1", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{89, 0, 174, 55, 0};
		gbl_panel.rowHeights = new int[]{23, 0, 22, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnNewButton = new JButton("Show Log");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				start();
				//textPane.setText(textPane.getText()+"hihi");
			}
		});
						
						chckbxNewCheckBox = new JCheckBox("");
						chckbxNewCheckBox.addChangeListener(new ChangeListener() {
							public void stateChanged(ChangeEvent e) {
								if(chckbxNewCheckBox.isSelected()){
									frame.setAlwaysOnTop(true);									
								}else{
									frame.setAlwaysOnTop(false);									
								}
							}
						});
						GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
						gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
						gbc_chckbxNewCheckBox.gridx = 1;
						gbc_chckbxNewCheckBox.gridy = 0;
						panel.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
						
						logPath = new JTextField();
						logPath.setText("/home/EAP-6.4.0/log/webservice-front-end.log");
						
						GridBagConstraints gbc_logPath = new GridBagConstraints();
						gbc_logPath.gridwidth = 2;
						gbc_logPath.insets = new Insets(0, 0, 5, 0);
						gbc_logPath.fill = GridBagConstraints.HORIZONTAL;
						gbc_logPath.gridx = 2;
						gbc_logPath.gridy = 0;
						panel.add(logPath, gbc_logPath);
						logPath.setColumns(10);
						
						btnStop = new JButton("Stop");
						GridBagConstraints gbc_btnStop = new GridBagConstraints();
						gbc_btnStop.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnStop.anchor = GridBagConstraints.NORTH;
						gbc_btnStop.insets = new Insets(0, 0, 5, 5);
						gbc_btnStop.gridx = 0;
						gbc_btnStop.gridy = 1;
						panel.add(btnStop, gbc_btnStop);
						btnStop.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								stop();
							}
						});
						
						IPField = new JTextField();
						IPField.setText("172.31.30.201");
						IPField.setColumns(10);
						GridBagConstraints gbc_IPField = new GridBagConstraints();
						gbc_IPField.insets = new Insets(0, 0, 5, 5);
						gbc_IPField.fill = GridBagConstraints.HORIZONTAL;
						gbc_IPField.gridx = 2;
						gbc_IPField.gridy = 1;
						panel.add(IPField, gbc_IPField);
						
						KeyField = new JTextField();
						KeyField.setText("C:\\Users\\Chris.Zhang\\Downloads\\YAMIBUY_API.pem");
						KeyField.setColumns(10);
						GridBagConstraints gbc_KeyField = new GridBagConstraints();
						gbc_KeyField.insets = new Insets(0, 0, 5, 0);
						gbc_KeyField.fill = GridBagConstraints.HORIZONTAL;
						gbc_KeyField.gridx = 3;
						gbc_KeyField.gridy = 1;
						panel.add(KeyField, gbc_KeyField);
						
						scrollPane = new JScrollPane();
						GridBagConstraints gbc_scrollPane = new GridBagConstraints();
						gbc_scrollPane.gridwidth = 4;
						gbc_scrollPane.fill = GridBagConstraints.BOTH;
						gbc_scrollPane.gridx = 0;
						gbc_scrollPane.gridy = 2;
						panel.add(scrollPane, gbc_scrollPane);
						GridBagConstraints gbc_textPane = new GridBagConstraints();
						gbc_textPane.anchor = GridBagConstraints.WEST;
						gbc_textPane.fill = GridBagConstraints.BOTH;
						gbc_textPane.gridwidth = 4;
						gbc_textPane.gridx = 0;
						gbc_textPane.gridy = 1;
						//scrollPane.doLayout();
						textPane = new JTextArea();
						textPane.setForeground(new Color(255, 182, 193));
						textPane.setBackground(Color.DARK_GRAY);
						//panel.add(textPane, gbc_textPane);
						scrollPane.setViewportView(textPane);
						textPane.setEditable(false);
						
						panel_1 = new JPanel();
						tabbedPane.addTab("Log 2", null, panel_1, null);
						GridBagLayout gbl_panel_1 = new GridBagLayout();
						gbl_panel_1.columnWidths = new int[]{105, 79, 55, 0};
						gbl_panel_1.rowHeights = new int[]{23, 22, 0};
						gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
						gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
						panel_1.setLayout(gbl_panel_1);
						
						btnShowErrorLog = new JButton("Show Error Log");
						btnShowErrorLog.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								start2();
							}
						});
						GridBagConstraints gbc_btnShowErrorLog = new GridBagConstraints();
						gbc_btnShowErrorLog.anchor = GridBagConstraints.NORTHWEST;
						gbc_btnShowErrorLog.insets = new Insets(0, 0, 5, 5);
						gbc_btnShowErrorLog.gridx = 0;
						gbc_btnShowErrorLog.gridy = 0;
						panel_1.add(btnShowErrorLog, gbc_btnShowErrorLog);
						
						button_1 = new JButton("Stop");
						button_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								stop2();
							}
						});
						GridBagConstraints gbc_button_1 = new GridBagConstraints();
						gbc_button_1.anchor = GridBagConstraints.NORTHWEST;
						gbc_button_1.insets = new Insets(0, 0, 5, 5);
						gbc_button_1.gridx = 1;
						gbc_button_1.gridy = 0;
						panel_1.add(button_1, gbc_button_1);
						
						logPath2 = new JTextField();
						logPath2.setText("/home/EAP-6.4.0/log/webservice-front-end.log | grep -E \"FATAL|ERROR\"");
						logPath2.setColumns(10);
						GridBagConstraints gbc_logPath2 = new GridBagConstraints();
						gbc_logPath2.fill = GridBagConstraints.HORIZONTAL;
						gbc_logPath2.insets = new Insets(0, 0, 5, 0);
						gbc_logPath2.gridx = 2;
						gbc_logPath2.gridy = 0;
						panel_1.add(logPath2, gbc_logPath2);
						
						scrollPane_1 = new JScrollPane();
						GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
						gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
						gbc_scrollPane_1.gridwidth = 3;
						gbc_scrollPane_1.gridx = 0;
						gbc_scrollPane_1.gridy = 1;
						panel_1.add(scrollPane_1, gbc_scrollPane_1);
						
						textPane_1 = new JTextArea();
						textPane_1.setBackground(Color.DARK_GRAY);
						textPane_1.setForeground(new Color(255, 182, 193));
						textPane_1.setEditable(false);
						scrollPane_1.setViewportView(textPane_1);
				//DefaultCaret caret = (DefaultCaret)textPane.getCaret();
		        //caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		panel2 = new JPanel();
	}

	public void start() {

		System.out.println("log 1 start...");
		textPane.setText("");
		m1 = new Thread(new Runnable() {
			public void run() {
				testssh();
			}
		});
		m1.start();
	}
	
	public void start2() {

		System.out.println("log 2 start...");
		textPane_1.setText("");
		m2 = new Thread(new Runnable() {
			public void run() {
				testssh2();
			}
		});
		m2.start();
	}	
	public void testssh()  {
		// Date d1 = df.parse("2004-03-26 13:31:40");
		String hostname = IPField.getText();
		System.out.println("Connecting "+hostname+"...");
		//String hostname = "172.31.30.252";
		String username = "root";
 		//输入密钥所在路径
		File keyfile = new File(KeyField.getText()); 
 		//输入密钥的加密密码，没有可以设为 null
		String keyfilePass = null; 
		try
		{
			/* 创建一个 SSH 连接 */
			conn = new Connection(hostname);
			/* 尝试连接 */
			conn.connect();
			/* 传入 SSH key 进行验证 */
			boolean isAuthenticated = conn.authenticateWithPublicKey(username,
									keyfile,keyfilePass);
			if (isAuthenticated == false)
			throw new IOException("Authentication failed.");
			/* 验证通过，开始 SSH 会话 */
			sess = conn.openSession();
			//执行 linux 命令
 			//sess.execCommand("uname -a && date && uptime && who");
			sess.execCommand("tail -f "+logPath.getText());
 			//获取命令行输出
			InputStream stdout = new StreamGobbler(sess.getStdout());
			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
			while (true)
			{
				String line = br.readLine();
				if (line == null)
					break;
				//System.out.println(line);
				textPane.append(line+"\n");
				textPane.setCaretPosition(textPane.getText().length());
				//textPane.setSelectionStart(textPane.getText().length());
				//System.out.println("maxLine:"+scrollPane.getVerticalScrollBar().getMaximum());
				//jscrollBar = scrollPane.getVerticalScrollBar();
				//scrollPane.getHorizontalScrollBar().setValue(0);
				//System.out.println("jscrollBar:"+jscrollBar.getMaximum());
				// if (jscrollBar != null){
				//jscrollBar.setValue(jscrollBar.getMaximum());
				// }
				
			}
			System.out.println("Disconnecting...");
			/* 关闭 SSH 会话 */
			sess.close();
			/* 关闭 SSH 连接 */
			conn.close();
		}
		catch (IOException e)
		{
			e.printStackTrace(System.err);
			System.exit(2);
		}
	}		
	public void testssh2()  {
		// Date d1 = df.parse("2004-03-26 13:31:40");
		String hostname = IPField.getText();
		System.out.println("Connecting "+hostname+"...");
		//String hostname = "172.31.30.252";
		String username = "root";
 		//输入密钥所在路径
		File keyfile = new File(KeyField.getText()); 
 		//输入密钥的加密密码，没有可以设为 null
		String keyfilePass = null; 
		try
		{
			/* 创建一个 SSH 连接 */
			conn2 = new Connection(hostname);
			/* 尝试连接 */
			conn2.connect();
			/* 传入 SSH key 进行验证 */
			boolean isAuthenticated = conn2.authenticateWithPublicKey(username,
									keyfile,keyfilePass);
			if (isAuthenticated == false)
			throw new IOException("Authentication failed.");
			/* 验证通过，开始 SSH 会话 */
			sess2 = conn2.openSession();
			//执行 linux 命令
 			//sess.execCommand("uname -a && date && uptime && who");
			sess2.execCommand("tail -f "+logPath2.getText());
 			//获取命令行输出
			InputStream stdout = new StreamGobbler(sess2.getStdout());
			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
			while (true)
			{
				String line = br.readLine();
				if (line == null)
					break;
				//System.out.println(line);
				textPane_1.append(line+"\n");
				textPane_1.setCaretPosition(textPane_1.getText().length());
				//textPane_1.setSelectionStart(textPane_1.getText().length());
				//jscrollBar = scrollPane.getVerticalScrollBar();
				//scrollPane.getHorizontalScrollBar().setValue(0);
				//System.out.println("jscrollBar:"+jscrollBar.getMaximum());
				// if (jscrollBar != null){
				//jscrollBar.setValue(jscrollBar.getMaximum());
				// }
				
			}
			/* 关闭 SSH 会话 */
			sess2.close();
			/* 关闭 SSH 连接 */
			conn2.close();
		}
		catch (IOException e)
		{
			e.printStackTrace(System.err);
			System.exit(2);
		}
	}		
	public void stop()  {	
		try{
			System.out.println("Closing log 1...");
		m1.stop();
		/* 关闭 SSH 会话 */
		sess.close();
		/* 关闭 SSH 连接 */
		conn.close();
		}catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
	}
	public void stop2()  {	
		try{
			System.out.println("Closing log 2...");
			m2.stop();
		/* 关闭 SSH 会话 */
		sess2.close();
		/* 关闭 SSH 连接 */
		conn2.close();
		}catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
	}	
}
