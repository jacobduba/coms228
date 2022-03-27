package Mar22.SortingWithGenerics;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;

public class Sorter<E extends Comparable<? super E>> {
    public void mergeSort(E[] a, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l) / 2;

        mergeSort(a, l, m);
        mergeSort(a, m + 1, r);

        merge(a, l, m , r);
    }

    public void merge(E[] a, int l, int m, int r) {
        int p = m - l + 1;
        int q = r - m;

        // Create two temporary arrays for left and right
        E[] L = (E[]) new Comparable[p];
        E[] R = (E[]) new Comparable[q];
        for (int i = 0; i < p; i++)
            L[i] = a[l + i];
        for (int j = 0; j < q; j++)
            R[j] = a[m + 1 + j];

        int i = 0; // Index tracking left side
        int j = 0; // Index tracking right side
        int k = l; // Index tracking where we're at in the original array

        while (i < p && j < q) {
            if (L[i].compareTo(R[j]) <= 0) { // The <= keeps it stable
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < p) {
            a[k] = L[i];
            i++; k++;
        }
        while (j < q) {
            a[k] = R[j];
            j++; k++;
        }
    }

    public void quickSort(E[] arr, int first, int last) {
        if (first >= last) return;
        int p = partition(arr, first, last);
        quickSort(arr, first, p - 1);
        quickSort(arr, p + 1, last);
    }

    public int partition(E[] arr, int first, int last) {
        E pivot = arr[last];
        int i = first - 1;
        for (int j = first; j < last; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                E temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        E temp = arr[i + 1];
        arr[i + 1] = arr[last];
        arr[last] = temp;
        return i + 1;
    }
}