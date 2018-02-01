package club.krist.minimalperipherals.tile;

import club.krist.minimalperipherals.util.LuaMethod;
import dan200.computercraft.api.lua.LuaException;
import net.minecraft.block.BlockPurpurSlab;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.*;

/**
 * Created by justy on 7/6/2016.
 */
public class TileChatBox extends TilePeripheral {
    public static final String name = "chat_box";
    protected static ArrayList<TileChatBox> chat_boxes = new ArrayList<TileChatBox>();
    protected static HashMap<TileChatBox, Long> chat_times = new HashMap<TileChatBox, Long>();

    public TileChatBox() {
        chat_boxes.add(this);
        chat_times.put(this, 0L);
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

        if (System.currentTimeMillis() - chat_times.get(this) < 1000L) {
            throw new LuaException("Throttled. Max message per second of 1.");
        }

        String message = (String) arguments[0];
        String formatted_message = String.format("%s[%d,%d,%d] %s: %s", pre, getPos().getX(), getPos().getY(), getPos().getZ(), label, message);
        for (TileChatBox chatBox : TileChatBox.chat_boxes) {
            if(chatBox != this) {
                chatBox.queueEvent("chatbox", new Object[]{pre, getPos().getX(), getPos().getY(), getPos().getZ(), label, message});
            }
        }
        sendMessage(formatted_message);
        chat_times.put(this, System.currentTimeMillis());
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

        if (System.currentTimeMillis() - chat_times.get(this) < 1000) {
            throw new LuaException("Throttled. Max message per second of 1.");
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

        EntityPlayer recipient = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(player);
        if (recipient != null) {
            recipient.sendMessage(new TextComponentString(formatted_message));
            return true;
        }
        return false;
    }

    @LuaMethod
    public Map<Integer, Object> getPlayerList(Object[] arguments) throws LuaException {
        Map<Integer, Object> indexedMap = new HashMap<Integer, Object>();
        List<? extends EntityPlayer> players = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers();
      
        for(EntityPlayer p : players) {
            indexedMap.put(players.indexOf(p) + 1, p.getName());
        }
        return indexedMap;
    }

    private void sendMessage(String message) {
        List<? extends EntityPlayer> players = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers();

        for (EntityPlayer player : players) {
            player.sendMessage(new TextComponentString(message));
        }
    }

    @Override
    public String getType() {
        return "chat_box";
    }

    public static class ChatListener {
        @SubscribeEvent
        public void onChat(ServerChatEvent event) {
            if (event.getMessage().startsWith("\\")) {
                String commandName = event.getMessage().substring(1);
                if (commandName.contains(" ")) {
                    commandName = commandName.substring(0, commandName.indexOf(" "));
                }
                String[] args = new String[] {};
                if (event.getMessage().contains(" ")) {
                    args = event.getMessage().substring(event.getMessage().indexOf(" ") + 1).split("\\s+");
                }

                for (TileChatBox chatBox : TileChatBox.chat_boxes) {
                    Map<Integer, Object> indexedMap = new HashMap<Integer, Object>();
                    List list = Arrays.asList(args);
                    for (Object obj : list) {
                        indexedMap.put(list.indexOf(obj) + 1, obj);
                    }
                    chatBox.queueEvent("command", new Object[] {event.getUsername(), commandName, indexedMap});
                }
                event.setCanceled(true);
            } else {
                for (TileChatBox chatBox : TileChatBox.chat_boxes) {
                    chatBox.queueEvent("chat", new Object[] {event.getUsername(), event.getMessage()});
                }
            }
        }

        @SubscribeEvent
        public void onDeath(LivingDeathEvent event) {
            if (event.getEntity() instanceof EntityPlayer) {
                String killerName = null;
                if (event.getSource() instanceof EntityDamageSource) {
                    killerName = event.getSource().getTrueSource().getName();
                }

                for (TileChatBox chatBox : TileChatBox.chat_boxes) {
                    chatBox.queueEvent("death", new Object[] {event.getEntity().getName(), killerName, event.getSource().damageType});
                }
            }
        }

        @SubscribeEvent
        public void onJoin(PlayerEvent.PlayerLoggedInEvent ev) {
            for (TileChatBox chatBox : TileChatBox.chat_boxes) {
                chatBox.queueEvent("join", new Object[] {ev.player.getName()});
            }
        }

        @SubscribeEvent
        public void onLeave(PlayerEvent.PlayerLoggedOutEvent ev) {
            for (TileChatBox chatBox : TileChatBox.chat_boxes) {
                chatBox.queueEvent("leave", new Object[] {ev.player.getName()});
            }
        }
    }
}
