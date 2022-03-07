// Source File Name:   MenuEvents.java

package qXmlPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Referenced classes of package qXmlPackage:
//            XMLeditorFrame, XMLFileFilter, QXMLParser, AboutDialog

public class MenuEvents implements ActionListener
{
	private XMLeditorFrame frame;
	
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem menu = (JMenuItem)e.getSource();
        int menuID = frame.getMenuID(menu.getText());
        JFileChooser filechooser = null;
        XMLFileFilter filter = null;
        switch(menuID)
        {
        default:
            break;

        case 0: // '\0'
        {
            filechooser = new JFileChooser();
            filter = new XMLFileFilter();
            filechooser.addChoosableFileFilter(filter);
            int result = filechooser.showOpenDialog(null);
            if(result == 0)
            {
                java.io.File xmlfile = filechooser.getSelectedFile();
                frame.getXMLComposer().load(xmlfile);
            }
            break;
        }

        case 1: // '\001'
        {
            filechooser = new JFileChooser();
            filter = new XMLFileFilter();
            filechooser.addChoosableFileFilter(filter);
            int result = filechooser.showSaveDialog(null);
            if(result == 0)
            {
                java.io.File xmlfile = filechooser.getSelectedFile();
                frame.getXMLComposer().save(xmlfile);
            }
            break;
        }

        case 99: // 'c'
        {
            AboutDialog about = new AboutDialog();
            about.pack();
            about.setVisible(true);
            break;
        }
        }
    }

    public MenuEvents(JFrame f)
    {
        frame = null;
        frame = (XMLeditorFrame)f;
    }

}