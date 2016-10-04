using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Contador
{//El usuario debe de introducir un valor, hasta que la suma del anterior, sea mayor que 20 o el contador de datos sea inferior a 7. 
    
    class Program
    {
        static void Main(string[] args)
        {
            int suma = 0;
            int contador = 0;
            while (suma <= 20 && contador != 7){
                Console.WriteLine("Introduzca valor a sumar: ");
                suma = suma + Int32.Parse(Console.ReadLine());
                contador++;
                Console.WriteLine("La suma actual es " + suma + " y el contador es " + contador);
            }     
        }
    }
}
