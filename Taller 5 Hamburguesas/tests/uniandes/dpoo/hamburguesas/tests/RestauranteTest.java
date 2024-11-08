package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;
public class RestauranteTest {
	
	private Restaurante restaurante;
	
	@BeforeEach
    void setUp() {
        restaurante = new Restaurante();
    }
	@AfterEach
	void tearDown() {
    }
	
    @Test
    void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
    	assertNull(restaurante.getPedidoEnCurso(),"Hay pedido en cusro");
    	restaurante.iniciarPedido( "Samuel Montoya", "Calle 107" );
    	Pedido pedido = restaurante.getPedidoEnCurso();
    assertNotNull(pedido,"No hay un pedido en cusro");
    assertThrows(YaHayUnPedidoEnCursoException.class, () -> 
    {
    	restaurante.iniciarPedido("Juan Roa", "162");
    	}, "Lanza la exception porque ya habiamos iniciado un pedido");}
    

    @Test
    void testNoPedidoEnCurso() throws NoHayPedidoEnCursoException {
    	assertNull(restaurante.getPedidoEnCurso());
    	assertThrows(NoHayPedidoEnCursoException.class, () -> {
            restaurante.cerrarYGuardarPedido();
        }, "Lanza la excepcion porque no hay un pedido en curso");
    }
    
    @Test
    void testCerrarYGuardarPedido() throws NoHayPedidoEnCursoException, IOException, YaHayUnPedidoEnCursoException {
    	restaurante.iniciarPedido( "Samuel Montoya", "Calle 107" );
        Pedido pedido = restaurante.getPedidoEnCurso();
        restaurante.cerrarYGuardarPedido();
        assertNull(restaurante.getPedidoEnCurso(),"El pedido sigue en curso");
        File file = new File("factura" + pedido.getIdPedido() + ".txt");
        System.out.println(file.toString());
        System.out.println(pedido.getIdPedido());
        assertTrue(file.exists(),"No se ha creado el archivo de factura");
        assertTrue(file.length()>0,"La factura se guardo");
    }
    @Test
    void testGetPedidos() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
    	restaurante.iniciarPedido( "Samuel Montoya", "Calle 107" );
    	restaurante.cerrarYGuardarPedido();
        restaurante.iniciarPedido( "Juan Roa", "162" );
        restaurante.cerrarYGuardarPedido();
        ArrayList<Pedido> pedidos = restaurante.getPedidos();
        assertEquals(2,pedidos.size(),"No hay dos pedidos en la lista");
    }
    @Test
    void testCargarRestaurante() throws NumberFormatException, HamburguesaException, IOException  {
    	File ing = new File("data/ingredientes.txt");
    	File menu = new File("data/menu.txt");
    	File combos = new File("data/combos.txt");
    	restaurante.cargarInformacionRestaurante(ing, menu, combos);
    	
    	assertEquals(restaurante.getIngredientes().size(),15,"No estan todos los ingredientes");
    	assertEquals(restaurante.getMenuBase().size(),22,"No estan todos los elementos del meno");
    	assertEquals(restaurante.getMenuCombos().size(),4,"No estan todos los combos");
    	
    }
}
