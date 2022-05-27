/**
 * @author : Ishara Maduarnga
 * Project Name: SuperMarket
 * Date        : 5/27/2022
 * Time        : 12:41 PM
 * Year        : 2022
 */

package lk.ijse.pos.bo;


import lk.ijse.pos.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
        ADD_NEW_CUSTOMER, ADD_NEW_ITEM , ADMIN_MAIN , CASHIER_MAIN , CUSTOMER_CONTROLLER , DELETE_CUSTOMER , DELETE_ITEM ,
        DELETE_ORDER , INCOME , ITEM_CONTROLLER , ORDER_AND_ORDER_DETAIL , PURCHASE_ORDER , SEARCH_CUSTOMER ,
        SEARCH_ITEM , SEARCH_ORDER , UPDATE_CUSTOMER , UPDATE_ITEM , UPDATE_ORDER
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case ADD_NEW_CUSTOMER:
                return new AddNewCustomerBOImpl();
            case ADD_NEW_ITEM:
                return new AddNewItemBOImpl();
            case ADMIN_MAIN:
                return new AdminMainBOImpl();
            case CASHIER_MAIN:
                return new CashierMainBOImpl();
            case CUSTOMER_CONTROLLER:
                return new CustomerControllerBOImpl();
            case DELETE_CUSTOMER:
                return new DeleteCustomerBOImpl();
            case DELETE_ITEM:
                return new DeleteItemBOImpl();
            case DELETE_ORDER:
                return new DeleteOrderBOImpl();
            case INCOME:
                return new IncomeBOImpl();
            case ITEM_CONTROLLER:
                return new ItemControllerBOImpl();
            case ORDER_AND_ORDER_DETAIL:
                return new OrderAndOrderDetailBOImpl();
            case PURCHASE_ORDER:
                return new PurchaseOrderBOImpl();
            case SEARCH_CUSTOMER:
                return new SearchCustomerBOImpl();
            case SEARCH_ITEM:
                return new SearchItemBOImpl();
            case SEARCH_ORDER:
                return new SearchOrderBOImpl();
            case UPDATE_CUSTOMER:
                return new UpdateCustomerBOImpl();
            case UPDATE_ITEM:
                return new UpdateItemBOImpl();
            case UPDATE_ORDER:
                return new UpdateOrderBOImpl();
            default:
                return null;
        }
    }
}
