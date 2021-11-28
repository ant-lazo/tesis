package com.bolsadeideas.springboot.app;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bolsadeideas.springboot.app.controllers.ClienteController;
import com.bolsadeideas.springboot.app.models.dao.IFacturaDao;
import com.bolsadeideas.springboot.app.models.dao.IProductoDao;
import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.ItemFactura;
import com.bolsadeideas.springboot.app.models.entity.Producto;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.bolsadeideas.springboot.app.models.service.ClienteServiceImpl;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageItem;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootDataJpaApplicationTests {
	
	//Comprobar la pagina actual del Paginator
	PageItem p = new PageItem(5, true);

	@Test
	void test1() {
		boolean resultado=p.isActual();
		System.out.println(resultado);
		assertTrue(resultado);
	}
	
	//Comprobar si cliente esta en la base de datos
	@Autowired
	ClienteServiceImpl clienteServiceImpl;
			
	@Test
	void test2() {
		Long id = (long) 4;
		Cliente cliente = clienteServiceImpl.findOne(id);
		String apellido = cliente.getApellido();
		System.out.println(apellido);
		assertEquals("lazo", apellido);
	}
	
	//Comprobar si exite un usuario con nombre omar
	@Autowired
	IUsuarioDao iUsuarioDao;
	
	@Test
	void test3() {
		String username = "omar";
		Usuario usuario =iUsuarioDao.findByUsername(username);
		String pass= usuario.getPassword();
		System.out.println(pass);
		assertTrue(usuario.getEnabled());
	}

}
