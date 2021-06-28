FROM containers.oracledatacloud.com/odc-fintech/serverjre:8
VOLUME /tmp

RUN mkdir /appdir /app
#RUN yum update -y && yum install vim -y
RUN yum install vim -y
#COPY ./kubernetes/application.properties /appdir/
ARG DEPENDENCY=./temp/
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app/BOOT-INF/classes
COPY ./startSpringBoot.sh /app/

RUN rm -rf /app/application.properties
RUN chmod -R 774 /app
RUN chmod +x /app/startSpringBoot.sh

CMD /bin/sh /app/startSpringBoot.sh
