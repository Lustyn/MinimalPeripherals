package club.krist.minimalperipherals.tile;

import club.krist.minimalperipherals.util.LuaMethod;
import dan200.computercraft.api.lua.LuaException;
import net.minecraft.network.play.server.SPacketCustomSound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Created by justy on 7/6/2016.
 */
public class TileIronNoteblock extends TilePeripheral {
    public static final String name = "tileIronNoteblock";

    private final String[] instruments = {"harp", "basedrum", "snare", "hat", "bass"};

    private void playSound(BlockPos pos, String soundIn, SoundCategory category, float volume, float pitch) {
        FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendToAllNearExcept(null, pos.getX(), pos.getY(), pos.getZ(), volume > 1.0F ? (double)(16.0F * volume) : 16.0D, getWorld().provider.getDimension(), new SPacketCustomSound(soundIn, category, pos.getX(), pos.getY(), pos.getZ(), volume, pitch));
    }

    @LuaMethod
    public void playNote(Object[] arguments) throws LuaException {
        if (arguments.length < 2)
            throw new LuaException("Wrong number of arguments. 2 expected.");
        if (!(arguments[0] instanceof Double) | (int) (double) (Double) arguments[0] < 0 | (int) (double) (Double) arguments[0] > 4)
            throw new LuaException("Bad argument #1 (expected number 0-4)");
        if (!(arguments[1] instanceof Double))
            throw new LuaException("Bad argument #2 (expected number)");

        String instrument = "minecraft:block.note." + instruments[(int) (double) (Double) arguments[0]];
        float note = (float) Math.pow(2D, ((int) (double) (Double) arguments[1] - 12) / 12D);
        //getWorld().playSound(null, getPos().getX() + 0.5D, getPos().getY() + 0.5D, getPos().getZ() + 0.5D, new SoundEvent(new ResourceLocation(instrument)), SoundCategory.RECORDS, 3F, note);
        playSound(getPos(), instrument, SoundCategory.RECORDS, 1F, note);
    }

    @LuaMethod
    public void playSound(Object[] arguments) throws LuaException {
        if (arguments.length < 3)
            throw new LuaException("Wrong number of arguments. 3 expected.");
        if (!(arguments[0] instanceof String))
            throw new LuaException("Bad argument #1 (expected string)");
        if (!(arguments[1] instanceof Double))
            throw new LuaException("Bad argument #2 (expected number)");
        if (!(arguments[2] instanceof Double))
            throw new LuaException("Bad argument #3 (expected number)");

        String sound = (String) arguments[0];
        float volume = (float) (double) (Double) arguments[1];
        float pitch = (float) (double) (Double) arguments[2];
        playSound(getPos(), sound, SoundCategory.RECORDS, volume, pitch);
    }

    public String getType() {
        return "iron_noteblock";
    }
}
