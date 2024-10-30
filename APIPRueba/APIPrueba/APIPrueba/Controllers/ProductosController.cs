using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using DAL.Handlers;
using Entidades;
using DAL.Listados;

namespace APIPrueba.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    
        public class ProductosController : Controller
        {
            //GET:ProductosController
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
                    Console.
                WriteLine(e.Message);
                }
                return salida;

            }

            // GET api/<ProductosController>/5
            [HttpGet("{idProducto}")]
            public IActionResult Get(int idProducto)
            {
                IActionResult salida;
                clsProducto productById = new clsProducto();

                try
                {
                    productById = clsListadoProductosDAL.readDetailsProductoDAL(idProducto);

                    //si el idProducto del producto es 0 significa que no existe en la tabla 
                    if (productById.IdProducto == 0)
                    {
                        salida = NotFound(); //no se encuentra el producto

                    }
                    else
                    {
                        salida = Ok(productById); //hay que mandar el producto con el ActionResult.
                    }

                }
                catch (Exception e)
                {
                    salida = BadRequest(e);
                Console.
                WriteLine(e.Message);
            }

                return salida;
            }

            // POST api/<ProductosController>
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

            // PUT api/<ProductosController>/5
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

            // DELETE api/<ProductosController>/5
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

