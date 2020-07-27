#returns a string of the currently requested branch

#Navigate to scripts folder
cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

#Check requested branch
currentBranch=`cat ./currentBranch.local)`

#If requested branch is not stable
if [currentBranch != "stable"];
then
  now=`date +%s`

  #check if request for a testing branch has expired
  if ((`cat requestExpiry.local` \< now));
  then
    echo stable > ./currentBranch.local
    currentBranch="stable"
  fi
fi


echo $currentBranch
