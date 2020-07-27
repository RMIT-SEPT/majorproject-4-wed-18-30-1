cd /home/remote/app

#Navigate to scripts directory
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

#If a different branch is requested to the currently loaded branch
if [`cat $DIR/currentBranch.local` != $1];
then
  git pull $1 remote
  echo $1 > ./currentBranch.local

  #Grant 30 minutes before reverting to stable
  echo $((`date +%s`+1800)) > requestedExpiry.local
fi