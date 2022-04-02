
package uf6;

import java.util.List;
import java.util.Scanner;

public class BDbanco {
      static Banco banco;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        banco = new Banco("Banco Amigo");
        
        int accion;
        do 
         {
            System.out.println("\n Bienvenid@ ");
         System.out.println("\n MENU DE OPCIONES\n");
         System.out.println("1. CREAR CUENTA");
         System.out.println("2. INGRESAR DINERO A LA CUENTA");
         System.out.println("3. RETIRAR DINERO DE LA CUENTA");
         System.out.println("4. MODIFICAR NOMBRE");
         System.out.println("5. OBTENER CUENTAS");
         System.out.println("6. ELIMINAR CUENTA");
         System.out.println("7. FILTRAR CUENTAS");
         System.out.println("8. SALIR");
         System.out.print("ELIGE LA OPCIÓN QUE DESEA REALIZAR: ");
         accion = sc.nextInt();
         switch(accion)
         {
             case 1:
                 nuevaC();
                 break;
             case 2:
                 Ingresar();
                 break;
             case 3:
                 retirar();
                 break;
             case 4:
                 ModificarNombre();
                 break;
             case 5:
                 imprimirCuentas();
                 break;
             case 6:
                 eliminar();
                 break;
             case 7:
                 Filtarcuentas();
                 break;
         }
          
         }while(accion != 8);
        
    }
    public static void imprimirCuentas()
    {
        List<CuentaCliente> cuentas = banco.LeerCuentas();
        
        for (CuentaCliente cuenta : cuentas)
        {
            System.out.println("EL NOMBRE DE LA CUENTA ES: "+cuenta.getNombre()+" CON NUMERO DE CUENTA "+cuenta.getNumero()+" Y SE CREO EL "+ cuenta.getFechaC()+" CON UN SALDO DE "+ cuenta.getSaldo());
        }
    }
    public static void nuevaC ()
    {
       
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un numero de cuenta: ");
        String numero = sc.next();
         
        System.out.print("Ingrese su nombre: ");
        String nombre = sc.next();
        
        CuentaCliente cuenta = new CuentaCliente(numero, nombre, 0);
        banco.InsertarCuentas(cuenta);
        
    }
    public static void ModificarNombre()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un numero de cuenta: ");
        String numero = sc.next();
        
        System.out.print("Ingrese el nuevo nombre: ");
        String nombre = sc.next();
        banco.ModificarN(numero, nombre);
    }
    public static void Ingresar()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un numero de cuenta: ");
        String numero = sc.next();
        
        System.out.print("Ingrese el importe: ");
        float importe = sc.nextFloat();
        banco.ingresarDinero(numero, importe);
    }
   public static void retirar()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un numero de cuenta: ");
        String numero = sc.next();
        
        System.out.print("Ingrese el importe: ");
        float importe = sc.nextFloat();
        banco.retirarDinero(numero, importe);
    }
    public static void eliminar()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un numero de cuenta: ");
        String numero = sc.next();
        
        banco.EliminarCuenta(numero);
    }
    public static void Filtarcuentas()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Filtra el las cuentas por el : ");
        String nombre = sc.next();
        List<CuentaCliente> cuentas = banco.filtrarCuentas(nombre);
        
        for (CuentaCliente cuenta : cuentas)
        {
            System.out.println("EL NOMBRE DE LA CUENTA ES: "+cuenta.getNombre()+" CON NUMERO DE CUENTA "+cuenta.getNumero()+" Y SE CREO EL "+ cuenta.getFechaC()+" CON UN SALDO DE "+ cuenta.getSaldo());
        }
    } 
}
