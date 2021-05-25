package com.hotel.myapp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The class models the generic Token for Hotel. The annotations included allows to store the object as an pojo in the DB.
 * @author Vidhusha
 *
 */
@Entity  
@Table(name="Token") 
public class Token {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false)
	private long id;

    @Column(name="user_id" , unique=true)  
    private long userID; 
    
    @Column(name="authenticationToken")  
    private String authenticationToken;  
      
    @Column(name="secretKey")  
    private String secretKey;  
      
    @Column(name="email_id")  
    private String emailId;
    
    public Token() { }  
    
    /**
     * @param id
     * @param userID
     * @param authenticationToken
     * @param secretKey
     * @param emailId
     */
    public Token(long id, long userID, String authenticationToken, String secretKey, String emailId) {  
        super();  
        this.id = id;  
        this.userID = userID;  
        this.authenticationToken = authenticationToken;  
        this.secretKey = secretKey;  
        this.emailId = emailId;  
    }  
  
    /**
     * @return Token Id
     */
    public long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return User Id
	 */
	public long getUserID() {  
        return userID;  
    }  
  
    /**
     * @param userID
     */
    public void setUserID(long userID) {  
        this.userID = userID;  
    }  
  
    /**
     * @return Authenication token
     */
    public String getAuthenticationToken() {  
        return authenticationToken;  
    }  
  
    /**
     * @param authenticationToken
     */
    public void setAuthenticationToken(String authenticationToken) {  
        this.authenticationToken = authenticationToken;  
    }  
  
    /**
     * @return Secret Key
     */
    public String getSecretKey() {  
        return secretKey;  
    }  
  
    /**
     * @param secretKey
     */
    public void setSecretKey(String secretKey) {  
        this.secretKey = secretKey;  
    }  
  
    /**
     * @return Email
     */
    public String getEmailId() {  
        return emailId;  
    }  
  
    /**
     * @param emailId
     */
    public void setEmailId(String emailId) {  
        this.emailId = emailId;  
    }  
  
    /**
     *This method generates toString
     */
    @Override  
    public String toString() {  
        return "Token [tokenID=" + id + ", userID=" + userID + ", authenticationToken=" + authenticationToken  
                + ", secretKey=" + secretKey + ", emailId=" + emailId + "]";  
    }
}