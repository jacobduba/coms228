package edu.iastate.cs228.hw4;


import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Stack;


/**
 * @author Jacob Duba
 */


/**
 * This class implements a splay tree.  Add any helper methods or implementation details
 * you'd like to include.
 */


public class SplayTree<E extends Comparable<? super E>> extends AbstractSet<E> {
	protected Node root;
	protected int size;

	public class Node  // made public for grading purpose
	{
		public E data;
		public Node left;
		public Node parent;
		public Node right;

		public Node(E data) {
			this.data = data;
		}

		@Override
		public Node clone() {
			return new Node(data);
		}
	}


	/**
	 * Default constructor constructs an empty tree.
	 */
	public SplayTree() {
		size = 0;
		root = null;
	}


	/**
	 * Needs to call addBST() later on to complete tree construction.
	 */
	public SplayTree(E data) {
		size = 1;
		root = new Node(data);
	}


	/**
	 * Copies over an existing splay tree. The entire tree structure must be copied.
	 * No splaying. Calls cloneTreeRec().
	 *
	 * @param tree
	 */
	public SplayTree(SplayTree<E> tree) {
		// TODO
	}


	/**
	 * Recursive method called by the constructor above.
	 *
	 * @param subTree
	 * @return
	 */
	public Node cloneTreeRec(Node subTree) {
		// TODO
		return null;
	}


	/**
	 * This function is here for grading purpose. It is not a good programming practice.
	 *
	 * @return element stored at the tree root
	 */
	public E getRoot() {
		return root.data;
	}


	@Override
	public int size() {
		return size;
	}


	/**
	 * Clear the splay tree.
	 */
	@Override
	public void clear() {
		root = null;
	}


	// ----------
	// BST method
	// ----------

	/**
	 * Adds an element to the tree without splaying.  The method carries out a binary search tree
	 * addition.  It is used for initializing a splay tree.
	 * <p>
	 * Calls link().
	 *
	 * @param data
	 * @return true  if addition takes place
	 * false otherwise (i.e., data is in the tree already)
	 */
	public boolean addBST(E data) {
		// Special case with empty tree; then comparisons cannot be called!
		if (size == 0) {
			root = new Node(data);
			++size;
			return true;
		}

		Node current = root;
		while (true) {
			int comp = current.data.compareTo(data);
			if (comp == 0) {
				// key is already in tree
				return false;
			} else if (comp > 0) {
				if (current.left != null) {
					current = current.left;
				} else {
					link(current, new Node(data));
					++size;
					return true;
				}
			} else { // if (comp < 0)
				if (current.right != null) {
					current = current.right;
				} else {
					link(current, new Node(data));
					++size;
					return true;
				}
			}
		}
	}


	// ------------------
	// Splay tree methods
	// ------------------

	/**
	 * Inserts an element into the splay tree. In case the element was not contained, this
	 * creates a new node and splays the tree at the new node. If the element exists in the
	 * tree already, it splays at the node containing the element.
	 * <p>
	 * Calls link().
	 *
	 * @param data element to be inserted
	 * @return true  if addition takes place
	 * false otherwise (i.e., data is in the tree already)
	 */
	@Override
	public boolean add(E data) {
		// TODO
		return false;
	}


	/**
	 * Determines whether the tree contains an element.  Splays at the node that stores the
	 * element.  If the element is not found, splays at the last node on the search path.
	 *
	 * @param data element to be determined whether to exist in the tree
	 * @return true  if the element is contained in the tree
	 * false otherwise
	 */
	public boolean contains(E data) {
		// TODO
		return false;
	}


	/**
	 * Finds the node that stores the data and splays at it.
	 *
	 * @param data
	 */
	public void splay(E data) {
		contains(data);
	}


	/**
	 * Removes the node that stores an element.  Splays at its parent node after removal
	 * (No splay if the removed node was the root.) If the node was not found, the last node
	 * encountered on the search path is splayed to the root.
	 * <p>
	 * Calls unlink().
	 *
	 * @param data element to be removed from the tree
	 * @return true  if the object is removed
	 * false if it was not contained in the tree
	 */
	public boolean remove(E data) {
		// TODO
		return false;
	}


	/**
	 * This method finds an element stored in the splay tree that is equal to data as decided
	 * by the compareTo() method of the class E.  This is useful for retrieving the value of
	 * a pair <key, value> stored at some node knowing the key, via a call with a pair
	 * <key, ?> where ? can be any object of E.
	 * <p>
	 * Calls findEntry(). Splays at the node containing the element or the last node on the
	 * search path.
	 *
	 * @param data
	 * @return element such that element.compareTo(data) == 0
	 */
	public E findElement(E data) {
		// TODO
		return null;
	}


	/**
	 * Finds the node that stores an element. It is called by methods such as contains(), add(), remove(),
	 * and findElement().
	 * <p>
	 * No splay at the found node.
	 *
	 * @param data element to be searched for
	 * @return node  if found or the last node on the search path otherwise
	 * null  if size == 0.
	 */
	public Node findEntry(E data) {
		// TODO
		return null;
	}


	/**
	 * Join the two subtrees T1 and T2 rooted at root1 and root2 into one.  It is
	 * called by remove().
	 * <p>
	 * Precondition: All elements in T1 are less than those in T2.
	 * <p>
	 * Access the largest element in T1, and splay at the node to make it the root of T1.
	 * Make T2 the right subtree of T1.  The method is called by remove().
	 *
	 * @param root1 root of the subtree T1
	 * @param root2 root of the subtree T2
	 * @return the root of the joined subtree
	 */
	protected Node join(Node root1, Node root2) {
		// TODO
		return null;
	}


