using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entidades
{
    public class clsProducto
    {
        #region atributos
        private int idProducto;
        private int idUsuario;
        private string nombre;
        private string marca;
        private string nombreOG;
        private string marcaOG;
        private string original;
        private string comentario;
        private string imagen;
        #endregion

        #region constructores
        public clsProducto()
        {
            idProducto = 0;
            idUsuario = 0;
            nombre= string.Empty;
            marca= string.Empty;
            nombreOG= string.Empty;
            marcaOG= string.Empty;
            original = string.Empty;
            comentario= string.Empty;
            imagen= string.Empty;
        }

        public clsProducto (int idProducto, int idUsuario, string nombre, string marca, string nombreOG, string marcaOG, string original, string comentario, string imagen)
        {
            this.idProducto = idProducto;
            this.idUsuario = idUsuario;
            this.nombre = nombre;
            this.marca = marca;
            this.nombreOG = nombreOG;
            this.marcaOG = marcaOG;
            this.original = original;
            this.comentario = comentario;
            this.imagen = imagen;
        }
        #endregion

        #region prodiedades
        public int IdProducto
        {
            get { return idProducto; }
            set { idProducto = value; }
        }

        public int IdUsuario
        {
            get { return idUsuario; }
            set { idUsuario = value; }
        }

        public string Nombre
        {
            get { return nombre; }
            set { nombre = value; }
        }

        public string Marca
        {
            get { return marca; }
            set { marca = value; }
        }

        public string NombreOG
        {
            get { return nombreOG; }
            set { nombreOG = value; }
        }

        public string MarcaOG
        {
            get { return marcaOG; }
            set { marcaOG = value; }
        }

        public string Original
        {
            get { return original; }
            set { original = value; }
        }

        public string Comentario
        {
            get { return comentario; }
            set { comentario = value; }
        }

        public string Imagen
        {
            get { return imagen; }
            set { imagen = value; }
        }
        #endregion
    }
}
