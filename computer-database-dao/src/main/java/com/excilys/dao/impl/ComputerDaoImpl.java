package com.excilys.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.ComputerDao;
import com.excilys.om.Computer;

@Repository("computerDaoImpl")
public class ComputerDaoImpl implements ComputerDao {

	private static final Logger logger = LoggerFactory
			.getLogger("ComputerDaoImpl");

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public Computer findComputerById(int computer_id) {
		return hibernateTemplate.get(Computer.class, computer_id);
	}

	@Override
	public void delete(Computer computer) {
		logger.info("dao.delete.computer :" + computer);
		hibernateTemplate.delete(computer);
	}

	@Override
	public void update(Computer computer) {
		logger.info("dao.update.computer:" + computer);
		hibernateTemplate.update(computer);
	}

	@Override
	public void insert(Computer computer) {
		logger.info("dao.insert.computer:" + computer);
		hibernateTemplate.save(computer);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Computer> findOrderByComputers(final int p, String req,
			String orderBy, final String search) {
		logger.info(new StringBuilder("dao.find.computers:[by:")
				.append(orderBy).append(";order:").append(req)
				.append(";search:").append(search).append("]").toString());
		final String sql = findStringRequest(p, req, orderBy, search);
		List<Computer> computers = hibernateTemplate
				.executeFind(new HibernateCallback<Object>() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query q = session.createQuery(sql);
						if (search != null && !"".equals(search)) {
							q.setString(0, PERCENT + search + PERCENT);
						}
						q.setFirstResult(p * LIMIT);
						q.setMaxResults(LIMIT);
						return q.list();
					}

				});
		return computers;
	}

	@Override
	@Transactional(readOnly = true)
	public int getCurrentCount(int p, String req, String orderBy, String search) {
		int value = 0;
		search = (search == null || search.trim() == "") ? "%" : "%" + search
				+ "%";
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(Computer.class);
			criteria.setProjection(Projections.rowCount()).add(
					Restrictions.like("name", search));
			List<?> results = hibernateTemplate.findByCriteria(criteria);
			value = Integer.parseInt(results.get(0).toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return value;
	}

	private String findStringRequest(int p, String req, String orderBy,
			String search) {
		StringBuilder sb = new StringBuilder();
		sb.append("from Computer c left outer join fetch c.company");
		if (search != null && !"".equals(search)) {
			sb.append(" where ");
			sb.append(orderBy);
			sb.append(" like ?");
		}
		sb.append(" order by isnull(");
		sb.append(orderBy).append("), ");
		sb.append(orderBy).append(" ");
		sb.append(req);
		return sb.toString();
	}

	@Autowired
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

}
