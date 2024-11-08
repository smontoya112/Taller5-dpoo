package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
public class ComboTest {

	private ProductoMenu prodMenu1;
	private ProductoMenu prodMenu2;	
	private ProductoMenu prodMenu3;	
	private ProductoMenu prodMenu4;	
	private ArrayList<ProductoMenu> productos1;
	private ArrayList<ProductoMenu> productos2;
	private double descuento1;
	private double descuento2;
	private Combo combo1;
	private Combo combo2;
	
	
	@BeforeEach
	void setUp( ) {
		productos1 = new ArrayList<ProductoMenu>();
		productos2 = new ArrayList<ProductoMenu>();
		
        prodMenu1 = new ProductoMenu("Hamburguesa",10000);
        prodMenu2 = new ProductoMenu("Papas",15000);
        
        prodMenu3 = new ProductoMenu("Hamburquesa",20000);
        prodMenu4 = new ProductoMenu("Quesadilla",25000);
        
        productos1.addLast(prodMenu1);
        productos1.addLast(prodMenu2);
        
        productos2.addLast(prodMenu3);
        productos2.addLast(prodMenu4);
        
        descuento1 = 0.1;
        descuento2 = 0.2;
        
        combo1 = new Combo(prodMenu1.getNombre() +" "+ prodMenu2.getNombre(),descuento1, productos1);
        combo2 = new Combo(prodMenu3.getNombre() +" "+ prodMenu4.getNombre(),descuento2, productos2);
        
    }
	@AfterEach
    void tearDown( ) {
    }
	
	@Test
	void testGetNombre() {
		assertEquals(combo1.getNombre(),"Hamburguesa Papas","Los nombre no son iguales");
		assertEquals(combo2.getNombre(),"Hamburquesa Quesadilla","Los nombre no son iguales");
	}
	@Test
    void testGetPrecio() {
        assertEquals(combo1.getPrecio(),25000-2500,"Los precios no son correctos");
        assertEquals(combo2.getPrecio(),45000-9000,"Los precios no son correctos");
        
        
    }
	@Test
    void testGenerarFactura() {

        
		assertEquals(combo1.generarTextoFactura(),"Combo Hamburguesa Papas\n Descuento: 0.1\n            22500\n", "La factura es incorrecta");
		assertEquals(combo2.generarTextoFactura(),"Combo Hamburquesa Quesadilla\n Descuento: 0.2\n            36000\n","La factura es incorrecta");
		
    }

}

