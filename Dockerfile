FROM rabbitmq:3.8-rc-management-alpine

ADD docker_config/rabbitmq.conf /etc/rabbitmq/
ADD docker_config/definitions.json /etc/rabbitmq/