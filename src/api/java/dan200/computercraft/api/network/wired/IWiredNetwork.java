package dan200.computercraft.api.network.wired;

import dan200.computercraft.api.peripheral.IPeripheral;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public interface IWiredNetwork
{
    boolean connect( @Nonnull IWiredNode left, @Nonnull IWiredNode right );

    boolean disconnect( @Nonnull IWiredNode left, @Nonnull IWiredNode right );

    boolean remove( @Nonnull IWiredNode node );

    void invalidate( @Nonnull IWiredNode node );
    
    @Nonnull
    Map<String, IPeripheral> getPeripherals();

    @Nullable
    IPeripheral getPeripheral(String peripheral);
}
