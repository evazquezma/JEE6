package es.sisifo.pruebas.boot.hibernate.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.sisifo.pruebas.boot.hibernate.api.facade.StoreFacade;
import es.sisifo.pruebas.boot.hibernate.api.model.Store;
import es.sisifo.pruebas.boot.hibernate.api.service.StoreService;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreFacade storeFacade;

	@Override
	@Transactional
	public Store saveStore(final Store store) {
		return storeFacade.saveStore(store);

	}

	@Override
	@Transactional(readOnly=true)
	public Store getStore(final Long id) {
		return storeFacade.getStore(id);
	}


}
