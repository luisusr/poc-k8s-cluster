FROM ubuntu

RUN apt-get update && apt-get install -y nano curl locales \
  && apt-get clean \
  && mkdir /opt/java
 
COPY sdk/java/ /opt/java

RUN chown -R root:root /opt/java/ && ln -s /opt/java/jdk-8/bin/* /usr/bin/

# Add user to run our application so that it doesn't need to run as root
RUN useradd -u 1002 cima \
  && echo 'es_ES@euro ISO-8859-15' > /etc/locale.gen \
  && locale-gen

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    HOME=/home/cima \
    APP_HOME=Modulos/cima-mspocws \
    TZ=Europe/Madrid \
    LANG=es_ES@euro \
    LANGUAGE=es_ES@euro \
    LC_ALL=es_ES@euro

WORKDIR $HOME

ADD $APP_HOME/src/main/java/Primos.java /tmp/Primos.java

ADD $APP_HOME/src/main/docker/entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown cima:cima entrypoint.sh
USER cima

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 9080

ADD $APP_HOME/target/*.jar app.jar
