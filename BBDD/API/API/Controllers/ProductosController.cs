using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using DAL.Handlers;
using Entidades;
using DAL.Listados;

namespace API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    
        public class ProductoController : Controller
        {
            //GET:UsuarioController
            [HttpGet]
            public IActionResult Get()
            {
                IActionResult salida;

                List<clsProducto> listaProductos = new List<clsProducto>();

                try
                {
                    listaProductos = clsListadoProductosDAL.getListadoProductosDAL();

                    if (listaProductos.Count() == 0)
                    {
                        salida = NoContent();
                    }
                    else
                    {
                        salida = Ok(listaProductos);
                    }
                }
                catch (Exception e)
                {
                    salida = BadRequest(e);
                }
                return salida;

            }

            // GET api/<PersonasController>/5
            [HttpGet("{idProducto}")]
            public IActionResult Get(int idProducto)
            {
                IActionResult salida;
                clsProducto productById = new clsProducto();


                try
                {
                    productById = clsListadoProductosDAL.readDetailsProductoDAL(idProducto);

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

            // POST api/<PersonasController>
            [HttpPost]
            public IActionResult Post([FromBody] clsProducto producto)
            {

                IActionResult salida;
                int numFilasAfectadas = 0;

                try
                {
                    numFilasAfectadas = clsHandlerProductoDAL.insertProductoDAL(producto);

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

            // PUT api/<PersonasController>/5
            [HttpPut("{idProducto}")]
            public IActionResult Put([FromBody] clsProducto producto)
            {

                IActionResult salida;
                int numFilasAfectadas = 0;

                try
                {
                    numFilasAfectadas = clsHandlerProductoDAL.updateProductoDAL(producto);

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

            // DELETE api/<PersonasController>/5
            [HttpDelete("{idProducto}")]
            public IActionResult Delete(int idProducto)

            {
                IActionResult salida;
                int numFilasAfectadas = 0;

                try
                {
                    numFilasAfectadas = clsHandlerProductoDAL.deleteProductoDAL(idProducto);

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

