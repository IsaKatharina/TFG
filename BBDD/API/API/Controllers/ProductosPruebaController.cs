using DAL.Listados;
using Entidades;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductosPruebaController : Controller
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
    }
}
