package com.mx.paquete.test.dao;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mx.paquete.accesodatos.dao.MaestroDAO;
import com.mx.paquete.accesodatos.pu.MaestroEntity;

import junit.framework.Assert;


//pendiente
@RunWith(Arquillian.class)
public class MaestroTest {
	
	@Inject MaestroDAO maestroDAO;
	
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"));

		JavaArchive jar = ShrinkWrap
				.create(JavaArchive.class)
				.addPackage("com.mx.paquete.accesodatos.dao")
				.addPackage("com.mx.paquete.accesodatos.jpa")
				.addPackage("com.mx.paquete.accesodatos.pu")
				.addAsResource("test-persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println("****  Inicio jar ****");
		System.out.println(jar.toString(true));
		System.out.println("****  Fin jar ****");
		war.addAsLibraries(jar);
		

		
		
		System.out.println(war.toString(true));
		return war;
	}
	
	
	@Test
	public void generarComprobanteVersion33(){
		List<MaestroEntity> lists = maestroDAO.obtenerPaginado(0, 0);
		
		Assert.assertTrue(lists.size()>0);
	}
	

}
