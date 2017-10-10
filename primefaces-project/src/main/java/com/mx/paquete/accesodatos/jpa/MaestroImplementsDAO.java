package com.mx.paquete.accesodatos.jpa;

import java.util.HashMap;
import java.util.List;

import com.mx.paquete.accesodatos.dao.MaestroDAO;
import com.mx.paquete.accesodatos.pu.MaestroEntity;

public class MaestroImplementsDAO extends GenericoImplementsDAO<MaestroEntity, Integer> implements MaestroDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<MaestroEntity> obtenerPorNombre(String nombre) {
		// TODO Auto-generated method stub
		HashMap<String,Object> params = new HashMap<>();
		params.put("nombre", nombre);
		return obtenerListaPaginado("from MaestroEntity as m where m.nombre=:nombre", params, 0, 0);
	}

}
