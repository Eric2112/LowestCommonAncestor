package lowestCommonAncestor;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class LowestCommonAncestorTest {
	
	@Test
	public void testPrintTree()
	{
		//Testing printing of an empty tree.
	     binarySearchTree<Character, Character> bst = new  binarySearchTree<Character, Character>();
	     assertEquals("Testing printing of empty tree",
	             "()", bst.printTree());
	     
	     //Testing printing of a one node tree.
	     bst.insert('B', 'B');
	     assertEquals("Testing printing of one node tree",
	             "(()B())", bst.printTree());
	     
	     
	     //Testing printing of a multiple node tree/
	     bst.insert('A', 'A');
	     bst.insert('C', 'C');
	     bst.insert('D', 'D');
	     
	     assertEquals("Testing printing of multiple node tree",
	    		 "((()A())B(()C(()D())))", bst.printTree());	     
	}
	
	@Test
	public void testInsert() {
		 binarySearchTree<Character, Character> bst = new  binarySearchTree<Character, Character>();
		
		
		//Testing node creation.
		bst.insert('B', 'B');
		assertEquals("Testing insert on empty tree", 
				"(()B())", bst.printTree());
		
		//Testing nodes get sorted correctly when added.
		bst.insert('A', 'A');
		bst.insert('C', 'C');
		bst.insert('D', 'D');
		
		
		/*
		 * 			B
		 * 		   / \
		 * 		  A   C
		 * 			   \
		 * 				D	  
		 */
		
		assertEquals("Testing insert of multiple nodes into tree", 
				"((()A())B(()C(()D())))", bst.printTree());
		
		//Testing update of value in tree.
		bst.insert('C', 'X');
		assertEquals("Testing insert of multiple nodes into tree", 
				'X', (char)bst.get('C'));
		
		//Testing insert of null value. (Should delete node)
		bst.insert('C', null);
		assertEquals("Testing insert of null value into tree", 
				"((()A())B(()D()))", bst.printTree());
		
		//Testing insert of null key. (Should have no effect)
		bst.insert(null, 'Q');
		assertEquals("Testing insert of null key into tree", 
				"((()A())B(()D()))", bst.printTree());
		
	}
	
	@Test
	public void testGet(){
		 binarySearchTree<Character, Character> bst = new  binarySearchTree<Character, Character>();
		
		assertNull("Testing get on empty tree", 
				bst.get('A'));
	     
	     bst.insert('B', 'B');
	     bst.insert('C', 'C');
	     bst.insert('D', 'D');
	     bst.insert('E', 'E');
	     
	     assertNull("Testing get on multiple node tree that doesn't contain key",
	             bst.get('A'));   
	     

	     assertEquals("Testing get on multiple node tree that does contain key",
	             'C', (char)bst.get('C'));   //Have to cast to char as will return type of 'Value'.
	     
	     assertNull("Testing null get", bst.get(null));
	      
	}	
	
	@Test
	public void testDelete(){
		 binarySearchTree<Integer, Integer> bst = new  binarySearchTree<Integer, Integer>();
		
		//Testing delete on an empty tree.
		bst.delete(1);
        assertEquals("Deleting from empty tree", "()", bst.printTree());
        
        
        bst.insert(7, 7);   //        _7_
        bst.insert(8, 8);   //      /     \
        bst.insert(3, 3);   //    _3_      8
        bst.insert(1, 1);   //  /     \
        bst.insert(2, 2);   // 1       6
        bst.insert(6, 6);   //  \     /
        bst.insert(4, 4);   //   2   4
        bst.insert(5, 5);   //        \
        					//         5
        
        assertEquals("Testing constructed tree is as expected.",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printTree());
        
        //Test deleting key not present in tree. Should have no effect.
        bst.delete(9);
        assertEquals("Deleting non-existent key",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printTree());

        //Test deleting null key. Should have no effect.
        bst.delete(null);
        assertEquals("Deleting non-existent key",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printTree());
        
        //Test deleting leaf.
        bst.delete(8);
        assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printTree());

        //Test deleting node with single child.
        bst.delete(6);
        assertEquals("Deleting node with single child",
                "(((()1(()2()))3(()4(()5())))7())", bst.printTree());

        //Test deleting node with two children
        bst.delete(3);
        assertEquals("Deleting node with two children",
                "(((()1())2(()4(()5())))7())", bst.printTree());
	}

	
	@Test
	public void testLowestCommonAncestor(){
		//Lowest Common Ancestor should require two keys and return key of LCA. We then can use 
		
		
		 binarySearchTree<Integer, Integer> bst = new  binarySearchTree<Integer, Integer>();
		
		//Testing empty tree.
		assertNull("Testing LCA on empty tree", bst.lowestCommonAncestor(1, 2));
		
		//Testing one-node tree.
		bst.insert(1,1);
		
		//If given non-present keys - should return null.
		
		//One key present.
		assertNull("Testing one node tree given non-present keys", bst.lowestCommonAncestor(2,1));
		
		//Both non-present keys.
		assertNull("Testing one node tree given non-present keys", bst.lowestCommonAncestor(2,3));
		
		//Testing multi-node tree
		 binarySearchTree<Integer, Integer> bst2 = new  binarySearchTree<Integer, Integer>();

	       	bst2.insert(7, 7);   //        _7_
	        bst2.insert(8, 8);   //      /     \
	        bst2.insert(3, 3);   //    _3_      8
	        bst2.insert(1, 1);   //  /     \
	        bst2.insert(2, 2);   // 1       6
	        bst2.insert(6, 6);   //  \     /
	        bst2.insert(4, 4);   //   2   4
	        bst2.insert(5, 5);   //        \
	        					 //         5
		
	    //If either given key is the root, should return the root
		assertEquals("Testing one node tree given root key", 7, (int)bst2.lowestCommonAncestor(7,2));    
	        
	    assertEquals("Testing multi-node tree", 7, (int)bst2.lowestCommonAncestor(3,8));
	    assertEquals("Testing multi-node tree", 7, (int)bst2.lowestCommonAncestor(5,8));
	    assertEquals("Testing multi-node tree", 3, (int)bst2.lowestCommonAncestor(3,6));
	    assertEquals("Testing multi-node tree", 3, (int)bst2.lowestCommonAncestor(2,5));
	}
	
	@Test
	public void testIsEmpty(){
		 binarySearchTree<Integer, Integer> bst = new  binarySearchTree<Integer, Integer>();
		assertEquals("Testing empty tree", true, bst.isEmpty());
		
		bst.insert(1, 1);
		assertEquals("Testing non-empty tree", false, bst.isEmpty());	
	}
	
	@Test
	public void testSize(){
		 binarySearchTree<Integer, Integer> bst = new  binarySearchTree<Integer, Integer>();
		assertEquals("Testing empty tree", 0, bst.size());
		
		bst.insert(1, 1);
		assertEquals("Testing single node tree", 1, bst.size());
		
		bst.insert(2, 2);
		bst.insert(7, 7);
		bst.insert(3, 3);
		bst.insert(99, 99);
		
		assertEquals("Testing multi node tree", 5, bst.size());
	}

	@Test
	public void testContains(){
		 binarySearchTree<Integer, Integer> bst = new  binarySearchTree<Integer, Integer>();
		assertEquals("Testing empty tree", false, bst.contains(4));
		
		bst.insert(1, 1);
		assertEquals("Testing single node tree containing key", true, bst.contains(1));
		assertEquals("Testing single node tree not containing key", false, bst.contains(5));
		
		bst.insert(2, 2);
		bst.insert(7, 7);
		bst.insert(3, 3);
		bst.insert(99, 99);
		
		assertEquals("Testing multi node tree containing key", true, bst.contains(99));
		assertEquals("Testing multi node tree containing key", false, bst.contains(4));
		
		assertEquals("Testing contains null. Should return false", false,  bst.contains(null));
	}
	
	
	//Tests for directed acyclic graph implementation. Going to need the following functions:
	// new dag()
	// printDag()
	// addEdge(int x, int y) - will need to check if it would make a cycle (true/false return maybe?)
	// V() - returns num vertices - will just be a public func. to return private variable. 
	// adj(int v) - will return adjacency table of node v. Maybe as an iterable?
	// lowestCommonAncestor(int v) - could have multiple lcas - iterable again?
	// Will also probably need some sort of search class - dfs/topological sort/bfs. 
	// But this can be private and so should be tested via lca.
	
