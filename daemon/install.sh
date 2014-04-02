DIR_LOCATION='/Users/bob/workspace/vmstats-turbinedb'

sudo chown root $DIR_LOCATION/daemon/com.sparcedge.vmstats.plist
sudo launchctl unload $DIR_LOCATION/daemon/com.sparcedge.vmstats.plist
sudo launchctl load $DIR_LOCATION/daemon/com.sparcedge.vmstats.plist
sudo launchctl start com.sparcedge.vmstats
sudo launchctl list | grep com.sparcedge.vmstats