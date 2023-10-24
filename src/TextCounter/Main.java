package TextCounter;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        TextReader reader = new TextReader();
        reader.readTextAndPrintResults();
    }
}
class TextCounter //Klass för att deklarera/beräkna/distribuera/returnera inläsning av rader
{
    //<editor-fold desc="Klassvariabler">
    private int lineCount = 0;
    private int characterCount = 0;
    private int wordCount = 0;
    private String longestWord = "";
    private final ArrayList<String> longestWords = new ArrayList<>();
    private boolean userTypedStop = false;
    //</editor-fold>

    //<editor-fold desc="Deklarering och beräkning">
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
                    longestWords.clear();
                    longestWords.add(word);
                }
                else if(word.length() == longestWord.length())
                {
                    longestWords.add(word);
                }
            }
        }
        else
        {
            userTypedStop = true;
        }
    }
    public String RemoveLastCharOfString(String word) //funktion för att ta bort sista tecknet i en String
    {
        String result = null;
        if ((word != null) && (!word.isEmpty())) {
            result = word.substring(0, word.length() - 1);
        }
        return result;
    }
    //</editor-fold>

    //<editor-fold desc="Returneringsmetoder">
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

    public ArrayList<String> getLongestWords()
    {
        return longestWords;
    }

    public boolean userTypedStop() //returnerar kontrollvärde (bool) för om användaren skrivit stop
    {
        return userTypedStop;
    }
    //</editor-fold>
}
class TextReader //Klass för inläsning av rader och utskrift av resultat från TextCounter
{
    public void readTextAndPrintResults() //Funktion för inmatning, felhantering inläsning och utskrivning av resultat
    {
        //<editor-fold desc="Inläsning">
        Scanner scanner = new Scanner(System.in);
        TextCounter counter = new TextCounter();

        System.out.println("Skriv in text (skriv 'stop' för att avsluta):");
        //</editor-fold>

        //<editor-fold desc="Inläsning felhantering">
        try
        {
            while (!counter.userTypedStop())
            {
                String inputLine = scanner.nextLine();
                counter.addLine(inputLine);

                if (counter.userTypedStop()) //Works as a fail-safe
                {
                    break;
                }
            }
        }
        catch (NoSuchElementException e) //Extends InputMismatchException
        {
            System.err.println("Det uppstod ett fel vid inmatningen: " + e.getMessage());
            System.out.println("------------------ \n");
        }
        catch(StringIndexOutOfBoundsException e)
        {
            System.out.println("Du skulle skriva in text, försök igen.");
            System.out.println("------------------ \n");
        }

        catch (Exception e) //För alla andra exceptions
        {
            System.out.println("Något oväntat gick fel." + "\n" + "Vänligen kontakta mig på rickard.jansson.apps@gmail.com om felet kvarstår.");
            System.out.println("------------------ \n");
        }
        finally
        {
            scanner.close();
            System.out.println("Inmatningen är avslutad. Resultat: ");
            System.out.println("------------------ \n");
        }
        //</editor-fold>

        //<editor-fold desc="Utskrift resultat">
        int characterCount = counter.getCharacterCount();
        int lineCount = counter.getLineCount();
        int wordCount = counter.getWordCount();
        ArrayList<String> longestWords = counter.getLongestWords();

        System.out.println("Antal tecken (inklusive blanksteg): " + characterCount);
        System.out.println("Antal rader (exklusive raden med 'stop'): " + lineCount);
        System.out.println("Antal ord: " + wordCount);
        if(longestWords.size() == 1)
        {
            System.out.println("Det längsta ordet: " + longestWords.get(0));
        }
        else if(longestWords.size() > 1)
        {
            System.out.println("De längsta orden: " + String.join(", ", longestWords));
        }
        //</editor-fold>
    }
}