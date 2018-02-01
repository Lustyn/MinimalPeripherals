package dan200.computercraft.api.network.wired;

import dan200.computercraft.api.network.IPacketNetwork;

import javax.annotation.Nonnull;

/**
 * The representation of {@link IWiredElement} inside the network.
 */
public interface IWiredNode extends IPacketNetwork
{
    /**
     * The associated element for this network node.
     *
     * @return This node's element.
     */
    @Nonnull
    IWiredElement getElement();

    /**
     * The network this node is currently connected to. Note that this may change
     * after any network operation, so it should not be cached.
     *
     * This should only be used on the server thread. Use {@link IPacketNetwork}
     * methods if you require thread safety.
     *
     * @return This node's network.
     */
    @Nonnull
    IWiredNetwork getNetwork();

    /**
     * Create a connection from this node to another.
     *
     * This should only be used on the server thread.
     *
     * @param node The other node to connect to.
     * @return {@code true} if a connection was created or {@code false} if the connection already exists.
     * @see IWiredNetwork#connect(IWiredNode, IWiredNode)
     */
    default boolean connectTo( @Nonnull IWiredNode node )
    {
        return getNetwork().connect( this, node );
    }

    /**
     * Destroy a connection between this node and another.
     *
     * This should only be used on the server thread.
     *
     * @param node The other node to disconnect from.
     * @return {@code true} if a connection was destroyed or {@code false} if no connection exists.
     * @throws IllegalArgumentException If {@code node} is not on the same network.
     * @see IWiredNetwork#disconnect(IWiredNode, IWiredNode)
     */
    default boolean disconnectFrom( @Nonnull IWiredNode node )
    {
        return getNetwork().disconnect( this, node );
    }

    default boolean remove()
    {
        return getNetwork().remove( this );
    }

    /**
     * Mark this node's peripherals as having changed.
     *
     * This should only be used on the server thread.
     *
     * @see IWiredElement#getPeripherals()
     */
    default void invalidate()
    {
        getNetwork().invalidate( this );
    }
}
