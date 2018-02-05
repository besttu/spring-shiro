package com.shiro.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.shiro.pojo.we.Movie;
import com.shiro.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Movie> getListByLike(String title, String name) {
		List<Movie> listslists = (List<Movie>) jdbcTemplate.query(
				"select * from tt where m_title like '%" + title + "%' or m_name like '%" + name + "%' limit 5",
				new ResultSetExtractor<List<Movie>>() {
					@Override
					public List<Movie> extractData(ResultSet rs) throws SQLException, DataAccessException {
						// TODO Auto-generated method stub
						List<Movie> lists = new ArrayList<Movie>();
						while (rs.next()) {
							Movie m = new Movie();
							m.setId(rs.getString("m_id"));
							m.setName(rs.getString("m_name"));
							m.setTitle(rs.getString("m_title"));
							m.setCategory(rs.getString("m_category"));
							m.setType(rs.getString("type"));
							m.setRegion(rs.getString("region"));
							m.setDate(rs.getString("m_data"));
							m.setUrl(rs.getString("m_url"));
							m.setImg(rs.getString("m_img"));
							m.setOther(rs.getString("m_other"));
							lists.add(m);
						}
						return lists;
					}
				});
		return listslists;
	}
}
