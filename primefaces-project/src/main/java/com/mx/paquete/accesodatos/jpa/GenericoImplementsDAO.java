package com.mx.paquete.accesodatos.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.mx.paquete.exception.BussinesException;


public class GenericoImplementsDAO<T, PK extends Serializable> implements com.mx.paquete.accesodatos.dao.GenericoDAO<T, PK>,Serializable {

	private static final long serialVersionUID = 1L;
	

	private Class<T> type;
	
	/**
	 * Constructor inicial de la clase
	 */
	@SuppressWarnings("unchecked")
	public GenericoImplementsDAO() {
		 type =  (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@PersistenceContext(unitName = "bitaPU") 
	private EntityManager em;
	
	/***
	 * Acceso al entity manager para aquellas operaciones que no son cubiertas
	 * por el repositorio puedan ser realizadas por el programador.
	 * @return
	 */
	public EntityManager obtenerEntityManager() {
		return em;
	}
	

	/**
	 * Busqueda por id , es parte de la firma GenericoDAO
	 * @param id
	 * @return si no encuentra resultados retorna un nullo
	 */
	@Override
	public T obtenerPorId(PK id) {
		return em.find(this.type, id);
	}

	@Override
	public List<T> obtenerPaginado(int page, int size) {
		
		TypedQuery<T> resultado= em.createQuery(String.format("Select c from %s as c", type.getSimpleName()), this.type);
		int startPosition = 0;
		if(page > 0 & size > 0){
			startPosition =  ((page-1) * size);
			resultado.setFirstResult(startPosition);
			resultado.setMaxResults(size);
			
		}
		return resultado.getResultList();
	}
	
	/**
     * Retorna la cantidad de registros que tiene la tabla
     * se utiliza como apoyo del metodo obtener paginado para saber la cantidad
     * de registros.Es parte de la firma Generico DAO
     * @return
     */
	@Override
	public long obtenerTotalRegistrosPaginado() {
		Query queryTotal = em.createQuery(String.format("Select count(c.id) from %s c ", type.getSimpleName()));
		Integer countResult = (Integer)queryTotal.getSingleResult();
		return countResult;
	}
	
	/***
	 * Este es un metodo de apoyo para el desarrollador para ahorrar codigo
	 * Crea un typed query en base a los parametros de consulta y la query pasada
	 * @param query :cadena con la query de la consulta el objeto de la consulta debe ser igual al del tipo de la clase heradada
	 * @param params : parametros que aplican a la query
	 * @return en caso de no encontrar resultados retorna un null
	 */
	protected TypedQuery<T> armarTypedQueryConParametros(String query,Map<String, Object>params){
		TypedQuery<T> resultado= em.createQuery(query, this.type);
		if(params != null && params.size() > 0){
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String  key = it.next();
				Object value = params.get(key);
				resultado.setParameter(key, value);
			}
		}
		return resultado;
	}
	
	/***
	 * Este es un metodo de apoyo para el desarrollador para ahorrar codigo
	 * Busqueda para obtener un single result 
	 * @param query :cadena con la query de la consulta el objeto de la consulta debe ser igual al del tipo de la clase heradada
	 * @param params : parametros que aplican a la query
	 * @return en caso de no encontrar resultados retorna un null
	 */
	protected T obtenerSingleResult(String query,Map<String, Object> params){
		try {
			return armarTypedQueryConParametros(query, params).getSingleResult();
		} catch (NoResultException e) {
			return null; 
		}
	}
	
	/**
	 * Este es un metodo de apoyo para el desarrollador para ahorrar codigo
	 * Busqueda para obtener un single result , el cual contempla que el elemento 
	 * este duplicado lo cacha y lanza una excepcion de negocio con un mensaje personalizado
	 * @param query :cadena con la query de la consulta el objeto de la consulta debe ser igual al del tipo de la clase heradada
	 * @param params : parametros que aplican a la query
	 * @param mensajeMonstrar : mensaje a mostrar en caso de no encontrar resultados duplicados
	 * @return en caso de no encontrar resultados retorna un null
	 * @throws BussinesException //hay que moverla
	 */
	protected T obtenerSingleResultNonUnique(String query,Map<String, Object> params,String mensajeMonstrar){
		try {
			return armarTypedQueryConParametros(query, params).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}catch (NonUniqueResultException e) {
			// TODO: handle exception
			throw new BussinesException(mensajeMonstrar);
		}
	}

	
	/**
	 * Este es un metodo de apoyo para el desarrollador para ahorrar codigo
	 * Retorna una consulta pagina en base a los parametros
	 * si los parametros page y size  se consultan con los valores 0  trae todos los resultados
	 * @param query :cadena con la query de la consulta el objeto de la consulta debe ser igual al del tipo de la clase heradada
	 * @param params : parametros que aplican a la query
	 * @param page :pagina inicia a partir de 1 
	 * @param size : maximo de registros por consulta inicia a partir de 1
	 * @return lista con elemos o vacia 
	 */
	protected List<T> obtenerListaPaginado(String query,Map<String, Object>params,int page, int size){
		TypedQuery<T> typed = armarTypedQueryConParametros(query, params);
		int startPosition = 0;
		if(page > 0 & size > 0){ 
			startPosition =  ((page-1) * size);
			typed.setFirstResult(startPosition);
			typed.setMaxResults(size);
		}
		return typed.getResultList();
	}
	
	/**
	 * Este es un metodo de apoyo para el desarrollador para ahorrar codigo
	 * Retorna una consulta pagina en base a los parametros
	 * si los parametros page y size  se consultan con los valores 0 trae todos los resultados
	 * @param query :cadena con la query de la consulta el objeto de la consulta debe ser igual al del tipo de la clase heradada
	 * @param params : parametros que aplican a la query
	 * @param page :pagina inicia a partir de 1 
	 * @param size  : tamaño de la consulta inicia apartir de 1
	 * @return lista con elemos o vacia 
	 */
	public List<T> obtenerListaPaginadaPorCriteria(CriteriaQuery<T> cq,int page, int size){
		TypedQuery<T> typed = em.createQuery(cq);
		int startPosition = 0;
		if(page >0 & size >0){ 
			startPosition =  ((page-1) * size);
			typed.setFirstResult(startPosition);
			typed.setMaxResults(size);
		}
		return typed.getResultList();
	}
	
	/**
	 * Acceso al objeto Criteria builder para construir consultas con criteria
	 * @return
	 */
	public CriteriaBuilder obtenerCriteriaBuilder(){
		return em.getCriteriaBuilder();
	}


	@Override
	public void guardar(T entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}


	@Override
	public T obtenerReferencia(PK id) {
		return em.getReference(this.type, id);
	}


	
	
	
	
}
