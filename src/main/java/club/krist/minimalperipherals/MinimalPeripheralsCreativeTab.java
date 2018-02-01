package club.krist.minimalperipherals;

import club.krist.minimalperipherals.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by justy on 7/6/2016.
 */
public class MinimalPeripheralsCreativeTab extends CreativeTabs {

    public MinimalPeripheralsCreativeTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModBlocks.circuit_block);
    }
}
