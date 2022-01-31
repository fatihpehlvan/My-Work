import java.util.ArrayList;
import java.util.Comparator;

public class Queue {
    private ArrayList<Tokens> arrayList = new ArrayList<>();
    // Add String in 0.index
    public void enqueue(String  item){
        Tokens tokens = new Tokens(item);
        arrayList.add(0, tokens);
        arrayList.sort(Comparator.comparing(Tokens::getPiece));
    }
    // Add item in 0.index
    public void enqueue(Tokens item){
        arrayList.add(0, item);
        arrayList.sort(Comparator.comparing(Tokens::getPiece));
    }
    // Remove element from 0.index
    public Tokens dequeue(){
        arrayList.sort(Comparator.comparing(Tokens::getPiece));
        Tokens firstElement = arrayList.get(0);
        arrayList.remove(0);
        return firstElement;
    }
    // This method for running for loop.
    public ArrayList<Tokens> getAsList(){
        return arrayList;
    }

}
