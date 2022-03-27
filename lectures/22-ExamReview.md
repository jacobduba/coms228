# Exam 2 review

## Question 1 (Quicksort)

Consider the following class with implements quicksort.

```java

public class QuickSort {
    public static void quickSort(int[] arr) {
        quickSortRec(arr, 0, arr.length - 1);
    }

    private static void quickSortRec(int[] arr, int first, int last) {
        if (first >= last)
            return;
        int p = partition(arr, first, last);
        quickSortRec(arr, first, last);
        quickSortRec(arr, p + 1, last);
    }

    private static partition(int[] arr, int first, int last) {
        /* Adapt the partition algorithm from class to use the largest odd element */

    public static boolean isOdd(int a) {
        return 
}
```

Answer

```java
int index = -1; // set to -1 because we have no discovered odd.

for (int i = first; i <= last; i++) {
    if (isOdd(arr[i])
        if (index == -1 || arr[i] > arr[index])
            index = 1;
    if (index == -1)
        index = first; // index is now the first index
    swap(arr, index, last);
}
```

What's the worst-case runtime, in terms of big-O, of the partition() method?

Answer: $O(n) + O(n) = O(n)$

What is the worst case runetime in terms of big-O, of the quickSort() method?

Answer: $O(n^2)$ When array is made of sorted odd numbers.

Supposed we wanted to make our sorter generic so that it works for arrays of type T, provided T is comparable.

```java
public static <T extends Comparable<? super T> void quickSort(T arr);
```

`T extends Comparable<? super T>` means:
"Only accept classes T that have Comparable implemented and the Comparable may be implemented in an ancesotr of T"
e.g. Insect implemenets comparable BUT Bee does not implement comparable, so Bee should be able to use comparable.

## Question 2

```java

iter = aList.listIterator(3);
iter.next();
iter.previous();
iter.remove();
System.out.println(iter.previousIndex());
```

```
P Q R | S T
P Q R | T
```

`Add()` from the iterator ALWAYS adds to the LEFT of the cursor.
This increments the cursor's index.

`set()` and `remove()` depend on wether `next()` or `previous()` were called:
if nothing was called after `next()`/`previous()`, you cannot call it a second time.

`set` can be called multiple times as long as next or previous called at some point in the past BUT

`add()` sets direction to NONE!

`next()`
`add()` 
`remove()` or `set()`

Exceptions that can be thrown:
Last exam:
* `ClassCastException` when the inheritance did not work correctly.
* `NullPointerException` when you call a method on a object reference that is empty - for Exam 1 that was `startDate.equals()` could throw `NullPointerException` if startDate was no assigned.

For Exam 2:
* `IllegalStateException` Delete or set when next() or previous() was not called,
* `NoSuchElementException` if you want to call next() when hasNext() is false.
* `IndexOutOfBoundsException` If we want to set the iterator

## Question 5

Sorting stability:

If we have duplicate elements, after sorting the duplicate elements remain in the same original order:

```
Alice   Bob   Cindy   
```


Sort the age in increasing order:

Two sorting orders:

```
19   20    20    21
Dan  Ciny 
```

fFUKC 

Merlot Omaha 2019
Zinfandel Sacramento 2020
Rose Canberra 2021
Merlot Austin 2019
Riesling Detriot 2018
Rose Aberdeen 2021
Zinfandel Alanta 2020
Riesling Boston 2018

Provide a sorted order for the wines by year only. STABLE

Answer:
Reisling Detriot 2019
Reilsing Bostom 2018

## Question 6

Stack has push(E item) which puts an element on top of the stack
E pop() which takes the top most element from the stack.

The above is analogous to a gun magazine that is top loaded.

push() should add at the head or tail

pop() should remove() from wherever add() added to

List methods: always think about booking = index, direction, and size need to be mantained.