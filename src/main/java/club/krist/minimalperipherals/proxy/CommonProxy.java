package club.krist.minimalperipherals.proxy;

import club.krist.minimalperipherals.tile.TileChatBox;
import club.krist.minimalperipherals.tile.TileIronNoteblock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by justy on 7/6/2016.
 */
public class CommonProxy {
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileChatBox.class, TileChatBox.name);
        GameRegistry.registerTileEntity(TileIronNoteblock.class, TileIronNoteblock.name);
    }

    public void registerRenders() {

    }
}
