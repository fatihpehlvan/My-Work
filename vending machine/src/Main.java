import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        /*
        * Read files and convert them properly versions (Stack, ArrayList, or Queue
        */

        Stack<String> parts = readStack(args[0]);
        Stack<Stack<String>> items = read2DStack(args[1], parts);
        Queue tokens = readQueue(args[2]);
        ArrayList<ArrayList<String>> tasks = read2DArrayList(args[3]);


        // Apply tasks.
        for (ArrayList<String> taskElement : tasks) {
            // There are 2 tasks in task. Check if it is buy or not.
            boolean buy = taskElement.get(0).equals("BUY");
            for (String taskItems : taskElement) {
                // Buy tasks separated by comma. Make list them.
                String[] taskItemsArray = taskItems.split(",");
                if (buy && taskItemsArray.length != 1) {
                    // in txt file first item is product and second item is how many product you buy.
                    String product = taskItemsArray[0];
                    int piece = Integer.parseInt(taskItemsArray[1]);
                    // To void ConcurrentModificationException I make nested loop. After pop item break the second for loop.
                    for (int i = 0; i < piece; i++) {
                        for (Stack<String> itemsElement : items.getAsList()) {
                            for (String itemsItems : itemsElement.getAsList()) {
                                String[] itemsElementArray = itemsItems.split(" ");
                                if (itemsElementArray[1].equals(product)) {
                                    itemsElement.pop();
                                    break;
                                }
                            }
                        }
                    }
                    // define variables
                    int total = 0;
                    int count = 0;
                    // To not change original tokens ArrayList make a new arraylist which is reverse of tokens ArrayList.
                    // ReverseToken start with the biggest token.
                    ArrayList<Tokens> reverseToken;
                    reverseToken = (ArrayList<Tokens>) tokens.getAsList().clone();
                    Collections.reverse(reverseToken);
                    for (Tokens tokensElement : reverseToken) {
                        if (tokensElement.getProduct().equals(product)) {
                            // firstly make them piece -1, because dequeue method delete elements in 0. index.
                            // if 1 token is less than piece, this block will run until sum of them greater than piece.
                                total+= tokensElement.getPiece();
                                tokensElement.setPiece(-1);
                                if (total == piece){
                                    tokens.dequeue();
                                    break;
                                }
                                else if (total > piece){
                                    tokens.dequeue();
                                    tokensElement.setPiece(total - piece);
                                    tokens.enqueue(tokensElement);
                                    break;
                                }
                                count++;
                            }
                    }
                    // this loop removes -1 pieces in tokens.
                    for (int i = 0; i < count; i++){
                        tokens.dequeue();
                    }
                }
                // Put tokens according to given tokens.
                else if (!buy && taskItemsArray.length != 1) {
                    for(Stack<String> itemsElement : items.getAsList()){
                        for (int index = 1; index < taskItemsArray.length; index++){//for (String putItems : taskItemsArray) {
                            if (itemsElement.peek(0).substring(0,itemsElement.peek(0).indexOf(" ")).equals(taskItemsArray[0])) {
                                itemsElement.getAsList().add(taskItemsArray[index] + " " + taskItemsArray[0]);
                            }
                        }
                    }
                }
            }
        }
        // make outputString and write them in a file.
        String outputString = "";

        for (Stack<String> itemsArray : items.getAsList()){
            int count = 0;
            Collections.reverse(itemsArray.getAsList().subList(1,itemsArray.size()));
            for (String itemsElement : itemsArray.getAsList()){
                count++;
                outputString += itemsElement.substring(0,itemsElement.indexOf(" "));
                if (itemsElement.contains("Title")){
                    outputString += ":";
                }
                outputString += "\n";
            }
            if (count ==1){
                outputString += "\n";
            }
            outputString += "---------------\n";
        }
        outputString += "Token Box:\n";
        for (Tokens tokensItem : tokens.getAsList()){
            outputString += tokensItem.getId() + " " + tokensItem.getProduct() + " " + tokensItem.getPiece() + "\n";
            }
        outputString = outputString.substring(0,outputString.length()-1);
        //Make string to output file
        File output = new File(args[4]);
        FileWriter writer = new FileWriter(output);
        writer.write(outputString);
        writer.flush();
        writer.close();
    }
    // All methods read files and make them properly versions.
    public static Stack<String> readStack(String fileName) throws IOException {
        Stack<String> data = new Stack<>();
        FileReader fr1 = new FileReader(fileName);
        BufferedReader bf1 = new BufferedReader(fr1);
        String line;

        while ((line = bf1.readLine()) != (null)) {
            data.push(line);
        }
        bf1.close();
        return data;
    }

    public static Stack<Stack<String>> read2DStack(String fileName, Stack<String> parts) throws IOException {
        Stack<Stack<String>> data = new Stack<>();

        for (String str : parts.getAsList()) {
            FileReader fr1 = new FileReader(fileName);
            BufferedReader bf1 = new BufferedReader(fr1);
            String line;
            Stack<String> stack = new Stack<>();
            stack.push(str + " Title");
            while ((line = bf1.readLine()) != (null)) {
                boolean statement = str.equals(line.split(" ")[1]);
                if (statement)
                    stack.push(line);
            }
            data.push(stack);
            bf1.close();
        }
        return data;
    }

    public static Queue readQueue(String fileName) throws IOException {
        Queue data = new Queue();
        FileReader fr1 = new FileReader(fileName);
        BufferedReader bf1 = new BufferedReader(fr1);
        String line;
        while ((line = bf1.readLine()) != (null)) {
            data.enqueue(line);
        }
        bf1.close();
        return data;
    }

    public static ArrayList<ArrayList<String>> read2DArrayList(String fileName) throws IOException {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        FileReader fr1 = new FileReader(fileName);
        BufferedReader bf1 = new BufferedReader(fr1);
        String line;

        while ((line = bf1.readLine()) != (null)) {
            String[] strSplit = line.split("\t");
            ArrayList<String> elements = new ArrayList<>(Arrays.asList(strSplit));
            data.add(elements);
        }
        bf1.close();
        return data;
    }
}