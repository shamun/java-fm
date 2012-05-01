/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figoo.guiClasses;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;


/**
 *
 * @author Lada Riha
 */
public class ListRenderer implements ListCellRenderer {


    public Component getListCellRendererComponent(JList jlist,
            Object value,
            int cellIndex,
            boolean isSelected,
            boolean cellHasFocus) {
        if (value instanceof JPanel) {
            Component component = (Component) value;
            component.setForeground(Color.white);
            component.setBackground(isSelected ? new java.awt.Color(187,204,255) : Color.white);
            return component;
        } 
            return new JLabel("???");
        
    }
}
