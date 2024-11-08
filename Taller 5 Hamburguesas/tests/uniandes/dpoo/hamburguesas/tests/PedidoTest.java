package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
public class PedidoTest {
	
	private ProductoMenu productoMenu1;
	private ProductoMenu productoMenu2;
	private ProductoAjustado productoAjustado;
	private Ingrediente ingrediente1;
	private Ingrediente ingrediente2;
	
	private Combo combo;
	private ArrayList<Producto> productos;
	private ArrayList<ProductoMenu> productosMenu;
	
	private Pedido pedido;
	
	
	@BeforeEach
	void setUp( ){
		productos = new ArrayList<Producto>();
		
		productoMenu1 = new ProductoMenu("Hamburguesa",10000);
		productoMenu2 = new ProductoMenu("Pizza",15000);
		productosMenu = new ArrayList<ProductoMenu>();
		
		productosMenu.add(productoMenu1);
		productosMenu.add(productoMenu2);
		
		ingrediente1 = new Ingrediente("Tomate",2000);
		ingrediente2 = new Ingrediente("Cebolla",1000);
		
		productoAjustado = new ProductoAjustado(productoMenu1);
		productoAjustado.agregarIngredientes(ingrediente1);
		productoAjustado.eliminarIngredientes(ingrediente2);
		
		combo = new Combo("Combo 1",0.1, productosMenu);
		pedido = new Pedido("Roberto", "Universidad de los andes");
		pedido.numeroPedidos = 0;
		
		pedido.agregarProducto(productoAjustado);
		pedido.agregarProducto(combo);
		pedido.agregarProducto(productoMenu1);
		pedido.agregarProducto(productoMenu2);
		
		
        
	}
	
	@AfterEach
	void tearDown( ){
    }
	
    @Test
    void testGetIdPedido(){
    		assertEquals(1, pedido.getIdPedido(),"El id del pedido no es el correcto");
    }
    
    @Test
    void testGetNombreCliente(){
    	
            assertEquals("Roberto", pedido.getNombreCliente(),"El nombre del cliente no es el correcto");
    }
    
    @Test
    void testAgregarProducto(){
    	
    		assertEquals(4,pedido.cantidadProductos(),"No se agregaron los productos correctamente");
    }
    
    @Test
    void testGetPrecioTotalPedido(){
    	
        assertEquals(70805, pedido.getPrecioTotalPedido(),"El precio total del pedido no es el correcto");
    }
    	@Test
    void testGenerarTextoFactura(){
    		
    		String stringCliente = "Cliente: Roberto\nDirección: Universidad de los andes\n----------------\n";
        	String stringProductos = productoAjustado.generarTextoFactura()+combo.generarTextoFactura()+productoMenu1.generarTextoFactura()+productoMenu2.generarTextoFactura();
        	String stringPrecio = "----------------\nPrecio Neto:  59500\nIVA:          11305\nPrecio Total: 70805\n";
        	String stringTotal = stringCliente+stringProductos+stringPrecio;
        	
        	assertEquals(stringTotal,pedido.generarTextoFactura(), "El texto del pedido se genero de manera incorrecta.");
    	}
    	@Test
    void testGetPrecioIVAPedido(){
    		
    		assertEquals(11305,pedido.getPrecioIVAPedido(),"El precio del Iva no es el correcto");
    		
    	}
    	@Test
    void testGetPrecioNetoPedidod(){
    		
    		assertEquals(59500,pedido.getPrecioNetoPedido(),"El precio del pedido no es el correcto");
    	}
    	@Test
    	void crearFactura(){
    		
    		File f = new File("data/facturaTestPedido.txt");
    		try {
				pedido.guardarFactura(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    		assertTrue(f.exists(), "El archivo de factura no se creo correctamente.");
    	}
    	@Test
    	void guardarFactura(){
    	
    		
    		File f = new File("data/facturaTestPedido.txt");
    		try {
				pedido.guardarFactura(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        	
            StringBuilder archivoString = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                	archivoString.append(linea).append("\n");
                }
            } catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            String facturaGenerada = pedido.generarTextoFactura();
            assertEquals(facturaGenerada, archivoString.toString(), "El contenido de la factura guardada es incorrecto.");
    	}
}
