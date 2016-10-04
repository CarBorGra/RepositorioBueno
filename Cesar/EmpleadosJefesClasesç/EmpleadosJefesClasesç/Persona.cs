using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace EmpleadosJefesClases
{
    class Persona
    {
        private String dni = "00000000A";
        private int sueldo = 1000;
        private String nombre = "Mengano";
        private DateTime fechaNacimiento;
        private Char estadoMarital = 'S';
        private Char sexo = 'H';
        private bool vehiculo = true;


        public String Dni
        {
            get { return dni; }
            set { dni = value; }
        }
        public int Sueldo
        {
            get { return sueldo; }
            set { sueldo = value; }
        }
        public String Nombre{
            get { return nombre; }
            set { nombre = value; }
        }
        public DateTime FechaNacimiento
        {
            get { return fechaNacimiento; }
            set { fechaNacimiento = value; }
        }
        public Char EstadoMarital
        {
            get { return estadoMarital; }
            set { estadoMarital = value; }
        }
        public Char Sexo
        {
            get { return sexo; }
            set { sexo = value; }
        }

        public bool Vehiculo
        {
            get { return vehiculo; }
            set { vehiculo = value; }
        }
        public Persona(String dni, String nombre, DateTime fechaNacimiento, int sueldo, Char estadoMarital, Char sexo, bool vehiculo)
        {
            this.dni = dni;
            this.nombre = nombre;
            this.fechaNacimiento = fechaNacimiento;
            this.sueldo = sueldo;
            this.estadoMarital = estadoMarital;
            this.sexo = sexo;
            this.vehiculo = vehiculo;
        }

        public Persona()
        {

        }

        public void sueldoX(int sueldo){
            this.sueldo = sueldo;
        }
    }
}
