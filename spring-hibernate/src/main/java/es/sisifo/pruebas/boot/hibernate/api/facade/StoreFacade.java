package es.sisifo.pruebas.boot.hibernate.api.facade;

import es.sisifo.pruebas.boot.hibernate.api.model.Store;

public interface StoreFacade {

	Store saveStore(Store store);

	Store getStore(Long id);

}
