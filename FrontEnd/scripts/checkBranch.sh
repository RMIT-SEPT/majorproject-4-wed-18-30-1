#returns a string of the currently requested branch

#Navigate to scripts folder
cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

#Check requested branch
currentBranch=`cat ./currentBranch.local`

echo "Checking if update is required"

#If requested branch is not stable
if [currentBranch != "stable"];
then
  now=`date +%s`

  #check if request for a testing branch has expired
  if ((`cat requestExpiry.local` < "$now"));
  then
    echo "Past request has expired, reverting to branch [stable]"
    echo stable > ./currentBranch.local
    currentBranch="stable"
  fi
fi

echo $currentBranch
