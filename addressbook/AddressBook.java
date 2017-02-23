package addressbook;

import java.io.File;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class AddressBook extends Observable
{
    private String title;
    private Vector<Person> collection  = new Vector<>();
    private File file;
    private boolean changedSinceLastSave;
    public int getNumberOfPersons()
    {
        setChangedSinceLastSave(true);
        return collection.size();
    }
    public String getTitle()
    {
        setChangedSinceLastSave(true);
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
        setChangedSinceLastSave(true);
    }
    public void addPerson(String firstName, String lastName, String address, String city, String state, String zip, String phone)
    {
        collection.add(new Person(firstName, lastName, address, city, state, zip, phone));
        setChangedSinceLastSave(true);
    }
    public void addPerson(String[] details)
    {
        collection.add(new Person(details[0], details[1], details[2], details[3], details[4], details[5], details[6]));
        setChangedSinceLastSave(true);
    }
    public String getFullNameOfPerson(int index)
    {
        String fullname;
        Person person = collection.get(index);
        setChangedSinceLastSave(true);
        return fullname = person.getFirstName() + person.getLastName();


    }
    public String[] getOtherPersonInformation(int index)
    {
        Person person = collection.get(index);
        String[] otherInfo = person.toArray();
        setChangedSinceLastSave(true);
        return otherInfo;
    }
    public void updatePerson(int index, String address, String city, String state, String zip, String phone)
    {
        Person person = collection.get(index);
        person.setAddress(address);
        person.setCity(city);
        person.setPhone(phone);
        person.setState(state);
        person.setZip(zip);
        setChangedSinceLastSave(true);
    }
    public void updatePerson(int index, String[] details)
    {
        Person person = collection.get(index);
        person.setAddress(details[0]);
        person.setCity(details[1]);
        person.setPhone(details[2]);
        person.setState(details[3]);
        person.setZip(details[4]);
        setChangedSinceLastSave(true);
    }
    public void removePerson(int index)
    {
        collection.remove(index);
        setChangedSinceLastSave(true);
    }
    public File getFile()
    {
        return file;
    }
    public void setFile(File file)
    {
        this.file = file;
    }
    public boolean getChangedSinceLastSave()
    {
        return changedSinceLastSave;
    }
    public Vector<Person> getCollection()
    {
        return collection;
    }
    public void setChangedSinceLastSave(boolean changedSinceLastSave)
    {
        this.changedSinceLastSave = changedSinceLastSave;
    }
    public void printAll()
    {
        StringBuilder sb = new StringBuilder();
        for (Person p: collection)
        {
            sb.append(p.getFirstName() + " " + p.getLastName());
            sb.append("\n");
            sb.append(p.getAddress() + " " + p.getCity() + " " + p.getZip() + " " + p.getState());
            sb.append("\n");
            sb.append(p.getPhone());
            sb.append("\n");
        }
        System.out.println(sb.toString());
        setChangedSinceLastSave(true);
    }
    public void sortByName()
    {
        Collections.sort(collection, new Person.CompareByName());
        setChangedSinceLastSave(true);
    }
    public void sortByZip()
    {
        Collections.sort(collection, new Person.CompareByZip());
        setChangedSinceLastSave(true);
    }
}
