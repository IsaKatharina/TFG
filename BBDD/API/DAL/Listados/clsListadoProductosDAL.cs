﻿using DAL.Connection;
using Entidades;
using Microsoft.Data.SqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL.Listados
{

    public class clsListadoProductosDAL 
    {
        public static List<clsProducto> getListadoProductosDAL()
        {

            clsConnection conexion = new clsConnection();
            List<clsProducto> listadoProductos = new List<clsProducto>();
            SqlCommand cmd = new SqlCommand();
            SqlDataReader reader;
            clsProducto oProducto;



            try
            {
                //abrimos la conexion y la guardamos en una variable
                SqlConnection conexionAbierta = conexion.getConnection();

                cmd.CommandText = "Select * from producto";
                cmd.Connection = conexionAbierta;

                reader = cmd.ExecuteReader();

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        oProducto = new clsProducto();
                        oProducto.IdProducto = (int)reader["idProducto"];
                        oProducto.IdUsuario = (int)reader["idUsuario"];
                        oProducto.Nombre = (string)reader["nombre"];
                        oProducto.Marca = (string)reader["marca"];
                        oProducto.NombreOG = (string)reader["nombreOG"];
                        oProducto.MarcaOG = (string)reader["marcaOG"];
                        oProducto.Original = (string)reader["original"];
                        oProducto.Comentario = (string)reader["comentario"];
                        oProducto.Imagen = (string)reader["imagen"];

                        listadoProductos.Add(oProducto);

                    }
                }
                reader.Close();
                conexionAbierta.Close();


            }
            catch (SqlException ex)
            {
                throw ex;
            }

            return listadoProductos;

        }
        /// <summary>
        /// Método que lee los detalles de una persona.
        /// 
        /// Pre: recibe un id de la persona y un idDepartamento.
        /// Post: 
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public static clsProducto readDetailsProductoDAL(int idProducto)
        {

            clsConnection conexion = new clsConnection();
            SqlCommand cmd = new SqlCommand();
            SqlDataReader reader;
            clsProducto oProducto = new clsProducto();

            //Añadimos un parámetro que luego necesitaremos en el comando sql.
            cmd.Parameters.Add("@id", System.Data.SqlDbType.Int).Value = idProducto;

            try
            {
                //abrimos la conexion y la guardamos en una variable
                SqlConnection conexionAbierta = conexion.getConnection();

                cmd.CommandText = "Select * from producto WHERE ID=@id";
                cmd.Connection = conexionAbierta;

                reader = cmd.ExecuteReader();

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        oProducto.IdProducto = (int)reader["idProducto"];
                        oProducto.IdUsuario = (int)reader["idUsuario"];
                        oProducto.Nombre = (string)reader["nombre"];
                        oProducto.Marca = (string)reader["marca"];
                        oProducto.NombreOG = (string)reader["nombreOG"];
                        oProducto.MarcaOG = (string)reader["marcaOG"];
                        oProducto.Original = (string)reader["original"];
                        oProducto.Comentario = (string)reader["comentario"];
                        oProducto.Imagen = (string)reader["imagen"];

                    }
                }
                reader.Close();
                //Cerramos la conexión
                conexionAbierta.Close();

            }
            catch (Exception ex)
            {
                throw ex;
            }

            return oProducto;

        }

    }
   

}