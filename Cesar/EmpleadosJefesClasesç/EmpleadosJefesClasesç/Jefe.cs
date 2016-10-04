using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace EmpleadosJefesClases
{
    class Jefe : Persona
    {
        private String departamento = "";

        public String Departamento
        {
            get { return departamento; }
            set { departamento = value; }
        }
        private String region = "";

        public String Region
        {
            get { return region; }
            set { region = value; }
        }
        private int codigo = 0;

        public int Codigo
        {
            get { return codigo; }
            set { codigo = value; }
        }

        public Jefe(String dni, String nombre, DateTime fechaNacimiento, int sueldo, Char estadoMarital, Char sexo, bool vehiculo, String departamento, String region, int codigo)
            : base(dni, nombre, fechaNacimiento, sueldo, estadoMarital, sexo, vehiculo)
        {
            this.departamento = departamento;
            this.region = region;
            this.codigo = codigo;
        }

        
            //Metodo sin usar, la intencion era mostrar si un empleado esta bajo el control de un jefe especifico
            Boolean esSuJefe(Empleado empleado)
        {
            if (this.codigo == empleado.CodigoJefe)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}