package com.hotel.myapp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.myapp.dao.TokenDao;

/**
 * This Service part of Myapp is responsible for implementing the Business Logic
 * @author Pradeep
 *
 */
@Service
public class TokenService {  
  
    @Autowired  
    private TokenDao tokenDAO;  
      
    /**
     * @param email
     * @param adminId
     * @param authenticationToken
     * @param secretKey
     * This method is used to save User Email and Id from Dao
     */
    @Transactional  
    public void saveUserEmail(String email, long adminId, String authenticationToken, String secretKey) {  
        tokenDAO.saveUserEmail(email, adminId, authenticationToken, secretKey);
    }  

    /**
     * @param email
     * @param authenticationToken
     * @param secretKey
     * @return Updated details of the User from Dao
     */
    @Transactional 
    public boolean updateToken(String email, String authenticationToken, String secretKey) {  
        return tokenDAO.updateToken(email, authenticationToken, secretKey);  
    }  
  
    /**
     * @param email
     * @return Token Details from Dao
     */
    @Transactional  
    public long getTokenDetail(String email) {  
        return tokenDAO.getTokenDetail(email);  
    }  
  
    /**
     * @param token
     * @param emailId
     * @return Authentication of the User from Dao
     */
    @Transactional  
    public long tokenAuthentication(String token, int emailId) {  
        return tokenDAO.tokenAuthentication(token, emailId);  
    }  
  
}  