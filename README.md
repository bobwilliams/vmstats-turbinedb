# vmstats-turbinedb

A simple scala script to log data from the vm_stat command into turbinedb (turbinedb.com)

## Usage
```sh
scala vmstats_metrics
```

## Daemon 
There is an accompanying plist if you wish to set this up as a daemon.  
To do so, please edit the paths in the following files for your location:
- ./daemon/com.sparcedge.vmstats.turbinedb.plist
- ./daemon/install.sh
- ./daemon/uninstall.sh

### to install the daemon
...from the root folder

```sh
cd ./daemon
sh install.sh
```

### to uninstall the daemon
...from the root folder

```sh
cd ./daemon
sh uninstall.sh
```