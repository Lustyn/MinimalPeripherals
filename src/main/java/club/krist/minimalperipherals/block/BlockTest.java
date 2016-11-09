package club.krist.minimalperipherals.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by justy on 7/6/2016.
 */
public class BlockTest extends BlockMinimal {

    public BlockTest() {
        super(Material.IRON, 1.5F);
    }

    @Override
    public String getName() {
        return "circuit_block";
    }
}
