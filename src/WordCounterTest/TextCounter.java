package WordCounterTest;
import java.util.Scanner;

public class TextCounter
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
            String[] words = line.split("\\s+"); // Dela upp raden i ord
            for (String word : words)
            {
                while(word.charAt(word.length()-1) == '.') //Change to while
                {
                    System.out.println(word);
                    word = RemoveLastCharOfString(word);
                    System.out.println(word);
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

    public int getLineCount()
    {
        return lineCount;
    }

    public int getCharacterCount()
    {
        return characterCount;
    }

    public int getWordCount()
    {
        return wordCount;
    }

    public String getLongestWord()
    {
        return longestWord;
    }

    public boolean userTypedStop()
    {
        return userTypedStop;
    }

    public String RemoveLastCharOfString(String word)
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
    public void readTextAndPrintResults()
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
    public String RemoveLastCharOfString(String word)
    {
        String result = null;
        if ((word != null) && (word.length() > 0)) {
            result = word.substring(0, word.length() - 1);
        }
        return result;
    }
}