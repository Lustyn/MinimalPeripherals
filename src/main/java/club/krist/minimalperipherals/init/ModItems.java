package club.krist.minimalperipherals.init;

import club.krist.minimalperipherals.MinimalPeripherals;
import club.krist.minimalperipherals.item.ItemElectronicArmor;
import club.krist.minimalperipherals.item.ItemMinimal;
import club.krist.minimalperipherals.item.ItemMinimalArmor;
import club.krist.minimalperipherals.item.ItemSteelIngot;
import club.krist.minimalperipherals.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by justy on 7/6/2016.
 */
public class ModItems {

    public static ItemElectronicArmor electronic_helmet = new ItemElectronicArmor(1, 0);
    public static ItemElectronicArmor electronic_chestplate = new ItemElectronicArmor(1, 1);
    public static ItemElectronicArmor electronic_leggings = new ItemElectronicArmor(2, 2);
    public static ItemElectronicArmor electronic_boots = new ItemElectronicArmor(1, 3);
    public static ItemSteelIngot steel_ingot = new ItemSteelIngot();

    public static void init() {
        GameRegistry.registerItem(electronic_helmet, electronic_helmet.getName());
        GameRegistry.registerItem(electronic_chestplate, electronic_chestplate.getName());
        GameRegistry.registerItem(electronic_leggings, electronic_leggings.getName());
        GameRegistry.registerItem(electronic_boots, electronic_boots.getName());
        GameRegistry.registerItem(steel_ingot, steel_ingot.getName());
    }
}
