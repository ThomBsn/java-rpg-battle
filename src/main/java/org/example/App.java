package org.example;
import java.util.*;

import org.example.enumGlobal.WeaponType;
import org.example.pojo.Character;
import org.example.pojo.Weapon;

public class App 
{
    public static void main( String[] args ) throws InterruptedException {

        //Création ennemi
        System.out.println("Crée ton Champion !");
        List<Weapon> allyInventory = new ArrayList<Weapon>();

        Weapon allyWeapon = createWeapon();
        allyInventory.add(allyWeapon);
        Thread.sleep(2000);

        Character allyChampion = createCharacter(allyInventory);
        Thread.sleep(4000);

        //Création ennemi
        System.out.println("\n\n\n\n\n\n\nCrée ton Ennemi !");
        List<Weapon> enemyInventory = new ArrayList<Weapon>();

        Weapon enemyWeapon = createWeapon();
        enemyInventory.add(enemyWeapon);
        Thread.sleep(2000);

        Character enemyChampion = createCharacter(enemyInventory);
        Thread.sleep(4000);


        //Combat Start
        int order = 0;
        System.out.println("\n\n\n\n\n\nLE COMBAT COMMENCE :");
        Thread.sleep(2000);
        int combatChange = 1;

        while(allyChampion.getPv() > 0 && enemyChampion.getPv() > 0 || allyChampion.getMana() < 0 || enemyChampion.getMana() < 0){
            //Ally part
            System.out.println("\n\n\n\n\n\nEchange numéro " + combatChange + " :\n");
            combat(order ,allyChampion, enemyChampion);
            order += 1;
            combatChange += 1;
            System.out.println(allyChampion.getName() + " possède : " + allyChampion.getPv() + " PV, " + allyChampion.getMana() + " MANA.");
            System.out.println(enemyChampion.getName() + " possède : " + enemyChampion.getPv() + " PV, " + enemyChampion.getMana() + " MANA.");
            Thread.sleep(12000);

            //Check if one player is dead
            if(allyChampion.getPv() <= 0 || enemyChampion.getPv() <= 0 || allyChampion.getMana() <= 0 || enemyChampion.getMana() <= 0){
                break;
            }

            //Enemy Part
            System.out.println("\n\n\n\n\nEchange numéro " + combatChange + " :\n");
            combat(order , enemyChampion, allyChampion);
            order -= 1;
            combatChange += 1;
            System.out.println(allyChampion.getName() + " possède : " + allyChampion.getPv() + " PV, " + allyChampion.getMana() + " MANA.");
            System.out.println(enemyChampion.getName() + " possède : " + enemyChampion.getPv() + " PV, " + enemyChampion.getMana() + " MANA.");
            Thread.sleep(12000);
        }


        //Check who win
        checkWin(allyChampion, enemyChampion);

    }

    public static void checkWin(Character ally, Character enemy){
        if (ally.getMana() == 0){
            System.out.println("\n\n\n\n\n" + ally.getName() + " cède la victoire à " + enemy.getName() + ", il n'a plus de force !!!");
        }
        else if (enemy.getMana() == 0){
            System.out.println("\n\n\n\n\n" + enemy.getName() + " cède la victoire à " + ally.getName() + ", il n'a plus de force !!!");
        }
        else if(ally.getPv() > enemy.getPv()){
            System.out.println("\n\n\n\n\n" + ally.getName() + " a remporté la victoire !!!");
        }
        else{
            System.out.println("\n\n\n\n\n" + enemy.getName() + " a remporté la victoire !!!");
        }
    }

    public static void combat(int order, Character attacking, Character attacked){
        System.out.println("###################################\n");
        if(order == 0) {
            System.out.println("Voici votre inventaire :\n");
            int ctr = 1;
            for (Weapon elem : attacking.getInventory()) {
                System.out.println("\nArme " + ctr + " :");
                System.out.println(elem);
            }
            int weaponChoice = changeToInt(getSaisie("Avec quelle arme voulez-vous attaquer ?"))-1;
            System.out.print("\nVous utilisez " + attacking.getInventory().get(weaponChoice).getName() + " :\n");
            attacking.attack(attacking.getInventory().get(weaponChoice), attacked);
        }
        else{
            int rdmNumber = (int) (Math.random() * ((attacked.getInventory().size() - 1) + 1));
            System.out.print(attacking.getName() + " utilisez " + attacking.getInventory().get(rdmNumber).getName() + " :\n");
            attacking.attack(attacking.getInventory().get(rdmNumber), attacked);
        }
        System.out.println("\n###################################\n");
    }



    public static Weapon createWeapon(){
        String weaponName = getSaisie("Entrez le nom de l'arme :");
        int weaponPower = changeToInt(getSaisie("Entrez la puissance de l'arme :"));

        String weaponTypeString = getSaisie("Entrez Le type d'arme que vous souhaitez : (entrez 't' pour voir les différants choix)");
        while(Objects.equals(weaponTypeString, "t")){
            System.out.println("\nLes choix sont : Lance, Dague, Masse, Sceptre, Baton.");
            weaponTypeString = getSaisie("Entrez Le type d'arme que vous souhaitez : (entrez 't' pour voir les différants choix)");
        }

        Weapon weaponToReturn;
        try {
            weaponToReturn = new Weapon(weaponName, weaponPower, turnIntoType(weaponTypeString));
            System.out.println("\nArme crée : \n" + weaponToReturn + "\n\n");
        }catch (ClassFormatError e){
            System.out.println("Création de l'arme échouée.");
            weaponToReturn = new Weapon();
        }

        return weaponToReturn;
    }

    public static Character createCharacter(List<Weapon> weaponList){
        String characterName = getSaisie("\n\n\n\n\n\n\n\n\nEntrez le nom du joueur :");
        int characterHealth = changeToInt(getSaisie("Entrez la vie du joueur :"));
        int characterMana = changeToInt(getSaisie("Entrez le mana du joueur :"));

        Character charactertoReturn;
        try {
            charactertoReturn = new Character(characterName, characterHealth, characterMana, weaponList);
            System.out.println(charactertoReturn);
        }catch (ClassFormatError e){
            charactertoReturn = new Character();
            System.out.println("Création du personnage échouée.");
        }

        return charactertoReturn;
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
