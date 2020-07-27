#Sends messages to log directory

#Navigate to scripts directory
cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

#sends messages to both launch.log (instance log) and launch.alog (appending/branch log)
nohup ./launch.sh | tee ../logs/launch.log >> ../logs/launch.alog 