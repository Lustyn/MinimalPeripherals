package club.krist.minimalperipherals;

import club.krist.minimalperipherals.init.ModBlocks;
import club.krist.minimalperipherals.init.ModItems;
import club.krist.minimalperipherals.init.ModRecipes;
import club.krist.minimalperipherals.proxy.CommonProxy;
import club.krist.minimalperipherals.reference.Reference;
import club.krist.minimalperipherals.tile.TileChatBox;
import club.krist.minimalperipherals.util.IMinimalPeripheral;
import dan200.computercraft.api.ComputerCraftAPI;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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

        ModBlocks.init();
        ModItems.init();
        proxy.registerTileEntities();
        ModRecipes.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerRenders();
        ComputerCraftAPI.registerPeripheralProvider(new IMinimalPeripheral.Provider());
    }
}
