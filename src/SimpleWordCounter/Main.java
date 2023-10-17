package SimpleWordCounter;
import java.util.Scanner;

// Klass för att räkna raderna och antalet tecken
class TextCounter {
    private int lineCount = 0;
    private int characterCount = 0;

    // Lägg till rad och antal tecken
    public void addLine(String line) {
        lineCount++;
        characterCount += line.length();
    }

    // Hämta antalet rader
    public int getLineCount() {
        return lineCount;
    }

    // Hämta antalet tecken
    public int getCharacterCount() {
        return characterCount;
    }
}

// Klass för att läsa in text och skriva ut resultat
class TextReader {
    public void readTextAndPrintResults() {
        Scanner scanner = new Scanner(System.in);
        TextCounter counter = new TextCounter();

        System.out.println("Skriv in text (skriv 'stop' för att avsluta):");

        while (true) {
            String inputLine = scanner.nextLine();
            counter.addLine(inputLine);

            if (inputLine.equalsIgnoreCase("stop")) {
                break;
            }
        }

        int characterCount = counter.getCharacterCount();
        int lineCount = counter.getLineCount() - 1; // Minska med 1 för att exkludera "stop"-raden

        System.out.println("Antal tecken: " + characterCount);
        System.out.println("Antal rader (exklusive raden med 'stop'): " + lineCount);

        scanner.close();
    }
}

public class Main {
    public static void main(String[] args) {
        TextReader reader = new TextReader();
        reader.readTextAndPrintResults();
    }
}