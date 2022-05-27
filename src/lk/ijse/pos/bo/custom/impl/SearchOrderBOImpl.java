package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.SearchOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.QueryDAO;
import lk.ijse.pos.dto.CustomDTO;

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
