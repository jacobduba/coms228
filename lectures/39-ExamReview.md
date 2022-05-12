# Final Exam

## Topics

* Stacks 
* Postfix infix
* Queues, Trees > lots of info, trees with more than two children (ex three children)
    * Pre, in, post, level order > need to know
    * Binary property (left smaller than root, right is larger)
    * Splay trees?
* Map (abstract set of methods) >> BST, hashtable (treemap)
    * Hash table (chaining, open addressing with linear probing)
* Binary heap >> Datatype 
    * precolateUp()
    * precolateDown()
    * Heapify (linear time)
    * Heap sort
* Adjacency list
* Adjacency map
* BFS (what you can use them for, shortest path)
* DFS (finding cycles, topological sort)
* List data structures (and their methods: pseudocode and java code without looking-–active recall)

No code for SplayTree, but you may have to write BST code! e.g. predecessor, successor, unlinkNode.

No Big-O questions on the final.

You will only need to solve a postfix expression.

## Question 1 (Splay Trees)

> "The height (of a tree) is in edges, not vertices."

Remember, when zigzig-ing to rotate the parent, and then the current. 
However, when zig-en and zigzag-ing you only work with current.

`remove()` removes the element and splays at parent.

## Question 2

Acyclic graph: Graph w/h no cycles

Topological sort of an acyclic graph: produces a list of the graphs so that dependencies come first.
Nodes that have no requirements go first.
If a node is not connected, it goes VERY first.

## Question 3

Min-heap and max-heap are exactly the same: execpt for the conditionals!

```java
public void heapify(int[] arr) {
    for (int i = arr.length / 2 - 1; i >= 0; i--) {
        percolateDown(arr, i);
    }
}

public void percolateDown(int[] arr, int index) {
    int swapIndex = 2 * index + 1; // acquire left child
    while (swapIndex < arr.length) {
        int rightIndex = 2 * index + 2; // Check right child
        if (rightIndex < arr.length) { 
            if (arr[rightIndex] < arr[swapIndex]) { // This changes for maxHeap
                swapIndex = rightIndex;
            }
        }
        if (arr[index] > arr[swapIndex]) { // This changes for maxHeap
            int temp = arr[index];
            arr[swapIndex] = arr[index];
            arr[index] = temp;
            index = swapIndex;
            swapIndex = 2 * index + 1;
        } else { break; }
    }
}
```

For reference:

```java
public void percolateUp(int[] data, int current) {
    int parent = (current - 1) / 2;
    while (current > 0 && data[current] < data[parent]) { // Changes for maxHeap
        int temp = data[current];
        data[current] = data[parent];
        data[parent] = temp;
        current = parent;
        parent = (current - 1) / 2;
    }
}
```

## Question 4

**Back edges** go from a vertex to one of its ancestors.

> "Another back edge, another *loop*"

The opposite of **back edges** are **tree edges**.

## Question 5

```
int[] findComponents(Graph g):
    for each vertex u in G.V
        u.color = WHITE
        u.prev = NULL
            u.comp = -1
        componentNumber = 1;
    
    for each vertex u in G.V
        componentNumber++
        if u.color == WHITE
            u.comp = componentNumber;
            DFSVisit(G, u)
        return comp

DFSVisit(G, u):
    u.color = GRAY
    for each vertex v adjacent to u
        if v.color == WHITE
            v.prev = u
                v.comp = componentNumber
            DFSVisit(G, v)
    u.color = BLACK
```

This question will not be on the exam, but he mentioned something else like "cycle detection" will show up on the exam.

The solution to cycle detection is just if you detect a back edge, there is a cycle.

Just for reference, the BSF algo for finding the shortest distance

```
BFS(G, s):
    let Q be an empty queue
    foreach node v in G except s
        color(v) = green
        dist(v) = INFINITY
        pred(v) = null
    color(s) = red
    dist(s) = 0
    pred(s) = null
    Q.enqueue(s)

    while(!Q.isEmpty())
        let u = Q.front()
        foreach neighbor v of u:
            if color(v) == green:
                color(v) = red
                dist(v) = dist(u) + 1
                pred(v) = u
                Q.enqueue(v)
            Q.dequeue()
            color(u) = black
        return dist
```