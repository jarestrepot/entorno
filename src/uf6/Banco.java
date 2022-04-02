
package uf6;

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Banco {
    
    private String nombre;
    
    public Banco(String nombre) {
        this.nombre = nombre;
    }
     public CuentaCliente ObtenerCuentas (String numero) 
    {
        CuentaCliente cuenta = null;
        try{
            Class.forName("org.sqlite.JDBC");
            
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C://sqlite//banco.db");
            
            PreparedStatement cs = conexion.prepareStatement("select * from cuenta where numero = ?");
            
            cs.setString(1, numero);
            
            ResultSet rs = cs.executeQuery();
            
            if (rs.next())
            {
                String nombre = rs.getString("nombre");
                float saldo = rs.getFloat("saldo");
                
                cuenta = new CuentaCliente(numero,nombre,saldo);
            }
             
            rs.close();
            cs.close();
            conexion.close();
        }catch(ClassNotFoundException | SQLException e)
        {
             System.out.println(e);
        }
        return cuenta;
    }
    public List<CuentaCliente> LeerCuentas () 
    {
        List<CuentaCliente> lista_cuentas = new ArrayList<>();
       
        try{
            Class.forName("org.sqlite.JDBC");
            
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C://sqlite//banco.db");
            
            Statement cs = conexion.createStatement();
            
            ResultSet rs = cs.executeQuery("select * from cuenta");
            
            while (rs.next())
            {
                String numero = rs.getString("numero");
                String nombre = rs.getString("nombre");
                float saldo = rs.getFloat("saldo");

                 CuentaCliente cuenta = new CuentaCliente(numero,nombre,saldo);
                 lista_cuentas.add(cuenta);
            }
            rs.close();
            cs.close();
            conexion.close();
        }catch(ClassNotFoundException | SQLException e)
        {
             System.out.println(e);
        }
        return lista_cuentas;
    }
     public void InsertarCuentas (CuentaCliente cuenta) 
    {
        try{
            Class.forName("org.sqlite.JDBC");
            
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C://sqlite//banco.db");
           
            PreparedStatement stm = conexion.prepareStatement("insert into cuenta (numero, nombre, saldo) values (?, ?, ?)");
             
            stm.setString(1, cuenta.getNumero());
            stm.setString(2, cuenta.getNombre());
            stm.setFloat(3, cuenta.getSaldo());
            
            stm.executeUpdate();
            
            stm.close();
            conexion.close();
        }catch(ClassNotFoundException | SQLException e)
        {
             System.out.println(e);
        }
    }
   public void ActualizarCuentas (CuentaCliente cuenta) 
    {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C://sqlite//banco.db");
           
            PreparedStatement stm = conexion.prepareStatement("update cuenta set nombre = ?, saldo = ? where numero = ?");
            
            stm.setString(1, cuenta.getNombre());
            stm.setFloat(2, cuenta.getSaldo());
            stm.setString(3, cuenta.getNumero());
            
            stm.executeUpdate();
            stm.close();
            conexion.close();
        }catch(ClassNotFoundException | SQLException e)
        {
             System.out.println(e);
        }
    }
   public void ModificarN (String numero, String nombre)
   {
        CuentaCliente cuenta = ObtenerCuentas(numero);
       
        if (cuenta!=null)
        { 
            cuenta.modicarNombre(nombre);
            ActualizarCuentas(cuenta);
            
            
        }
   }
   public void ingresarDinero (String numero, float importe)
   {
       CuentaCliente cuenta = ObtenerCuentas(numero);
       
        if (cuenta!=null)
        { 
            cuenta.ingresar_dinero(importe);
            ActualizarCuentas(cuenta);
        }
   }
   public void retirarDinero (String numero, float importe)
   {
       CuentaCliente cuenta = ObtenerCuentas(numero);
       
        if (cuenta!=null)
        { 
            cuenta.reintegro(importe);
            ActualizarCuentas(cuenta);
        }
   }
   public void EliminarCuenta (String numero) 
    {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C://sqlite//banco.db");
            PreparedStatement cs = conexion.prepareStatement("delete from cuenta where numero = ?");
            cs.setString(1, numero);
            cs.executeUpdate();

            cs.close();
            conexion.close();
        }catch(ClassNotFoundException | SQLException e)
        {
             System.out.println(e);
        }
    }
   public List<CuentaCliente> filtrarCuentas (String texto) 
    {
        List<CuentaCliente> lista_cuentas = new ArrayList<CuentaCliente>();
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:C://sqlite//banco.db");
            PreparedStatement cs = conexion.prepareStatement("select * from cuenta where nombre like ?");
            
            String resultado = "%" + texto + "%";
            
            cs.setString(1, resultado);
            
            ResultSet rs = cs.executeQuery();
            
            while (rs.next())
            {
                String numero = rs.getString("numero");
                String nombre = rs.getString("nombre");
                float saldo = rs.getFloat("saldo");
                CuentaCliente cuenta = new CuentaCliente(numero,nombre,saldo);
                lista_cuentas.add(cuenta);
            }
            cs.close();
            conexion.close();
        }catch(ClassNotFoundException e)
        {
             System.out.println(e);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return lista_cuentas;
    }
}
