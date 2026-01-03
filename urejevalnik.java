import java.util.Scanner;

@SuppressWarnings("all")
class Array{
    private int[] content;
    private int size;
    private int brake;


    public Array() {
        this.content = new int[10];
        this.size = 0;
        this.brake = 0;
    }

    public void add(int x) {
        if (this.size == this.content.length) {
            int[] newSize = new int[this.content.length * 2];
            for (int i = 0; i < this.content.length; i++) {
                newSize[i] = this.content[i];
            }
            this.content = newSize;
        }
        this.content[this.size] = x;
        this.size++;
    }        

    public int get(int i) {
        return this.content[i];
    }   

    public int size() {
        return this.size;
    }

    public void set(int i, int x) {
        this.content[i] = x;
    }

    public int brake() {
        return this.brake;
    }

    public void setbrake(int brake) {
        this.brake = brake;
    }

    public void incrementbrake() {
        this.brake++;
    }

    public void decrementbrake() {
        this.brake--;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.content.length;
    }

    public void clear() {
        this.size = 0;
    }

    public int[] getContent() {
        return this.content;
    }

    public int max() {
        int max = this.content[0];
        for (int i = 1; i < this.size; i++) {
            if (this.content[i] > max) {
                max = this.content[i];
            }
        }
        return max;
    }

    public int min() {
        int min = this.content[0];
        for (int i = 1; i < this.size; i++) {
            if (this.content[i] < min) {
                min = this.content[i];
            }
        }
        return min;
    }



    @Override
    public String toString() {
        String niz = "";
        for (int i = 0; i < this.size; i++) {

            niz = niz + this.content[i];
            
            if (i != this.size - 1){
                 niz = niz + " ";
            }
                
            if (this.brake != 0 && i + 1 == this.brake) {
                if (i == this.size - 1){
                    niz = niz + " ";
                }
                niz = niz + "|";
                if (i != this.size - 1){
                    niz = niz + " ";
                }
            }
        }
        return niz;
    }

}

public class urejevalnik {
    static boolean up = true;
    static boolean trace = true;
    static boolean debug = true;
    static int comp = 0;
    static int steps = 0;

    public static void insertionSort(Array arr) {
            System.out.println(arr.toString());
        for (int i = 1; i < arr.size(); i++) {
            arr.setbrake(i);
            int key = arr.get(i);
            int j = i - 1;

            while (j >= 0 && ((up && arr.get(j) > key) || (!up && arr.get(j) < key))) {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
            }
            arr.set(j + 1, key);
            arr.incrementbrake();
            if (trace) {
                System.out.println(arr.toString());
            }
        }
    }

    public static void selectionSort(Array arr){
        System.out.println(arr.toString());
        for (int i = 0; i < arr.size() - 1; i++) {
            arr.setbrake(i);
            int min_idx = i;
            for (int j = i + 1; j < arr.size(); j++) {
                if ((up && arr.get(j) < arr.get(min_idx)) || (!up && arr.get(j) > arr.get(min_idx))) {
                    min_idx = j;
                }
            }
            int temp = arr.get(min_idx);
            arr.set(min_idx, arr.get(i));
            arr.set(i, temp);
            arr.incrementbrake();
            if (trace) {
                System.out.println(arr.toString());
            }
        }
    }

    public static void bubbleSort(Array arr){
        System.out.println(arr.toString());
        arr.setbrake(1);
        

        for (int i = 0; i < arr.size() - 1; i++) {
            int swop = arr.size() - 2;
            for (int j = arr.size() - 1; j > i; j--) {
                if ((up && arr.get(j - 1) > arr.get(j)) || (!up && arr.get(j - 1) < arr.get(j))) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j - 1));
                    arr.set(j - 1, temp);
                    swop = j - 1;
                    if(debug) System.out.println("swap " + arr.get(j) + " and " + arr.get(j - 1));
                }

