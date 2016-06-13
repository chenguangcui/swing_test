package com.ccg.test;

import java.awt.EventQueue;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class QqRandom extends JFrame{

	private JFrame frame;
	private JTextField qqField;
	private JTextField pwdField;
	private JLabel pwdLable;
	private JScrollPane scrollPane;
	private JTextArea okArea;
	private String qq;
	private String pwd;
	private String qqTemp;
	private JTextField beforeField;
	private JLabel beforeLable;
	private String beforeValue;
	private JButton copyBtn;
	private Clipboard clipboard = getToolkit().getSystemClipboard();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QqRandom window = new QqRandom();
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
	public QqRandom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 543, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		qqField = new JTextField();
		qqField.setBounds(37, 27, 168, 43);
		frame.getContentPane().add(qqField);
		qqField.setColumns(10);
		
		JLabel label = new JLabel("输入后6位",JLabel.CENTER);
		label.setBounds(37, 10, 168, 15);
		frame.getContentPane().add(label);
		
		pwdField = new JTextField();
		pwdField.setColumns(10);
		pwdField.setBounds(306, 27, 168, 43);
		frame.getContentPane().add(pwdField);
		
		pwdLable = new JLabel("输入指定密码", SwingConstants.CENTER);
		pwdLable.setBounds(296, 10, 168, 15);
		frame.getContentPane().add(pwdLable);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 158, 427, 112);
		frame.getContentPane().add(scrollPane);
		
		okArea = new JTextArea();
		scrollPane.setViewportView(okArea);
		
		JButton okBtn = new JButton("生成");
		okBtn.setBounds(306, 92, 168, 23);
		frame.getContentPane().add(okBtn);
		
		beforeField = new JTextField();
		beforeField.setColumns(10);
		beforeField.setBounds(37, 105, 168, 43);
		frame.getContentPane().add(beforeField);
		
		beforeLable = new JLabel("输入前4位", SwingConstants.CENTER);
		beforeLable.setBounds(37, 80, 168, 15);
		frame.getContentPane().add(beforeLable);
		
		copyBtn = new JButton("复制生成文本");
		copyBtn.setBounds(306, 125, 168, 23);
		frame.getContentPane().add(copyBtn);
		
		//这里是点击生成触发的方法
		okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				okArea.setText("");
				qq = qqField.getText();
				pwd = pwdField.getText();
				beforeValue = beforeField.getText();
				int intBeforeValue = Integer.parseInt(beforeValue);
				//这里循环生成前4位递增的QQ
				for(int i = intBeforeValue; i<intBeforeValue+100 ;i++){
					qqTemp = "";
					qqTemp = String.valueOf(i) + qq ;
					okArea.append(qqTemp+"----"+pwd);
					okArea.append("\n");
				}
			}
		});
		
		//复制选中的OKArea内容
		copyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selectAreaText = okArea.getText();
				StringSelection clipString = new StringSelection(selectAreaText);
				clipboard.setContents(clipString, clipString);
			}
		});
	}
}
