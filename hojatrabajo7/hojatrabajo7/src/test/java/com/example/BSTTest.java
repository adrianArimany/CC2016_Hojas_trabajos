package com.example;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import com.example.objecthomeappliance.HomeApplianceRecord;
import com.example.searchstructure.BST;
import com.example.searchstructure.ITraversal;
import com.example.searchstructure.Ibst;

public class BSTTest {
    
private HomeApplianceRecord createRecord(String sku, float priceCurrent, float priceRetail, String product, String category) {
    return new HomeApplianceRecord(sku, priceRetail, priceCurrent, product, category);
}

@Test
void testInsertionAndSearch() {
    Ibst<String, HomeApplianceRecord> bst = new BST<>();
    
    HomeApplianceRecord r1 = createRecord("A001", 75.0f, 90.0f, "Toaster", "Appliances");
    HomeApplianceRecord r2 = createRecord("A002", 80.0f, 100.0f, "Blender", "Kitchen");
    HomeApplianceRecord r3 = createRecord("A003", 500.0f, 600.0f, "TV", "Electronics");
    
    bst.insert(r1.getSKU(), r1);
    bst.insert(r2.getSKU(), r2);
    bst.insert(r3.getSKU(), r3);
    
    // Search for existing SKU.
    HomeApplianceRecord found = bst.search("A002");
    assertNotNull(found, "Record A002 should be found");
    assertEquals("Blender", found.getProductName(), "Product name should be Blender");
    
    // Search for non-existent SKU.
    assertNull(bst.search("NONEXISTENT"), "Non-existent key should return null");
}

@Test
void testRemove() {
    Ibst<String, HomeApplianceRecord> bst = new BST<>();
    
    HomeApplianceRecord r1 = createRecord("A001", 75.0f, 90.0f, "Toaster", "Appliances");
    HomeApplianceRecord r2 = createRecord("A002", 80.0f, 100.0f, "Blender", "Kitchen");
    HomeApplianceRecord r3 = createRecord("A003", 500.0f, 600.0f, "TV", "Electronics");
    
    bst.insert(r1.getSKU(), r1);
    bst.insert(r2.getSKU(), r2);
    bst.insert(r3.getSKU(), r3);
    
    // Remove a record.
    HomeApplianceRecord removed = bst.remove("A002");
    assertNotNull(removed, "Record A002 should be removed");
    assertEquals("Blender", removed.getProductName(), "Removed record should be Blender");
    
    // Verify removal.
    assertNull(bst.search("A002"), "After removal, A002 should not be found");
}

@Test
void testInOrderTraversal() {
    Ibst<String, HomeApplianceRecord> bst = new BST<>();
    List<String> keysInOrder = new ArrayList<>();
    
    // Create records with keys that will be in order when traversed.
    HomeApplianceRecord r1 = createRecord("A003", 500.0f, 600.0f, "TV", "Electronics");
    HomeApplianceRecord r2 = createRecord("A001", 75.0f, 90.0f, "Toaster", "Appliances");
    HomeApplianceRecord r3 = createRecord("A002", 80.0f, 100.0f, "Blender", "Kitchen");
    
    bst.insert(r1.getSKU(), r1);
    bst.insert(r2.getSKU(), r2);
    bst.insert(r3.getSKU(), r3);
    
    // Collect keys during in-order traversal.
    ITraversal<String, HomeApplianceRecord> traversal = actualNode -> keysInOrder.add(actualNode.get_key()); 
    bst.InOrder(traversal);
    
    // Expected lexicographical order: A001, A002, A003.
    assertEquals(3, keysInOrder.size());
    assertEquals("A001", keysInOrder.get(0));
    assertEquals("A002", keysInOrder.get(1));
    assertEquals("A003", keysInOrder.get(2));
}

@Test
void testPreOrderTraversal() {
    Ibst<String, HomeApplianceRecord> bst = new BST<>();
    List<String> preOrderKeys = new ArrayList<>();
    
    // Insert records. Insertion order determines the tree structure.
    HomeApplianceRecord r1 = createRecord("A002", 80.0f, 100.0f, "Blender", "Kitchen");
    HomeApplianceRecord r2 = createRecord("A001", 75.0f, 90.0f, "Toaster", "Appliances");
    HomeApplianceRecord r3 = createRecord("A003", 500.0f, 600.0f, "TV", "Electronics");
    
    bst.insert(r1.getSKU(), r1);
    bst.insert(r2.getSKU(), r2);
    bst.insert(r3.getSKU(), r3);
    
    ITraversal<String, HomeApplianceRecord> traversal = actualNode -> preOrderKeys.add(actualNode.get_key());
    
    bst.PreOrder(traversal);
    
    // With insertion order A002, A001, A003, pre-order should be: A002, A001, A003.
    assertEquals(3, preOrderKeys.size());
    assertEquals("A002", preOrderKeys.get(0));
    assertEquals("A001", preOrderKeys.get(1));
    assertEquals("A003", preOrderKeys.get(2));
}

@Test
void testPostOrderTraversal() {
    Ibst<String, HomeApplianceRecord> bst = new BST<>();
    List<String> postOrderKeys = new ArrayList<>();
    
    // Insert records.
    HomeApplianceRecord r1 = createRecord("A002", 80.0f, 100.0f, "Blender", "Kitchen");
    HomeApplianceRecord r2 = createRecord("A001", 75.0f, 90.0f, "Toaster", "Appliances");
    HomeApplianceRecord r3 = createRecord("A003", 500.0f, 600.0f, "TV", "Electronics");
    
    bst.insert(r1.getSKU(), r1);
    bst.insert(r2.getSKU(), r2);
    bst.insert(r3.getSKU(), r3);
    
    ITraversal<String, HomeApplianceRecord> traversal = actualNode -> postOrderKeys.add(actualNode.get_key());
    
    bst.PostOrder(traversal);
    
    // For insertion order A002, A001, A003, the post-order (left, right, root) should be: A001, A003, A002.
    assertEquals(3, postOrderKeys.size());
    assertEquals("A001", postOrderKeys.get(0));
    assertEquals("A003", postOrderKeys.get(1));
    assertEquals("A002", postOrderKeys.get(2));
}

}
