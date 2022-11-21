package org.example;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import org.example.enumGlobal.WeaponType;
import org.example.pojo.Character;
import org.example.pojo.Weapon;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Weapon> inventory = new ArrayList<Weapon>();
        String weapon_name = getSaisie("Entrez le nom de votre arme :");
        int weapon_puissance = changeToInt(getSaisie("Entrez la puissance de votre arme :"));
        Weapon lance_demoniaque = new Weapon(weapon_name, weapon_puissance, WeaponType.LANCE);
        inventory.add(lance_demoniaque);

        Weapon dague_dentelee = new Weapon("Purge", 200, WeaponType.DAGUE);
        inventory.add(dague_dentelee);

        Character monChampion = new Character("Thomas", 100, 150, inventory);

        System.out.println(monChampion);
    }

    public static String getSaisie(String question){
        Scanner sc = new Scanner(System.in);
        System.out.println(question);
        String res = "";
        boolean verif = false;

        while (!verif){
            try{
                res = sc.nextLine();
                verif = true;
            }catch (InputMismatchException e){
                System.out.println("Votre saisie est incorrecte , veuillez reessayer  :");
                sc.next();
            }
        }
        return res;
    }

    public static int changeToInt(String input){
        int conversion = 0;
        try{
            conversion = Integer.parseInt(input);
            return conversion;
        }catch (ClassCastException e) {
            return -1;
        }
    }

}
