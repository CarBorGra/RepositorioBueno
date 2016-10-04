using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace EmpleadosJefesClases
{
    class Program
    {
        //public Persona(String dni, String nombre, DateTime fechaNacimiento, int sueldo, Char estadoMarital, Char sexo, bool vehiculo)
        //public Empleado(String dni, String nombre, DateTime fechaNacimiento, int sueldo, Char estadoMarital, Char sexo, bool vehiculo, int duracionContrato, int codigoJefe)
        //public Jefe(String dni, String nombre, DateTime fechaNacimiento, int sueldo, Char estadoMarital, Char sexo, bool vehiculo, String departamento, String region, int codigo)
        static void Main(string[] args)
        {
            Empleado testEmpleado = new Empleado("99999999B", "Mengano", new DateTime(1995, 5, 5), 1700, 'C', 'H', false, 800, 800);
            Jefe testJefe = new Jefe("12312312A", "Pablo", new DateTime(1985, 10, 10), 1200, 'S', 'H', true, "Finanzas", "La Rioja", 231);
            int opc = menu();
            do
            {
                switch (opc)
                {
                    case 1:
                        Console.WriteLine("Nombre empleado: {0}", testEmpleado.Nombre);
                        Console.WriteLine("DNI: {0}", testEmpleado.Dni);
                        Console.WriteLine("Fecha de nacimiento: {0}", testEmpleado.FechaNacimiento);
                        Console.WriteLine("Su sueldo es: {0}", testEmpleado.Sueldo);
                        Console.WriteLine("Su estado matricial es {0}", testEmpleado.EstadoMarital);
                        Console.WriteLine("Su sexo es {0}", testEmpleado.Sexo);
                        String cocheEmp = testEmpleado.Vehiculo ? "Tiene coche" : "No tiene coche";
                        Console.WriteLine(cocheEmp);
                        Console.WriteLine("Le queda {0} de contrato", testEmpleado.DuracionContrato);
                        Console.WriteLine("El codigo de su jefe es {0}", testEmpleado.CodigoJefe);
                        Console.WriteLine("\n");
                        break;

                    case 2:
                        Console.WriteLine("Nombre empleado: {0}", testJefe.Nombre);
                        Console.WriteLine("DNI: {0}", testJefe.Dni);
                        Console.WriteLine("Fecha de nacimiento: {0}", testJefe.FechaNacimiento);
                        Console.WriteLine("Su sueldo es: {0}", testJefe.Sueldo);
                        Console.WriteLine("Su estado matricial es {0}", testJefe.EstadoMarital);
                        Console.WriteLine("Su sexo es {0}", testJefe.Sexo);
                        String cocheJefe = testJefe.Vehiculo ? "Tiene coche" : "No tiene coche";
                        Console.WriteLine(cocheJefe);
                        Console.WriteLine("Su departamento es {0}", testJefe.Departamento);
                        Console.WriteLine("Esta en la region {0}", testJefe.Region);
                        Console.WriteLine("Su codigo de jefe es {0}", testJefe.Codigo);
                        Console.WriteLine("\n");
                        break;
                    default:
                        Console.WriteLine("Saliendo del programa");
                        break;
                }
                opc = menu();
            } while (opc != 0);
            
        }
        static int menu() 
        {
            Console.WriteLine("Elija una opcion: ");
            Console.WriteLine("1.- Mostrar Empleados.");
            Console.WriteLine("2.- Mostrar Jefes");
            Console.WriteLine("0.- Salir");

            int opc = Int32.Parse(Console.ReadLine());

            Console.WriteLine("\n");
            return opc;
        }
    }
}
