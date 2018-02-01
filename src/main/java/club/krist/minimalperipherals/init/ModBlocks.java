package club.krist.minimalperipherals.init;

import club.krist.minimalperipherals.block.BlockChatBox;
import club.krist.minimalperipherals.block.BlockIronNoteblock;
import club.krist.minimalperipherals.block.BlockTest;
import club.krist.minimalperipherals.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by justy on 7/6/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static BlockTest circuit_block = new BlockTest();
    public static BlockChatBox chat_box = new BlockChatBox();
    public static BlockIronNoteblock iron_noteblock = new BlockIronNoteblock();

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            event.getRegistry().register(circuit_block.setRegistryName(circuit_block.getName()));
            event.getRegistry().register(chat_box.setRegistryName(chat_box.getName()));
            event.getRegistry().register(iron_noteblock.setRegistryName(iron_noteblock.getName()));
        }

        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
            event.getRegistry().register(new ItemBlock(circuit_block).setRegistryName(circuit_block.getName()));
            event.getRegistry().register(new ItemBlock(chat_box).setRegistryName(chat_box.getName()));
            event.getRegistry().register(new ItemBlock(iron_noteblock).setRegistryName(iron_noteblock.getName()));
        }
    }
}
