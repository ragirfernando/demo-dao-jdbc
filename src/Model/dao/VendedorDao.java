package Model.dao;

import Model.entidades.Departamento;
import Model.entidades.Vendedor;
import java.util.List;

public interface VendedorDao {
    void inserir(Vendedor obj);
    void atualizar(Vendedor obj);
    void deletar(Integer id);
    Vendedor encontrarPeloId(Integer id);
    List<Vendedor> encontrarTodos();
    List<Vendedor> buscarPorDepartamento(Departamento departamento);
}
