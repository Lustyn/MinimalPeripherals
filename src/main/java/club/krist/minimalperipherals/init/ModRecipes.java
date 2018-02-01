package club.krist.minimalperipherals.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by justy on 7/7/2016.
 */
public class ModRecipes {
    /*public static void init(RegistryEvent.Register<IRecipe> event) {
        //Circuit Block
        event.getRegistry().register(new ShapelessRecipes());
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.circuit_block, 4), new Object[]{new ItemStack(Items.DYE, 1, 2), new ItemStack(Items.REDSTONE), new ItemStack(Items.GOLD_INGOT)});

        //ChatBox & Iron Noteblock
        GameRegistry.addRecipe(new ItemStack(ModBlocks.chat_box), "#$#", "$%$", "#$#", '$', ModBlocks.circuit_block, '#', Items.GOLD_INGOT, '%', Items.REDSTONE);
        GameRegistry.addRecipe(new ItemStack(ModBlocks.iron_noteblock), "#$#", "$%$", "#$#", '$', ModBlocks.circuit_block, '#', Items.IRON_INGOT, '%', Items.REDSTONE);
    }*/
}
