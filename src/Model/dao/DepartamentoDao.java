package Model.dao;

import Model.entidades.Departamento;
import java.util.List;

public interface DepartamentoDao {
    
    void inserir(Departamento obj);
    void atualizar(Departamento obj);
    void deletar(Integer id);
    Departamento encontrarPeloId(Integer id);
    List<Departamento> encontrarTodos();
}
