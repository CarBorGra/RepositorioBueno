using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Calculadora3
{
    class Program
    {
        static void Main(string[] args)
        {
            //Realizamos la suma de 5 valores, si la suma es mayor de 10 la consola escribira "La suma es mayor que 10", y si no, escribira "La suma es menor que 10
            int[] valoresarray = new int[5];
            for (int i = 0; i<5; i++){
                Console.WriteLine("Introduzca el valor nº "+(i+1)+": ");
                valoresarray[i] = Int32.Parse(Console.ReadLine());
            }
            int sumtotal = 0;
            for (int i = 0; i < 5; i++)
            {
                sumtotal = sumtotal + valoresarray[i];
            }
            if (sumtotal < 10)
            {
                Console.WriteLine("La suma es menor que 10");
            }
            else if (sumtotal > 10)
            {
                Console.WriteLine("La suma es mayor que 10");
            }
            Console.ReadKey();
        }
    }
}
