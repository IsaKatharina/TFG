using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Entidades;
using DAL.Listados;
using DAL.Handlers;

namespace API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsuariosController : Controller
    {
        //GET:UsuariosController
        [HttpGet]
        public IActionResult Get()
        {
            IActionResult salida;

            List<clsUsuario> listaUsuarios = new List<clsUsuario>();

            try
            {
                listaUsuarios = clsListadoUsuariosDAL.getListadoUsuarios();

                if (listaUsuarios.Count() == 0)
                {
                    salida = NoContent();
                }
                else
                {
                    salida = Ok(listaUsuarios);
                }
            }
            catch (Exception e)
            {
                salida = BadRequest(e);
            }
            return salida;

        }

        // GET api/<UsuariosController>/5
        [HttpGet("{idUsuario}")]
        public IActionResult Get(int idUsuario)
        {
            IActionResult salida;
            clsUsuario userById = new clsUsuario();


            try
            {
                userById = clsListadoUsuariosDAL.readDetailsUsuarioDAL(idUsuario);

                if (userById == null)
                {
                    salida = NotFound(); //no se encuentra la persona

                }
                else
                {
                    salida = Ok(userById); //hay que mandar la persona con el ActionResult.
                }

            }
            catch (Exception e)
            {
                salida = BadRequest(e);
            }

            return salida;
        }

        // POST api/<UsuariosController>
        [HttpPost]
        public IActionResult Post([FromBody] clsUsuario usuario)
        {

            IActionResult salida;
            int numFilasAfectadas = 0;

            try
            {
                numFilasAfectadas = clsHandlerUsuarioDAL.insertUsuarioDAL(usuario);

                if (numFilasAfectadas == 0)
                {
                    salida = NotFound(); //no se ha hecho

                }
                else
                {
                    salida = Ok(); //se ha creado el usuario
                }

            }
            catch (Exception e)
            {
                salida = BadRequest(e);
            }

            return salida;


        }

        // PUT api/<UsuariosController>/5
        [HttpPut("{idUsuario}")]
        public IActionResult Put([FromBody] clsUsuario usuario)
        {

            IActionResult salida;
            int numFilasAfectadas = 0;

            try
            {
                numFilasAfectadas = clsHandlerUsuarioDAL.updateUsuarioDAL(usuario);

                if (numFilasAfectadas == 0)
                {
                    salida = NotFound(); //no se ha hecho

                }
                else
                {
                    salida = Ok(); //se ha borrado 
                }

            }
            catch (Exception e)
            {
                salida = BadRequest(e);
            }

            return salida;


        }

        // DELETE api/<UsuariosController>/5
        [HttpDelete("{idUsuario}")]
        public IActionResult Delete(int idUsuario)
        {
            IActionResult salida;
            int numFilasAfectadas = 0;

            try
            {
                numFilasAfectadas = clsHandlerUsuarioDAL.deleteUsuarioDAL(idUsuario);

                if (numFilasAfectadas == 0)
                {
                    salida = NotFound(); //no se ha hecho

                }
                else
                {
                    salida = Ok(); //se ha borrado 
                }

            }
            catch (Exception e)
            {
                salida = BadRequest(e);
            }

            return salida;
        }

    }
}
