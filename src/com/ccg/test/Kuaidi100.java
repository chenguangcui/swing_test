package com.ccg.test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.ccg.util.Kuaidi100Util;
import com.ccg.vo.KuaidiInfo;
import com.ccg.vo.KuaidiInfoVo;
import com.ccg.vo.KuaidiSource;

public class Kuaidi100 {

	private JFrame frmBy;
	private JTextField yundanField;
	private JLabel label;
	private JTextArea resultArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kuaidi100 window = new Kuaidi100();
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
	public Kuaidi100() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBy = new JFrame();
		frmBy.setTitle("by:����ĺ�����");
		frmBy.setBounds(100, 100, 677, 465);
		frmBy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBy.getContentPane().setLayout(null);
		
		yundanField = new JTextField();
		yundanField.setBounds(191, 37, 246, 34);
		frmBy.getContentPane().add(yundanField);
		yundanField.setColumns(10);
		
		JButton searchButton = new JButton("��ѯ");
		searchButton.setBounds(466, 42, 65, 23);
		frmBy.getContentPane().add(searchButton);
		
		JLabel yundanLabel = new JLabel("�˵���");
		yundanLabel.setBounds(116, 46, 54, 15);
		frmBy.getContentPane().add(yundanLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 106, 532, 279);
		frmBy.getContentPane().add(scrollPane);
		
		resultArea = new JTextArea();
		scrollPane.setViewportView(resultArea);
		
		JLabel yundanTypeLable = new JLabel("�˵�����");
		yundanTypeLable.setBounds(248, 81, 54, 15);
		frmBy.getContentPane().add(yundanTypeLable);
		
		label = new JLabel("");
		label.setBounds(353, 81, 54, 15);
		frmBy.getContentPane().add(label);
		
		//�����ѯ�����¼�
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String postId = yundanField.getText();
				if("".equals(postId) || postId == null){
					resultArea.setText("");
					resultArea.setText("�������˵��ţ�");
					return;
				}
				KuaidiSource res = null;
				try {
					res = Kuaidi100Util.getKuaidiSource(postId);
					String type = res.getAuto().get(0).getComCode(); //�˵���Դ
					label.setText(type);
					KuaidiInfo kuaidiInfo = Kuaidi100Util.getKuaidiInfo(type, postId);
					List<KuaidiInfoVo> infoList = kuaidiInfo.getData();
					resultArea.setText("");
					for(KuaidiInfoVo temp : infoList){
						resultArea.append(temp.getTime()+"----"+temp.getContext());
						resultArea.append("\n");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
}
