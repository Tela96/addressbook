package addressbook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;

public class AddressBookController
{
    private FileSystem fileSystem;
    public AddressBookController(FileSystem fileSystem)
    {
        this.fileSystem = fileSystem;
    }
    public AddressBook newAddressBook(String filename)
    {
        File f = new File(filename);
        AddressBook book = fileSystem.readFile(f);
        return book;
    }
    public AddressBook newAddressBook()
    {
        return new AddressBook();
    }
    public void doAdd(AddressBookGUI gui)
    {
        String[] prompts = {"Firstname", "Lastname", "Addres", "City", "State", "Zip", "Phone"};
        JFrame frame = new JFrame();
        String[] details = MultiInputPane.showMultiInputDialog(frame, prompts, "Add person");
        gui.getAddressBook().addPerson(details[0], details[1], details[2],details[3], details[4], details[5], details[6]);
    }
    public void doEdit(AddressBookGUI gui, int index)
    {
        String[] prompts = {"Addres", "City", "State", "Zip", "Phone"};
        JFrame frame = new JFrame();
        String[] details = MultiInputPane.showMultiInputDialog(frame, prompts, "Update person");
        gui.getAddressBook().updatePerson(index, details);

    }
    public void doDelete(AddressBookGUI gui, int index)
    {
        gui.getAddressBook().removePerson(index);
    }

    public void doSortByName(AddressBookGUI gui)
    {
        gui.getAddressBook().sortByName();
    }
    public void doSortByZip(AddressBookGUI gui)
    {
        gui.getAddressBook().sortByZip();
    }

    public void doNew(AddressBookGUI gui)
    {
        gui.setAddressBook(newAddressBook());
        gui.getAddressBook().setFile(new File("defaultaddressbook.xml"));
    }

    public void doOpen(AddressBookGUI gui)
    {
        String[] prompts = {"File path"};
        JFrame frame = new JFrame();
        String[] details = MultiInputPane.showMultiInputDialog(frame, prompts, "Open file");
        gui.setAddressBook(newAddressBook(details[0]));
        gui.getAddressBook().setFile(new File(details[0]));
    }
    public void doSave(AddressBookGUI gui)
    {
        fileSystem.saveFile(gui.getAddressBook(), gui.getAddressBook().getFile());
    }
    public void doSaveAs(AddressBookGUI gui)
    {
        String[] prompts = {"File path"};
        JFrame frame = new JFrame();
        String[] details = MultiInputPane.showMultiInputDialog(frame, prompts, "Save as");
        gui.getAddressBook().setFile(new File(details[0]));
        fileSystem.saveFile(gui.getAddressBook(), gui.getAddressBook().getFile());
    }
    public void doPrint(AddressBookGUI gui)
    {
        gui.getAddressBook().printAll();
    }
    public void doOfferSaveChanges(AddressBookGUI gui)
    {
        JFrame frame = new JFrame();
        frame.setSize(300,100);
        frame.setLocationRelativeTo(null);
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton saveAsButton = new JButton("Save as");
        JButton noButton = new JButton("No save");
        JButton cancelButton = new JButton("Cancel");
        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                doSave(gui);
            }
        });
        saveAsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                doSaveAs(gui);
            }
        });
        noButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.setAddressBook(new AddressBook());
            }
        });
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(false);
            }
        });
        buttonPanel.add(saveButton); buttonPanel.add(saveAsButton); buttonPanel.add(noButton); buttonPanel.add(cancelButton);
        frame.add(buttonPanel);
        frame.setVisible(true);

    }
}
