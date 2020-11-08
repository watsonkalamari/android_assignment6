package edu.ualr.intentsassignment.model;

// TODO 05. Modify the Contact class, so you can use it to exchange Contact data between ContactFormActivity and ContactInfoActivity

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String website;

    public Contact(String fName, String lName, String phoneNum, String email, String address, String website) {
    this.firstName=fName;
    this.lastName=lName;
    this.phoneNumber=phoneNum;
    this.emailAddress=email;
    this.address=address;
    this.website=website;
    }
    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName){
        this.firstName = firstName;
    }

    public String getFullName () {
        return firstName + " " + lastName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName){
        this.lastName = lastName;
    }

    public String getPhoneNumber () {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress () {
        return emailAddress;
    }

    public void setEmailAddress (String emailAddress){
        this.emailAddress = emailAddress;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address){
        this.address = address;
    }

    public String getWebsite () {
        return website;
    }

    public void setWebsite (String website){
        this.website = website;
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(phoneNumber);
        parcel.writeString(emailAddress);
        parcel.writeString(address);
        parcel.writeString(website);
    }
    protected Contact(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.phoneNumber = in.readString();
        this.emailAddress = in.readString();
        this.address = in.readString();
        this.website = in.readString();
    }
}
