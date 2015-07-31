/*******************************************************************************
 * Copyright (c) 2014, 2015 mtons.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package mblog.persist.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mblog.data.Group;
import mblog.persist.dao.GroupDao;
import mblog.persist.entity.GroupPO;
import mblog.persist.service.GroupService;
import mblog.persist.utils.BeanMapUtils;

/**
 * @author langhsu
 *
 */
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupDao groupDao;

	@Override
	@Transactional(readOnly = true)
	public List<Group> findAll() {
		List<GroupPO> list = groupDao.list();
		List<Group> rets = new ArrayList<>();

		list.forEach(po -> rets.add(BeanMapUtils.copy(po)));
		return rets;
	}

	@Override
	@Transactional(readOnly = true)
	public Group getById(int id) {
		return BeanMapUtils.copy(groupDao.get(id));
	}

	@Override
	@Transactional(readOnly = true)
	public Group getByKey(String key) {
		return BeanMapUtils.copy(groupDao.getByKey(key));
	}
	
}