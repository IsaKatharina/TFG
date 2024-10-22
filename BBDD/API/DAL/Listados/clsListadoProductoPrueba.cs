using DAL.Connection;
using Entidades;
using Microsoft.Data.SqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL.Listados
{
    public class clsListadoProductoPrueba
    {
        public static List<clsProductoPrueba> getListadoProductosPruebaDAL()
        {

            clsConnection conexion = new clsConnection();
            List<clsProductoPrueba> listadoProductosPrueba = new List<clsProductoPrueba>();
            SqlCommand cmd = new SqlCommand();
            SqlDataReader reader;
            clsProductoPrueba oProductoPrueba;



            try
            {
                //abrimos la conexion y la guardamos en una variable
                SqlConnection conexionAbierta = conexion.getConnection();

                cmd.CommandText = "Select * from ProductosPrueba";
                cmd.Connection = conexionAbierta;

                reader = cmd.ExecuteReader();

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        oProductoPrueba = new clsProductoPrueba();
                       
                        //en caso de que pueda ser null
                        if (reader["nombre"] != System.DBNull.Value)
                        {
                            oProductoPrueba.Nombre = (string)reader["nombre"];
                        }
                       
                        if (reader["img"] != System.DBNull.Value)
                        {
                            oProductoPrueba.Img = (string)reader["img"];
                        }

                        listadoProductosPrueba.Add(oProductoPrueba);

                    }
                }
                reader.Close();
                conexionAbierta.Close();


            }
            catch (SqlException ex)
            {
                throw ex;
            }

            return listadoProductosPrueba;

        }

    }
}

