package lk.ijse.pos.dto;

import javafx.collections.ObservableList;

public class CustomerDTO {
    private String cusID;
    private String cusTitle;
    private String cusName;
    private String cusAddress;
    private String city;
    private String Province;
    private String postCode;

    public CustomerDTO() {
    }

    public CustomerDTO(String cusID, String cusTitle, String cusName, String cusAddress, String city, String province, String postCode) {
        this.cusID = cusID;
        this.cusTitle = cusTitle;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.city = city;
        Province = province;
        this.postCode = postCode;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getCusTitle() {
        return cusTitle;
    }

    public void setCusTitle(String cusTitle) {
        this.cusTitle = cusTitle;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "cusID='" + cusID + '\'' +
                ", cusTitle='" + cusTitle + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusAddress='" + cusAddress + '\'' +
                ", city='" + city + '\'' +
                ", Province='" + Province + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
