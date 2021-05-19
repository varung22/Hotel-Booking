package com.hotel.myapp.dao;

import com.hotel.myapp.pojo.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends DAO {
	public User registerUser(User u) throws Exception{
		try {
			begin();
			getSession().save(u);
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			commit();
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
			e.printStackTrace();
			rollback();
		}finally {
			commit();
		}
		return null;
	}
}
