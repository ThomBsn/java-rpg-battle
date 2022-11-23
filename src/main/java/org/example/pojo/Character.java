package org.example.pojo;

import java.util.ArrayList;
import java.util.List;

public class Character {
    //Attributes
    private String name;
    private int pv;
    private int mana;
    private List<Weapon> inventory;

    //Constructors
    public Character(){
        this.name = "";
        this.pv = 0;
        this.mana = 0;
        this.inventory = new ArrayList<Weapon>();
    }

    public Character(String name, int pv, int mana, List<Weapon> inventory) {
        this.name = name;
        this.pv = pv;
        this.mana = mana;
        this.inventory = inventory;
    }

    //Methods
    public String getName() {
        return this.name;
    }

    public int getPv() {
        return this.pv;
    }

    public int getMana() {
        return this.mana;
    }

    public List<Weapon> getInventory() {
        return this.inventory;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        String presentation = "\n\nJe suis " + this.name + "\nJ'ai " + this.pv + " PV" + ", " + this.mana + " mana.\n\nMon inventaire contient : \n";
        toReturn.append(presentation);
        for (Weapon elem : this.inventory){
            toReturn.append(elem.toString());
        }
        return toReturn.toString();
    }

    public void attack(Weapon weapon, Character target){
        int lostMana = 0;
        switch(weapon.getType().toString().toLowerCase()){
            case "baton" :
                lostMana = 5;
            case "sword" :
                lostMana = 20;
                break;
            case "dague" :
                lostMana = 10;
                break;
            case "lance" :
                lostMana = 15;
                break;
            case "spectre" :
                lostMana = 20;
                break;
            case "masse" :
                lostMana = 30;;
                break;
        }
        this.mana -= lostMana;
        if (this.mana < 0){
            this.mana = 0;
        }

        System.out.println(this.name + " a perdu : " + lostMana + " mana.");
        target.takeDamage(weapon.getPuissance());
    }

    public void takeDamage(int WeaponDmg){
        System.out.println(this.name + " a perdu : " + WeaponDmg/2 + " PV.");
        this.pv -= WeaponDmg/2;
        if (this.pv < 0){
            this.pv = 0;
        }
    }
}
