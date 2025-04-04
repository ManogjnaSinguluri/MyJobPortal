package com.example.myjobportal.ui.jobs;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Job implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("primary_details")
    private PrimaryDetails primaryDetails;
    @SerializedName("whatsapp_no")
    private String whatsappNo;

    // Constructor for Parcelable
    protected Job(Parcel in) {
        id = in.readInt();
        title = in.readString();
        primaryDetails = in.readParcelable(PrimaryDetails.class.getClassLoader());
        whatsappNo = in.readString();
    }

    // Default constructor (optional, useful for Gson or manual instantiation)
    public Job() {
    }

    // Parcelable Creator
    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeParcelable(primaryDetails, flags);
        dest.writeString(whatsappNo);
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getLocation() { return primaryDetails != null ? primaryDetails.getPlace() : "N/A"; }
    public String getSalary() { return primaryDetails != null ? primaryDetails.getSalary() : "N/A"; }
    public String getPhone() { return whatsappNo != null ? whatsappNo : "N/A"; }

    // Setters (optional, if needed for Gson or other purposes)
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setPrimaryDetails(PrimaryDetails primaryDetails) { this.primaryDetails = primaryDetails; }
    public void setWhatsappNo(String whatsappNo) { this.whatsappNo = whatsappNo; }

    // Nested PrimaryDetails class implementing Parcelable
    static class PrimaryDetails implements Parcelable {
        @SerializedName("Place")
        private String place;
        @SerializedName("Salary")
        private String salary;

        // Constructor for Parcelable
        protected PrimaryDetails(Parcel in) {
            place = in.readString();
            salary = in.readString();
        }

        // Default constructor (optional, useful for Gson or manual instantiation)
        public PrimaryDetails() {
        }

        // Parcelable Creator
        public static final Creator<PrimaryDetails> CREATOR = new Creator<PrimaryDetails>() {
            @Override
            public PrimaryDetails createFromParcel(Parcel in) {
                return new PrimaryDetails(in);
            }

            @Override
            public PrimaryDetails[] newArray(int size) {
                return new PrimaryDetails[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(place);
            dest.writeString(salary);
        }

        // Getters
        public String getPlace() { return place; }
        public String getSalary() { return salary; }

        // Setters (optional, if needed for Gson or other purposes)
        public void setPlace(String place) { this.place = place; }
        public void setSalary(String salary) { this.salary = salary; }
    }
}