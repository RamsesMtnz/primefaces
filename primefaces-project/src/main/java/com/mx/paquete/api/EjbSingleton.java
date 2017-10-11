package com.mx.paquete.api;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.mx.paquete.accesodatos.dao.MaestroDAO;
import com.mx.paquete.accesodatos.pu.MaestroEntity;

@Singleton
//@Startup
public class EjbSingleton {

	@Inject MaestroDAO maestroDAO;
	
	@PostConstruct
	public void inicio(){
		System.out.println("Hola iniciando :)");
		
		List<MaestroEntity> lists = maestroDAO.obtenerPorNombre("Marcos Antonio");
		System.out.println("Van los maestros");
		for (MaestroEntity me : lists) {
			System.out.println(me.getNombre()+" "+me.getApellido_paterno());
			
			me.setNombre("Ramses");
		}
		System.out.println("Fin  los maestros");
		
		MaestroEntity m = new MaestroEntity();
		m.setApellido_materno("dominguez");
		m.setApellido_paterno("perro");
		m.setCorreo("perro@hotmail.com");
		m.setIdEmpleado(50);
		m.setNombre("pluto el perrito maricon");
		maestroDAO.guardar(m);
	}
	
	
}
