package com.xsbweb.vo.extend;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 验证用户名和密码的
 * @author Administrator
 *
 */
public class ThisAuthenticator extends Authenticator{  
    String userName=null;  
    String password=null;  
       
    public ThisAuthenticator(){  
    }  
    public ThisAuthenticator(String username, String password) {   
        this.userName = username;   
        this.password = password;   
    }   
    protected PasswordAuthentication getPasswordAuthentication(){  
        return new PasswordAuthentication(userName, password);  
    }
}