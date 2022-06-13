//FINAL
public class Tree<K extends Comparable<K>, V> {
    private Node root;//the root Node
    private int n;//the number of nodes in the tree

    /* CONSTRUCTOR */
    public Tree() {
	this.root = null;
	this.n = 0;
    }

    /* PUBLIC METHODS */

    /***
     *insert a new (key, val) into tree
     *or replace value of existing key
     */
    public void put(K key, V val) {
    	root = putHelper(key, val, root);
    }
    
    /***
     *get the value associated with the given key;
     *return null if key doesn't exist
     */
    public V get(K key) {
    	Node rec_node = search(key, root, 0);
    	if (rec_node != null) {
    		return rec_node.val;
    	}
    	else {
    		return null;
    	}
    }

    /***
     *return true if the tree
     *is empty and false 
     *otherwise
     */
    public boolean isEmpty() {
    	if (this.root == null) {
    		return true;
    	}
    	return false;
    }

    /***
     *return the number of Nodes
     *in the tree
     */
    public int size() {
    	return this.n;
    }

    /***
     *returns the height of the tree
     */
    public int height() {
    	return heightHelper(root);
    }

    /***
     *returns the height of node 
     *with given key in the tree;
     *return -1 if the key does
     *not exist in the tree
     */
    public int height(K key) {
    	return heightHelper(search(key, root, 0));
    }

    /***
     *returns true if the tree contains
     *the given key, false otherwise
     ***/
    public boolean contains(K key) {
		if (search(key, root, 0) != null) {
			return true;
		}
		return false;
    }

    /***
     *returns the size (i.e. number of elements) of the subtree
     *whose root contains the given key; make sure to include the 
     *node itself in the count;
     *return -1 if the key is not in the tree
     ***/
    public int size(K key) {
    	Node ret_node = search(key, root, 0);
    	if (ret_node == null) {
    		return -1;
    	}
    	return sizeHelper(ret_node);
    }
    
    //private
    private Node search(K key, Node node, int height) {
    	if (node == null) {
    		return null;
    	}
    	
    	if (key.equals(node.key)) {
    		node.height = height;
    		return node;
    	} else if (key.compareTo(node.key) < 0) {
    		return search(key, node.left, height+1);
    	} else if (key.compareTo(node.key) > 0) {
    		return search(key, node.right, height+1);
    	}
    	return null;
    }
    
    private Node putHelper(K key, V val, Node node) {
    	if (node == null) {
    		node = new Node(key, val);
    		n++;
    		return node;	
    	}
    	
    	if (key.equals(node.key)) {
    		node.val = val;
    	} else if (key.compareTo(node.key) < 0) {
    		node.left = putHelper(key, val, node.left);
    	} else if (key.compareTo(node.key) > 0) {
    		node.right = putHelper(key, val, node.right);
    	}
    	return node;
    }
    
    private int heightHelper(Node node) {
    	if (node == null) {
    		return -1;
    	} else {
    		int left = heightHelper(node.left);
    		int right = heightHelper(node.right);
    		if (right > left) {
    			return right+1;
    		}
    		else {
    			return left+1;
    		}
    	}
    }
    
    private int sizeHelper(Node node) {
    	if (node == null) {
    		return 0;
    	}
    	else {
    		return (sizeHelper(node.left)) + (sizeHelper(node.right)) + 1;
    	}
    }

    /* NODE CLASS */
    public class Node {
	K key;
	V val;
	Node left, right;
	int height;//the height of this node
	int N;//the size of the subtree rooted at this node

	public Node(K key, V val) {
	    this.key = key;
	    this.val = val;
	    this.N = 1;
	}
	
	public String toString() {
	    return "(" + key + ", " + val + "): " + height;
	}
    }
}    
