// Source File Name:   AboutDialog.java

package qXmlPackage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AboutDialog extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelPoza;
	private JButton btnClose;
	
    public AboutDialog()
    {
        labelPoza = null;
        btnClose = null;
        setupComponents();
    }

    private void setupComponents()
    {
        btnClose = new JButton("Close");
        btnClose.addActionListener(this);
        btnClose.setAlignmentX(0.5F);
        labelPoza = new JLabel("<html>License project 2006</html>", new ImageIcon("img/about.jpg"), 2);
        labelPoza.setAlignmentX(0.5F);
        add(labelPoza);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(btnClose);
        setTitle("About XMLedit");
        setDefaultCloseOperation(2);
        setLocationByPlatform(true);
        setModal(true);
        setLayout(new BoxLayout(getContentPane(), 3));
    }

    public void actionPerformed(ActionEvent e)
    {
        dispose();
    }

  }