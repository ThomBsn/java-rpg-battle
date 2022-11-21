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
        return name;
    }

    public int getPv() {
        return pv;
    }

    public int getMana() {
        return mana;
    }

    public List<Weapon> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        String presentation = "Je suis " + this.name + "\nJ'ai " + this.pv + " PV" + ", " + this.mana + " mana.\n\nMon inventaire contient : \n";
        toReturn.append(presentation);
        for (Weapon elem : this.inventory){
            toReturn.append(elem.toString());
        }
        return toReturn.toString();
    }
}
