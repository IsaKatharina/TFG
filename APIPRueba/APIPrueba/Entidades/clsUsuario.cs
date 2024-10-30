using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entidades
{
    public class clsUsuario
    {
        #region atributos
        private int idUsuario;
        private string nombre;
        private string apellidos;
        private string correo;
        private string password;
        private DateTime fechaNac;
        private string foto;
        #endregion

        #region constructores
        public clsUsuario()
        {
            idUsuario = 0;
            nombre = string.Empty;
            apellidos = string.Empty;
            correo = string.Empty;
            password = string.Empty;
            fechaNac = DateTime.Now;
            foto = string.Empty;
        }

        public clsUsuario(int idUsuario, string nombre, string apellidos, string correo, string password, DateTime fechaNac, string foto)
        {
            this.idUsuario = idUsuario;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.correo = correo;
            this.password = password;
            this.fechaNac = fechaNac;
            this.foto = foto;
        }
        #endregion

        #region propiedades
        public int IdUsuario {
            get { return idUsuario; }
            set { idUsuario = value; }
        }

        public string Nombre {
            get { return nombre; }
            set { nombre = value; }
        }

        public string Apellidos
        {
            get { return apellidos; }
            set { apellidos = value; }
        }

        public string Correo
        {
            get { return correo; }
            set { correo = value; }
        }

        public string Password
        {
            get { return password; }
            set { password = value; }
        }

        public DateTime FechaNac
        {
            get { return fechaNac; }
            set { fechaNac = value; }
        }

        public string Foto
        {
            get { return foto; }
            set { foto = value; }
        }

        #endregion

    }
}
