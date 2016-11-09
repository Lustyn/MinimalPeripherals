package club.krist.minimalperipherals.block;

import club.krist.minimalperipherals.tile.TileIronNoteblock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by justy on 7/6/2016.
 */
public class BlockIronNoteblock extends BlockMinimal implements ITileEntityProvider {
    public BlockIronNoteblock(){
        super(Material.IRON, 1.5F);
    }

    @Override
    public String getName() {
        return "iron_noteblock";
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileIronNoteblock();
    }
}
