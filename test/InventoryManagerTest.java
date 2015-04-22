/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cop4331.controller.InventoryManager;
import java.util.ArrayList;
import cop4331.model.DiscountedProduct;
import cop4331.model.Product;
import cop4331.model.ProductFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Lenore
 */
public class InventoryManagerTest {
    
    public InventoryManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        InventoryManager.getInstance();
    }
    
    @After
    public void tearDown() {
    }
    // Test of getProductList method, of class InventoryManager.
    // Verify that items are returned by call
    //
     @Test
     public void getProductListCount() 
     {        
        ArrayList actuals = InventoryManager.getInstance().getProductList();
        boolean hasValues = (actuals.size()>0);
        Assert.assertEquals(true, hasValues);
     }
    
     // Test of getProductList method, of class InventoryManager.
    // Verify that items are returned by call
    //
     @Test
     public void getProductListIsProduct() 
     {        
        ArrayList actuals = InventoryManager.getInstance().getProductList();
        Object[] products = actuals.toArray();
        if(products[0] instanceof Product)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
     }
    // Test of CreateProduct method, of class ProductFactory.
    // Verify that items are returned by call
    //
     @Test
     public void ProductFactoryPass() 
     {     
        Product expected = ProductFactory.CreateProduct(2, 1, "Blue Coffee Mug", "Blue glazed, raku fired", 0.89, 4.99, 250, 0.0, "database\\images\\blue-coffee-cup-raku.jpg");
        
        Product actual = InventoryManager.getInstance().getProductByID(2);
        Assert.assertEquals(expected, actual);
        
     }
    // Test of CreateProduct method, of class ProductFactory.
    // Verify that items are returned by call
    //
     @Test
     public void ProductFactoryFail() 
     {     
        Product expected = ProductFactory.CreateProduct(2, 1, "Blue Coffee Mug", "Blue glazed, raku fired", 0.89, 4.99, 250, 0.0, "database\\images\\blue-coffee-cup-raku.jpg");
        
        Product actual = InventoryManager.getInstance().getProductByID(3);
        Assert.assertEquals(expected, actual);
        
     }
      // Test of CreateProduct method, of class ProductFactory.
    // Verify that item is a discountedproduct
    //
     @Test
     public void ProductFactoryDiscountedPass() 
     {     
        Product expected = ProductFactory.CreateProduct(3, 1, "Black Coffee Cup", "Black glazed, raku finish (Marked down by 5.0%)", 0.99, 4.99, 999, 5.0, "database\\images\\black-coffee-cup-raku.jpg");
        
         if(expected instanceof DiscountedProduct)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
        
     }
     
      // Test of CreateProduct method, of class ProductFactory.
    // Verify that item is a discountedproduct
    //
     @Test
     public void ProductFactoryDiscountedFail() 
     {     
        Product expected = ProductFactory.CreateProduct(2, 1, "Blue Coffee Mug", "Blue glazed, raku fired", 0.89, 4.99, 250, 0.0, "database\\images\\blue-coffee-cup-raku.jpg");
        
         if(expected instanceof DiscountedProduct)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
        
     }
}
