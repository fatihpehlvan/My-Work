import java.util.LinkedHashMap;

public class Board {

    public Board() {
    }

    public void board(LinkedHashMap<String, String> boardArray) {

        String topAndBottom = "";
        String board = "";
        for (int i = 0; i < Math.sqrt(boardArray.keySet().size()) * 2 + 2; i++) {
            topAndBottom += "*";
        }
        board = topAndBottom + "\n*";
        int rowsFirst = 0;
        for (String rows : boardArray.keySet()){
            if (!rows.substring(0,1).equals(String.valueOf(rowsFirst))) {
                board += "*\n*";
                rowsFirst++;
            }
            if (rows.substring(0,1).equals(String.valueOf(rowsFirst))){
                board += boardArray.get(rows);
            }

        }

        board += "*\n" + topAndBottom;
        Main.outputString += board + "\n\n";
    }
}