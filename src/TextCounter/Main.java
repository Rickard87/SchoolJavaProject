package TextCounter;

public class Main //Programmet börjar alltid från Main
{
    public static void main(String[] args)
    {
        TextReader reader = new TextReader(); //Skapar en instans/objekt av en klass
        reader.readTextAndPrintResults(); //Kallar på metod för att starta inmatning till en Scanner
    }
}