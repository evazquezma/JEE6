package es.pruebas.sisifo.spring4.negocio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pruebas.sisifo.spring4.api.service.TestService;
import es.pruebas.sisifo.spring4.persistencia.dao.UserDao;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly=true)
	public String sayHello(final String name) {
		userDao.getUsers();
		return "Hello " + name;
	}

}
