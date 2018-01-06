package es.sisifo.pruebas.boot.hibernate.persistencia.facade;

import java.sql.Blob;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.engine.jdbc.LobCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sisifo.pruebas.boot.hibernate.api.facade.StoreFacade;
import es.sisifo.pruebas.boot.hibernate.api.model.Store;
import es.sisifo.pruebas.boot.hibernate.persistencia.dao.StoreDao;
import es.sisifo.pruebas.boot.hibernate.persistencia.entity.StoreEntity;

@Service("storeFacade")
public class StoreFacadeImpl implements StoreFacade {

	@Autowired
	private StoreDao storeDao;

	@Override
	public Store saveStore(final Store store) {
		StoreEntity entity = negocio2Entity(store);
		entity= storeDao.save(entity);

		store.setId(entity.getId());
		return store;
	}

	private StoreEntity negocio2Entity(final Store store) {
		final LobCreator lobCreator = Hibernate.getLobCreator(storeDao.getCurrentSession());
		final Blob blob = lobCreator.createBlob(store.getContenStream(), store.getContentSize());

		final StoreEntity entity = new StoreEntity();
		entity.setId(store.getId());
		entity.setName(store.getName());
		entity.setFileContent(blob);
		entity.setFileSize(store.getContentSize());
		return entity;
	}



	@Override
	public Store getStore(final Long id) {
		final StoreEntity entity = storeDao.find(id);
		return entity2Negocio(entity);
	}

	private Store entity2Negocio(final StoreEntity entity) {
		if (entity == null) {
			return null;
		}

		try {
			final Store store = new Store();
			store.setId(entity.getId());
			store.setName(entity.getName());
			store.setContenStream(entity.getFileContent().getBinaryStream());
			store.setContentSize(entity.getFileSize());
			return store;
		} catch (final SQLException e) {
			throw new RuntimeException("Error with blob", e);
		}
	}
}
