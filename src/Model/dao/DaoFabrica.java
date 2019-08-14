package Model.dao;

import Model.dao.implementacao.VendedorDaoJDBC;

public class DaoFabrica {
    public static VendedorDao criarVendedorDao(){
        return new VendedorDaoJDBC();
    }
}
