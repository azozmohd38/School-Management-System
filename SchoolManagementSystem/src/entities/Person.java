package entities;

import interfaces.Displayable;
import utils.HelperUtils;

import java.util.Objects;

public class Person implements Displayable {

    private String id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private String nationalId;
    private int age;
    private boolean active;

    public Person(String id, String firstName, String lastName, String dateOfBirth,
                  String gender, String phoneNumber, String email, String address,
                  String nationalId, int age, boolean active) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setAddress(address);
        setNationalId(nationalId);
        setAge(age);
        setActive(active);
    }

    public Person(String id, String firstName, String lastName) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        this.dateOfBirth = "N/A";
        this.gender = "N/A";
        this.phoneNumber = "N/A";
        this.email = "N/A";
        this.address = "N/A";
        this.nationalId = "N/A";
        this.age = 0;
        this.active = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (!HelperUtils.isValidText(id)) {
            throw new IllegalArgumentException("Id cannot be empty");
        }
        this.id = id.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (!HelperUtils.isValidText(firstName)) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (!HelperUtils.isValidText(lastName)) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        this.lastName = lastName.trim();
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (!HelperUtils.isValidText(dateOfBirth)) {
            throw new IllegalArgumentException("Date of birth cannot be empty");
        }
        this.dateOfBirth = dateOfBirth.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (!HelperUtils.isValidText(gender)) {
            throw new IllegalArgumentException("Gender cannot be empty");
        }
        this.gender = gender.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!HelperUtils.isValidPhone(phoneNumber)) {
            throw new IllegalArgumentException("Phone number length is invalid");
        }
        this.phoneNumber = phoneNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!HelperUtils.isValidText(email)) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        this.email = email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (!HelperUtils.isValidText(address)) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        this.address = address.trim();
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        if (!HelperUtils.isValidText(nationalId)) {
            throw new IllegalArgumentException("National id cannot be empty");
        }
        this.nationalId = nationalId.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (!HelperUtils.isValidAge(age)) {
            throw new IllegalArgumentException("Age must be between 0 and 120");
        }
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void updateContact(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }

    public void updateContact(String phoneNumber, String email) {
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    @Override
    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + getFullName());
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
        System.out.println("National ID: " + nationalId);
        System.out.println("Age: " + age);
        System.out.println("Active: " + active);
    }

    @Override
    public void displaySummary() {
        System.out.println(id + " - " + getFullName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", age=" + age +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Person)) {
            return false;
        }
        Person person = (Person) object;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
