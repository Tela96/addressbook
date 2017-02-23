package addressbook;

import java.util.Comparator;

public class Person
{
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public Person(String firstName, String lastName, String address, String city, String state, String zip, String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }

    public String getZip()
    {
        return zip;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String[] toArray()
    {
        String[] details = toString().split("'");
        return details;
    }

    @Override
    public String toString()
    {
        return  address + '\'' +
                city + '\'' +
                state + '\'' +
                zip + '\'' +
                phone;
    }
    public static class CompareByName implements Comparator
    {

        public int compare(Object person1, Object person2)
        {
            int compareByLast = ((Person) person1).getLastName().compareTo(
                    ((Person) person2).getLastName());
            if (compareByLast != 0)
                return compareByLast;
            else
                return ((Person) person1).getFirstName().compareTo(
                        ((Person) person2).getFirstName());
        }
        public boolean equals(Object person1, Object person2)
        {
            return compare(person1, person2) == 0;
        }
    }

    public static class CompareByZip implements Comparator
    {
        public int compare(Object person1, Object person2)
        {
            int compareByZip = ((Person) person1).getZip().compareTo(
                    ((Person) person2).getZip());
            if (compareByZip != 0)
                return compareByZip;
            else
                return new CompareByName().compare(person1, person2);
        }
        public boolean equals(Object person1, Object person2)
        {
            return compare(person1, person2) == 0;
        }
    }

}
