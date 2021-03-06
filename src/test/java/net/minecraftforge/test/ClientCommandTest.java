package net.minecraftforge.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameData;

import javax.annotation.Nullable;

@Mod(modid="clientcommandtest", name="Client Command Test", version="0.0.0", clientSideOnly = true)
public class ClientCommandTest {
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientCommandHandler.instance.registerCommand(new TestCommand());
    }

    private class TestCommand extends CommandBase {
        @Override
        public String getCommandName()
        {
            return "clientCommandTest";
        }

        @Override
        public String getCommandUsage(ICommandSender sender)
        {
            return "clientCommandTest <block>";
        }

        @Override
        public boolean checkPermission(MinecraftServer server, ICommandSender sender)
        {
            return true;
        }

        @Override
        public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos)
        {
            if (args.length > 0)
            {
                return getListOfStringsMatchingLastWord(args, GameData.getBlockRegistry().getKeys());
            }

            return Collections.emptyList();
        }

        @Override
        public void execute( MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
        {
            if (args.length > 0)
            {
                sender.addChatMessage(new TextComponentString("Input: " + Arrays.toString(args)));
            }
            else
            {
                sender.addChatMessage(new TextComponentString("No arguments."));
            }
        }
    }
}
