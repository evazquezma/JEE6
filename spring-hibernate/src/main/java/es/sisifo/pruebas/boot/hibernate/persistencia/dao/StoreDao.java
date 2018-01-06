package es.sisifo.pruebas.boot.hibernate.persistencia.dao;

import org.hibernate.Session;

import es.sisifo.pruebas.boot.hibernate.persistencia.entity.StoreEntity;

public interface StoreDao {

	Session getCurrentSession();

	StoreEntity find(Long id);

	StoreEntity save(StoreEntity storeEntity);

}
