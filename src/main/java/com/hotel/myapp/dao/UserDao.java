package com.hotel.myapp.dao;

import com.hotel.myapp.pojo.User;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *    This interface models a Data Access Object for User pojo

 * @author Priyanka
 *
 */
@Repository
@Transactional
public class UserDao {
	@Autowired  
    private SessionFactory sessionFactory;
	
	/**
	 * @param u
	 * @return Registered User
	 * This Method is Used to register User
	 * It is used to display the register page for the user after that it returns current user
	 * @throws Exception
	 */
	public User registerUser(User u) throws Exception{
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			session.save(u);
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}
		return u;
	}
	
	/**
	 * @param email
	 * @param password
	 * @param type
	 * @return Login Details
	 * This Method is used to return Login details by the User
	 * It is used to get the user details from the user Email given password and Type from the user class
	 */
	public User loginUser(String email, String password, String type) {
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			Query<User> query = session.createQuery("from User where userEmail = ? and password = ? and type = ?", User.class);
			query.setParameter(0, email);
			query.setParameter(1, password);
			query.setParameter(2, type);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}
		return null;
	}
	
	/**
	 * @param username
	 * @param password
	 * @param type
	 * @return Login Username and password
	 * It is used to get the user details from the user Email given password and Type from the user class
	 */
	public User loginUserByUsername(String username, String password, String type) {
		Session session = null; 
		
		try {
			session = sessionFactory.getCurrentSession();
			Query<User> query = session.createQuery("from User where username = ? and password = ? and type = ?", User.class);
			query.setParameter(0, username);
			query.setParameter(1, password);
			query.setParameter(2, type);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.flush(); 
		}
		return null;
	}
}