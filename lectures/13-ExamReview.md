# COM S 228 Review Session

## Problem 1

```
Arthimetic (Interface)---------
|                             |
CPU (Abs)---                Hands
|          |
X86       Arm
|
AMD64
```

Runtime Exception - most likely ClassCastException because the compiler was unable to catch an error.

We want to know if the student understands how the compiler catches errors.

1. getCores gives `core*2 = 4*2 = 8`
2. add(5,7) gives `(5+7) % 10 = 12 % 10 = 2`
3. add(2, 3) uses ARM's add which adds 1 to the result, so `2+3+1 = 6`
4. Compiler catcers trying to cast to a non-descendant or non-ancestor, with the error:
Compiler time error: java: incompatible types: X86 cnanot be converted to Hands.
5. Fooled the compiler by upcasting to a a parent, then downcasting to a sibling.
But the runtime cannot be fooled and will throw a ClassCastException.
6. We cast through the parent to a sibling, but the runtime realized the incompatiblity, so it threw a ClassCastException.
7. CPU is abstract, cannot be instantiated.
8. 3 frequency + 2 cores = 5

> The takeaway: casting to a sibling directly will be caught by the Compiler.
Casting to a parent, then a sibling will fool the compiler BUT will cause a ClassCastException.

## Problem 2

```java
public void myLoops(int n) {
	int accumulator = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j = j*2) {
			for (int k = 0; k < 219; k++) {
				accumulator += i + j - k;
			}
		}
	}
}
```

* Loop 1 is definitely O(n) because it goes from 0 to n-1 1 step at a time.
* Intermediate loop is O(infinity).
* Inner loop is O(1).

> Total: O(infinity)

## Problem 3

Write an equals() method

1. @Override on top because it overrides Object.equals()
2. Pass in object o as paramater
3. Check if the object is null or o.getClass() != this.getClass().
If either is true, we return false.
4. Cast o to Fragrance: `Fragrance other = (Fragrance) o;`

```java
@Override
public boolean equals(Object o) {
	if (o == null || o.getClass() != getClass())
		return false;
	Fragrance other = (Fragrance) o;
	if (!name.equals(other.name) || !manufacturer.equals(other.manugacturer)
	|| !(ingredients.length == other.ingredients.length))
		return false;
	else {
		for (int i = 0; i < ingredients.length; i++) {
			boolean found = false;
			for (int j = 0; j < ingredients.length; j++) {
				found = true;
			}
			if (!found) return false;
		}
	}
	return true;
}
```

## Problem 4:

Will be some kind of sorting numbers with a certain number of steps,
has to be a Selection or Insert sort or it could be a clone() method.

```
5 12 1 3 19 18 17 <--- Sort this with selection sort
1 12 5 3 19 18 17
etc...
```

## Problem 5:

This problem will have some code that we studied in class.

 - Selection sort implementation
 - Insertion sort implementation
 - Finding if one array's elements are present in another array
 - Write a sorting method with Comparator or comparable
 - ETC

 ## How to implement a clone method:

 1. Implement Cloneable
 2. @Override clone()
 3. Call super.clone() inside your clone.
 	- This shallow copies over all instance variables.
 4. Create new objects if object variables were copied.
 	- Copy each object "recursively", e.g. new array of coordinates `coords = new Coordinate[3]` to prevent "conjoined objects"
 5. Wrap the implementation in a try-catch block like this:

 ```java
try {
	super.clone();
	// Other commaCan you go over witting a clone() method?nds
} catch (CloneNotSupportedException e) {
	// Nothing here.
}
 ```
