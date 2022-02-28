package edu.iastate.jduba.study;

public class Feb24th2022 {
    public static void main(String[] args) {
        int[] arr = {4, 1, 9, 6, 7, 2};
        int[] sortedArr = mergeSort(arr);
        for (int i : sortedArr) {
            System.out.print(i + " ");
        }
    }

    // This took a while... didn't get to Quicksort like I wanted to.

    public static int[] mergeSort(int[] arr) {
        int n = arr.length;
        if (n == 1) return arr;
        int m = n / 2;
        int[] aLeft = new int[m];
        for (int i = 0; i < m; i++) {
            aLeft[i] = arr[i];
        }
        int[] aRight = new int[n - m];
        for (int i = m; i < n; i++) {
            aRight[i - m] = arr[i];
        }
        aLeft = mergeSort(aLeft);
        aRight = mergeSort(aRight);
        return merge(aLeft, aRight);
    }

    private static int[] merge(int[] b, int[] c) {
        int p = b.length, q = c.length;
        int[] d = new int[p + q];
        int i = 0, j = 0;
        while (i < p && j < q) {
            if (b[i] <= c[j]) {
                d[i + j] = b[i];
                i++;
            } else {
                d[i + j] = c[j];
                j++;
            }
        }
        if (i >= p) {
            while (j < q) {
                d[i + j] = c[j];
                j++;
            }
        } else {
            while (i < p) {
                d[i + j] = b[i];
                i++;
            }
        }
        return d;
    }
}
