package com.mx.paquete.accesodatos.dao;

import java.util.List;

import com.mx.paquete.accesodatos.pu.MaestroEntity;

public interface MaestroDAO extends GenericoDAO<MaestroEntity, Integer>{
	
	List<MaestroEntity> obtenerPorNombre(String nombre);

}
