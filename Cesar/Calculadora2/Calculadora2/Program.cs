using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Calculadora2
{
    class Program
    {
        static void Main(string[] args)
        {
            //Calculadora pero la consola pide introducir valor1 y valor2
            Console.WriteLine("Introduzca el primer valor: ");
            int valor1 = Int32.Parse(Console.ReadLine());
            Console.WriteLine("Introduzca el segundo valor: ");
            int valor2 = Int32.Parse(Console.ReadLine());
            Console.WriteLine("La suma de los dos valores es: " + (valor1 + valor2));
            Console.ReadKey();
        }
    }
}
