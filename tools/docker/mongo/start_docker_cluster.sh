#!/bin/bash
# Emmanuel Deviller

#########################
# Copy mongo scripts and template them
PWD=`pwd`
CUR_DIR=$PWD
DEPLOYMENT_PATH="../../../deployment/"

#echo "Remove old files (mongo-entrypoint)."
#rm mongo-entrypoint/last/*

echo "Execute playbooks/tools/database_scripts_templater with custom variables."
cd $DEPLOYMENT_PATH
ansible-playbook -i environment/hosts playbooks/tools/database_scripts_templater.yml -e "@$CUR_DIR/mongo_vars_dev.yml"
cd -

#########################

docker-compose -f ./mongo_cluster.yml up -d

sleep 2

# Create replica set and wait a few before execution other init scripts
docker exec -it vitamui-mongo /bin/bash -c "mongo --port=27018 < /vitamui/scripts/mongo/replica-set/000_replicaset_dev.js;sleep 5;mongo --port=27018 < /vitamui/scripts/mongo/replica-set/00_check_replicaset.js"

docker exec -it vitamui-mongo bash -c "cat /vitamui/scripts/mongo/data/last/*.js |  mongo --port=27018 "

echo "vitamui-mongo is started"
