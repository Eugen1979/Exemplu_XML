// Source File Name:   XMLFileFilter.java

package qXmlPackage;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class XMLFileFilter extends FileFilter
{

    public XMLFileFilter()
    {
    }

    public boolean accept(File arg0)
    {
        if(arg0.isDirectory())
            return true;
        else 
        	{
        		String extension = getExtension(arg0);
        		if(extension != null)
        			return getExtension(arg0).equals("xml");
        		else
        			return false;
        	}
     }

    public String getDescription()
    {
        return "XML files (*.xml)";
    }

    private String getExtension(File f)
    {
        String ext = f.getName();
        int i = ext.lastIndexOf(".");
        if(i > 0 && i < ext.length() - 1)
        {
            ext = ext.substring(i + 1).toLowerCase();
            return ext;
        } else
        {
            return null;
        }
    }
}