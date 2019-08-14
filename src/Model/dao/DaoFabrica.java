package Model.dao;

import Model.dao.implementacao.VendedorDaoJDBC;
import db.DB;

public class DaoFabrica {
    public static VendedorDao criarVendedorDao(){
        return new VendedorDaoJDBC(DB.getConnection());
    }
}
