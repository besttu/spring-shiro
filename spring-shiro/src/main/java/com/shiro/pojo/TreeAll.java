package com.shiro.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取所有menu的pojo
 * 
 * @author admin
 *
 */
public class TreeAll<T> {
	private T tree;
	private List<TreeAll> children = new ArrayList<TreeAll>();

	public T getTree() {
		return tree;
	}

	public void setTree(T tree) {
		this.tree = tree;
	}

	public List<TreeAll> getChildren() {
		return children;
	}

	public void setChildren(List<TreeAll> children) {
		this.children = children;
	}

}
