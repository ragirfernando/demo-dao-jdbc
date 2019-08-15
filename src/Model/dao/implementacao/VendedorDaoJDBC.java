package Model.dao.implementacao;

import Model.dao.VendedorDao;
import Model.entidades.Departamento;
import Model.entidades.Vendedor;
import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendedorDaoJDBC implements VendedorDao {
    
    private Connection conn;
    
    public VendedorDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void inserir(Vendedor obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartamento().getId());
            
            int linhaAfetada = st.executeUpdate();
            
            if (linhaAfetada > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeRt(rs);
            } else {
                throw new DbException("Erro inesperado! nenhuma linha foi afetada ");
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeSt(st);
        }
        
    }
    
    @Override
    public void atualizar(Vendedor obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void deletar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Vendedor encontrarPeloId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement(
                    " SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ? ");
            
            st.setInt(1, id);
            rs = st.executeQuery();
            
            if (rs.next()) {
                
                Departamento dep = instanciaDepartamento(rs);
                Vendedor obj = intanciaVendedor(rs, dep);
                return obj;
                
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeSt(st);
            DB.closeRt(rs);
        }
        
    }
    
    @Override
    public List<Vendedor> encontrarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY Name ");
            
            rs = st.executeQuery();
            
            List<Vendedor> lista = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();
            
            while (rs.next()) {
                Departamento dep = map.get(rs.getInt("DepartmentId"));
                
                if (dep == null) {
                    dep = instanciaDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                    
                }
                Vendedor obj = intanciaVendedor(rs, dep);
                lista.add(obj);
                
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeSt(st);
            DB.closeRt(rs);
        }
    }
    
    private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
        
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }
    
    private Vendedor intanciaVendedor(ResultSet rs, Departamento dep) throws SQLException {
        Vendedor obj = new Vendedor();
        
        obj.setId(rs.getInt("Id"));
        obj.setNome(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        
        obj.setDepartamento(dep);
        return obj;
    }
    
    @Override
    public List<Vendedor> buscarPorDepartamento(Departamento departamento) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name ");
            
            st.setInt(1, departamento.getId());
            rs = st.executeQuery();
            
            List<Vendedor> lista = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();
            
            while (rs.next()) {
                Departamento dep = map.get(rs.getInt("DepartmentId"));
                
                if (dep == null) {
                    dep = instanciaDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                    
                }
                Vendedor obj = intanciaVendedor(rs, dep);
                lista.add(obj);
                
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeSt(st);
            DB.closeRt(rs);
        }
        
    }
    
}
