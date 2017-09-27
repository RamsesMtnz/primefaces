package com.mx.paquete.accesodatos.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericoDAO<T,PK extends Serializable> {
	
	T obtenerReferencia(PK id);
	
	/**
	 * Busqueda por id 
	 * @param id
	 * @return si no encuentra resultados  retorna un nullo
	 */
	T obtenerPorId(PK id);  
    
	/**
	 * Retorna lista paginada 
	 * Si al page o el size se le pasa 0 retorna todo el catalogo
	 * @param page : pagina a consultar , inicia apartir de 1
	 * @param size : tamaño maximo de la consulta inicia apartir de 1
	 * @return
	 */
    List<T> obtenerPaginado( int page, int size);  
    
    /**
     * Retorna la cantidad de registros que tiene la tabla
     * se utiliza como apoyo del metodo obtener paginado para saber la cantidad
     * de registros
     * @return
     */
    long obtenerTotalRegistrosPaginado();
    
    void guardar(T entidad);
   

}
