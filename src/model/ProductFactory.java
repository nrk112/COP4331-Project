/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lenore
 */
public class ProductFactory {
     public static Product CreateProduct(int productID, int sellerID, String name, String description, double cost, double price, int quantity, int discountedBy) {
        
         Product product = new StandardProduct(
                 productID,
                 sellerID,
                 name,
                 description,
                 cost,
                 price,
                 quantity
         );
         if (discountedBy>0) 
         {
            Product discountedProduct = new DiscountedProduct(
                    product,
		    discountedBy
            );
            return discountedProduct;
         }
            return product;
       
        }
    }
    
