package dto;

import entity.Address;

import java.time.LocalDate;

public class StudentDto {
    private String id;
    private String name;
    private int contactNumber;
    private LocalDate dob;
    private String gender;
    private String noAndLane;
    private String city;
    private String district;
    private String province;
    private int postalCode;

    public StudentDto() {
    }

    public StudentDto(String id, String name, int contactNumber, LocalDate dob, String gender, String noAndLane, String city, String district, String province, int postalCode) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.dob = dob;
        this.gender = gender;
        this.noAndLane = noAndLane;
        this.city = city;
        this.district = district;
        this.province = province;
        this.postalCode = postalCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNoAndLane() {
        return noAndLane;
    }

    public void setNoAndLane(String noAndLane) {
        this.noAndLane = noAndLane;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber=" + contactNumber +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", noAndLane='" + noAndLane + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", province='" + province + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
