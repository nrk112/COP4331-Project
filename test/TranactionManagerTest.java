/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.TransactionManager;
import java.util.ArrayList;
import model.TransactionLineItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lenore
 */
public class TranactionManagerTest {
    
    public TranactionManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        TransactionManager.getInstance();
    }
    
    @After
    public void tearDown() {
    }

     // Test of getTransactionLineItemList method, of class TransactionManager.
    // verify that if there are objects in list ot returns them
    //
     @Test
     public void getTransactionLineItemList() {
      ArrayList actuals = TransactionManager.getInstance().getTransactionLineItemList();
        boolean hasValues = (actuals.size()>0);
        Assert.assertEquals(true, hasValues);
     }
     
     // Test of getTransactionLineItemList method, of class TransactionManager.
     // Verify the object returned is the appropriate object
     @Test
     public void getTransactionLineItemListIsTransactionLineItem() 
     {   
        ArrayList actuals = TransactionManager.getInstance().getTransactionLineItemList();
        Object[] lineItems = actuals.toArray();
        if(lineItems[0] instanceof TransactionLineItem)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
     }
}
