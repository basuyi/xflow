
package net.ms.designer.editors.componentdetail.core;

/**
 * @author lili
 * @version 1.1.0
 * @explain defind the interface ITableContentProvider
 */
public interface ITableContentProvider {
	/**
	 * Read the contents that will be shown in the TableView
	 * 
	 * @return Table Contents(MultiLine)
	 */
	String[][] getTableContents();
}