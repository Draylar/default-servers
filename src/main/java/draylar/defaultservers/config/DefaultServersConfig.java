package draylar.defaultservers.config;

import draylar.omegaconfig.api.Config;

public class DefaultServersConfig implements Config {

    private final ServerData[] servers = new ServerData[0];

    @Override
    public String getName() {
        return "default-servers";
    }

    public ServerData[] getServers() {
        return servers;
    }
}
