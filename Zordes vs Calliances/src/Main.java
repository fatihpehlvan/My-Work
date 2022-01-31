import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class Main {
    public static String outputString = "";
    //board key = y,x and value = value
    public static LinkedHashMap<String, String> board;
    public static ArrayList<Armies> armiesArrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        LinkedHashMap<String, String> previousBoard = new LinkedHashMap<>();
        ReadFile read1 = new ReadFile(args[0]);
        ArrayList<ArrayList<String>> initials = read1.read();
        ReadFile read = new ReadFile(args[1]);
        ArrayList<ArrayList<String>> commands = read.read();
        int sub = initials.get(1).get(0).indexOf("x");
        int boardShape = Integer.parseInt(initials.get(1).get(0).substring(0, sub));
        board = makeEmptyBoard(boardShape);
        for (ArrayList<String> initialsElement : initials) {
            if (initialsElement.size() == 4) {
                String key = initialsElement.get(3) + "," + initialsElement.get(2);
                board.put(key, initialsElement.get(1));
                switch (initialsElement.get(0)) {
                    case "ELF":
                        armiesArrayList.add(new Elf(initialsElement.get(1)));
                        break;
                    case "DWARF":
                        armiesArrayList.add(new Dwarf(initialsElement.get(1)));
                        break;
                    case "HUMAN":
                        armiesArrayList.add(new Human(initialsElement.get(1)));
                        break;
                    case "GOBLIN":
                        armiesArrayList.add(new Goblin(initialsElement.get(1)));
                        break;
                    case "TROLL":
                        armiesArrayList.add(new Troll(initialsElement.get(1)));
                        break;
                    case "ORK":
                        armiesArrayList.add(new Ork(initialsElement.get(1)));
                        break;
                }
            }
        }
        Board initialBoard = new Board();
        initialBoard.board(board);
        armiesArrayList.sort(Comparator.comparing(Armies::getId));
        for (Armies armies : armiesArrayList) {
            outputString += armies.getId() + "\t" + armies.getHeal() + "\t(" + armies.getDefaultHeal() + ")" + "\n";
        }
        outputString += "\n";
        for (ArrayList<String> commandElements : commands) {
            boolean callianceExist = false;
            boolean zordesExist = false;
            Armies champion = null;
            for (Armies armiesElement : armiesArrayList) {
                if (commandElements.get(0).equals(armiesElement.getId())) {
                    champion = armiesElement;
                }
                if (armiesElement instanceof Calliances) callianceExist = true;
                else if(armiesElement instanceof Zordes) zordesExist = true;
            }
            try {
                if (callianceExist && zordesExist) {
                    String moves = commandElements.get(1).substring(0, commandElements.get(1).length() - 1);
                    if (champion instanceof Ork) {
                        ((Ork) champion).attack(moves, champion);
                    } else if (champion instanceof Dwarf) {
                        ((Dwarf) champion).attack(moves, champion);
                    } else if (champion instanceof Troll) {
                        ((Troll) champion).attack(moves, champion);
                    } else if (champion instanceof Goblin) {
                        ((Goblin) champion).attack(moves, champion);
                    } else if (champion instanceof Human) {
                        ((Human) champion).attack(moves, champion);
                    } else if (champion instanceof Elf) {
                        ((Elf) champion).attack(moves, champion);
                    }

                    armiesArrayList.removeIf(armies -> armies.getHeal() <= 0);
                    initialBoard.board(board);
                    previousBoard = new LinkedHashMap<>(board);
                    armiesArrayList.sort(Comparator.comparing(Armies::getId));
                    for (Armies armies : armiesArrayList) {
                        outputString += armies.getId() + "\t" + armies.getHeal() + "\t(" + armies.getDefaultHeal() + ")" + "\n";
                    }
                    outputString += "\n";

                } else break;
            } catch (Exception e) {
                if (!previousBoard.equals(board)) {
                    initialBoard.board(board);
                    armiesArrayList.sort(Comparator.comparing(Armies::getId));
                    for (Armies armies : armiesArrayList) {
                        outputString += armies.getId() + "\t" + armies.getHeal() + "\t(" + armies.getDefaultHeal() + ")" + "\n";
                    }
		    outputString += "\n";
                }
                outputString += e.getMessage() + "\n\n";
            }
        }
        outputString += "\nGame Finished\n";
        if (armiesArrayList.get(0) instanceof Zordes) {
            outputString += "Zorde Wins";
        } else outputString += "Calliance Wins";

        //Make string to output file
        File output = new File(args[2]);
        FileWriter writer = new FileWriter(output);
        writer.write(outputString);
        writer.flush();
        writer.close();

    }

    private static LinkedHashMap<String, String> makeEmptyBoard(int boardShape) {
        LinkedHashMap<String, String> board = new LinkedHashMap<>();
        String emptyArea = "  ";
        for (int i = 0; i < boardShape; i++) {
            for (int j = 0; j < boardShape; j++) {
                String key = i + "," + j;
                board.put(key, emptyArea);
            }
        }
        return board;
    }
}