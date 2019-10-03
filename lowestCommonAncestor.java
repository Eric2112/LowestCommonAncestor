package lowestCommonAncestor;

//Java implementation to find lowest common ancestor of 
//n1 and n2 using one traversal of binary tree 
//It also handles cases even when n1 and n2 are not there in Tree 

/* Class containing left and right child of current node and key */
class Node 
{ 
 int data; 
 Node left, right; 

 public Node(int item) 
 { 
     data = item; 
     left = right = null; 
 } 

 /* Driver program to test above functions */
 public static void main(String args[]) 
 { 
     binaryTree tree = new binaryTree(); 
     tree.root = new Node(1); 
     tree.root.left = new Node(2); 
     tree.root.right = new Node(3); 
     tree.root.left.left = new Node(4); 
     tree.root.left.right = new Node(5); 
     tree.root.right.left = new Node(6); 
     tree.root.right.right = new Node(7); 

     Node lca = tree.findLCA(4, 5); 
     if (lca != null) 
         System.out.println("LCA(4, 5) = " + lca.data); 
     else
         System.out.println("Keys are not present"); 

     lca = tree.findLCA(4, 10); 
     if (lca != null) 
         System.out.println("LCA(4, 10) = " + lca.data); 
     else
         System.out.println("Keys are not present"); 
 } 
} 
