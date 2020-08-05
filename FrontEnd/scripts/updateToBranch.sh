#pulls the branch named in the first parameter

cd /home/remote/app

#Navigate to scripts directory
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

git pull origin $1
echo $1 > $DIR/currentBranch.local

if ["$1" != "stable"];
then
  #Grant 30 minutes before reverting to stable
  echo $((`date +%s`+1800)) > $DIR/requestExpiry.local
else
  #wait for an hour before checking for a new stable branch
  echo $((`date +%s`+3600)) > $DIR/requestExpiry.local  
fi