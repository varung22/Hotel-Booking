package com.hotel.myapp.dao;

import org.hibernate.query.Query;

import com.hotel.myapp.pojo.User;

public class UserDao extends DAO {
	public User registerUser(User u) throws Exception{
		try {
			begin();
			getSession().save(u);
			commit();
			return u;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return u;
	}
	
	public User loginUser(String userEmail, String password, String type) {
		try {
			begin();
			Query query = getSession().createQuery("from User where userEmail = ? and password = ? and type = ?");
			query.setParameter(0, userEmail);
			query.setParameter(1, password);
			query.setParameter(2, type);
			User user = (User) query.uniqueResult();
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			rollback();
		}
		return null;
	}
}
