package week1;

import java.util.*;
public class RockPaperScissor
{
    public static String func(String p,String c)
    {
        if(p.equalsIgnoreCase(c))
            return "Draw";

        if((p.equalsIgnoreCase("Rock") && c.equals("Scissors")) ||
                (p.equalsIgnoreCase("Paper") && c.equals("Rock")) ||
                (p.equalsIgnoreCase("Scissors") && c.equals("Paper")))
            return "Win";

        return "Loss";
    }
    public static void main()
    {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        String moves[] = {"Rock","Paper","Scissors"};
        int w=0,l=0,d=0;
        for(int i=0;i<3;i++)
        {
            System.out.print("\nRound "+i+" Enter Move (Rock/Paper/Scissors): ");
            String player = sc.next();
            String comp = moves[r.nextInt(3)];
            String result = func(player,comp);
            System.out.println("Computer Move : "+comp);
            System.out.println("Result : "+result);
            if(result.equals("Win"))
                w++;
            else if(result.equals("Loss"))
                l++;
            else
                d++;
        }
        System.out.println("\nFinal Score");
        System.out.println("Wins : "+w);
        System.out.println("Losses : "+l);
        System.out.println("Draws : "+d);
    }
}