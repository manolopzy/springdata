package com.worldexplorer.springboot.webapp.dataaccess.repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.worldexplorer.springboot.webapp.dataaccess.domain.Equip;

@Repository
public class EquipRepository implements CommonRepository<Equip> {

	private static final String SQL_INSERT = "insert into equip (id, description, created, modified, completed) values (:id,:description,:created,:modified,:completed)";
	private static final String SQL_QUERY_FIND_ALL = "select id, description, created, modified, completed from equip";
	private static final String SQL_QUERY_FIND_BY_ID = SQL_QUERY_FIND_ALL + " where id = :id";
	private static final String SQL_UPDATE = "update equip set description = :description, modified = :modified, completed = :completed where id = :id";
	private static final String SQL_DELETE = "delete from equip where id = :id";
	/**
	 * NamedParameterJdbcTemplate (a JdbcTemplate wrapper) helps 
	 * with all the named parameters, which means that instead 
	 * of using ? in your SQL statements, you can use names like :id.
	 */
	private final NamedParameterJdbcTemplate jdbcTemplate;

	//@Autowired
	public EquipRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private RowMapper<Equip> rowMapper = (ResultSet rs, int rowNum) -> {
		Equip equip = new Equip();
		equip.setId(rs.getString("id"));
		equip.setDescription(rs.getString("description"));
		equip.setModified(rs.getTimestamp("modified").toLocalDateTime());
		equip.setCreated(rs.getTimestamp("created").toLocalDateTime());
		equip.setCompleted(rs.getBoolean("completed"));
		return equip;
	};

	@Override
	public Equip save(final Equip domain) {
		Equip result = findById(domain.getId());
		if (result != null) {
			result.setDescription(domain.getDescription());
			result.setCompleted(domain.isCompleted());
			result.setModified(LocalDateTime.now());
			return upsert(result, SQL_UPDATE);
		}
		return upsert(domain, SQL_INSERT);
	}

	private Equip upsert(final Equip equip, final String sql) {
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("id", equip.getId());
		namedParameters.put("description", equip.getDescription());
		namedParameters.put("created", java.sql.Timestamp.valueOf(equip.getCreated()));
		namedParameters.put("modified", java.sql.Timestamp.valueOf(equip.getModified()));
		namedParameters.put("completed", equip.isCompleted());
		this.jdbcTemplate.update(sql, namedParameters);
		return findById(equip.getId());
	}

	@Override
	public Iterable<Equip> save(Collection<Equip> domains) {
		// for each entity in the entities collection, execute
		// the "save" method of this class
		domains.forEach(this::save);
		return findAll();
	}

	@Override
	public void delete(Equip domain) {
		Map<String, String> namedParameters = Collections.singletonMap("id", domain.getId());
		this.jdbcTemplate.update(SQL_DELETE, namedParameters);
	}

	@Override
	public Equip findById(String id) {
		try {
			Map<String, String> namedParameters = Collections.singletonMap("id", id);
			return this.jdbcTemplate.queryForObject(SQL_QUERY_FIND_BY_ID, namedParameters, rowMapper);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public Iterable<Equip> findAll() {
		return this.jdbcTemplate.query(SQL_QUERY_FIND_ALL, rowMapper);
	}
}
