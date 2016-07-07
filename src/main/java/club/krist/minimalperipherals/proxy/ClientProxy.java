package club.krist.minimalperipherals.proxy;

import club.krist.minimalperipherals.client.ItemRenderRegister;
import club.krist.minimalperipherals.init.ModBlocks;
import club.krist.minimalperipherals.init.ModItems;

/**
 * Created by justy on 7/6/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenders() {
        ItemRenderRegister.init();
    }
}
