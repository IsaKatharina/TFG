using DAL.Listados;
using Entidades;
using Microsoft.AspNetCore.Mvc;

namespace APIPrueba.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductosPruebaController : ControllerBase
    {
        //GET:ProductosPruebasController
        [HttpGet]
        public IActionResult Get()
        {
            IActionResult salida;

            List<clsProductoPrueba> listaProductosPrueba = new List<clsProductoPrueba>();

            try
            {
                listaProductosPrueba = clsListadoProductoPrueba.getListadoProductosPruebaDAL();

                if (listaProductosPrueba.Count() == 0)
                {
                    salida = NoContent();
                }
                else
                {
                    salida = Ok(listaProductosPrueba);
                }
            }
            catch (Exception e)
            {
                salida = BadRequest(e);
            }
            return salida;

        }


        // GET api/<ProductosPrueba>/5
        [HttpGet("{nombre}")]
        public IActionResult Get(string nombre)
        {
            IActionResult salida;
            clsProductoPrueba productById = new clsProductoPrueba();


            try
            {
                productById = clsListadoProductoPrueba.readDetailsProductosPruebaDAL(nombre);

                if (productById == null)
                {
                    salida = NotFound(); //no se encuentra la persona

                }
                else
                {
                    salida = Ok(productById); //hay que mandar la persona con el ActionResult.
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
