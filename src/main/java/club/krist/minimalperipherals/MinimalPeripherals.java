package club.krist.minimalperipherals;

import club.krist.minimalperipherals.init.ModBlocks;
import club.krist.minimalperipherals.init.ModItems;
import club.krist.minimalperipherals.init.ModRecipes;
import club.krist.minimalperipherals.proxy.CommonProxy;
import club.krist.minimalperipherals.reference.Reference;
import club.krist.minimalperipherals.tile.TileChatBox;
import club.krist.minimalperipherals.util.IMinimalPeripheral;
import dan200.computercraft.api.ComputerCraftAPI;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by justy on 7/6/2016.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class MinimalPeripherals {
    @Mod.Instance(Reference.MOD_ID)
    public static MinimalPeripherals instance;

    public static final MinimalPeripheralsCreativeTab tabMinimalPeripherals = new MinimalPeripheralsCreativeTab("tabMinimalPeripherals");

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new TileChatBox.ChatListener());
        proxy.registerTileEntities();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerRenders();
        ComputerCraftAPI.registerPeripheralProvider(new IMinimalPeripheral.Provider());
    }
}
