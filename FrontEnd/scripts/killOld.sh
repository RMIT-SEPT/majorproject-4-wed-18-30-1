#kills old node.js instance(s)
cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

#Sends a kill signal to any stubborn instances
for INSTANCE in `cat ./toKill.local`;
do
  kill $INSTANCE
  echo $INSTANCE >> toKill.local
done

#Clear the kill list
echo > toKill.local

#Sends a quit signal to the previous instances
for INSTANCE in `cat ./pid.local`;
do
  echo "Stopping previous server: [$INSTANCE]"
  kill -3 $INSTANCE
  echo $INSTANCE >> toKill.local
done

#Clear the running list
echo > pid.local

echo "Old processes are purged, ready to launch new process"