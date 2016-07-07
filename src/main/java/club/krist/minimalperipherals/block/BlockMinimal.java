package club.krist.minimalperipherals.block;

import club.krist.minimalperipherals.MinimalPeripherals;
import club.krist.minimalperipherals.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by justy on 7/6/2016.
 */
public abstract class BlockMinimal extends Block {
    public BlockMinimal(Material materialIn, float hardness) {
        super(materialIn);
        this.setUnlocalizedName(Reference.MOD_ID + ":" + getName());
        this.setCreativeTab(MinimalPeripherals.tabMinimalPeripherals);
        this.setResistance(10.0F);
        this.setHardness(hardness);
        this.setHarvestLevel("pickaxe", 1);
    }

    public abstract String getName();
}
