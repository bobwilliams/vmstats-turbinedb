# vmstats-turbinedb

A simple scala script to log data from the vm_stat command into turbinedb (turbinedb.com)

## Usage
You can run either separately via the command line, optionally specifying a config file; 
or via the daemon.

Default values are as follows:
```sh
{
	"server": "http://localhost:8080",
	"database": "usagestats",
	"collection": "vm_stats"
}
```

### Command Line

```sh
scala vmstats_metrics <config.json>
```

### Daemon 
To run via a daemon please edit the paths in the following files for your location:
- ./daemon/com.sparcedge.vmstats.turbinedb.plist (you may specify the config.json here too)
- ./daemon/install.sh
- ./daemon/uninstall.sh

#### to install the daemon
...from the root folder

```sh
cd ./daemon
sh install.sh
```

#### to uninstall the daemon
...from the root folder

```sh
cd ./daemon
sh uninstall.sh
```