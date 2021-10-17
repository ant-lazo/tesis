package com.bolsadeideas.springboot.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.dao.IRolDao;
import com.bolsadeideas.springboot.app.models.entity.Role;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRolDao roleDao;
	
	public void saveRole(Role authority) {
		roleDao.save(authority);
	}

}
