using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace EmpleadosJefesClases
{
    class Empleado : Persona
    {
        private int duracionContrato = 90;

        public int DuracionContrato
        {
            get { return duracionContrato; }
            set { duracionContrato = value; }
        }
        private int codigoJefe = 0;

        public int CodigoJefe
        {
            get { return codigoJefe; }
            set { codigoJefe = value; }
        }

        public Empleado(int duracionContrato, int codigoJefe)
        {
            this.duracionContrato = duracionContrato;
            this.codigoJefe = codigoJefe;
        }
        public Empleado(String dni, String nombre, DateTime fechaNacimiento, int sueldo, Char estadoMarital, Char sexo, bool vehiculo, int duracionContrato, int codigoJefe)
            : base(dni, nombre, fechaNacimiento, sueldo, estadoMarital, sexo, vehiculo)
        {
            this.duracionContrato = duracionContrato;
            this.codigoJefe = codigoJefe;
        }
        void extenderContrato(int tiempo)
        {
            this.duracionContrato = this.duracionContrato + tiempo;
        }

    }
}
