package dao.custom.impl;

import dao.custom.QueryDAO;
import model.CustomDTO;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomDTO> searchOrderByCusID(String id)throws SQLException,ClassNotFoundException{
     String sql=   "SELECT `order`.orderid,`order`.orderdate,`order`.cusid,`order details`.itemcode,`order details`.orderqty,`order details`.discount,`order details`.price from `order` inner join `order details` on `order`.orderid=`order details`.orderid where `order`.orderid=?;";
        ResultSet rst = CrudUtil.execute(sql, id);
        ArrayList<CustomDTO> orderRecords= new ArrayList();
        while (rst.next()) {

            CustomDTO customDTO = new CustomDTO();
            customDTO.setOrderID(rst.getString(1));
            customDTO.setOrderDate(rst.getString(2));
            customDTO.setCusID(rst.getString(3));
            customDTO.setItemCode(rst.getString(4));
            customDTO.setOrderQTY(rst.getInt(5));
            customDTO.setDiscount(rst.getDouble(6));
            customDTO.setTotal(rst.getDouble(7));

            orderRecords.add(customDTO);

        }
        return orderRecords;
    }
}
