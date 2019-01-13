package es.pruebas.sisifo.spring4.persistencia.dao;

import java.util.List;

import es.pruebas.sisifo.spring4.persistencia.entity.UserEntity;

public interface UserDao {

	List<UserEntity> getUsers();

}
