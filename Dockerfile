FROM jenkins/jenkins:lts

# Copia scripts de inicialização do Jenkins
COPY jenkins/init.groovy.d/ /usr/share/jenkins/ref/init.groovy.d/

USER root

# Atualiza e instala Maven e OpenJDK 17
RUN apt-get update && \
    apt-get install -y maven openjdk-17-jdk && \
    apt-get clean

USER jenkins

# Define JAVA_HOME
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=/usr/etc/bin:$JAVA_HOME/bin:$PATH
