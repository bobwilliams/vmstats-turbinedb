DIR_LOCATION='/Users/bob/workspace/vmstats-turbinedb'

sudo chown root $DIR_LOCATION/daemon/com.sparcedge.vmstats.plist
sudo launchctl stop com.sparcedge.vmstats
sudo launchctl unload $DIR_LOCATION/daemon/com.sparcedge.vmstats.plist