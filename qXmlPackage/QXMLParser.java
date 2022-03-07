// Source File Name:   QXMLParser.java

package qXmlPackage;

import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import org.jdom.*;
import org.jdom.input.SAXBuilder;

public class QXMLParser extends JEditorPane
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3224507804751630070L;
	JTree XmlTree;
	JPanel xqPane;
	Vector<String> tags;
	boolean add;
    public Document doc;
    
    public QXMLParser()
    {
        XmlTree = null;
        doc = new Document();
        tags = new Vector<String>();
    }

    public QXMLParser(JTree tree)
    {
        doc = new Document();
        XmlTree = tree;
        tags = new Vector<String>();
    }
    
    public QXMLParser(JPanel pane)
    {
        xqPane = pane;
        doc = new Document();
        XmlTree = null;
        tags = new Vector<String>();
    }
 
     public void load(File fileLoad)
    {
       	try {
			java.io.InputStream is = new FileInputStream(fileLoad);
			if(buildTagsTree(fileLoad))
			{
				read(is, null);
			}
		} catch(FileNotFoundException er)
        {
            JOptionPane.showMessageDialog(null, "File not found", "MY APPLICATION", 0);
        }catch(IOException er)
        {
            JOptionPane.showMessageDialog(null, "IO Error", "MY APPLICATION", 0);
        } 
    }

    public void save(File fileSave)
    {
        try
        {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileSave));
            write(out);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "IO Error!", "QXMLedit", 0);
        }
    }

    /** functia care realizeaza parsarea fiserului in format XML */
    public boolean buildTagsTree(File file){

    	SAXBuilder builder = new SAXBuilder();
    	try{
    		if (file == null){
    			JOptionPane.showMessageDialog(null, "You must select an XML file");
    		}else{
	    		doc = builder.build(file);
	    		Element root = doc.getRootElement();
	    		DefaultMutableTreeNode node = (DefaultMutableTreeNode)XmlTree.getModel().getRoot();
	    		node.removeAllChildren();
	    		node.setUserObject(root.getName());
	    		node = listElements(root, node);
	    		DefaultTreeModel model = new DefaultTreeModel(node);
	    		XmlTree.setModel(model);
	    		System.out.println("Tags no " + tags.size());
	    			for (int i = 0; i < tags.size(); i++) {
	    				System.out.println(tags.get(i) + "   !!!   " );
	    			}
    		}
    	}
    	catch(JDOMException e)
        {
        	System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error encountered while parsing the XML", "MY APPLICATION", 0);
            return false;
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "I/O error", "MY APPLICATION", 0);
            return false;
        }
        XmlTree.setRootVisible(true);
        XmlTree.setVisible(true);
        return true;
    }
     
    public Document cleanDocument(Document doc){
    	Document cleanDoc = new Document();
    
    	Element root = doc.getRootElement();
    	List children = root.getChildren();
    	while (root != null){
    		for (int i = 0; i < children.size(); i++) {
    			Element e = (Element)children.get(i);
    			if (e.getName().equalsIgnoreCase("script")){
    				children.remove(i);
    			}
    		}
    		
    	}
    	return cleanDoc;
    }
    
    public DefaultMutableTreeNode listElements(Element current, DefaultMutableTreeNode node)
    {    
    	String tagName = new String();
    	if(current != null){
    		tagName = current.getName();
			
    		if(node != null)
    			node.setUserObject(tagName);
  
    		List children = current.getChildren();
    		Vector childrenVect = new Vector();
    		int n = children.size();
    		boolean ok = false; 
    		for(int i = 0; i < n;i++){
    			Element e = (Element)children.get(i);
    			ok = false;
    			for(int j = 0; j < childrenVect.size();j++){
    				Element e1 = (Element)childrenVect.get(j);
    				
    				if(e1.getName().equals(e.getName())){
    					ok = true;
    					break;
    				}
    			}
    			
    		if(ok == false){
    				childrenVect.addElement(e);
    			}
    		else{
    				System.out.println(e + "***");
    		}
    		
    		}
    	
    		int i = 0;
    		while(i < childrenVect.size()) {
    			Element child = (Element) childrenVect.get(i);
    			i++;
    			DefaultMutableTreeNode node1 = new DefaultMutableTreeNode();
            	if(node != null){
            	    listElements(child, node1);
            	    node.add(node1);
            	}
    		}
    	}
    	return node;
    }
    
    public JTree getXMLTree()
    {
    	return XmlTree;
    }
}