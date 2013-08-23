package com.project.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface DAOComun {

	// Guardar un objetos
		public <T> boolean guardarObjeto(T obj) throws DataAccessException;
	// Obtener objeto por ID	
		public <T> T obtenerObjetoId(Class<T> clase, Long id);
		
		public <T> T obtenerObjetoId(Class<T> clase, Long id, String lstLista);
		public List<String> getCustomerLevels();
}
