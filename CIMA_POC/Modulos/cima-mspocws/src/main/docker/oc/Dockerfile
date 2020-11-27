FROM centos:7

RUN sed -i '/override_install_langs/d' /etc/yum.conf \
 && yum -y update && yum reinstall -y glibc-common \
 && yum -y install java-1.8.0-openjdk \
 && yum clean all \
 && rm -rf /var/cache/yum

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    HOME=/home \
    TZ=Europe/Madrid \
    LC_ALL=es_ES@euro \
    LANG=es_ES@euro \
    LANGUAGE=es_ES@euro

RUN echo 'LANG="es_ES@euro"' > /etc/locale.conf \
 && . /etc/locale.conf \
 && locale

WORKDIR $HOME

ADD /src/main/docker/entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh


ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 9080

ADD /target/*.jar app.jar