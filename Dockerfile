FROM ibmjava:8-sdk AS builder
LABEL maintainer="IBM hackathon"

WORKDIR /app
RUN apt-get update && apt-get install -y maven

RUN addgroup -S -g 1001 axonserver \
    && adduser -S -u 1001 -h /axonserver -D axonserver \
    && mkdir -p /axonserver/config /axonserver/data /axonserver/events /axonserver/log \
    && chown -R axonserver:axonserver /axonserver
    
COPY --from=source /etc/passwd /etc/group /etc/
COPY --from=source --chown=axonserver /axonserver /axonserver

COPY --chown=axonserver axonserver.jar axonserver.properties /axonserver/

COPY pom.xml .
RUN mvn -N io.takari:maven:wrapper -Dmaven=3.5.0

COPY . /app
RUN ./mvnw install

ARG bx_dev_user=root
ARG bx_dev_userid=1000
RUN BX_DEV_USER=$bx_dev_user
RUN BX_DEV_USERID=$bx_dev_userid
RUN if [ $bx_dev_user != "root" ]; then useradd -ms /bin/bash -u $bx_dev_userid $bx_dev_user; fi

FROM adoptopenjdk/openjdk8:ubi-jre

# Copy over app from builder image into the runtime image.
RUN mkdir /opt/app
COPY --from=builder /app/target/order-service-1.0-SNAPSHOT.jar /opt/app/app.jar
EXPOSE 8024/tcp 8124/tcp 8224/tcp

CMD [ "java", "-jar", "axonserver.jar" ]
CMD [ "sh", "-c", "java -jar /opt/app/app.jar" ]
