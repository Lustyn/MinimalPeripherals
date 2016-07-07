package club.krist.minimalperipherals.item;

import club.krist.minimalperipherals.MinimalPeripherals;
import club.krist.minimalperipherals.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

/**
 * Created by justy on 7/6/2016.
 */
public abstract class ItemMinimal extends Item {
    public ItemMinimal() {
        this.setUnlocalizedName(Reference.MOD_ID + ":" + getName());
        this.setCreativeTab(MinimalPeripherals.tabMinimalPeripherals);
    }

    public abstract String getName();
}
