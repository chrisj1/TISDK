package sdk.view;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ContextMenu extends JPopupMenu {
	
	JMenuItem addRoomAbove;
	JMenuItem addRoomBelow;
	JMenuItem addRoomRight;
	JMenuItem addRoomLeft;
	
    public ContextMenu(){
        addRoomAbove = new JMenuItem("Add Room Above");
        add(addRoomAbove);
        
        addRoomBelow = new JMenuItem("Add Room Below");
        add(addRoomBelow);
        
        addRoomBelow = new JMenuItem("Add Room Right");
        add(addRoomBelow);
        
        addRoomBelow= new JMenuItem("Add Room Left");
        add(addRoomBelow);
    }
}
