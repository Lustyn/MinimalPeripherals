package club.krist.minimalperipherals.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by justy on 7/7/2016.
 */
public class ModRecipes {
    public static void init() {
        //Circuit Block
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.circuit_block, 4), new Object[]{new ItemStack(Items.dye, 1, 2), new ItemStack(Items.redstone), new ItemStack(Items.gold_ingot)});

        //ChatBox & Iron Noteblock
        GameRegistry.addRecipe(new ItemStack(ModBlocks.chat_box), "#$#", "$%$", "#$#", '$', ModBlocks.circuit_block, '#', Items.gold_ingot, '%', Items.redstone);
        GameRegistry.addRecipe(new ItemStack(ModBlocks.iron_noteblock), "#$#", "$%$", "#$#", '$', ModBlocks.circuit_block, '#', Items.iron_ingot, '%', Items.redstone);
    }
}
