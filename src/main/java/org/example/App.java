package org.example;
import java.util.*;

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

        Weapon allyWeapon = createWeapon();
        inventory.add(allyWeapon);


        Character allyChampion = createCharacter(inventory);

        System.out.println(allyChampion);
    }

    public static Weapon createWeapon(){
        String weaponName = getSaisie("Entrez le nom de l'arme :");
        int weaponPower = changeToInt(getSaisie("Entrez la puissance de l'arme :"));

        String weaponTypeString = getSaisie("Entrez Le type d'arme que vous souhaitez : (entrez 't' pour voir les différants choix)");
        while(Objects.equals(weaponTypeString, "t")){
            System.out.println("\nLes choix sont : Lance, Dague, Masse, Sceptre, Baton.");
            weaponTypeString = getSaisie("Entrez Le type d'arme que vous souhaitez : (entrez 't' pour voir les différants choix)");
        }

        try {
            return new Weapon(weaponName, weaponPower, turnIntoType(weaponTypeString));
        }catch (ClassFormatError e){
            System.out.println("Création de l'arme échouée.");
        }

        return new Weapon();
    }

    public static Character createCharacter(List<Weapon> weaponList){
        String characterName = getSaisie("Entrez le nom du joueur :");
        int characterHealth = changeToInt(getSaisie("Entrez la vie du joueur :"));
        int characterMana = changeToInt(getSaisie("Entrez le mana du joueur :"));

        try {
            return new Character(characterName, characterHealth, characterMana, weaponList);
        }catch (ClassFormatError e){
            System.out.println("Création du personnage échouée.");
        }

        return new Character();
    }

    public static WeaponType turnIntoType(String typeString) {

        WeaponType weaponType = WeaponType.BATON;
        switch (typeString.toLowerCase()){
            case "sword" :
                weaponType = WeaponType.SWORD;
                break;
            case "dague" :
                weaponType = WeaponType.DAGUE;
                break;

            case "lance" :
                weaponType = WeaponType.LANCE;
                break;

            case "spectre" :
                weaponType = WeaponType.SCEPTRE;
                break;

            case "masse" :
                weaponType = WeaponType.MASSE;;
                break;

        }

        return weaponType;
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
