package Mar22.SortingWithGenerics;

/**
 * author: Jacob Duba
 */
public class Main {
    public static void main(String[] args) {
        Sorter<Circle> sorter = new Sorter<Circle>();
        Circle[] toMergeSort = {
                new Circle(1, 2, 3),
                new Circle(5, 3, 1),
                new Circle(1, 2, 4),
                new Circle(3, 4, 1),
                new Circle(2, 3, 5),
                new Circle(3, 4, 2),
                new Circle(4, 4, 2)
        };

        System.out.println("Array before sorting:");
        for (Circle c : toMergeSort) {
            System.out.println(c);
        }

        sorter.mergeSort(toMergeSort, 0, toMergeSort.length - 1);
        System.out.println("Array after sorting w/h Merge Sort:");
        for (Circle c : toMergeSort) {
            System.out.println(c);
        }

        Circle[] toQuickSort = {
                new Circle(1, 2, 3),
                new Circle(5, 3, 1),
                new Circle(1, 2, 4),
                new Circle(3, 4, 1),
                new Circle(2, 3, 5),
                new Circle(3, 4, 2),
                new Circle(4, 4, 2)
        };

        sorter.quickSort(toQuickSort, 0, toQuickSort.length - 1);
        System.out.println("Array after sorting w/h Merge Sort:");
        for (Circle c : toQuickSort) {
            System.out.println(c);
        }
    }
}
