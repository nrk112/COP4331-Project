/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.AccountManager;
import junit.framework.Assert;
import model.Buyer;
import model.Seller;
import model.UserFactory;
import model.UserModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lenore
 */
public class AccountManagerTest {
    
    public AccountManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        AccountManager.getInstance();
    }
    
    @After
    public void tearDown() {
    }

    
    // Test of authorizeUserPass method, of class AccountManager.
    // the user is authorized
    //
     @Test
     public void authorizeUserPass() {
          boolean isAuthorised = AccountManager.getInstance().authorizeUser("lmontalbano", "lenore");
          Assert.assertEquals(true, isAuthorised);
     }
     
    // Test of authorizeUserPass method, of class AccountManager.
    // the user is not authorized
    //
     @Test
     public void authorizeUserFail() {
     
          boolean isAuthorised = AccountManager.getInstance().authorizeUser("lmontalbano1", "lenore");
          Assert.assertEquals(false, isAuthorised);
     }
     
    // Test of authorizeUserPass method, of class AccountManager.
    // the user is not authorized
    //
     @Test
     public void createUserFactoryReturnSellerPass() {
     
        UserModel user = UserFactory.CreateUser(1,"Lenore Montalbano","lmontalbano","lenore","2043 Bonisle Circle","Riviera Beach","FL","33418",true);
        if(user instanceof Seller)
        {
            Assert.assertTrue(true);
        }
        else
        {
            
            Assert.assertTrue(false);
        }
     }
     // Test of authorizeUserPass method, of class AccountManager.
    // the user is not authorized
    //
     @Test
     public void createUserFactoryReturnSellerFail() {
     
        UserModel user = UserFactory.CreateUser(1,"Lenore Montalbano","lmontalbano","lenore","2043 Bonisle Circle","Riviera Beach","FL","33418",true);
        if(user instanceof Buyer)
        {
            Assert.assertTrue(true);
        }
        else
        {
            
            Assert.assertTrue(false);
        }
     }
    // Test of authorizeUserPass method, of class AccountManager.
    // the user is not authorized
    //
     @Test
     public void createUserFactoryReturnUser() {
     
        UserModel user = UserFactory.CreateUser(3,"Paco Pico","PacoPico","paco","1701 US HWY 1","North Palm Beach","FL","33401",false);
        if(user instanceof Buyer)
        {
            Assert.assertTrue(true);
        }
        else
        {
            
            Assert.assertTrue(false);
        }
     }
     
     
     @Test
     public void createUserFactoryReturnUserFail() {
     
        UserModel user = UserFactory.CreateUser(3,"Paco Pico","PacoPico","paco","1701 US HWY 1","North Palm Beach","FL","33401",false);
        if(user instanceof Seller)
        {
            Assert.assertTrue(true);
        }
        else
        {
            
            Assert.assertTrue(false);
        }
     }
}
