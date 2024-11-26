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
        private string nombreUsu;
        private string correo;
        private string password;
        private string foto;
        #endregion

        #region constructores
        public clsUsuario()
        {
            idUsuario = 0;
            nombreUsu = string.Empty;
            correo = string.Empty;
            password = string.Empty;
            foto = string.Empty;
        }

        public clsUsuario(int idUsuario, string nombreUsu, string apellidos, string correo, string password, DateTime fechaNac, string foto)
        {
            this.idUsuario = idUsuario;
            this.nombreUsu = nombreUsu;
            this.correo = correo;
            this.password = password;
            this.foto = foto;
        }
        #endregion

        #region propiedades
        public int IdUsuario {
            get { return idUsuario; }
            set { idUsuario = value; }
        }

        public string NombreUsu {
            get { return nombreUsu; }
            set { nombreUsu = value; }
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

        public string Foto
        {
            get { return foto; }
            set { foto = value; }
        }

        #endregion

    }
}
