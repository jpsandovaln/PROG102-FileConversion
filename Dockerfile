FROM openjdk:11.0.8-slim-buster
RUN apt update && apt install unzip
COPY ./build/distributions/converter-boot-0.0.1-SNAPSHOT.zip /usr/src/
RUN unzip /usr/src/converter-boot-0.0.1-SNAPSHOT.zip -d /usr/src/
WORKDIR /usr/src/converter-boot-0.0.1-SNAPSHOT/bin/
CMD [ "/bin/bash", "-c", "/usr/src/converter-boot-0.0.1-SNAPSHOT/bin/converter"]
