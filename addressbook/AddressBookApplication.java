package addressbook;
/*
 * AddressBookApplication.java
 *
 * Main program for address book application
 *
 * Copyright (c) 2001, 2003, 2005 - Russell C. Bjork
 *
 */

import java.awt.Frame;
import java.awt.event.WindowEvent;

public class AddressBookApplication
{
    public static void main(String [] args)
    {
        FileSystem fileSystem = new XMLHandler();
        AddressBookController controller = new AddressBookController(fileSystem);
        AddressBookGUI gui = new AddressBookGUI(controller, new AddressBook());
        gui.setVisible(true);
    }
    public static void quitApplication()
    {
        Frame [] openWindows = Frame.getFrames();
        for (int i = 0; i < openWindows.length; i ++)
        {
            if (openWindows[i] instanceof AddressBookGUI)
            {
                openWindows[i].dispatchEvent(new WindowEvent(
                        openWindows[i],
                        WindowEvent.WINDOW_CLOSING));
                if (openWindows[i].isShowing())
                    return;
            }
        }
        System.exit(0);
    }
}

