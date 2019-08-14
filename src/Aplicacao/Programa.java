package Aplicacao;

import Model.dao.DaoFabrica;
import Model.dao.VendedorDao;
import Model.entidades.Departamento;
import Model.entidades.Vendedor;
import java.util.Date;

public class Programa {

    public static void main(String[] args) {
            
        
        VendedorDao vendedorDao = DaoFabrica.criarVendedorDao();
        
        Vendedor vendedor = vendedorDao.encontrarPeloId(3);
        System.out.println(vendedor);
    }
}
