package Aplicacao;

import Model.entidades.Departamento;
import Model.entidades.Vendedor;
import java.util.Date;

public class Programa {

    public static void main(String[] args) {
            
        Departamento obj = new Departamento(1, "Books");
        //System.out.println(obj);
        
        Vendedor vendedor = new Vendedor(21, "Ragir", "ragirfernando@gmail.com ", new Date(), 3000.00, obj);
        System.out.println(vendedor);
    }
}
