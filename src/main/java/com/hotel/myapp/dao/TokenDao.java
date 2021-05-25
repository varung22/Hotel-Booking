package com.hotel.myapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.myapp.pojo.Token;

/**
 *   This interface models a Data Access Object for Token pojo

 * @author Priyanka
 *
 */
@Repository
@Transactional
public class TokenDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @param email
	 * @param adminId
	 * @param authenticationToken
	 * @param secretKey
	 * This Method is used to save user details like Email, Id and Token of the User
	 */
	public void saveUserEmail(String email, long adminId, String authenticationToken, String secretKey) {
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();

			Token t = new Token();
			t.setUserID(adminId);
			t.setEmailId(email);
			t.setAuthenticationToken(authenticationToken);
			t.setSecretKey(secretKey);
			session.save(t);
		} catch (Exception exception) {
			System.out.println("Exception in saving UserEmail In Token Table :: " + exception.getMessage());
		} finally {
			session.flush();
		}

	}

	/**
	 * @param email
	 * @param authenticationToken
	 * @param secretKey
	 * @return Updated tokens
	 * This Method is used to update Token
	 * This Method is used to save user details like Email, Id and Token of the User
	 */
	public boolean updateToken(String email, String authenticationToken, String secretKey) {
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();

			int result = session.createQuery(
					"Update Token set authenticationToken = :authenticationToken , secretKey = :secretKey where emailId =:userEmail ")
					.setParameter("authenticationToken", authenticationToken).setParameter("userEmail", email)
					.setParameter("secretKey", secretKey).executeUpdate();

			if (result == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			System.out.println("Error while updating token :: " + exception.getMessage());
			return false;
		} finally {
			session.flush();
		}
	}

	/**
	 * @param email
	 * @return User Email from given token
	 * This method is used to get details from the token of the users Email from the token class
	 */
	public long getTokenDetail(String email) {
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			Query<Token> query = session.createQuery("from Token where emailId =:userEmail", Token.class);
			query.setParameter("userEmail", email);

			List<Token> tokenDetails = query.list();

			if (tokenDetails.size() > 0) {
				return tokenDetails.get(0).getId();
			} else {
				return 0l;
			}

		} catch (Exception exception) {
			System.out.println("Exception while getting token ID :: " + exception.getMessage());
		} finally {
			session.flush();
		}

		return 0;
	}

	/**
	 * @param token
	 * @param emailId
	 * @return User Id 
	 * This Method is used to Authenticate User Id and Token
	 * It is used to return Token details and authentication of the token by the user Id from token Class
	 */
	public long tokenAuthentication(String token, int emailId) {
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();

			Query<Token> query = session
					.createQuery("from Token where userID =:userID and authenticationToken = :token", Token.class);
			query.setParameter("userID", emailId);
			query.setParameter("token", token);
			List<Token> tokenDetails = query.list();

			if (!tokenDetails.isEmpty()) {
				return tokenDetails.get(0).getId();
			} else {
				return 0;
			}

		} catch (Exception exception) {
			System.out.println("Exception while Authenticating token :: " + exception);
			return 0;
		} finally {
			session.flush();
		}

	}

}