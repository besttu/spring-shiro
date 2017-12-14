package com.shiro.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.shiro.entity.SysUserRole;

public interface UserRoleService {

	Set<String> findRolesByUid(String id);

}
