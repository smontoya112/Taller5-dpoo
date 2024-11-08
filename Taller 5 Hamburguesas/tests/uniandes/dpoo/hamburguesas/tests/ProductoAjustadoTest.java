package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	
	private ProductoAjustado producto1;
	private ProductoAjustado producto2;
	private ProductoMenu prodMenu1;
	private ProductoMenu prodMenu2;	
	private Ingrediente ing1;
	private Ingrediente ing2;
	private Ingrediente ing3;
	private Ingrediente ing4;
	
	
	
	@BeforeEach
    void setUpClass() throws Exception {
	ing1 = new Ingrediente("Papas",4000);
	ing2 = new Ingrediente("Tomate",2000);
	ing3 = new Ingrediente("Cebolla",2000);
	ing4 = new Ingrediente("Pepinillos",1000);
	prodMenu1 = new ProductoMenu("Hamburguesa", 25000);
	prodMenu2 = new ProductoMenu("Hamburquesa", 30000);
	producto1 = new ProductoAjustado(prodMenu1);
	producto2 = new ProductoAjustado(prodMenu2);
	producto1.agregarIngredientes(ing1);
	producto1.eliminarIngredientes(ing2);
	producto2.agregarIngredientes(ing3);
	producto2.eliminarIngredientes(ing4);
	}
	
    @AfterEach
    void tearDownClass()
    {
    }
    
    @Test
    void testGetNombre() {
    	
    	assertEquals(producto1.getNombre(), "Hamburguesa", "Los nombres no concuerdan");
    	assertEquals(producto2.getNombre(), "Hamburquesa", "Los nombres no concuerdan");
    
    }
    
    @Test
    void testGetCosto() {
    
    	assertEquals(producto1.getPrecio(),29000,"El precio no es el correcto");
    	assertEquals(producto2.getPrecio(),32000,"El precio no es el correcto");
    	
    }
    @Test
    void testGenerarFactura() {
    	assertEquals(producto1.generarTextoFactura(),"Hamburguesa    +Papas                4000    -Tomate            29000\n");
    	assertEquals(producto2.generarTextoFactura(),"Hamburquesa    +Cebolla                2000    -Pepinillos            32000\n");
    	
    }
}
