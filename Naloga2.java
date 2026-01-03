package domace_naloge.DN2;

import java.util.Scanner;

@SuppressWarnings("all")
class ResizableArray {
    private int[] array;
    private int size;
    private int line;

    public ResizableArray() {
        this.array = new int[10];
        this.size = 0;
        this.line = 0;
    }

    public void add(int element) {
        if (this.size == this.array.length) {
            resize();
        }
        this.array[size] = element;
        this.size++;
    }

    public void clear() {
        for (int i = 0; i < size; i++) array[i] = 0;
        this.size = 0;
    }

    private void resize() {
        int newSize = this.array.length * 2;
        int[] newArray = new int[newSize];
        System.arraycopy(this.array, 0, newArray, 0, this.array.length);
        this.array = newArray;
    }

    public int get(int index) {
        return this.array[index];
    }

    public void set(int index, int element) {
        this.array[index] = element;
    }

    public void swap(int index1, int index2) {
        int tmp = this.array[index1];
        this.array[index1] = this.array[index2];
        this.array[index2] = tmp;
    }

    public boolean equals(ResizableArray other) {
        if (this.size != other.size) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (this.array[i] != other.array[i]) {
                return false;
            }
        }
        return true;
    }

    public ResizableArray copy() {
        ResizableArray newArray = new ResizableArray();
        for (int i = 0; i < this.size; i++) {
            newArray.add(this.array[i]);
        }
        return newArray;
    }

    public int max() {
        int maxValue = this.array[0];
        for (int i = 1; i < this.size; i++) {
            if (this.array[i] > maxValue) {
                maxValue = this.array[i];
            }
        }
        return maxValue;
    }

    public int min() {
        int minValue = this.array[0];
        for (int i = 1; i < this.size; i++) {
            if (this.array[i] < minValue) {
                minValue = this.array[i];
            }
        }
        return minValue;
    }

    public void setLine(int n) {
        this.line = n;
    }

    public int size() {
        return this.size;
    }

    public int[] getArray() {
        return this.array;
    }

    @Override
    public String toString() {
        String niz = "";
        for (int i = 0; i < this.size; i++) {
            niz += this.array[i];
            if (i != this.size - 1) niz += " ";
            if (this.line != 0 && i + 1 == this.line) {
                if (i == this.size - 1) niz += " ";
                niz += "|";
                if (i != this.size - 1) niz += " ";
            }
        }
        return niz;
    }
}

@SuppressWarnings("all")
public class Naloga2 {
    static boolean sortUP = true;
    static boolean trace = true;

    static int compare = 0;
    static int move = 0;

    static void insertionSort(ResizableArray zaporedje) {
        move = 0;
        compare = 0;

        if (trace) System.out.println(zaporedje.toString());

        int n = zaporedje.size();
        for (int i = 1; i < n; i++) {
            int key = zaporedje.get(i); move++;
            int j = i;

            while (j > 0) {
                compare++;
                if (sortUP ? zaporedje.get(j - 1) > key : zaporedje.get(j - 1) < key) {
                    zaporedje.set(j, zaporedje.get(j - 1)); move++;
                    j--;
                    compare++;
                }
                else {
                    compare++;
                    break;
                }
            }

            zaporedje.set(j, key); move++;

            zaporedje.setLine(i + 1);
            if (trace) System.out.println(zaporedje.toString());
        }
        compare /= 2;
    }

    static void selectionSort(ResizableArray zaporedje) {
        move = 0;
        compare = 0;

        if (trace) System.out.println(zaporedje.toString());

        int n = zaporedje.size();
        for (int i = 0; i < n - 1; i++) {
            int m = i;

            for (int j = i + 1; j < n; j++) {
                compare++;
                if (sortUP ? zaporedje.get(j) < zaporedje.get(m) : zaporedje.get(j) > zaporedje.get(m)) {
                    m = j;
                }
            }
            zaporedje.swap(i, m);
            move += 3;

            zaporedje.setLine(i + 1);
            if (trace) System.out.println(zaporedje.toString());
        }
    }

