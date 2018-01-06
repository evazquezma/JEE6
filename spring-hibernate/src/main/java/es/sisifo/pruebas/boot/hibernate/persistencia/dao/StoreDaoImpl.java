package es.sisifo.pruebas.boot.hibernate.persistencia.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.sisifo.pruebas.boot.hibernate.persistencia.entity.StoreEntity;

@Repository("storeDao")
public class StoreDaoImpl implements StoreDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}


	@Override
	public StoreEntity find(final Long id) {
		return getCurrentSession().get(StoreEntity.class, id);
	}

	@Override
	public StoreEntity save(final StoreEntity storeEntity) {
		return (StoreEntity) getCurrentSession().merge(storeEntity);
	}


}
