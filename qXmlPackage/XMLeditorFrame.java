// Source File Name:   XMLeditorFrame.java

package qXmlPackage;

import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

// Referenced classes of package qXmlPackage:
//            QXMLParser, MainWindowEvents, MenuEvents

public class XMLeditorFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTree HierView;
    private QXMLParser editxml;
    private MainWindowEvents windowEvents;
    private JScrollPane HierViewPane;
    private JScrollPane editxmlPane;
    public JSplitPane SplitPane;
    private JMenuBar mainMenu;
    private JMenu menuFile;
    private JMenuItem menuFileOpen;
    private JMenuItem menuFileSave;
    private JMenu menuHelp;
    private JMenuItem menuHelpAbout;
    private MenuEvents mainMenuEvents;
    private HashMap mainMenuMap;

    public XMLeditorFrame()
    {
        HierView = new JTree(new DefaultMutableTreeNode("void"));
        editxml = new QXMLParser(HierView);
        windowEvents = new MainWindowEvents();
        HierViewPane = new JScrollPane(HierView);
        editxmlPane = new JScrollPane(editxml);
        SplitPane = new JSplitPane(1, true, HierViewPane, editxmlPane);
        mainMenu = new JMenuBar();
        menuFile = new JMenu("File");
        menuFileOpen = new JMenuItem("Open");
        menuFileSave = new JMenuItem("Save");
        menuHelp = new JMenu("Help");
        menuHelpAbout = new JMenuItem("About");
        mainMenuEvents = new MenuEvents(this);
        mainMenuMap = new HashMap();
        setTitle("XMLeditFrame");
        setSize(new Dimension(400, 400));
        setDefaultCloseOperation(3);
        setupComponents();
        pack();
    }

    private void setupComponents()
    {
        editxmlPane.setMinimumSize(new Dimension(100, 100));
        HierViewPane.setMinimumSize(new Dimension(100, 100));
        SplitPane.setPreferredSize(new Dimension(640, 480));
        add(SplitPane);
        addComponentListener(windowEvents);
        menuFileOpen.addActionListener(mainMenuEvents);
        menuFileSave.addActionListener(mainMenuEvents);
        menuFile.add(menuFileOpen);
        menuFile.add(menuFileSave);
        menuHelpAbout.addActionListener(mainMenuEvents);
        menuHelp.add(menuHelpAbout);
        mainMenu.add(menuFile);
        mainMenu.add(menuHelp);
        setJMenuBar(mainMenu);
        mainMenuMap.put("Open", Integer.valueOf(0));
        mainMenuMap.put("Save", Integer.valueOf(1));
        mainMenuMap.put("About", Integer.valueOf(99));
        HierView.setRootVisible(false);
        editxml.setEditable(false);
        setTitle("Query for XML transformation using XQuery");
        setLocationByPlatform(true);
    }

    public QXMLParser getXMLComposer()
    {
        return editxml;
    }

    public int getMenuID(String menutext)
    {
        return ((Integer)mainMenuMap.get(menutext)).intValue();
    }

}