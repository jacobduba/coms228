package edu.iastate.jduba.study;

public class Mar3rd2022 {
    public static void main(String[] args) {
        int[] arr = {4, 1, 9, 6, 7, 2};
        quickSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void quickSort(int[] arr) {
        quickSortRec(arr, 0 , arr.length - 1);
    }

    public static void quickSortRec(int[] arr, int first, int last) {
        if (first >= last) return;
        int p = partition(arr, first, last);
        partition(arr, first, p - 1);
        partition(arr, p + 1, last);
    }

    public static int partition(int[] arr, int first, int last) {
        int pivot = arr[last];
        int i = first - 1;
        for (int j = first; j < last; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, last);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