                else {
                    if(debug) System.out.println("skip");
                }
            }

        if(i < swop){
            i = swop;
        }

        arr.setbrake(i + 1);
            
        if (trace) {
            System.out.println(arr.toString());
        }
        }
    }

    public static void makeHeap(Array arr, int n, int i){
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && ((up && arr.get(left) > arr.get(largest)) || (!up && arr.get(left) < arr.get(largest)))) {
            largest = left;
        }

        if (right < n && ((up && arr.get(right) > arr.get(largest)) || (!up && arr.get(right) < arr.get(largest)))) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, swap);

            makeHeap(arr, n, largest);
        }
    }

    public static void heapSort(Array arr){
        System.out.println(arr.toString());
        int n = arr.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            makeHeap(arr, n, i); 
        }
        arr.setbrake(n);
        if (trace) {
            System.out.println(arr.toString());
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr.get(0);
            arr.set(0, arr.get(i));
            arr.set(i, temp);

            makeHeap(arr, i, 0);
            arr.setbrake(i);
            if (trace) {
                System.out.println(arr.toString());
            }
        }
    }

    public static void quickSort(Array arr, int low, int high){
        if (low < high) {
            // choose first element as pivot
            int pivotIndex = low;
            int pivot = arr.get(pivotIndex);
            // print the pivot surrounded by bars before partitioning
            // move pivot to end so existing partition logic (pivot at high) can be reused
            arr.set(pivotIndex, arr.get(high));
            arr.set(high, pivot);
            if (debug) {
                System.out.println("Choosing pivot: " + pivot);
            }

            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if ((up && arr.get(j) <= pivot) || (!up && arr.get(j) >= pivot)) {
                    i++;
                    if (debug) {
                        System.out.println("Swapping " + arr.get(i) + " and " + arr.get(j));
                    }
                    int temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, temp);
                }
            }
            int temp = arr.get(i + 1);
            arr.set(i + 1, arr.get(high));
            arr.set(high, temp);
            int pi = i + 1;

            arr.setbrake(pi);
            if (trace) {
                System.out.println(toStringWithPivotBars(arr, pi));
            }

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static String toStringWithLineMerge(Array arr, int left, int mid, int right) {
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            if (i == mid + 1) {
                sb.append("| ");
            }
            sb.append(arr.get(i)).append(" ");
        }
        return sb.toString().trim();
    }

    static String toStringMerge(Array arr, int left, int right) {
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            sb.append(arr.get(i)).append(" ");
        }
        return sb.toString().trim();
    }

    static String toStringWithPivotBars(Array arr, int pivotIndex) {
        StringBuilder sb = new StringBuilder();
        int n = arr.size();
        for (int i = 0; i < n; i++) {
            if (i == pivotIndex) {
                sb.append("| ");
            }
            sb.append(arr.get(i));
            if (i == pivotIndex) {
                sb.append(" |");
            }
            if (i != n - 1) sb.append(" ");
        }
        return sb.toString();
    }

    
    
    public static void merge(Array arr, int left, int mid, int right){
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = arr.get(left + i);
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr.get(mid + 1 + j);
        }
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if ((up && L[i] <= R[j]) || (!up && L[i] >= R[j])) {
                arr.set(k, L[i]);
                i++;
            } else {
                arr.set(k, R[j]);
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr.set(k, L[i]);
            i++;
            k++;
        }
        while (j < n2) {
            arr.set(k, R[j]);
            j++;
            k++;
        }
    }
    

    public static void mergeSort(Array arr, int left, int right){
        if (left < right) {
            int mid = (left + right) / 2;
            
            if(trace) {
                System.out.println(toStringWithLineMerge(arr, left, mid, right));
            }

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
            arr.setbrake(0);
            if (trace) {
                System.out.println(toStringMerge(arr, left, right));
            }
        }
    }

    public static void countingSort(Array arr, int exp){
        int n = arr.size();
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            count[(arr.get(i) / exp) % 10]++;
        }
        if (up) {
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                int d = (arr.get(i) / exp) % 10;
                output[count[d] - 1] = arr.get(i);
                count[d]--;
            }
        } else {
            for (int i = 8; i >= 0; i--) {
                count[i] += count[i + 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                int d = (arr.get(i) / exp) % 10;
                output[count[d] - 1] = arr.get(i);
                count[d]--;
            }
        }
        for (int i = 0; i < n; i++) {
            arr.set(i, output[i]);
        }
    }

    public static void radixSort(Array arr){
        int max = arr.max();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
            arr.setbrake(0);
            if (trace) {
                System.out.println(arr.toString());
            }
        }
    }

    public static void bucketSort(Array arr){
        int n = arr.size();
        if (n <= 0) return;

        // use n/2 buckets as requested (at least 1)
        int bucketCount = Math.max(1, n / 2);

        Array[] buckets = new Array[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new Array();
        }

        // compute bucket index using range between min and max
        int minValue = arr.min();
        int maxValue = arr.max();
        int range = maxValue - minValue + 1;

        for (int i = 0; i < n; i++) {
            int val = arr.get(i);
            int bucketIndex = (int) ((long)(val - minValue) * bucketCount / range);
            if (bucketIndex < 0) bucketIndex = 0;
            if (bucketIndex >= bucketCount) bucketIndex = bucketCount - 1;
            buckets[bucketIndex].add(val);
        }

        // print sequence after distribution with '|' between buckets
        if (trace) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bucketCount; i++) {
                for (int j = 0; j < buckets[i].size(); j++) {
                    sb.append(buckets[i].get(j));
                    if (!(i == bucketCount - 1 && j == buckets[i].size() - 1)) sb.append(" ");
                }
                if (i != bucketCount - 1) sb.append("| ");
            }
            System.out.println(sb.toString().trim());
        }

        // perform insertion sort on all buckets simultaneously: do one outer iteration per bucket per pass
        int maxBucketSize = 0;
        for (int i = 0; i < bucketCount; i++) if (buckets[i].size() > maxBucketSize) maxBucketSize = buckets[i].size();

        for (int pass = 1; pass < maxBucketSize; pass++) {
            // perform one insertion outer iteration for each bucket if possible
            for (int b = 0; b < bucketCount; b++) {
                Array bucket = buckets[b];
                if (bucket.size() > pass) {
                    int key = bucket.get(pass);
                    int j = pass - 1;
                    while (j >= 0 && ((up && bucket.get(j) > key) || (!up && bucket.get(j) < key))) {
                        bucket.set(j + 1, bucket.get(j));
                        j--;
                    }
                    bucket.set(j + 1, key);
                }
            }

            // after completing this pass for all buckets, print combined array
            if (trace) {
                StringBuilder sb = new StringBuilder();
                for (int b = 0; b < bucketCount; b++) {
                    Array bucket = buckets[b];
                    int sortedCount = Math.min(pass + 1, bucket.size());
                    for (int k = 0; k < sortedCount; k++) {
                        sb.append(bucket.get(k));
                        sb.append(" ");
                    }
                    if (sortedCount < bucket.size()) {
                        sb.append("| ");
                        for (int k = sortedCount; k < bucket.size(); k++) {
                            sb.append(bucket.get(k));
                            sb.append(" ");
                        }
                    }
                    if (b != bucketCount - 1) sb.append("| ");
                }
                System.out.println(sb.toString().trim());
            }
        }

        // flatten buckets back into arr
        arr.clear();
        for (int i = 0; i < bucketCount; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr.add(buckets[i].get(j));
            }
        }
        arr.setbrake(0);
        if (trace) {
            System.out.println(arr.toString());
        }
    }




    public static void main(String[] args) {
            try {
                String[] inst = new String[3];
                Array order = new Array();
                String line, niz;
                Scanner scn_one, scn_two;

                scn_one = new Scanner(System.in);
                line = scn_one.nextLine();
                scn_two = new Scanner(line);
                for (int i = 0; scn_two.hasNext() && i < inst.length; i++) {
                    String ukaz = scn_two.next();
                    inst[i] = ukaz;
                }
                scn_two.close();

                line = scn_one.nextLine();
                scn_two = new Scanner(line);
                while (scn_two.hasNext()) {
                    String n = scn_two.next();
                    order.add(Integer.parseInt(n));
                }
                scn_two.close();
                scn_one.close();

                if(inst[0].equals("count")){
                    trace = false;
                }
                if (inst[2].equals("down")){
                    up = false;
                }

                switch(inst[1]){
                    case "insert":
                        if (trace){
                            insertionSort(order);
                        }
                        break;

                    case "select":
                        if (trace){
                            selectionSort(order);
                        }
                        break;

                    case "bubble":
                        if (trace){
                            bubbleSort(order);    
                        }
                        break;

                    case "heap":
                        if (trace){
                            heapSort(order);
                        }
                        break;

                    case "merge":
                        if (trace){
                            System.out.println(order.toString());
                            mergeSort(order, 0, order.size() - 1);
                        }
                        break;

                    case "quick":
                        if (trace){
                            System.out.println(order.toString());
                            quickSort(order, 0, order.size() - 1);
                        }
                        break;

                    case "radix":
                        if (trace){
                            System.out.println(order.toString());
                            radixSort(order);
                        }
                        break;

                    case "bucket":
                        if (trace){
                            System.out.println(order.toString());
                            bucketSort(order);
                        }
                        break;

                    default:
                        throw new Exception("Unknown sorting algorithm: " + inst[1]);
                }
            }
            catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
    }
}
