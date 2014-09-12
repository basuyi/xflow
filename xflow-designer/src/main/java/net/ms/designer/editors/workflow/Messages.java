/**
 * @author liuchunxia
 * get the value in .properties file
 */
package net.ms.designer.editors.workflow;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages 
{
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("net.ms.designer.editors.workflow.messages");

    /**
     * @param key: the key of one field
     * @return the value which key is equal to the one inputed
     */
    public static String getString(String key) 
    {
        try 
        {
            return RESOURCE_BUNDLE.getString(key);
        } 
        catch (MissingResourceException e) 
        {
            return '!' + key + '!';
        }
    }
}