    static void bubbleSort(ResizableArray zaporedje) {
        move = 0;
        compare = 0;

        if (trace) System.out.println(zaporedje.toString());

        int n = zaporedje.size();
        for (int i = 0; i < n - 1; i++) {
            int lastSwitch = n - 2;
            for (int j = n - 1; j > i; j--) {
                compare++;
                if (sortUP ? zaporedje.get(j - 1) > zaporedje.get(j) : zaporedje.get(j - 1) < zaporedje.get(j)) {
                    zaporedje.swap(j, j - 1);
                    lastSwitch = j - 1;
                    move += 3;
                }
            }

            if (i < lastSwitch) i = lastSwitch;

            zaporedje.setLine(i + 1);

            if (trace) System.out.println(zaporedje.toString());
        }
    }

    static void heapify(ResizableArray zaporedje, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n) {
            compare++;
            if (sortUP ? zaporedje.get(l) > zaporedje.get(largest) : zaporedje.get(l) < zaporedje.get(largest)) {
                largest = l;
            }
        }

        if (r < n) {
            compare++;
            if (sortUP ? zaporedje.get(r) > zaporedje.get(largest) : zaporedje.get(r) < zaporedje.get(largest)) {
                largest = r;
            }
        }

        if (largest != i) {
            zaporedje.swap(i, largest);
            move += 3;

            heapify(zaporedje, n, largest);
        }
    }

    static void heapSort(ResizableArray zaporedje) {
        move = 0;
        compare = 0;

        if (trace) System.out.println(zaporedje.toString());

        int n = zaporedje.size();

        for (int i = n / 2 - 1; i >= 0; i--) heapify(zaporedje, n, i);

        zaporedje.setLine(n);
        if (trace) System.out.println(zaporedje.toString());

        for (int i = n - 1; i > 0; i--) {
            zaporedje.swap(i, 0);
            move += 3;

            heapify(zaporedje, i, 0);

            zaporedje.setLine(i);
            if (trace) System.out.println(zaporedje.toString());
        }
    }

    static String toStringWithLineMerge(ResizableArray zaporedje, int left, int mid, int right) {
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            if (i == mid + 1) {
                sb.append("| ");
            }
            sb.append(zaporedje.get(i)).append(" ");
        }
        return sb.toString().trim();
    }

    static String toStringMerge(ResizableArray zaporedje, int left, int right) {
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            sb.append(zaporedje.get(i)).append(" ");
        }
        return sb.toString().trim();
    }

    static void merge(ResizableArray zaporedje, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(zaporedje.getArray(), left, L, 0, n1);
        System.arraycopy(zaporedje.getArray(), mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            compare++;
            if (sortUP ? L[i] <= R[j] : L[i] >= R[j]) {
                zaporedje.set(k++, L[i++]);
                move += 2;
            }
            else {
                zaporedje.set(k++, R[j++]);
                move += 2;
            }
        }

        while (i < n1) {
            zaporedje.set(k++, L[i++]);
            move += 2;
        }
        while (j < n2) {
            zaporedje.set(k++, R[j++]);
            move += 2;
        }
    }

    static void mergeSort(ResizableArray zaporedje, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            if (trace) System.out.println(toStringWithLineMerge(zaporedje, left, mid, right));

            mergeSort(zaporedje, left, mid);
            mergeSort(zaporedje, mid + 1, right);

            merge(zaporedje, left, mid, right);

            if (trace) System.out.println(toStringMerge(zaporedje, left, right));
        }
    }

    static String toStringQuick(ResizableArray zaporedje, int left, int right, int pivotIndex) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < zaporedje.size(); i++) {
            if (i == pivotIndex) sb.append("| ");
            if (i >= left && i <= right) {
                sb.append(zaporedje.get(i)).append(" ");
            }
            if (i == pivotIndex) sb.append("| ");
        }

        return sb.toString().trim();
    }

    static int partition(ResizableArray zaporedje, int left, int right) {
        int pivot = zaporedje.get(left);
        int l = left;
        int r = right + 1;

        while (true) {
            do {
                l++;
                compare++;
            } while (l < right && (sortUP ? zaporedje.get(l) < pivot : zaporedje.get(l) > pivot));

            do {
                r--;
                compare++;
            }
            while (sortUP ? zaporedje.get(r) > pivot : zaporedje.get(r) < pivot);

            if (l >= r) break;

            zaporedje.swap(l, r);
            move += 3;
        }

        zaporedje.swap(left, r);
        move += 3;

        return r;
    }

    static void quickSort(ResizableArray zaporedje, int left, int right) {
        if (left >= right) return;
        int r = partition(zaporedje, left, right);
        move++;

        if (trace) System.out.println(toStringQuick(zaporedje, left, right, r));

        quickSort(zaporedje, left, r - 1);
        quickSort(zaporedje, r + 1, right);
    }

    static void countSort(ResizableArray zaporedje, int exp) {
        int n = zaporedje.size();
        int output[] = new int[n];
        int count[] = new int[10];
        for (int i = 0; i < count.length; i++) count[i] = 0;

        for (int i = 0; i < n; i++) {
            count[(zaporedje.get(i) / exp) % 10]++;
            compare++;
        }

        if (sortUP) {
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
        } else {
            for (int i = 8; i >= 0; i--) {
                count[i] += count[i + 1];
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(zaporedje.get(i) / exp) % 10] - 1] = zaporedje.get(i);
            count[(zaporedje.get(i) / exp) % 10]--;
            compare++;
        }

        for (int i = 0; i < n; i++) {
            zaporedje.set(i, output[i]);
            move += 2;
        }
    }

    static void radixSort(ResizableArray zaporedje) {
        move = 0;
        compare = 0;

        if (trace) System.out.println(zaporedje.toString());

        int m = zaporedje.max();

        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(zaporedje, exp);
            if (trace) System.out.println(zaporedje.toString());
        }
    }

    static int insertionSortBucket(ResizableArray zaporedje) {
        int compare = 0;

        int n = zaporedje.size();
        for (int i = 1; i < n; i++) {
            int key = zaporedje.get(i); move++;
            int j = i;

            while (j > 0) {
                compare++;
                if (sortUP ? zaporedje.get(j - 1) > key : zaporedje.get(j - 1) < key) {
                    zaporedje.set(j, zaporedje.get(j - 1)); move++;
                    j--;
                    compare++;
                }
                else {
                    compare++;
                    break;
                }
            }

            zaporedje.set(j, key); move++;

            zaporedje.setLine(i + 1);
            if (trace) System.out.println(zaporedje.toString());
        }
        compare /= 2;
        return compare;
    }

    static void bucketSort(ResizableArray zaporedje) {
        move = 0;
        compare = 0;

        if (trace) System.out.println(zaporedje.toString());

        int n = zaporedje.size();

        int min = zaporedje.get(0), max = zaporedje.get(0);
        for (int i = 1; i < zaporedje.size(); i++) {
            compare += 2;
            if (zaporedje.get(i) < min) {
                compare--;
                min = zaporedje.get(i);
            }
            if (zaporedje.get(i) > max) {
                max = zaporedje.get(i);
            }
        }

        int k = n / 2;
        double v = (double) (max - min + 1) / k;

        ResizableArray[] buckets = new ResizableArray[k];
        for (int i = 0; i < k; i++) buckets[i] = new ResizableArray();

        for (int i = 0; i < n; i++) {
            int bi = (int) Math.floor((zaporedje.get(i) - min) / v);
            buckets[bi].add(zaporedje.get(i));
            move++;
            compare += 2;
        }

        if (trace) {
            if (sortUP) {
                for (int i = 0; i < buckets.length; i++) {
                    if (buckets[i].size() != 0 && i != 0) System.out.print(" ");
                    System.out.print(buckets[i].toString());
                    if (i != buckets.length - 1) System.out.print(" |");
                }
            }
            else {
                for (int i = buckets.length - 1; i >= 0; i--) {
                    if (buckets[i].size() != 0 && i != buckets.length - 1) System.out.print(" ");
                    System.out.print(buckets[i].toString());
                    if (i != 0) System.out.print(" |");
                }
            }
            System.out.println();
        }

        zaporedje.clear();
        if (sortUP) {
            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < buckets[i].size(); j++) {
                    zaporedje.add(buckets[i].get(j));
                    move++;
                }
            }
        }
        else {
            for (int i = buckets.length - 1; i >= 0; i--) {
                for (int j = 0; j < buckets[i].size(); j++) {
                    zaporedje.add(buckets[i].get(j));
                    move++;
                }
            }
        }

        compare += insertionSortBucket(zaporedje);
    }

    public static void main(String[] args) {
        // za lokalno testiranje:
        // bodi v directoriju: C:\Users\JFC\Desktop\APS1\src>
        // javac domace_naloge\DN2\Naloga2.java
        // java domace_naloge.DN2.Naloga2 < domace_naloge\DN2\vhod.txt
        try {
            String[] ukazi = new String[3];
            ResizableArray zaporedje = new ResizableArray();
            String vrstica, niz;
            Scanner sc_v, sc_n;

            sc_v = new Scanner(System.in);
            vrstica = sc_v.nextLine();
            sc_n = new Scanner(vrstica);
            for (int i = 0; sc_n.hasNext() && i < ukazi.length; i++) {
                String ukaz = sc_n.next();
                ukazi[i] = ukaz;
            }

            vrstica = sc_v.nextLine();
            sc_n = new Scanner(vrstica);
            while (sc_n.hasNext()) {
                String n = sc_n.next();
                zaporedje.add(Integer.parseInt(n));
            }

            if (ukazi[0].equals("count")) trace = false;
            if (ukazi[2].equals("down")) sortUP = false;

            switch (ukazi[1]) {
                case "insert":
                    if (trace) insertionSort(zaporedje);
                    else {
                        int[] moveANDcomp = new int[6];

                        insertionSort(zaporedje);
                        moveANDcomp[0] = move; moveANDcomp[1] = compare;

                        insertionSort(zaporedje);
                        moveANDcomp[2] = move; moveANDcomp[3] = compare;

                        sortUP = !sortUP;
                        insertionSort(zaporedje);
                        moveANDcomp[4] = move; moveANDcomp[5] = compare;

                        for (int i = 0; i < moveANDcomp.length; i++) {
                            if (i % 2 == 0 && i != 0) System.out.print("| ");
                            System.out.print(moveANDcomp[i]);
                            if (i != moveANDcomp.length - 1) System.out.print(" ");
                        }
                    }
                    break;
                case "select":
                    if (trace) selectionSort(zaporedje);
                    else {
                        int[] moveANDcomp = new int[6];

                        selectionSort(zaporedje);
                        moveANDcomp[0] = move; moveANDcomp[1] = compare;

                        selectionSort(zaporedje);
                        moveANDcomp[2] = move; moveANDcomp[3] = compare;

                        sortUP = !sortUP;
                        selectionSort(zaporedje);
                        moveANDcomp[4] = move; moveANDcomp[5] = compare;

                        for (int i = 0; i < moveANDcomp.length; i++) {
                            if (i % 2 == 0 && i != 0) System.out.print("| ");
                            System.out.print(moveANDcomp[i]);
                            if (i != moveANDcomp.length - 1) System.out.print(" ");
                        }
                    }
                    break;
                case "bubble":
                    if (trace) bubbleSort(zaporedje);
                    else {
                        int[] moveANDcomp = new int[6];

                        bubbleSort(zaporedje);
                        moveANDcomp[0] = move; moveANDcomp[1] = compare;

                        bubbleSort(zaporedje);
                        moveANDcomp[2] = move; moveANDcomp[3] = compare;

                        sortUP = !sortUP;
                        bubbleSort(zaporedje);
                        moveANDcomp[4] = move; moveANDcomp[5] = compare;

                        for (int i = 0; i < moveANDcomp.length; i++) {
                            if (i % 2 == 0 && i != 0) System.out.print("| ");
                            System.out.print(moveANDcomp[i]);
                            if (i != moveANDcomp.length - 1) System.out.print(" ");
                        }
                    }
                    break;
                case "heap":
                    if (trace) heapSort(zaporedje);
                    else {
                        int[] moveANDcomp = new int[6];

                        heapSort(zaporedje);
                        moveANDcomp[0] = move; moveANDcomp[1] = compare;

                        heapSort(zaporedje);
                        moveANDcomp[2] = move; moveANDcomp[3] = compare;

                        sortUP = !sortUP;
                        heapSort(zaporedje);
                        moveANDcomp[4] = move; moveANDcomp[5] = compare;

                        for (int i = 0; i < moveANDcomp.length; i++) {
                            if (i % 2 == 0 && i != 0) System.out.print("| ");
                            System.out.print(moveANDcomp[i]);
                            if (i != moveANDcomp.length - 1) System.out.print(" ");
                        }
                    }
                    break;
                case "merge":
                    if (trace) {
                        System.out.println(zaporedje.toString());
                        mergeSort(zaporedje, 0, zaporedje.size() - 1);
                    }
                    else {
                        int[] moveANDcomp = new int[6];

                        move = 0; compare = 0;
                        mergeSort(zaporedje, 0, zaporedje.size() - 1);
                        moveANDcomp[0] = move; moveANDcomp[1] = compare;

                        move = 0; compare = 0;
                        mergeSort(zaporedje, 0, zaporedje.size() - 1);
                        moveANDcomp[2] = move; moveANDcomp[3] = compare;

                        sortUP = !sortUP;
                        move = 0; compare = 0;
                        mergeSort(zaporedje, 0, zaporedje.size() - 1);
                        moveANDcomp[4] = move; moveANDcomp[5] = compare;

                        for (int i = 0; i < moveANDcomp.length; i++) {
                            if (i % 2 == 0 && i != 0) System.out.print("| ");
                            System.out.print(moveANDcomp[i]);
                            if (i != moveANDcomp.length - 1) System.out.print(" ");
                        }
                    }
                    break;
                case "quick":
                    if (trace) {
                        System.out.println(zaporedje.toString());
                        quickSort(zaporedje, 0, zaporedje.size() - 1);
                        System.out.println(zaporedje.toString());
                    }
                    else {
                        int[] moveANDcomp = new int[6];

                        move = 0; compare = 0;
                        quickSort(zaporedje, 0, zaporedje.size() - 1);
                        moveANDcomp[0] = move; moveANDcomp[1] = compare;

                        move = 0; compare = 0;
                        quickSort(zaporedje, 0, zaporedje.size() - 1);
                        moveANDcomp[2] = move; moveANDcomp[3] = compare;

                        sortUP = !sortUP;
                        move = 0; compare = 0;
                        quickSort(zaporedje, 0, zaporedje.size() - 1);
                        moveANDcomp[4] = move; moveANDcomp[5] = compare;

                        for (int i = 0; i < moveANDcomp.length; i++) {
                            if (i % 2 == 0 && i != 0) System.out.print("| ");
                            System.out.print(moveANDcomp[i]);
                            if (i != moveANDcomp.length - 1) System.out.print(" ");
                        }
                    }
                    break;
                case "radix":
                    if (trace) radixSort(zaporedje);
                    else {
                        int[] moveANDcomp = new int[6];

                        radixSort(zaporedje);
                        moveANDcomp[0] = move; moveANDcomp[1] = compare;

                        radixSort(zaporedje);
                        moveANDcomp[2] = move; moveANDcomp[3] = compare;

                        sortUP = !sortUP;
                        radixSort(zaporedje);
                        moveANDcomp[4] = move; moveANDcomp[5] = compare;

                        for (int i = 0; i < moveANDcomp.length; i++) {
                            if (i % 2 == 0 && i != 0) System.out.print("| ");
                            System.out.print(moveANDcomp[i]);
                            if (i != moveANDcomp.length - 1) System.out.print(" ");
                        }
                    }
                    break;
                case "bucket":
                    if (trace) bucketSort(zaporedje);
                    else {
                        int[] moveANDcomp = new int[6];

                        bucketSort(zaporedje);
                        moveANDcomp[0] = move; moveANDcomp[1] = compare;

                        bucketSort(zaporedje);
                        moveANDcomp[2] = move; moveANDcomp[3] = compare;

                        sortUP = !sortUP;
                        bucketSort(zaporedje);
                        moveANDcomp[4] = move; moveANDcomp[5] = compare;

                        for (int i = 0; i < moveANDcomp.length; i++) {
                            if (i % 2 == 0 && i != 0) System.out.print("| ");
                            System.out.print(moveANDcomp[i]);
                            if (i != moveANDcomp.length - 1) System.out.print(" ");
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
