using DAL.Connection;
using Microsoft.Data.SqlClient;
using Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL.Handlers
{
  public class clsHandlerUsuarioDAL
    {
        /// <summary>
        /// Método que borra un registro de la tabla Personas
        /// 
        /// Precondición: int idPersona
        /// Postcondición: int numeroFilasAfectadas
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public static int deleteUsuarioDAL(int idUsuario)
        {
            int numeroFilasAfectadas = 0;

            clsConnection conexion = new clsConnection();
            SqlCommand cmd = new SqlCommand();

            //Añadimos un parámetro que luego necesitaremos en el comando sql.
            cmd.Parameters.Add("@id", System.Data.SqlDbType.Int).Value = idUsuario;

            try
            {
                //abrimos la conexion y la guardamos en una variable
                SqlConnection conexionAbierta = conexion.getConnection();

                cmd.CommandText = "DELETE FROM Usuario WHERE idUsuario=@id";
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
        public static int updatePersonaDAL(clsUsuario usuario)
        {
            int numeroFilasAfectadas = 0;

            clsConnection conexion = new clsConnection();
            SqlCommand cmd = new SqlCommand();

            //Añadimos un parámetro que luego necesitaremos en el comando sql.
            cmd.Parameters.Add("@idUsuario", System.Data.SqlDbType.Int).Value = usuario.IdUsuario;
            cmd.Parameters.Add("@nombre", System.Data.SqlDbType.VarChar, 30).Value = usuario.Nombre;
            cmd.Parameters.Add("@apellidos", System.Data.SqlDbType.VarChar, 60).Value = usuario.Apellidos;
            cmd.Parameters.Add("@correo", System.Data.SqlDbType.VarChar, 100).Value = usuario.Correo;
            cmd.Parameters.Add("@password", System.Data.SqlDbType.VarChar, 30).Value = usuario.Password;
            cmd.Parameters.Add("@fechaNac", System.Data.SqlDbType.Date).Value = usuario.FechaNac;
            cmd.Parameters.Add("@foto", System.Data.SqlDbType.Date).Value = usuario.Foto;


            try
            {
                //abrimos la conexion y la guardamos en una variable
                SqlConnection conexionAbierta = conexion.getConnection();

                cmd.CommandText = "UPDATE Usuario SET Nombre=@nombre, Apellidos=@apellidos, correo=@correo, password=@password," +
                    "Foto=@foto, FechaNac=@fechaNac WHERE idUsuario=@idUsuario";
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
        public static int insertUsuarioDAL(clsUsuario usuario)
        {
            int numeroFilasAfectadas = 0;

            clsConnection conexion = new clsConnection();
            SqlCommand cmd = new SqlCommand();

            //Añadimos un parámetro que luego necesitaremos en el comando sql.
            cmd.Parameters.Add("@nombre", System.Data.SqlDbType.VarChar, 30).Value = usuario.Nombre;
            cmd.Parameters.Add("@apellidos", System.Data.SqlDbType.VarChar, 60).Value = usuario.Apellidos;
            cmd.Parameters.Add("@correo", System.Data.SqlDbType.VarChar, 100).Value = usuario.Correo;
            cmd.Parameters.Add("@password", System.Data.SqlDbType.VarChar, 30).Value = usuario.Password;
            cmd.Parameters.Add("@fechaNac", System.Data.SqlDbType.Date).Value = usuario.FechaNac;
            cmd.Parameters.Add("@foto", System.Data.SqlDbType.Date).Value = usuario.Foto;

            try
            {
                //abrimos la conexion y la guardamos en una variable
                SqlConnection conexionAbierta = conexion.getConnection();

                cmd.CommandText = "INSERT INTO Usuario (Nombre, Apellidos, Correo, Password, Foto, FechaNac)" +
                    "values (@nombre, @apellidos, @correo, @password, @foto, @fechaNac)";
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
