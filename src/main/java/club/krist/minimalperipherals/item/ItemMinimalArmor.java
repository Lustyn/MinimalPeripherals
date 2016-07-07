package club.krist.minimalperipherals.item;

import club.krist.minimalperipherals.MinimalPeripherals;
import club.krist.minimalperipherals.reference.Reference;
import net.minecraft.item.ItemArmor;

/**
 * Created by justy on 7/6/2016.
 */
public abstract class ItemMinimalArmor extends ItemArmor {
    public enum ArmorType { helmet, chestplate, leggings, boots };

    public ItemMinimalArmor(ArmorMaterial material, int renderIndex, int armorType) {
        super(material, renderIndex, armorType);
        this.setUnlocalizedName(Reference.MOD_ID + ":" + getName());
        this.setCreativeTab(MinimalPeripherals.tabMinimalPeripherals);
    }

    public abstract String getName();
}