//	@Test
//	public void testPrintDag(){
//		dag dag1 = new dag(5);
//		// Should have vertices 0,1,2,3,4.
//		
//		//Not fully sure how it will print - will depend on how I setup the dag obj. But more than likely
//		//it will be a series of adjacency bags each printed on a line.
//		
//		assertEquals("Testing printDag on a no-edge dag", "0: \n1: \n2: \n3: \n4: ", dag.testPrintDag());
//		
//		dag1.addEdge(0, 1);
//		
//		assertEquals("Testing printDag on a single edge dag", "0: 1.\n1: .\n2: .\n3: .\n4: .", dag.testPrintDag());
//		
//		dag1.addEdge(0, 2);
//		dag1.addEdge(3, 4);
//		dag1.addEdge(4, 2);
//		dag1.addEdge(4, 1);
//		
//		assertEquals("Testing printDag on a multi-edge dag", "0: 1, 2.\n1: .\n2: .\n3: 4.\n4: 2, 1.", dag.testPrintDag());
//	}
	
	@Test
	public void testAddEdge(){
		directedAcyclicGraph dag1 = new directedAcyclicGraph(5);
		
		assertEquals("Testing adding a self-loop. Should return false.", false, dag1.addEdge(0, 0));
		
		assertEquals("Testing adding a valid edge. Should return true.", true, dag1.addEdge(0, 1));
		assertEquals("Testing adding a valid edge. Should return true.", true, dag1.addEdge(1, 2));
		
		assertEquals("Testing adding an edge that would result in a cylce. Should return false.", false, dag1.addEdge(2, 0));
		
		assertEquals("Testing adding an edge from non-existing vertices. Should return false.", false, dag1.addEdge(5, 4));
		assertEquals("Testing adding an edge from non-existing vertices. Should return false.", false, dag1.addEdge(89, 53));
		assertEquals("Testing adding an edge from negative vertices. Should return false.", false, dag1.addEdge(-2, -4));
		
	}
	
	@Test
	public void testV(){
		//Not much to test - should return the num of vertices.
		directedAcyclicGraph dag1 = new directedAcyclicGraph(5);
		assertEquals("Testing V()", 5, dag1.V());
	}
	
	@Test
	public void testAdj(){
		directedAcyclicGraph dag1 = new directedAcyclicGraph(5);
		
		assertTrue("Testing empty adj list", dag1.adj(0).isEmpty());
		
		ArrayList<Integer> expectedResult = new ArrayList<Integer>();
		
		//Testing single edge adj list
		expectedResult.add(2);
		dag1.addEdge(1, 2);
		
		assertTrue("Testing single edge adj list", dag1.adj(1).size() == expectedResult.size());
		for(int i : expectedResult){
			assertTrue("Testing single edge adj list", dag1.adj(1).contains(i));
		}
		
		expectedResult.clear();
		
		expectedResult.add(3);
		expectedResult.add(4);
		
		dag1.addEdge(2, 3);
		dag1.addEdge(2, 4);
		
		assertTrue("Testing multi-edge adj list", dag1.adj(2).size() == expectedResult.size());
		for(int i : expectedResult){
			assertTrue("Testing multi-edge adj list", dag1.adj(2).contains(i));
		}
	}
	
