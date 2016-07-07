package club.krist.minimalperipherals.tile;

import club.krist.minimalperipherals.util.LuaMethod;
import dan200.computercraft.api.lua.LuaException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.*;

/**
 * Created by justy on 7/6/2016.
 */
public class TileChatBox extends TilePeripheral {
    public static final String name = "chat_box";
    protected static ArrayList<TileChatBox> chat_boxes = new ArrayList<TileChatBox>();

    public TileChatBox() {
        chat_boxes.add(this);
    }

    @LuaMethod
    public boolean say(Object[] arguments) throws LuaException {
        if (arguments.length < 1) {
            throw new LuaException("Wrong number of arguments. Minimum 1 expected.");
        }
        if (!(arguments[0] instanceof String))
            throw new LuaException("Invalid argument #1. Expected string.");

        String label = "ChatBox";
        if (arguments.length > 1) {
            if (!(arguments[1] instanceof String))
                throw new LuaException("Invalid argument #2. Expected string.");
            label = (String) arguments[1];
        }

        String pre = "";
        if (arguments.length > 2) {
            if (!(arguments[2] instanceof String))
                throw new LuaException("Invalid argument #3. Expected string.");
            pre = (String) arguments[2];
        }

        String message = (String) arguments[0];
        String formatted_message = String.format("%s[%d,%d,%d] %s: %s", pre, getPos().getX(), getPos().getY(), getPos().getZ(), label, message);
        sendMessage(formatted_message);
        return true;
    }

    @LuaMethod
    public boolean tell(Object[] arguments) throws LuaException {
        if (arguments.length < 1) {
            throw new LuaException("Wrong number of arguments. Minimum 1 expected.");
        }
        if (!(arguments[0] instanceof String))
            throw new LuaException("Invalid argument #1. Expected string.");
        if (!(arguments[1] instanceof String))
            throw new LuaException("Invalid argument #2. Expected string.");
        String label = "ChatBox";
        if (arguments.length > 2) {
            if (!(arguments[2] instanceof String))
                throw new LuaException("Invalid argument #3. Expected string.");
            label = (String) arguments[2];
        }

        String pre = "";
        if (arguments.length > 3) {
            if (!(arguments[3] instanceof String))
                throw new LuaException("Invalid argument #4. Expected string.");
            pre = (String) arguments[3];
        }

        String player = (String) arguments[0];
        String message = (String) arguments[1];
        String formatted_message = String.format("%s[%d,%d,%d][PM] %s: %s", pre, getPos().getX(), getPos().getY(), getPos().getZ(), label, message);

        EntityPlayer recipient = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(player);
        if (recipient != null) {
            recipient.addChatMessage(new ChatComponentText(formatted_message));
            return true;
        }
        return false;
    }

    private void sendMessage(String message) {
        List<? extends EntityPlayer> players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;

        for (EntityPlayer player : players) {
            player.addChatMessage(new ChatComponentText(message));
        }
    }

    @Override
    public String getType() {
        return "chat_box";
    }

    public static class ChatListener {
        @SubscribeEvent
        public void onChat(ServerChatEvent event) {
            if (event.message.startsWith("\\")) {
                String commandName = event.message.substring(1);
                if (commandName.contains(" ")) {
                    commandName = commandName.substring(0, commandName.indexOf(" "));
                }
                String[] args = new String[] {};
                if (event.message.contains(" ")) {
                    args = event.message.substring(event.message.indexOf(" ") + 1).split("\\s+");
                }

                for (TileChatBox chatBox : TileChatBox.chat_boxes) {
                    Map<Integer, Object> indexedMap = new HashMap<Integer, Object>();
                    List list = Arrays.asList(args);
                    for (Object obj : list) {
                        indexedMap.put(list.indexOf(obj) + 1, obj);
                    }
                    chatBox.queueEvent("command", new Object[] {event.username, commandName, indexedMap});
                }
                event.setCanceled(true);
            } else {
                for (TileChatBox chatBox : TileChatBox.chat_boxes) {
                    chatBox.queueEvent("chat", new Object[] {event.username, event.message});
                }
            }
        }

        @SubscribeEvent
        public void onDeath(LivingDeathEvent event) {
            if (event.entity instanceof EntityPlayer) {
                String killerName = null;
                if (event.source instanceof EntityDamageSource) {
                    killerName = event.source.getEntity().getName();
                }

                for (TileChatBox chatBox : TileChatBox.chat_boxes) {
                    chatBox.queueEvent("death", new Object[] {event.entity.getName(), killerName, event.source.damageType});
                }
            }
        }
    }
}
