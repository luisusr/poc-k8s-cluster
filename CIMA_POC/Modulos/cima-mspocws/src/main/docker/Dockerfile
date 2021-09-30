FROM centos:7

RUN sed -i '/override_install_langs/d' /etc/yum.conf \
 && yum -y update && yum reinstall -y glibc-common \
 && yum -y install nano \
 && yum clean all \
 && rm -rf /var/cache/yum \
 && mkdir /opt/java
 
ADD sdk/java/jdk-8.tar.gz /opt/java

RUN chown -R root:root /opt/java/ && ln -s /opt/java/jdk-8/bin/* /usr/bin/

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    HOME=/home/cima \
    APP_HOME=Modulos/cima-mspocws \
    TZ=Europe/Madrid \
    LC_ALL=es_ES@euro \
    LANG=es_ES@euro \
    LANGUAGE=es_ES@euro

# Add user to run our application so that it doesn't need to run as root
RUN useradd -u 1002 cima \
 && echo 'LANG="es_ES@euro"' > /etc/locale.conf \
 && . /etc/locale.conf \
 && locale
 
WORKDIR $HOME

ADD $APP_HOME/src/main/java/Primos.java /tmp/Primos.java

ADD $APP_HOME/src/main/docker/entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown cima:cima entrypoint.sh
USER cima

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 9080

ADD $APP_HOME/target/*.jar app.jar