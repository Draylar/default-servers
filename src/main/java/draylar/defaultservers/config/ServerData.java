package draylar.defaultservers.config;

import net.minecraft.client.network.ServerInfo;

public class ServerData {

    private final String name;
    private final String ip;
    private final String resources;
    private final boolean forced;

    public ServerData(String name, String ip, String resources, boolean forced) {
        this.name = name;
        this.ip = ip;
        this.resources = resources;
        this.forced = forced;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public ServerInfo.ResourcePackState getResourcePolicy() {
        if(resources != null) {
            switch (resources) {
                case "enabled":
                    return ServerInfo.ResourcePackState.ENABLED;
                case "disabled":
                    return ServerInfo.ResourcePackState.DISABLED;
                case "prompt":
                    return ServerInfo.ResourcePackState.PROMPT;
            }
        }

        return ServerInfo.ResourcePackState.PROMPT;
    }

    public boolean isForced() {
        return forced;
    }
}
