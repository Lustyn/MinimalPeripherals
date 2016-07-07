package club.krist.minimalperipherals.item;

import club.krist.minimalperipherals.reference.Reference;
import com.sun.org.apache.xml.internal.security.utils.I18n;
import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.filesystem.IMount;
import dan200.computercraft.api.media.IMedia;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by justy on 7/6/2016.
 */
public class ItemElectronicArmor extends ItemMinimalArmor implements IMedia {

    public ItemElectronicArmor(int renderIndex, int armorType) {
        super(EnumHelper.addArmorMaterial("ELECTRONIC", Reference.MOD_ID + ":electronic", 40, new int[]{5, 5, 5, 5}, 25), renderIndex, armorType);
    }

    public String getName() {
        return "electronic" + "_" + ArmorType.values()[armorType];
    }

    @Override
    public String getLabel(ItemStack stack) {
        if (stack.getSubCompound("display", true).getString("Name") == "") {
            return null;
        }
        return stack.getSubCompound("display", true).getString("Name");
    }

    @Override
    public boolean setLabel(ItemStack stack, String label) {
        stack.getSubCompound("display", true).setString("Name", label);
        return true;
    }

    @Override
    public String getAudioTitle(ItemStack stack) {
        return null;
    }

    @Override
    public String getAudioRecordName(ItemStack stack) {
        return null;
    }

    @Override
    public IMount createDataMount(ItemStack stack, World world) {
        boolean hasId = false;
        try {
            if (stack.getTagCompound().getInteger("HelmetId") > 0) hasId = true;
        } catch (NullPointerException e) {
            //we good
        }
        if (!hasId) {
            stack.setTagInfo("FloppyId", new NBTTagInt(ComputerCraftAPI.createUniqueNumberedSaveDir(world, "computer/armor")));
        }
        int uniqueId = stack.getTagCompound().getInteger("FloppyId");
        return ComputerCraftAPI.createSaveDirMount(world, "computer/armor/" + uniqueId, 1000000);
    }
}
