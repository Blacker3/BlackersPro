package com.example.neeraj.blackerspro.addusers;

public class GetHodInfo {
    private String name;
    private String email;
    private String dob;
    private String phone;
    private String branch;
    private String profileimageurl;
    private String gender;

    public GetHodInfo() {
// firestore needed a empty constructor
    }

    public GetHodInfo(String name, String email, String dob, String phone, String branch, String profileimageurl, String gender) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.branch = branch;
        this.profileimageurl = profileimageurl;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getBranch() {
        return branch;
    }

    public String getProfileimageurl() {
        return profileimageurl;
    }

    public String getGender() {
        return gender;
    }
}
