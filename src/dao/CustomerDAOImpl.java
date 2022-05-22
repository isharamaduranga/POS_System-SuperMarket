package dao;

import model.CustomerDTO;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl {

    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allCustomer= new ArrayList<>();
        while (rst.next()) {

            allCustomer.add(new CustomerDTO(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getString(4),rst.getString(5),
                    rst.getString(6),rst.getString(7) ));

        }
        return allCustomer;
    }

    public boolean saveCustomer(CustomerDTO dto)throws SQLException, ClassNotFoundException{
        return CrudUtil.execute("INSERT INTO Customer (CusID,CusTitle,CusName,CusAddress,City,Povince,PostCode) VALUES (?,?,?,?,?,?,?)",
                dto.getCusID(), dto.getCusTitle(), dto.getCusName(), dto.getCusAddress(), dto.getCity(), dto.getProvince(), dto.getPostCode());

    }


}