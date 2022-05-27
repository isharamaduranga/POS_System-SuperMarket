package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerControllerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerControllerBOImpl implements CustomerControllerBO {

    /**
     * Apply Dependency Injection (Property Injection)
     */
    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO>allCustomers=new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer customer : all) {
            allCustomers.add(new CustomerDTO(customer.getCusID(),
                    customer.getCusTitle(),
                    customer.getCusName(),
                    customer.getCusAddress(),
                    customer.getCity(),
                    customer.getPovince(),
                    customer.getPostCode()
                    ));
        }
        return allCustomers;

    }

}
