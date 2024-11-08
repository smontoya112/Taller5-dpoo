package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	
	private ProductoMenu productoMenu;
	
    @BeforeEach
    void setUp( ) throws Exception{
    	productoMenu =new ProductoMenu("Hamburguesa",10000);
    }
	@AfterEach
	void tearDown( ) {
    }
    
	@Test
    void testGetPrecio() {
        assertEquals(10000, productoMenu.getPrecio(),"El precio del producto no coincide con el esperado");
    }
	
	@Test
    void testGetNombre() {
        assertEquals("Hamburguesa", productoMenu.getNombre(), "El nombre del producto no coincide con el esperado");
    }
	
	@Test
	void testFactura() {
			String factura = productoMenu.generarTextoFactura();
        assertEquals("Hamburguesa\n            10000\n", factura, "El texto de la factura no coincide con el esperado");
	}
}
