package com.yamibuy.log;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1379963724699883220L;
	/**
	* 构造函数
	* 
	*/
	private JLabel label1;
    private JComboBox box;
    private JMenuBar menuBar;
    private JSlider slider;
    private JSpinner spinner;
    private JToolBar toolBar;
	public MyFrame() {
	// 设置窗口标题
	this.setTitle("程序标题");
	// 定位窗口
	this.setLocation(20, 20);
	// 设置窗口大小
	this.setSize(700,500);
	
	JButton btnNewButton = new JButton("New button");
	getContentPane().add(btnNewButton, BorderLayout.WEST);

	// 显示窗口
	setVisible(true);
	}

	public static void main(String[] args){
	new MyFrame();
	}
}
