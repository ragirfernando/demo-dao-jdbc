package Aplicacao;

import Model.dao.DaoFabrica;
import Model.dao.VendedorDao;
import Model.entidades.Departamento;
import Model.entidades.Vendedor;
import java.util.Date;
import java.util.List;

public class Programa {

    public static void main(String[] args) {

        VendedorDao vendedorDao = DaoFabrica.criarVendedorDao();

        System.out.println("=== Teste 1: Vendedor encontrarPeloId ====");
        Vendedor vendedor = vendedorDao.encontrarPeloId(3);
        System.out.println(vendedor);

        System.out.println("\n=== Teste 2: Vendedor encontrarPeloDepartamento ====");
        Departamento departamento = new Departamento(2, null);
        List<Vendedor> list = vendedorDao.buscarPorDepartamento(departamento);
        for (Vendedor ven : list) {
            System.out.println(ven);
        }

        System.out.println("\n=== Teste 3: Vendedor encontrarTodos ====");
        list = vendedorDao.encontrarTodos();
        for (Vendedor ven : list) {
            System.out.println(ven);
        }

        System.out.println("\n=== Teste 4: Vendedor inserir ====");
        Vendedor newVendedor = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.00, departamento);
        
        vendedorDao.inserir(newVendedor);
        System.out.println("Inserido novo id = "+newVendedor.getId());
    }
}
