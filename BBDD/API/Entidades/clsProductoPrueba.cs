using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entidades
{
    public class clsProductoPrueba
    {
        #region atributos
        private string nombre;
        private string img;
        #endregion

        #region constructores
        public clsProductoPrueba()
        {
            nombre = string.Empty;
            img = string.Empty;
        }

        public clsProductoPrueba(string nombre,string img)
        {
            this.nombre = nombre;
            this.img = img;
        }
        #endregion

        #region prodiedades

        public string Nombre
        {
            get { return nombre; }
            set { nombre = value; }
        }

        public string Img
        {
            get { return img; }
            set { img = value; }
        }
        #endregion
    }
}
