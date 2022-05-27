package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.UpdateCustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateCustomerBOImpl implements UpdateCustomerBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCusID(),
                dto.getCusTitle(),
                dto.getCusName(),
                dto.getCusAddress(),
                dto.getCity(),
                dto.getProvince(),
                dto.getPostCode()));
    }
}
