import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordSearch {
  private static final int SIZE = 10;
  private static final int WORDCOUNT = 10;
  private static final char[][] grid = new char[SIZE][SIZE];
  private static List<String> words = new ArrayList<>();
  private static final Random random = new Random();

  public static void main(String[] args) {
    initializeGrid();

    try {
      words = getLinesFromFile("Woordenlijst.txt", 18, 35);
      System.out.println(words);
    } catch (IOException e) {
      System.err.println("FOUT" + e.getMessage());
    }
    // List of words to hide in the word search
    // Collections.addAll(words, "kip", "pot", "week", "bus", "jas", "taak", "pit",
    // "toon", "kim", "maan");

    for (String word : words) {
      placeWord(word);
    }

    fillEmptySpaces();
    printGrid();
  }

  public static List<String> getLinesFromFile(String filePath, int startLine, int endLine) throws IOException {
    List<String> selectedLines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      int currentLine = 1;

      while ((line = reader.readLine()) != null) {
        if (currentLine >= startLine && currentLine <= endLine) {
          selectedLines.add(line);
        }
        if (currentLine > endLine)
          break;
        currentLine++;
      }
    }

    return selectedLines;
  }

  private static void initializeGrid() {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        grid[row][col] = ' ';
      }
    }
  }

  private static void placeWord(String word) {
    boolean placed = false;
    while (!placed) {
      int row = random.nextInt(SIZE);
      int col = random.nextInt(SIZE);
      int direction = random.nextInt(3); // 0 = horizontal, 1 = vertical, 2 = diagonal

      if (canPlaceWord(word, row, col, direction)) {
        for (int i = 0; i < word.length(); i++) {
          if (direction == 0) {
            grid[row][col + i] = word.charAt(i); // horizontal
          } else if (direction == 1) {
            grid[row + i][col] = word.charAt(i); // vertical
          } else {
            grid[row + i][col + i] = word.charAt(i); // diagonal
          }
        }
        placed = true;
      }
    }
  }

  private static boolean canPlaceWord(String word, int row, int col, int direction) {
    if (direction == 0 && col + word.length() > SIZE)
      return false;
    if (direction == 1 && row + word.length() > SIZE)
      return false;
    if (direction == 2 && (row + word.length() > SIZE || col + word.length() > SIZE))
      return false;

    for (int i = 0; i < word.length(); i++) {
      char current;
      if (direction == 0)
        current = grid[row][col + i];
      else if (direction == 1)
        current = grid[row + i][col];
      else
        current = grid[row + i][col + i];

      if (current != ' ' && current != word.charAt(i))
        return false;
    }
    return true;
  }

  private static void fillEmptySpaces() {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        if (grid[row][col] == ' ') {
          grid[row][col] = (char) ('a' + random.nextInt(26));
        }
      }
    }
  }

  private static void printGrid() {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        System.out.print(grid[row][col] + " ");
      }
      System.out.println();
    }
  }
}
