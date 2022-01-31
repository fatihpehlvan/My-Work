import java.util.ArrayList;

public class Stack<T> {
    private final ArrayList<T> arrayList = new ArrayList<>();
    public void push(T item){
        arrayList.add(item);
    }

    // Remove element from last index
    public T pop(){
        T lastElement = arrayList.get(arrayList.size()-1);
        arrayList.remove(arrayList.size()-1);
        return lastElement;
    }

    // Add element in last index
    public T peek(int index){
        return arrayList.get(index);
    }

    // This method for running for loop.
    public ArrayList<T> getAsList(){
        return arrayList;
    }

    // Get size
    public int size(){
        return arrayList.size();
    }

}