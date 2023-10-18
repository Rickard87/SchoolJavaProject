package TextCounter;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        TextReader reader = new TextReader();
        reader.readTextAndPrintResults();
    }
}

class TextCounter
{
    private int lineCount = 0;
    private int characterCount = 0;
    private int wordCount = 0;
    private String longestWord = "";
    private boolean userTypedStop = false;

    public void addLine(String line)
    {
        if (!line.equalsIgnoreCase("stop")) // Exkluderar "stop"-raden
        {
            lineCount++;
            characterCount += line.length();
            String[] words = line.split("\\s+"); // Dela upp raden i ord, \\s är "white space", blanksteg, kommatecken etc.
            //läs mer om det här https://stackoverflow.com/questions/13750716/what-does-regular-expression-s-s-do
            //https://www.regular-expressions.info/shorthand.html
            for (String word : words)
            {
                while(word.charAt(word.length()-1) == '.') //En while-loop kontrollerar om sista tecknet i sista ordet är en punkt ända tills ordet inte avslutas med punkt längre
                {
                    word = RemoveLastCharOfString(word);
                }
                wordCount++;
                if (word.length() > longestWord.length())
                {
                    longestWord = word;
                }
            }
        }
        else
        {
            userTypedStop = true;
        }
    }

    public int getLineCount() //returnerar antalet rader till användaren
    {
        return lineCount;
    }

    public int getCharacterCount() //returnerar antalet tecken till användaren
    {
        return characterCount;
    }

    public int getWordCount() //returnerar antalet ord till användaren
    {
        return wordCount;
    }

    public String getLongestWord() //returnerar det längsta ordet till användaren
    {
        return longestWord;
    }

    public boolean userTypedStop() //returnerar kontrollvärde (bool) för om användaren skrivit stop
    {
        return userTypedStop;
    }

    public String RemoveLastCharOfString(String word) //funktion för att ta bort sista tecknet i en String
    {
        String result = null;
        if ((word != null) && (word.length() > 0)) {
            result = word.substring(0, word.length() - 1);
        }
        return result;
    }
}

class TextReader
{
    public void readTextAndPrintResults() //funktion för inmatning, kontroll om användaren skrivit 'stop' och utskrivning av resultat
    {
        Scanner scanner = new Scanner(System.in);
        TextCounter counter = new TextCounter();

        System.out.println("Skriv in text (skriv 'stop' för att avsluta):");

        while (!counter.userTypedStop())
        {
            String inputLine = scanner.nextLine();
            counter.addLine(inputLine);

            if (counter.userTypedStop()) //Works as a fail-safe
            {
                break;
            }
        }

        int characterCount = counter.getCharacterCount();
        int lineCount = counter.getLineCount();
        int wordCount = counter.getWordCount();
        String longestWord = counter.getLongestWord();

        System.out.println("Antal tecken (inklusive blanksteg): " + characterCount);
        System.out.println("Antal rader (exklusive raden med 'stop'): " + lineCount);
        System.out.println("Antal ord: " + wordCount);
        System.out.println("Det längsta ordet: " + longestWord);

        scanner.close();
    }
}