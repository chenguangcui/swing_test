package com.ccg.test;

import java.awt.EventQueue;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class RandomQq extends JFrame{

	private JFrame frmBy;
	private JTextField frontField;
	private JTextField behindField;
	private JTextField pwdField;
	private JTextArea okArea;
	private JRadioButton randomFrontRadio;
	private JRadioButton randomBehindRadio;
	private Clipboard clipboard = getToolkit().getSystemClipboard();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandomQq window = new RandomQq();
					window.frmBy.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RandomQq() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBy = new JFrame();
		frmBy.setTitle("by:飞翔的荷兰人");
		frmBy.setBounds(100, 100, 646, 362);
		frmBy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBy.getContentPane().setLayout(null);
		
		frontField = new JTextField();
		frontField.setColumns(10);
		frontField.setBounds(60, 37, 232, 44);
		frmBy.getContentPane().add(frontField);
		
		behindField = new JTextField();
		behindField.setColumns(10);
		behindField.setBounds(60, 104, 232, 44);
		frmBy.getContentPane().add(behindField);
		
		pwdField = new JTextField();
		pwdField.setColumns(10);
		pwdField.setBounds(60, 178, 232, 44);
		frmBy.getContentPane().add(pwdField);
		
		randomFrontRadio = new JRadioButton("前面随机",true);
		randomFrontRadio.setBounds(60, 262, 81, 23);
		frmBy.getContentPane().add(randomFrontRadio);
		
		randomBehindRadio = new JRadioButton("后面随机(只支持3位)");
		randomBehindRadio.setBounds(157, 262, 148, 23);
		frmBy.getContentPane().add(randomBehindRadio);
		
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(randomFrontRadio);
	    group.add(randomBehindRadio);
		
		JLabel frontLable = new JLabel("前几位");
		frontLable.setBounds(10, 51, 54, 15);
		frmBy.getContentPane().add(frontLable);
		
		JLabel behindLable = new JLabel("后几位");
		behindLable.setBounds(10, 118, 54, 15);
		frmBy.getContentPane().add(behindLable);
		
		JLabel pwdLable = new JLabel("密 码");
		pwdLable.setBounds(10, 192, 54, 15);
		frmBy.getContentPane().add(pwdLable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(346, 37, 223, 185);
		frmBy.getContentPane().add(scrollPane);
		
		okArea = new JTextArea();
		scrollPane.setViewportView(okArea);
		
		JLabel okLable = new JLabel("输出结果集");
		okLable.setBounds(422, 12, 81, 15);
		frmBy.getContentPane().add(okLable);
		
		JButton generateButton = new JButton("生成");
		generateButton.setBounds(346, 262, 93, 23);
		frmBy.getContentPane().add(generateButton);
		
		JButton copyButton = new JButton("复制");
		copyButton.setBounds(476, 262, 93, 23);
		frmBy.getContentPane().add(copyButton);
		
		JLabel cleanLable = new JLabel("清空输入框");
		cleanLable.setBounds(133, 12, 81, 15);
		cleanLable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				frontField.setText("");
				behindField.setText("");
				pwdField.setText("");
				okArea.setText("");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		frmBy.getContentPane().add(cleanLable);
		
		//复制选中的OKArea内容
		copyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selectAreaText = okArea.getText();
				StringSelection clipString = new StringSelection(selectAreaText);
				clipboard.setContents(clipString, clipString);
			}
		});
		//点击生成按钮触发的事件
		generateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(randomFrontRadio.isSelected()){  //选择前面随机
					randomFront();
				}
				if(randomBehindRadio.isSelected()){ //选择后面随机
					randomBehind();
				}
			}
		});
	}
	//随机前面的
	private void randomFront(){
		okArea.setText("");
		String frontValue = frontField.getText(); //取到前几位值
		String behindValue = behindField.getText();  //取到后几位值
		String pwd = pwdField.getText();
		int frontValueInt = Integer.parseInt(frontValue);
		String qq = "";
		for(int i = frontValueInt; i < frontValueInt + 100 ; i++){
			qq = i + behindValue;
			okArea.append(qq+"----"+pwd);
			okArea.append("\n");
		}
	}
	
	//随机后面的暂时只能随机三位
	private void randomBehind(){
		okArea.setText("");
		String frontValue = frontField.getText(); //取到前几位值
		String behindValue = behindField.getText();  //取到后几位值
		int behindValueInt = Integer.parseInt(behindValue);
		String pwd = pwdField.getText();
		String randomBehind = "";
		String qq = "";
		for(int i = behindValueInt; i < behindValueInt+100; i++){
			if(i < 10){
				randomBehind = "00" + i;
			}else if(i >=10 && i < 100){
				randomBehind = "0"  + i;
			}else if( i >= 100){
				randomBehind = String.valueOf(i);
			}
			qq = frontValue + randomBehind;
			okArea.append(qq+"----"+pwd);
			okArea.append("\n");
		}
	}
}
