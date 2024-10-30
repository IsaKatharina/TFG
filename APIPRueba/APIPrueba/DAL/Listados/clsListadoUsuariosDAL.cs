using Microsoft.Data.SqlClient;
using Entidades;
using DAL.Connection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL.Listados
{
    /// <summary>
    /// Clase que se conecta con una base de datos y devuelve un listado de usuarios
    /// </summary>
    public class clsListadoUsuariosDAL
    {
        public static List<clsUsuario> getListadoUsuarios()
        {

            clsConnection conexion = new clsConnection();
            List<clsUsuario> listadoUsuarios = new List<clsUsuario>();
            SqlCommand cmd = new SqlCommand();
            SqlDataReader reader;
            clsUsuario oUsuario;



            try
            {
                //abrimos la conexion y la guardamos en una variable
                SqlConnection conexionAbierta = conexion.getConnection();

                cmd.CommandText = "Select * from usuarios";
                cmd.Connection = conexionAbierta;

                reader = cmd.ExecuteReader();

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        oUsuario = new clsUsuario();
                        oUsuario.IdUsuario = (int)reader["idUsuario"];
                        oUsuario.Nombre = (string)reader["nombre"];
                        oUsuario.Apellidos = (string)reader["apellidos"];
                        oUsuario.Correo = (string)reader["correo"];
                        oUsuario.Password = (string)reader["password"];
                        oUsuario.FechaNac = (DateTime)reader["fechaNac"];
                        oUsuario.Foto = (string)reader["foto"];

                        listadoUsuarios.Add(oUsuario);

                    }
                }
                reader.Close();
                conexionAbierta.Close();


            }
            catch (SqlException ex)
            {
                throw ex;
            }

            return listadoUsuarios;

        }
        /// <summary>
        /// Método que lee los detalles de una persona.
        /// 
        /// Pre: recibe un id de la persona y un idDepartamento.
        /// Post: 
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public static clsUsuario readDetailsUsuarioDAL(int idUsuario)
        {

            clsConnection conexion = new clsConnection();
            SqlCommand cmd = new SqlCommand();
            SqlDataReader reader;
            clsUsuario oUsuario = new clsUsuario();

            //Añadimos un parámetro que luego necesitaremos en el comando sql.
            cmd.Parameters.Add("@id", System.Data.SqlDbType.Int).Value = idUsuario;

            try
            {
                //abrimos la conexion y la guardamos en una variable
                SqlConnection conexionAbierta = conexion.getConnection();

                cmd.CommandText = "Select * from usuarios WHERE ID=@id";
                cmd.Connection = conexionAbierta;

                reader = cmd.ExecuteReader();

                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
               
                        oUsuario.IdUsuario = (int)reader["idUsuario"];
                        oUsuario.Nombre = (string)reader["nombre"];
                        oUsuario.Apellidos = (string)reader["apellidos"];
                        oUsuario.Correo = (string)reader["correo"];
                        oUsuario.Password = (string)reader["password"];
                        oUsuario.FechaNac = (DateTime)reader["fechaNac"];
                        oUsuario.Foto = (string)reader["foto"];
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

            return oUsuario;

        }   
    }
}
