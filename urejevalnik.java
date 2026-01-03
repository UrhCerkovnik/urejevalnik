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

    public static void mergeSort(Array arr, int left, int right){
        // Implementation of merge sort
    }

    public static void quickSort(Array arr, int low, int high){
        // Implementation of quick sort
    }

    public static void heapSort(Array arr){
        // Implementation of heap sort
    }

    public static void radixSort(Array arr){
        // Implementation of radix sort
    }

    public static void countingSort(Array arr){
        // Implementation of counting sort
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
                            mergeSort(order, 0, order.size() - 1);
                        }
                        break;

                    case "merge":
                        if (trace){
                            quickSort(order, 0, order.size() - 1);
                        }
                        break;

                    case "quick":
                        if (trace){
                            heapSort(order);
                        }
                        break;

                    case "radix":
                        if (trace){
                            radixSort(order);
                        }
                        break;

                    case "bucket":
                        if (trace){
                            countingSort(order);
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
