# ====================================================================
# Copyright (c) 2018 Obeo
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#    Obeo - initial API and implementation
# ====================================================================

FROM openjdk:10-jre

LABEL maintainer="https://www.eclipse.org/sirius"

LABEL org.label-schema.schema-version="1.0"
LABEL org.label-schema.name="eclipsesirius/sirius-server"
LABEL org.label-schema.description="Eclipse Sirius - Server"
LABEL org.label-schema.url="https://www.eclipse.org/sirius"
LABEL org.label-schema.vcs-url="http://git.eclipse.org/c/sirius/org.eclipse.sirius.git"
LABEL org.label-schema.vendor="Eclipse Foundation"

RUN groupadd -r developer && useradd -r -g developer developer

ENV SIRIUS_SERVER_HOME /home/developer
RUN mkdir -p "$SIRIUS_SERVER_HOME"

ENV PLATFORM photon

COPY /target/products/sirius-server-linux.gtk.x86_64.zip /home/developer

WORKDIR $SIRIUS_SERVER_HOME
RUN unzip sirius-server-linux.gtk.x86_64.zip
RUN chown -R developer:developer "$SIRIUS_SERVER_HOME"

USER developer
RUN chmod +x /home/developer/sirius-server/sirius-server
WORKDIR /home/developer/sirius-server

EXPOSE 8080
ENTRYPOINT ["./sirius-server"]