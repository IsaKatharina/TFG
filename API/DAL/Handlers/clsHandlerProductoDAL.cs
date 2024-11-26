using DAL.Connection;
using Entidades;
using Microsoft.Data.SqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL.Handlers
{
    public class clsHandlerProductoDAL
    {
        /// <summary>
        /// Método que borra un registro de la tabla Personas
        /// 
        /// Precondición: int idPersona
        /// Postcondición: int numeroFilasAfectadas
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public static int deleteProductoDAL(int idProducto)
        {
            int numeroFilasAfectadas = 0;

            clsConnection conexion = new clsConnection();
            SqlCommand cmd = new SqlCommand();

            //Añadimos un parámetro que luego necesitaremos en el comando sql.
            cmd.Parameters.Add("@idProducto", System.Data.SqlDbType.Int).Value = idProducto;

            try
            {
                //abrimos la conexion y la guardamos en una variable
                SqlConnection conexionAbierta = conexion.getConnection();

                cmd.CommandText = "DELETE FROM Productos WHERE idProducto=@idProducto";
                cmd.Connection = conexionAbierta;
                numeroFilasAfectadas = cmd.ExecuteNonQuery();

                //Cerramos la conexión
                conexionAbierta.Close();

            }
            catch (Exception ex)
            {
                throw ex;
            }


            return numeroFilasAfectadas;


        }

        /// <summary>
        /// Método que actualiza el idDepartamento al que pertenece una persona.
        /// 
        /// Pre: recibe un id de la persona y un idDepartamento.
        /// Post: devuelve un número de filas actualizadas.
        /// </summary>
        /// <param name="id"></param>
        /// <param name="idDepartamento"></param>
        /// <returns></returns>
        public static int updateProductoDAL(clsProducto producto)
        {
            int numeroFilasAfectadas = 0;

            clsConnection conexion = new clsConnection();
            SqlCommand cmd = new SqlCommand();

            //Añadimos un parámetro que luego necesitaremos en el comando sql.
            cmd.Parameters.Add("@idProducto", System.Data.SqlDbType.Int).Value = producto.IdProducto;
            cmd.Parameters.Add("@idUsuario", System.Data.SqlDbType.Int).Value = producto.IdUsuario;
            cmd.Parameters.Add("@nombre", System.Data.SqlDbType.VarChar, 30).Value = producto.Nombre;
            cmd.Parameters.Add("@marca", System.Data.SqlDbType.VarChar, 30).Value = producto.Marca;
            cmd.Parameters.Add("@nombreOG", System.Data.SqlDbType.VarChar, 30).Value = producto.NombreOG;
            cmd.Parameters.Add("@marcaOG", System.Data.SqlDbType.VarChar, 30).Value = producto.MarcaOG;
            cmd.Parameters.Add("@original", System.Data.SqlDbType.VarChar, 2).Value = producto.Original;
            cmd.Parameters.Add("@comentario", System.Data.SqlDbType.VarChar, 1000).Value = producto.Comentario;
            cmd.Parameters.Add("@imagen", System.Data.SqlDbType.VarChar, 1000).Value = producto.Imagen;


            try
            {
                //abrimos la conexion y la guardamos en una variable
                SqlConnection conexionAbierta = conexion.getConnection();

                cmd.CommandText = "UPDATE Productos SET Nombre=@nombre, marca=@marca, nombreOG=@nombreOG, marcaOG=@marcaOG, original=@original," +
                    "comentario=@comentario, imagen=@imagen WHERE idProducto=@idProducto and idUsuario=@idUsuario";
                cmd.Connection = conexionAbierta;
                numeroFilasAfectadas = cmd.ExecuteNonQuery();

                //Cerramos la conexión
                conexionAbierta.Close();

            }
            catch (Exception ex)
            {
                throw ex;
            }


            return numeroFilasAfectadas;


        }


        /// <summary>
        /// Método que inserta una persona nueva en la tabla Personas.
        /// 
        /// Pre:
        /// Post: devuelve la variable numeroFilasAfectadas.
        /// </summary>
        /// <param name="nombre"></param>
        /// <param name="apellidos"></param>
        /// <param name="tlf"></param>
        /// <param name="direccion"></param>
        /// <param name="foto"></param>
        /// <param name="fechaNac"></param>
        /// <param name="idDepartamento"></param>
        /// <returns></returns>
        public static int insertProductoDAL(clsProducto producto)
        {
            int numeroFilasAfectadas = 0;

            clsConnection conexion = new clsConnection();
            SqlCommand cmd = new SqlCommand();

            //Añadimos un parámetro que luego necesitaremos en el comando sql.
            cmd.Parameters.Add("@idUsuario", System.Data.SqlDbType.Int).Value = producto.IdUsuario;
            cmd.Parameters.Add("@nombre", System.Data.SqlDbType.VarChar, 30).Value = producto.Nombre;
            cmd.Parameters.Add("@marca", System.Data.SqlDbType.VarChar, 30).Value = producto.Marca;
            cmd.Parameters.Add("@nombreOG", System.Data.SqlDbType.VarChar, 30).Value = producto.NombreOG;
            cmd.Parameters.Add("@marcaOG", System.Data.SqlDbType.VarChar, 30).Value = producto.MarcaOG;
            cmd.Parameters.Add("@original", System.Data.SqlDbType.VarChar, 2).Value = producto.Original;
            cmd.Parameters.Add("@comentario", System.Data.SqlDbType.VarChar, 1000).Value = producto.Comentario;
            cmd.Parameters.Add("@imagen", System.Data.SqlDbType.VarChar, 1000).Value = producto.Imagen;

            try
            {
                //abrimos la conexion y la guardamos en una variable
                SqlConnection conexionAbierta = conexion.getConnection();

                cmd.CommandText = "INSERT INTO Productos (idUsuario, nombre, marca, nombreOG, marcaOG, original, comentario, imagen)" +
                    "values (@idUsuario, @nombre, @marca, @nombreOG, @marcaOG, @original, @comentario, @imagen)";
                cmd.Connection = conexionAbierta;
                numeroFilasAfectadas = cmd.ExecuteNonQuery();

                //Cerramos la conexión
                conexionAbierta.Close();

            }
            catch (Exception ex)
            {
                throw ex;
            }


            return numeroFilasAfectadas;

        }

    }
}
