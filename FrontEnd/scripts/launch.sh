#Starts a node.js using the current server.js
echo $$ >> pid.local
echo "Launching Server with PID: [$$]"
node /home/remote/app/FrontEnd/server.js