FROM centos:7

RUN sed -i '/override_install_langs/d' /etc/yum.conf \
 && yum -y update && yum reinstall -y glibc-common \
 && yum -y install java-1.8.0-openjdk-devel nc telnet bind-utils openssh-clients net-tools ftp nano \
 && yum clean all \
 && rm -rf /var/cache/yum

ENV HOME=/home/cima \
    TZ=Europe/Madrid \
    LC_ALL=es_ES@euro \
    LANG=es_ES@euro \
    LANGUAGE=es_ES@euro

RUN useradd -u 1001 cima \
 && echo 'LANG="es_ES@euro"' > /etc/locale.conf \
 && . /etc/locale.conf \
 && locale

WORKDIR $HOME

USER cima

ENTRYPOINT ["/bin/sh"]