	/**
	 * Splay at the current node.  This consists of a sequence of zig, zigZig, or zigZag
	 * operations until the current node is moved to the root of the tree.
	 *
	 * @param current node to splay
	 */
	protected void splay(Node current) {
		// TODO
	}


	/**
	 * This method performs the zig operation on a node. Calls leftRotate()
	 * or rightRotate().
	 *
	 * @param current  node to perform the zig operation on
	 */
	protected void zig(Node current) {
		if (current.parent.left == current) {
			rightRotate(current);
		} else { // current.parent.right == current
			leftRotate(current);
		}
	}


	/**
	 * This method performs the zig-zig operation on a node. Calls leftRotate()
	 * or rightRotate().
	 *
	 * @param current node to perform the zig-zig operation on
	 */
	protected void zigZig(Node current) {
		// TODO test if this works?
		if (current.parent.left == current) {
			rightRotate(current);
			rightRotate(current);
		} else { // current.parent.right == current
			leftRotate(current);
			leftRotate(current);
		}
	}


	/**
	 * This method performs the zig-zag operation on a node. Calls leftRotate()
	 * and rightRotate().
	 *
	 * @param current node to perform the zig-zag operation on
	 */
	protected void zigZag(Node current) {
		// TODO
	}


	/**
	 * Carries out a left rotation at a node such that after the rotation
	 * its former parent becomes its left child.
	 * <p>
	 * Calls link().
	 *
	 * @param current
	 */
	public void leftRotate(Node current) {
		Node a = current;
		Node b = current.parent;
		Node c = current.left;
		link(b, c);
		if (b == root) {
			root = a;
		} else {
			link(b.parent, a);
		}
		link(a, b);
	}


	/**
	 * Carries out a right rotation at a node such that after the rotation
	 * its former parent becomes its right child.
	 * <p>
	 * Calls link().
	 *
	 * @param current
	 */
	public void rightRotate(Node current) {
		Node a = current;
		Node b = current.parent;
		Node c = current.right;
		link(b, c);
		if (b == root) {
			root = a;
		} else {
			link(b.parent, a);
		}
		link(a, b);
	}


	/**
	 * Establish the parent-child relationship between two nodes.
	 * <p>
	 * Called by addBST(), add(), leftRotate(), and rightRotate().
	 *
	 * @param parent
	 * @param child
	 */
	private void link(Node parent, Node child) {
		if (parent.data.compareTo(child.data) > 0) {
			parent.left = child;
		} else {
			parent.right = child;
		}
		child.parent = parent;
	}


	/**
	 * Removes a node n by replacing the subtree rooted at n with the join of the node's
	 * two subtrees.
	 * <p>
	 * Called by remove().
	 *
	 * @param n
	 */
	private void unlink(Node n) {
		// TODO
	}


	/**
	 * Perform BST removal of a node.
	 * <p>
	 * Called by the iterator method remove().
	 *
	 * @param n
	 */
	public void unlinkBST(Node n) {
		// TODO
	}


	/**
	 * Called by unlink() and the iterator method next().
	 *
	 * @param n
	 * @return successor of n
	 */
	public Node successor(Node n) {
		// TODO
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		return new SplayTreeIterator();
	}


	/**
	 * Write the splay tree according to the format specified in Section 2.2 of the project
	 * description.
	 * <p>
	 * Calls toStringRec().
	 */
	@Override
	public String toString() {
		if (root == null) {
			return "";
		} else {
			return toStringRec(root, 0);
		}
	}


	private String toStringRec(Node n, int depth) {
		String str = "";
		for (int i = 0; i < depth; i++) {
			str += "    ";
		}
		if (n == null) {
			str += "null\n";
		} else { // else if (n.data != null) {
			str += n.data.toString() + "\n";
			if (n.left != null || n.right != null) {
				str += toStringRec(n.left, depth + 1);
				str += toStringRec(n.right, depth + 1);
			}
		}
		return str;
	}


	/**
	 * Iterator implementation for this splay tree.  The elements are returned in
	 * ascending order according to their natural ordering.  The methods hasNext()
	 * and next() are exactly the same as those for a binary search tree --- no
	 * splaying at any node as the cursor moves.  The method remove() behaves like
	 * the class method remove(E data) --- after the node storing data is found.
	 */
	private class SplayTreeIterator implements Iterator<E> {
		Stack<Node> s;

		public SplayTreeIterator() {
			s = new Stack<Node>();

			Node cur = root;
			while (cur != null) {
				s.push(cur);
				cur = cur.left;
			}
		}

		@Override
		public boolean hasNext() {
			return s.size() != 0;
		}

		@Override
		public E next() {
			E ret = s.peek().data;
			Node temp = s.pop().right;

			while (temp != null) {
				s.push(temp);
				temp = temp.left;
			}

			return ret;
		}

		/**
		 * This method will join the left and right subtrees of the node being removed,
		 * and then splay at its parent node.  It behaves like the class method
		 * remove(E data) after the node storing data is found.  Place the cursor at the
		 * parent (or the new root if removed node was the root).
		 * <p>
		 * Calls unlinkBST().
		 */
		@Override
		public void remove() {
			// TODO
		}
	}
}
