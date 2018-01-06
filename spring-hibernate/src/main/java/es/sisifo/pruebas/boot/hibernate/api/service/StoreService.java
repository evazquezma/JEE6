package es.sisifo.pruebas.boot.hibernate.api.service;

import es.sisifo.pruebas.boot.hibernate.api.model.Store;

public interface StoreService {
	Store saveStore(Store store);

	Store getStore(Long id);
}
