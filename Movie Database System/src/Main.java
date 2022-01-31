

import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<String>> firstArticletxtArray = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> firstAuthortxtArray = new ArrayList<ArrayList<String>>();
        String output = "";
        String articleFile = "";
        int count = 0;
        String author = args[0];
        String command = args[1];
        File outputTxt = new File("output.txt");
        FileWriter writer1 = new FileWriter(outputTxt,false);
        writer1.write("");
        writer1.flush();
        writer1.close();
        ArrayList<ArrayList<String>> commands = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list2 = new ArrayList<ArrayList<String>>();
        ReadFile firstAuthortxt = new ReadFile(author,list2);
        firstAuthortxtArray = firstAuthortxt.author();
        ReadFile commandList = new ReadFile(command, commands);
        ArrayList<ArrayList<String>> arrayList = commandList.author();
        for (ArrayList<String> i : arrayList){
            if (i.get(0).equals("read")){
                count += 1;
                if (count != 1){
                    String otherFile = i.get(1);
                    ArrayList<ArrayList<String>> data = new ArrayList<>();
                    ReadFile readFile = new ReadFile(otherFile, data);
                    FileWriter writer = new FileWriter(articleFile,true);
                    for(ArrayList k: readFile.author()) {
                        writer.write("\n");
                        for (Object str : k) {
                            writer.write((String) str + " ");
                        }

                    }
                    writer.close();
                    output = "";
                    ReadLines read = new ReadLines(output, articleFile, author);
                    output = "----------------------------------------------List---------------------------------------------\n" +
                            read.read() +
                            "----------------------------------------------End----------------------------------------------\n\n";
                    continue;
                }
                articleFile = i.get(1);
                output = "";
                ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
                ReadFile firstArticletxt = new ReadFile(articleFile, list);
                firstArticletxtArray = firstArticletxt.author();
                ReadLines read = new ReadLines(output, articleFile, author);
                output = "----------------------------------------------List---------------------------------------------\n" +
                        read.read() +
                        "----------------------------------------------End----------------------------------------------\n\n";
            }
            else if (i.get(0).equals("list")){
                List strToText = new List (output);
                strToText.print();
            }
            else if (i.get(0).equals("completeAll")){
                CompleteAll completeAll = new CompleteAll(articleFile, author);
                output = "*************************************CompleteAll Successful*************************************\n" +
                        "----------------------------------------------List---------------------------------------------\n" +
                        completeAll.complete() +
                        "----------------------------------------------End----------------------------------------------\n\n";

            }
            else if (i.get(0).equals("sortedAll")){
                SortedAll sort = new SortedAll(articleFile, author);
                output = "*************************************SortedAll Successful*************************************\n"+
                        "----------------------------------------------List---------------------------------------------\n"+
                        sort.sort() +
                        "----------------------------------------------End----------------------------------------------\n\n";
            }
            else if (i.get(0).equals("del")){
                String delId = (String) i.get(1);
                DelAuthor delete = new DelAuthor (articleFile, author, delId);
                output = "*************************************del Successful*************************************\n"+
                        "----------------------------------------------List---------------------------------------------\n"+
                        delete.del() +
                        "----------------------------------------------End----------------------------------------------\n\n";

            }
        }
        FileWriter writer = new FileWriter(author,false);
        for(ArrayList k: firstAuthortxtArray) {

            for (Object str : k) {
                writer.write((String) str + " ");
            }
            writer.write("\n");

        }
        writer.close();
        FileWriter writer2 = new FileWriter(articleFile,false);
        for(ArrayList k: firstArticletxtArray) {

            for (Object str : k) {
                writer2.write((String) str + " ");
            }
            writer2.write("\n");

        }
        writer2.close();
    }
}