//	@Test
	public void testDagLowestCommonAncestor(){
		directedAcyclicGraph testDag1 = new directedAcyclicGraph(4);
		
		testDag1.addEdge(0, 1);
		testDag1.addEdge(0, 2);
		testDag1.addEdge(2, 3);
		testDag1.addEdge(3, 4);
		
		ArrayList<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(0);
				
		assertTrue("Testing single lca return", testDag1.lowestCommonAncestor(4,1).size() == expectedResult.size());
		for(int i : expectedResult){
			assertTrue("Testing single lca return", testDag1.lowestCommonAncestor(4,1).contains(i));
		}
		
		
		
		
		
		directedAcyclicGraph testDag2 = new directedAcyclicGraph(5);

		testDag2.addEdge(0, 3);			
		testDag2.addEdge(1, 3);
		testDag2.addEdge(2, 5);
		testDag2.addEdge(3, 5);
		testDag2.addEdge(4, 6);
		
		expectedResult.clear();
		expectedResult.add(2);
		expectedResult.add(3);
				
		assertTrue("Testing mutliple lca return", testDag1.lowestCommonAncestor(5,6).size() == expectedResult.size());
		for(int i : expectedResult){
			assertTrue("Testing mutliple lca return", testDag1.lowestCommonAncestor(5,6).contains(i));
		}
				
		//Testing non present vertices input
		assertTrue("Testing negative input", testDag2.lowestCommonAncestor(-2, -1).isEmpty());
		
		assertTrue("Testing out of range input", testDag2.lowestCommonAncestor(2457, 987).isEmpty());		
	
	}	
}
