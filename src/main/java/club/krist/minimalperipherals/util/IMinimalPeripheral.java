package club.krist.minimalperipherals.util;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by justy on 7/6/2016.
 */
public interface IMinimalPeripheral extends IPeripheral {
    class Provider implements IPeripheralProvider {
        @Override
        public IPeripheral getPeripheral(World world, BlockPos pos, EnumFacing facing) {
            TileEntity tile = world.getTileEntity(pos);
            return tile instanceof IMinimalPeripheral ? (IMinimalPeripheral) tile : null;
        }
    }
}
