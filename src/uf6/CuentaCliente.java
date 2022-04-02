
package uf6;

import java.util.Date;
/**
 * CuentaCliente resperesenta cada una de las cuentas de los clientes y sus parametros de entrada para que este se construya 
 * @author Javier Restrepo
 */

public class CuentaCliente {
   
    private String numero;
    private String nombre;
    private Date fechaC;
    private float saldo;
    private final String direccion;
    /**
     * Este es el constructor de la clase CuentaCliente.
     * @param numero este indica el numero de cuenta del cliente.
     * @param nombre parametro del nombre del cliente.
     * @param saldo es la cantidad de dinero que tiene en su cuenta.
     */
    public CuentaCliente(String numero, String nombre, float saldo)
    {
        this.numero = numero;
        this.nombre = nombre;
        this.saldo = saldo;
        fechaC = new Date();
        direccion = "Calle Badalona 14";
    }
    /**
     * Direccion de la oficina principal.
     * @return devuelve la dirección de la oficina principal.
     */
    public String getdireccion(){
        return direccion;
    }
    /**
     * Numero de cuenta.
     * @return Devuelve el numero de cuenta de un cliente.
     */
    public String getNumero() {
        return numero;
    }
    /**
     * Nombre del cliente asociado a una cuenta.
     * @return Devuelve el nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Esta funcion devuelve la Fecha de creación.
     * @return retorna la fecha en la que se creo la cuenta del cliente.
     */
    public Date getFechaC() {
        return fechaC;
    }
    /**
     * Saldo total de la cuenta_cliente
     * @return Esta funcion devuelve el saldo del cliente.
     */
    public float getSaldo() {
        return saldo;
    }
    /**
     * Esta funcion permite modificar el nombre de un cliente.
     * @param nuevoNombre es el nuevo nombre que se le asignara al titular de la cuenta.
     */
    public void modicarNombre (String nuevoNombre)
    {
        nombre = nuevoNombre;
    }
    /**
     * Esta funcion permite ingresar dinero a la cuenta siempre y cuando no sea menor a 0.
     * @param cantidad_ingresar dinero que se quiere ingresar.
     */
    public boolean ingresar_dinero(double cantidad_ingresar)
     {
  
      if (cantidad_ingresar < 0)
       {
         System.out.println("El valor no puede ser menor o igual a  0");
         return false;
       }
       else 
       {
         saldo +=cantidad_ingresar;
         return true;
       } 
     }
    /**
     * Cantidad que se va a retirar del la cuenta.
     * @param cantidad dinero que se quiere retirar.
     * @return devuelve true si la cantidad a retirar es menor que es saldo y false en lo contrario.
     */
    public boolean reintegro(double cantidad) 
     {
     
       if ( cantidad > 0 && cantidad <= saldo )
       { 
         saldo=  saldo -=cantidad;
        return true;
       }
       else 
       {
        return false;
       }
       
     }
}
