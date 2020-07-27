#kills old node.js instance(s)
cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

#Sends a kill signal to any stuborn instances
for INSTANCE in `cat ./toKill.local`;
do
  kill $INSTANCE
  echo $INSTANCE >> toKill.local
done

echo > toKill.local

#Sends a quit signal to the previous instances
for INSTANCE in `cat ./pid.local`;
do
  kill -3 $INSTANCE
  echo $INSTANCE >> toKill.local
done

echo > pid.local