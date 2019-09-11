# rabbitmq-example

ActiveMQ Installation:

SO used for this example : Ubuntu 19.04
 - Add RabbitMQ signing key, this will instruct apt to trust packages signed by that key:
     - curl -fsSL https://github.com/rabbitmq/signing-keys/releases/download/2.0/rabbitmq-release-signing-key.asc | sudo apt-key add -
 - Install apt-transport-https, this will download RabbitMq and Erlang packages:
     - sudo apt-get install apt-transport-https
 - Add Bintray repositories that provision latest RabbitMQ and Erlang 21.x releases, next four lines are one single command, run it from terminal:
	sudo tee /etc/apt/sources.list.d/bintray.rabbitmq.list <<EOF
	deb https://dl.bintray.com/rabbitmq-erlang/debian bionic erlang-21.x
	deb https://dl.bintray.com/rabbitmq/debian bionic main
	EOF
 - Update package indexes:
	sudo apt-get update -y

 - Install rabbitmq-server and its dependencies:
	sudo apt-get install rabbitmq-server -y --fix-missing

 - Start RabbitMQ server:
	sudo systemctl start rabbitmq-server.service

 - Start RabbitMQ server on Linux boot:
	sudo systemctl enable rabbitmq-server.service

 - Check server status
	sudo rabbitmqctl status

 - Enable Web Admin Console
	sudo rabbitmq-plugins enable rabbitmq_management
	sudo chown -R rabbitmq:rabbitmq /var/lib/rabbitmq/

 - Add admin user
	sudo rabbitmqctl add_user rmqadmin yourPassword
	sudo rabbitmqctl set_user_tags rmqadmin administrator
	sudo rabbitmqctl set_permissions -p / rmqadmin ".*" ".*" ".*"

 - Navigate to admin console: http://localhost:15672
