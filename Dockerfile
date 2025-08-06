FROM jenkins/jenkins:lts

USER root

# Atualiza e instala Maven e OpenJDK 17
RUN apt-get update && \
    apt-get install -y maven openjdk-17-jdk && \
    apt-get clean

USER jenkins

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH
