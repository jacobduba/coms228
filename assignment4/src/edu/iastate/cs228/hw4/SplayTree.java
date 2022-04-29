package edu.iastate.cs228.hw4;


import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
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
		setRoot(null);
	}


	/**
	 * Needs to call addBST() later on to complete tree construction.
	 */
	public SplayTree(E data) {
		size = 1;
		setRoot(new Node(data));
	}


	/**
	 * Copies over an existing splay tree. The entire tree structure must be copied.
	 * No splaying. Calls cloneTreeRec().
	 *
	 * @param tree
	 */
	public SplayTree(SplayTree<E> tree) {
		setRoot(cloneTreeRec(tree.root));
		size = tree.size();
	}


	/**
	 * Recursive method called by the constructor above.
	 *
	 * @param subTree
	 * @return
	 */
	public Node cloneTreeRec(Node subTree) {
		if (subTree == null) return null;

		Node copy = new Node(subTree.data);
		copy.left = cloneTreeRec(subTree.left);
		copy.right = cloneTreeRec(subTree.right);

		if (copy.left != null) copy.left.parent = copy;
		if (copy.right != null) copy.right.parent = copy;

		return copy;
	}


	/**
	 * This function is here for grading purpose. It is not a good programming practice.
	 *
	 * @return element stored at the tree root
	 */
	public E getRoot() {
		if (root == null) return null;

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
		setRoot(null);
		size = 0;
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
		Node add = new Node(data);

		// Special case with empty tree; then comparisons cannot be called!
		if (size == 0) {
			setRoot(add);
			++size;
			return true;
		}

		Node n = findEntry(data);
		// key is already in use
		if (n.data.compareTo(data) == 0) {
			return false;
		} else { // key is not in use
			size++;
			link(n, add);
			return true;
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
		Node add = new Node(data);

		// Special case with empty tree; then comparisons cannot be called, must set root
		if (size == 0) {
			setRoot(add);
			size++;
			return true;
		}

		Node n = findEntry(data);
		// key is already in use
		if (n.data.compareTo(data) == 0) {
			splay(n);
			return false;
		} else { // key is not in use
			size++;
			link(n, add);
			splay(add);
			return true;
		}
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
		if (size == 0) return false;

		Node cur = root;
		while (true) {
			int comp = cur.data.compareTo(data);
			if (comp == 0) {
				splay(cur);
				return true;
			} else if (comp > 0) { // Move to left
				if (cur.left == null) {
					splay(cur);
					return false;
				}
				cur = cur.left;
			} else { // comp < 0 Move to right
				if (cur.right == null) {
					splay(cur);
					return false;
				}
				cur = cur.right;
			}
		}
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
		if (size == 0) return false;

		Node n = findEntry(data);
		if (n.data.compareTo(data) == 0) { // if n.data == data
			unlink(n); // Removal
			if (n.parent != null) splay(n.parent); // Splay at its parent node after removal
			return true;
		} else { // n.data != data,
			// If the node was not found, the last node encountered on the search path is splayed to the root
			splay(n);
			return false;
		}
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
		Node entry = findEntry(data);
		splay(entry);
		if (entry.data.compareTo(data) == 0) {
			return findEntry(data).data;
		} else { // entry.data != data
			return null;
		}
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
		if (size == 0) return null;

		Node cur = root;
		while (true) {
			int comp = cur.data.compareTo(data);
			if (comp == 0) {
				return cur;
			} else if (comp > 0) { // Move to left
				if (cur.left == null) return cur;
				cur = cur.left;
			} else { // comp < 0 Move to right
				if (cur.right == null) return cur;
				cur = cur.right;
			}
		}
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
		// Find the largest node in T1, which is just the right most.
		Node x = root1;
		while (x.right != null) {
			x = x.right;
		}
		// Then splay x to move it to the top
		splay(x);
		link(x, root2);

		return x;
	}


	/**
	 * Splay at the current node.  This consists of a sequence of zig, zigZig, or zigZag
	 * operations until the current node is moved to the root of the tree.
	 *
	 * @param current node to splay
	 */
	protected void splay(Node current) {
		while (current != root) {
			// If p is the root
			if (current.parent == root) {
				zig(current);
			// If p is not the root and x and p are either both right children or both left children
			} else if ((current.parent.left == current && current.parent.parent.left == current.parent) ||
					(current.parent.right == current && current.parent.parent.right == current.parent)) {
				zigZig(current);
			// when p is not the root and x is a right child and p is a left child or vice versa
			} else {
				zigZag(current);
			}
		}
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
		if (current.parent.left == current) {
			rightRotate(current.parent);
			rightRotate(current);
		} else { // current.parent.right == current
			leftRotate(current.parent);
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
		if (current.parent.left == current) {
			rightRotate(current);
			leftRotate(current);
		} else { // current.parent.right == current
			leftRotate(current);
			rightRotate(current);
		}
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

		if (c == null) {
			// If C is null, get rid of b.right (which is a), you will get infinite loop otherwise
			b.right = null;
		} else {
			link(b, c);

		}
		if (b == root) {
			setRoot(a);
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

		if (c == null) {
			// If C is null, get rid of b.left (which is a), you will get infinite loop otherwise
			b.left = null;
		} else {
			link(b, c);
		}

		if (b == root) {
			setRoot(a);
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
		// TODO better code?

		// Check if I have two children, splay logic
		if (n.left != null && n.right != null) {
			// Here I'm creating a new Splay tree because in order for splay to work
			// in join() it needs to be a separate subtree.
			SplayTree<E> subTree = new SplayTree<E>();
			Node subTreeRoot = cloneTreeRec(n);
			subTree.setRoot(subTreeRoot);

			subTreeRoot = subTree.join(subTreeRoot.left, subTreeRoot.right);

			if (n == root) {
				setRoot(subTreeRoot);
			} else {
				link(n.parent, subTreeRoot);
			}
		} else if (n.right != null) { // and n.left == null
			if (n == root) {
				setRoot(n.right);
			} else {
				link(n.parent, n.right);
			}
		} else if (n.left != null) { // and n.right == null
			if (n == root) {
				setRoot(n.left);
			} else {
				link(n.parent, n.left);
			}
		} else { // if n.left == null && r.right == null
			if (n == root) {
				setRoot(null);
			} else {
				if (n.parent.left == n) {
					n.parent.left = null;
				} else { // if n.parent.right == n
					n.parent.right = null;
				}
			}
		}

		size--;
	}

	/**
	 * Helper method that sets the root, sets root's parent to null.
	 * This was common enough to create a helper method for.
	 */
	private void setRoot(Node n) {
		root = n;
		if (n != null) {
			n.parent = null;
		}
	}


	/**
	 * Perform BST removal of a node.
	 * <p>
	 * Called by the iterator method remove().
	 *
	 * @param n
	 */
	public void unlinkBST(Node n) {
		if (n.left != null && n.right != null) {
			Node s = successor(n);
			n.data = s.data;
			n = s;
		}

		Node replacement = null;
		if (n.left != null) {
			replacement = n.left;
		} else if (n.right != null) {
			replacement = n.right;
		}

		if (n.parent == null) {
			root = replacement;
		} else {
			if (n == n.parent.left) {
				n.parent.left = replacement;
			} else {
				n.parent.right = replacement;
			}
		}

		if (replacement != null) {
			replacement.parent = n.parent;
		}

		size--;
	}


	/**
	 * Called by unlink() and the iterator method next().
	 *
	 * @param n
	 * @return successor of n
	 */
	public Node successor(Node n) {
		if (n == null) {
			return null;
		} else if (n.right != null){
			// leftmost entry in subtree
			Node cur = n.right;
			while (cur.left != null) {
				cur = cur.left;
			}
			return cur;
		} else {
			// We need to go up the tree to the closest ancestor that
			// is a left child; its parent must be the successor
			Node cur = n.parent;
			Node child = n;
			while (cur != null && cur.right == child) {
				child = cur;
				cur = cur.parent;
			}
			return cur;
		}
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
			return "null\n";
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
		Node current;
		Node pending;

		public SplayTreeIterator() {
			current = root;
			while (current != null && current.left != null) {
				current = current.left;
			}
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}


		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();

			pending = current;
			E ret = current.data;

			if (current.right != null) {
				current = current.right;

				while(current.left != null) {
					current = current.left;
				}
			} else {
				Node parent = current.parent;
				while (parent != null && current == parent.right) {
					current = parent;
					parent = parent.parent;
				}
				current = parent;
			}
			return ret;
		}

		/**
		 * This method will join the left and right subtrees of the node being removed,
		 * It behaves like the class method remove(E data) after the node storing data
		 * is found.  Place the cursor at the parent (or the new root if removed node was
		 * the root).
		 * <p>
		 * Calls unlinkBST().
		 */
		@Override
		public void remove() {
			if (pending == null) {
				throw new IllegalStateException();
			} else {
				current = pending.parent;
				unlinkBST(pending);
			}
		}
	}
}
