package es.pruebas.sisifo.spring4.persistencia.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.pruebas.sisifo.spring4.persistencia.entity.UserEntity;

@Repository
public class UserDaoImpl implements UserDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> getUsers() {
		LOGGER.info("Recuperando usuarios");
		return sessionFactory.getCurrentSession().createCriteria(UserEntity.class).list();
	}
}
