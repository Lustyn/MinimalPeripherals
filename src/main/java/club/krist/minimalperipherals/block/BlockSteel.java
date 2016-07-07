package club.krist.minimalperipherals.block;

import net.minecraft.block.material.Material;

/**
 * Created by justy on 7/7/2016.
 */
public class BlockSteel extends BlockMinimal {
    public BlockSteel() {
        super(Material.iron, 1.5F);
    }

    @Override
    public String getName() {
        return "steel_block";
    }
}
