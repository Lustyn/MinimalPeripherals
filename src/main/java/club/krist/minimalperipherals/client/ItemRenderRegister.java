package club.krist.minimalperipherals.client;

import club.krist.minimalperipherals.block.BlockChatBox;
import club.krist.minimalperipherals.init.ModBlocks;
import club.krist.minimalperipherals.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;

/**
 * Created by justy on 7/7/2016.
 */
public class ItemRenderRegister {
    public static void init() {
        register(Item.getItemFromBlock(ModBlocks.chat_box));
        register(Item.getItemFromBlock(ModBlocks.iron_noteblock));
        register(Item.getItemFromBlock(ModBlocks.circuit_block));
        register(Item.getItemFromBlock(ModBlocks.steel_block));
        register(ModItems.electronic_helmet);
        register(ModItems.electronic_chestplate);
        register(ModItems.electronic_leggings);
        register(ModItems.electronic_boots);
        register(ModItems.steel_ingot);
    }

    private static void register(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getUnlocalizedName().substring(5), "inventory"));
    }
}
