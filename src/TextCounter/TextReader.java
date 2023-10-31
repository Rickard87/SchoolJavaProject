package TextCounter;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
            System.out.println("\nResultatet är ogiltigt, försök igen.");
            System.out.println("Anledningen kan vara att du endast tryckte 'Enter'\neller att du klistrat in text med tomma radbrytningar.");
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