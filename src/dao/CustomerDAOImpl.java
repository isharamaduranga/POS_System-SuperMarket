package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CustomerDTO;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();
        while (rst.next()) {

            allCustomer.add(new CustomerDTO(rst.getString(1), rst.getString(2),
                    rst.getString(3), rst.getString(4), rst.getString(5),
                    rst.getString(6), rst.getString(7)));

        }
        return allCustomer;
    }

    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Customer (CusID,CusTitle,CusName,CusAddress,City,Povince,PostCode) VALUES (?,?,?,?,?,?,?)",
                dto.getCusID(), dto.getCusTitle(), dto.getCusName(), dto.getCusAddress(), dto.getCity(), dto.getProvince(), dto.getPostCode());

    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE customer SET CusTitle=?,CusName=?,CusAddress=?,City=?,Povince=?,PostCode=? WHERE CusID=?",
                dto.getCusTitle(), dto.getCusName(), dto.getCusAddress(), dto.getCity(), dto.getProvince(), dto.getPostCode(), dto.getCusID());
    }

    @Override
    public ResultSet search(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Customer WHERE CusID=?", id);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE CusID=?", id);
    }

    @Override
    public ObservableList<String> getIds() throws SQLException, ClassNotFoundException {
        ObservableList<String> ids = FXCollections.observableArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
        while (rst.next()) {
            ids.add(rst.getString(1));
        }
        return ids;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT CusID FROM Customer ORDER BY CusID DESC LIMIT 1");

        if (result.next()) {

            String rnno = result.getString("CusID");
            int co = rnno.length();
            String txt = rnno.substring(0, 2);//mul deka  (CI)
            String num = rnno.substring(2, co);//aga deaka (1000)

            int n = Integer.parseInt(num);
            n++;
            String snum = Integer.toString(n);
            String newID = txt + snum;
            return newID;
        } else {
            return "CI1000";
        }
    }
}
