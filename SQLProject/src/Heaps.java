import java.util.*; //import for arraylist
public class Heaps<E extends Comparable<E>> { //type generic, implement comparable interface for heaps
    private ArrayList<E> unsortedArray; //initalize the initially unsorted array



    public ArrayList<E> sortUsingHeaps(ArrayList<E> unsortedArray) { //constructor that accepts array list input
        int currSize = unsortedArray.size()-1; //current size of arraylist - 1
        for(int i = currSize/2;i>=0;i--) { //for half the array list (binary)
            Heapify(unsortedArray,currSize+1,i); //heapify it to order it
        }

        for(int i = currSize;i>=0;i--) { //for the entire array list
            swap(0,i,unsortedArray); //swap the places to switch the order (min vs max)
            Heapify(unsortedArray,i,0); //heapify it to order it again
        }
        return unsortedArray; //return the now sorted array list



    }

    private void swap(int a, int b, ArrayList<E> tempArray) {
        E temp = tempArray.get(a); //change the positions of a and b in the arraylist
        tempArray.set(a, tempArray.get(b));
        tempArray.set(b, temp);
    }

    private void Heapify(ArrayList<E> tempArray, int size, int parentIndex) {
        int minIndex = parentIndex; //set the min index to the parent index (root)
        int leftIndex = parentIndex*2+1; //using the heap algorithim, left index is parent*2+1
        int rightIndex = parentIndex*2+2; //right index is parent*2+2

        if(leftIndex < size && (tempArray.get(leftIndex)).compareTo(tempArray.get(minIndex))< 0) {
            minIndex = leftIndex; //if the left index is less then the size of the array, and it is smaller then then min index, it becomes the min index
        }
        if(rightIndex < size && (tempArray.get(rightIndex)).compareTo(tempArray.get(minIndex))< 0) {
            minIndex = rightIndex; //if the right eft index is less then the size of the array, and it is smaller then then min index, it becomes the min index
        }
        if(minIndex != parentIndex) { //if the min index is not parent index (min heap)
            swap(parentIndex, minIndex, tempArray); //swap places
            Heapify(tempArray,size,minIndex); //heapify to check again
        }

    }
    
}
