using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ParImpar
{//Realizar un programa, que diga si el numero introducido es par o impar, pero con UNA FUNCIÓN. 
    class Program
    {
        static public Boolean par(int numero)
        {
            return (numero % 2 == 0);
        }
        static void Main(string[] args){
            Console.WriteLine("Introduzca un numero para saber is es par o impar o 0 para cerrar");
            int numeroIntroducido = Int32.Parse(Console.ReadLine());
            while (numeroIntroducido != 0)
            {
                if (par(numeroIntroducido))
                {
                    Console.WriteLine("El numero " + numeroIntroducido + " es par");
                }
                else
                {
                    Console.WriteLine("El numero " + numeroIntroducido + " es impar");
                }
                Console.WriteLine("Introduzca un numero para saber is es par o impar o 0 para cerrar");
                numeroIntroducido = Int32.Parse(Console.ReadLine());
            }
        }

    }
}
