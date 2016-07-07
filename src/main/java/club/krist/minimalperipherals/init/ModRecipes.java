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

        //Electronic Armor
        GameRegistry.addRecipe(new ItemStack(ModItems.electronic_helmet), "$#$", "# #", '$', ModBlocks.circuit_block, '#', ModItems.steel_ingot);
        GameRegistry.addRecipe(new ItemStack(ModItems.electronic_chestplate), "# #", "$#$", "#$#", '#', ModItems.steel_ingot, '$', ModBlocks.circuit_block);
        GameRegistry.addRecipe(new ItemStack(ModItems.electronic_leggings), "#$#", "$ $", "# #", '#', ModItems.steel_ingot, '$', ModBlocks.circuit_block);
        GameRegistry.addRecipe(new ItemStack(ModItems.electronic_boots), "$ $", "# #", '#', ModItems.steel_ingot, '$', ModBlocks.circuit_block);

        //Steel
        GameRegistry.addSmelting(Items.iron_ingot, new ItemStack(ModItems.steel_ingot), 0.7F);
        GameRegistry.addRecipe(new ItemStack(ModBlocks.steel_block), "###", "###", "###", '#', ModItems.steel_ingot);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.steel_ingot, 9), new Object[]{new ItemStack(ModBlocks.steel_block)});

        //ChatBox & Iron Noteblock
        GameRegistry.addRecipe(new ItemStack(ModBlocks.chat_box), "#$#", "$%$", "#$#", '$', ModBlocks.circuit_block, '#', Items.gold_ingot, '%', Items.redstone);
        GameRegistry.addRecipe(new ItemStack(ModBlocks.iron_noteblock), "#$#", "$%$", "#$#", '$', ModBlocks.circuit_block, '#', ModItems.steel_ingot, '%', Items.redstone);
    }
}
