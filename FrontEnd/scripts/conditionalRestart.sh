#restarts the node.js if the requested branch is different to the current loaded branch

#Navigate to scripts folder
cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

requestedVersion=`./checkBranch.sh`

#if a different version has been requested to what is currently installed
if [$requestedVersion != `cat currentBranch.local`];
then
  echo "Changing branch"

  #restart the node.js process & re-pull from git
  ./restart.sh
fi