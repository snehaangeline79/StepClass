package week2;

import java.util.Scanner;
public class VowelsConsonants
{
    public static void countVowelsConsonants(String text) {
        int v = 0, c = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            ch = Character.toLowerCase(ch);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
                v++;
            else if (ch > 'a' && ch < 'z')
                c++;
        }
        System.out.println("Vowels: " + v);
        System.out.println("Consonants: " + c);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string");
        String text = sc.nextLine();
        countVowelsConsonants(text);
        sc.close();
        }
    }
