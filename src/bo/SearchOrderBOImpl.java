package bo;

import dao.custom.QueryDAO;
import dao.custom.impl.QueryDAOImpl;
import model.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchOrderBOImpl {
    /**
     * Dependency Injection
     */
    private final QueryDAO queryDAO = new QueryDAOImpl();

    public ArrayList<CustomDTO> searchOrderByOrderID(String oid) throws SQLException, ClassNotFoundException {
        return queryDAO.searchOrderByCusID(oid);
    }

}
