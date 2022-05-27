/**
 * @author : Ishara Maduarnga
 * Project Name: SuperMarket
 * Date        : 5/27/2022
 * Time        : 3:58 PM
 * Year        : 2022
 */

package lk.ijse.pos.entity;

public class Customer {
    private String CusID;
    private String CusTitle;
    private String CusName;
    private String CusAddress;
    private String City;
    private String Povince;
    private String PostCode;

    public Customer() {
    }

    public Customer(String cusID, String cusTitle, String cusName, String cusAddress, String city, String povince, String postCode) {
        CusID = cusID;
        CusTitle = cusTitle;
        CusName = cusName;
        CusAddress = cusAddress;
        City = city;
        Povince = povince;
        PostCode = postCode;
    }

    public String getCusID() {
        return CusID;
    }

    public void setCusID(String cusID) {
        CusID = cusID;
    }

    public String getCusTitle() {
        return CusTitle;
    }

    public void setCusTitle(String cusTitle) {
        CusTitle = cusTitle;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public String getCusAddress() {
        return CusAddress;
    }

    public void setCusAddress(String cusAddress) {
        CusAddress = cusAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPovince() {
        return Povince;
    }

    public void setPovince(String povince) {
        Povince = povince;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    @Override
    public String  toString() {
        return "Customer{" +
                "CusID='" + CusID + '\'' +
                ", CusTitle='" + CusTitle + '\'' +
                ", CusName='" + CusName + '\'' +
                ", CusAddress='" + CusAddress + '\'' +
                ", City='" + City + '\'' +
                ", Povince='" + Povince + '\'' +
                ", PostCode='" + PostCode + '\'' +
                '}';
    }
}
