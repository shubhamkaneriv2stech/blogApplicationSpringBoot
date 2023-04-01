package com.bloging.application.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.bloging.application.dao.CategoriesDao;
import com.bloging.application.entity.Categories;
import com.bloging.application.entity.Categories_;
import com.bloging.application.entity.Comments;
import com.bloging.application.entity.Comments_;
import com.bloging.application.entity.Post;
import com.bloging.application.entity.Post_;
import com.bloging.application.util.FilterCriteria;

@Repository
public class CategoryDaoImpl implements CategoriesDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean ischeckedTitleIsUnique(String name, Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<Categories> root = query.from(Categories.class);
		List<Predicate> conditions = new ArrayList<>();
		if (id != null) {
			conditions.add(cb.notEqual(root.get(Categories_.id), id));
		}
		if (StringUtils.isNotBlank(name)) {
			String pattern = name.toLowerCase();
			Expression<String> trainingCategory = root.get(Categories_.title);
			conditions.add(cb.like(cb.lower(trainingCategory), pattern));
		}
		query.select(cb.count(root.get(Categories_.id)));
		query.where(cb.and(conditions.toArray(new Predicate[conditions.size()])));
		try {
			return em.createQuery(query).getSingleResult() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Post> fetchAllPostByCriteria(FilterCriteria filterCriteria) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> query = cb.createQuery(Post.class);
		Root<Post> root = query.from(Post.class);
		query.select(root);
		query.orderBy(cb.asc(root.get(Post_.addedDate)));
		try {
			if (filterCriteria != null) {
				return em.createQuery(query).setFirstResult(filterCriteria.getOffset())
						.setMaxResults(filterCriteria.getLimit()).getResultList();
			} else {
				return em.createQuery(query).getResultList();
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Comments> fetchCommentsByID(Integer postId) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Comments> query = cb.createQuery(Comments.class);
		Root<Comments> root = query.from(Comments.class);
		List<Predicate> conditions = new ArrayList<>();

		conditions.add(cb.equal(root.get(Comments_.POSTS), postId));
		query.where(cb.and(conditions.toArray(new Predicate[conditions.size()])));
		query.select(root);
		query.orderBy(cb.desc(root.get(Comments_.addedDate)));
		try {
			return em.createQuery(query).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Integer deleteUserFromRolesMapping(Integer id) {
		StringBuilder queryStr = prepareNativeForTaskDetailsQueryString(id);
		Query query = em.createNativeQuery(queryStr.toString());
		if (null != id) {
			query.setParameter("id", id);
		}
		return query.executeUpdate();
	}

	private StringBuilder prepareNativeForTaskDetailsQueryString(Integer id) {
		StringBuilder qstr = new StringBuilder();
		qstr.append(" DELETE FROM USER_ROLE ");
		if (null != id) {
			qstr.append("WHERE users = :id");
		}
		return qstr;
	}

}
