### Default Servers

*Default Servers* provides a simple solution for modpack creators trying to ship out default server lists to players.
Instead of overwriting the servers.dat file, which often leads to player-added servers being deleted on updates, 
Default Servers allows you to ship default data inside `config/default-servers.json`. This file is loaded at runtime
and will populate the servers.dat file if it does not exist.

### Usage

The default config file is composed of a list of server entries, as seen below:
```json
{
  "servers": [
    {
      "name": "All of Fabric",
      "ip": "127.0.0.1",
      "forced": false
    },
    {
      "name": "My Epic Server",
      "ip": "127.0.0.1",
      "resources": "enabled",
      "forced": false
    },
    {
      "name": "Gamer Time",
      "ip": "127.0.0.1",
      "forced": true
    }
  ]
}
```

`name` and `ip` fields are required, while `resources` and `forced` are optional.
Resources can be one of the following values:
- "enabled" - server will automatically download resource packs
- "disabled" - server will not download resource packs
- "prompt" - server will prompt for resource packs [default]

If `forced` is set to true (default: false), the server will be added to the servers.dat list on boot regardless of whether the file already exists.
If a server entry with the same IP address already exists, the entry will not be added.

### License

Default Servers is available under MIT.