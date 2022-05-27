package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.AddNewCustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;

public class AddNewCustomerBOImpl implements AddNewCustomerBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCusID(),
                dto.getCusTitle(),
                dto.getCusName(),
                dto.getCusAddress(),
                dto.getCity(),
                dto.getProvince(),
                dto.getPostCode()));
    }


}
