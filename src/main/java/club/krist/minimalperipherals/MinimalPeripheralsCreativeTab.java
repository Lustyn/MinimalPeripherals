package club.krist.minimalperipherals;

import club.krist.minimalperipherals.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by justy on 7/6/2016.
 */
public class MinimalPeripheralsCreativeTab extends CreativeTabs {

    public MinimalPeripheralsCreativeTab(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(ModBlocks.circuit_block);
    }
}
