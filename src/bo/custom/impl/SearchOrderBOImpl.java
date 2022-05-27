package bo.custom.impl;

import bo.custom.SearchOrderBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dao.custom.impl.QueryDAOImpl;
import model.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchOrderBOImpl implements SearchOrderBO {
    /**
     * Dependency Injection
     */
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY_DAO);

    @Override
    public ArrayList<CustomDTO> searchOrderByOrderID(String oid) throws SQLException, ClassNotFoundException {
        return queryDAO.searchOrderByCusID(oid);
    }

}
