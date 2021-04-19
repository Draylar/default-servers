package draylar.defaultservers;

import draylar.defaultservers.config.DefaultServersConfig;
import draylar.defaultservers.config.ServerData;
import draylar.omegaconfig.OmegaConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.options.ServerList;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Environment(EnvType.CLIENT)
public class DefaultServers implements ClientModInitializer {

    public static final DefaultServersConfig CONFIG = OmegaConfig.register(DefaultServersConfig.class);

    @Override
    public void onInitializeClient() {
        ServerList list = new ServerList(MinecraftClient.getInstance());

        // If the servers.dat file has not been saved previously, create it now with values from the default-servers.json config file.
        if(!Files.exists(Paths.get(FabricLoader.getInstance().getGameDir().toString(), "servers.dat")))  {
            for (ServerData server : CONFIG.getServers()) {
                ServerInfo serverInfo = new ServerInfo(server.getName(), server.getIp(), false);
                serverInfo.setResourcePackState(server.getResourcePolicy());
                list.add(serverInfo);
            }
        }

        // File already exists. Check for any forceful server entries and add them now.
        else {
            list.loadFile();

            Arrays.asList(CONFIG.getServers()).forEach(server -> {
                if(server.isForced()) {
                    // check if any existing servers match
                    for(int i = 0; i < list.size(); i++) {
                        ServerInfo existing = list.get(i);

                        // If one of the existing IP entries matches, do not add this forced server addition
                        if(existing.address.equals(server.getIp())) {
                            return;
                        }
                    }

                    // Checks passed, add the forced server now!
                    ServerInfo serverInfo = new ServerInfo(server.getName(), server.getIp(), false);
                    serverInfo.setResourcePackState(server.getResourcePolicy());
                    list.add(serverInfo);
                }
            });
        }

        // Save the modified servers.dat file to disk.
        list.saveFile();
    }
}
