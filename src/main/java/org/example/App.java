package org.example;
import java.util.List;
import java.util.ArrayList;

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
        Weapon lance_demoniaque = new Weapon("Lance démoniaque", 100, WeaponType.LANCE);
        inventory.add(lance_demoniaque);

        Weapon dague_dentelee = new Weapon("Purge", 200, WeaponType.DAGUE);
        inventory.add(dague_dentelee);

        Character monChampion = new Character("Thomas", 100, 150, inventory);

        System.out.println(monChampion);
    }


}
