package entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String noAndLane;
    private String city;
    private String district;
    private String province;
    private int postalCode;

    public Address() {
    }

    public Address(String noAndLane, String city, String district, String province, int postalCode) {
        this.noAndLane = noAndLane;
        this.city = city;
        this.district = district;
        this.province = province;
        this.postalCode = postalCode;
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
        return "Address{" +
                "noAndLane='" + noAndLane + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", province='" + province + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
