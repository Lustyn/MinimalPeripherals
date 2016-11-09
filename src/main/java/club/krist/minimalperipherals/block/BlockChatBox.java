package club.krist.minimalperipherals.block;

import club.krist.minimalperipherals.tile.TileChatBox;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by justy on 7/6/2016.
 */
public class BlockChatBox extends BlockMinimal implements ITileEntityProvider {
    public BlockChatBox() {
        super(Material.IRON, 1.5F);
    }

    @Override
    public String getName() {
        return "chat_box";
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileChatBox();
    }
}
