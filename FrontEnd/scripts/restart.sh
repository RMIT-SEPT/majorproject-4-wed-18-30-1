#Restarts Node.js with then newest version of the current branch 

#Kills current server pages
#Updates to most recent requested branch
#Starts serving that branch

#Navigate to scripts directory
cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

#Kill old process
./killOld.sh

#Update to requested branch
./updateToBranch.sh $(./checkBranch.sh)

#Restart service
./launch.sh

