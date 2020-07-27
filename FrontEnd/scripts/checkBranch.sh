#Navigate to scripts folder
cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

#Check requested branch
requestedBranch=`cat ./requestedBranch.local)`

#If requested branch is not stable
if [requestedBranch != "stable"];
then
  now=`date +%s`

  #check if request for a testing branch has expired
  if ((`cat requestedExpiry.local` \< now));
  then
    echo stable > ./requestedBranch.local
    requestedBranch="stable"
  fi
fi


echo $requestedBranch
