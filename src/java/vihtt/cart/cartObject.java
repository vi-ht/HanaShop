/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Thanh Vi
 */
public class cartObject implements Serializable{

         Map<String, Integer> items;

         public Map<String, Integer> getItems() {
                  return items;
         }

         public void addItemToCard(String ID, int quantity) {
                  if (this.items == null) {
                           this.items = new HashMap<>();
                  }
                  if (this.items.containsKey(ID)) {
                           int totalQuantity = this.items.get(ID) + quantity;
                           this.items.replace(ID, totalQuantity);
                  } else {
                           this.items.put(ID, quantity);
                  }
         }
         
         public void addQuantityToCard(String ID, int quantity) {
                  if (this.items == null) {
                           this.items = new HashMap<>();
                  }
                  if (this.items.containsKey(ID)) {
                           this.items.replace(ID, quantity);
                  } 
         }
         
         public void subtractQuantityToCard(String ID, int quantity) {
                  if (this.items == null) {
                           this.items = new HashMap<>();
                  }
                  if (this.items.containsKey(ID)) {
                           this.items.replace(ID, quantity);
                  }
         }

         public void removeItemFormCard(String ID) {
                  if (this.items == null) {
                           return;
                  }
                  if (this.items.containsKey(ID)) {
                           this.items.remove(ID);
                           if (this.items.isEmpty()) {
                                    this.items = null;
                           }
                  }
         }
}
