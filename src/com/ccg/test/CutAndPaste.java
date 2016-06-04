package com.ccg.test;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class CutAndPaste extends JFrame {
  private JMenuBar mb = new JMenuBar();

  private JMenu edit = new JMenu("Edit");

  private JMenuItem cut = new JMenuItem("Cut"), 
		  			copy = new JMenuItem("Copy"),
		  			paste = new JMenuItem("Paste");
  private JTextArea text = new JTextArea(20, 20);

  private Clipboard clipbd = getToolkit().getSystemClipboard();

  public CutAndPaste() {
    cut.addActionListener(new CutL());
    copy.addActionListener(new CopyL());
    paste.addActionListener(new PasteL());
    edit.add(cut);
    edit.add(copy);
    edit.add(paste);
    mb.add(edit);
    setJMenuBar(mb);
    getContentPane().add(text);
  }

  class CopyL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String selection = text.getText();
      if (selection == null)
        return;
      StringSelection clipString = new StringSelection(selection);
      clipbd.setContents(clipString, clipString);
    }
  }

  class CutL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String selection = text.getSelectedText();
      if (selection == null)
        return;
      StringSelection clipString = new StringSelection(selection);
      clipbd.setContents(clipString, clipString);
      text.replaceRange("", text.getSelectionStart(), text
          .getSelectionEnd());
    }
  }

  class PasteL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Transferable clipData = clipbd.getContents(CutAndPaste.this);
      try {
        String clipString = (String) clipData
            .getTransferData(DataFlavor.stringFlavor);
        text.replaceRange(clipString, text.getSelectionStart(), text
            .getSelectionEnd());
      } catch (Exception ex) {
        System.err.println("Not String flavor");
      }
    }
  }

  public static void main(String[] args) {
    run(new CutAndPaste(), 300, 200);
  }

  public static void run(JFrame frame, int width, int height) {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(width, height);
    frame.setVisible(true);
  }
} 