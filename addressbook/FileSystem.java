package addressbook;

import java.io.File;

public interface FileSystem
{
    public AddressBook readFile(File file);

    public void saveFile(AddressBook addressBook, File file);

}
