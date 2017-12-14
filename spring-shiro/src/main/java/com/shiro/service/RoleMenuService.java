package com.shiro.service;

import java.util.Set;

public interface RoleMenuService {

	Set<String> findMenusByUid(String userId);

}
