package org.example.pojo;

import org.example.enumGlobal.WeaponType;

public class Weapon {
    //Attributes
    private String name;
    private int puissance;
    private WeaponType type;


    //Constructors
    public Weapon(){
        name = "";
        puissance = 0;
        type = WeaponType.BATON;
    }

    public Weapon(String name, int puissance, WeaponType type) {
        this.name = name;
        this.puissance = puissance;
        this.type = type;
    }


    //Methods
    public String getName() {
        return this.name;
    }

    public int getPuissance() {
        return this.puissance;
    }

    public WeaponType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Nom de l'arme : " + this.name +
                "\nPuissance : " + this.puissance +
                "\nType : " + this.type + "\n\n";
    }
}
