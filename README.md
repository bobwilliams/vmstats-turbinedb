# vmstats TurbineDB

A simple scala script to log data from the vm_stat command into [turbinedb](http://turbinedb.com)

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

## License

The MIT License (MIT)

Copyright (c) 2015 Bob Williams

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.