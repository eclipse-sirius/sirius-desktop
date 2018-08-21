FROM debian:9.4-slim

# To build the image, use the following line. You have to be in the folder of the Dockerfile to run it (or use -f option)
#
# docker build . --tag sirius/sirius-build:1.0
#
# the --tag option works as follow : the name of the image will be sirius/sirius-build and it will be tagged 1.0


# To run a new container from a sirius image, use the following command line. The parts between {} are to customize.
#
# docker run --rm --name {name of the container} -p {30000}:5900 -v {.m2 folder path}:/sirius/.m2 -v {sirius git repo path}:/sirius/git {sirius/sirius-build:1.0 - image name}
#
# The --rm option delete the container after the run. As each run creates a new container, using this command will save space in host disk.
# Since the build is on a shared folder, nothing will be lost. The only reason to remove this option is if there is a system or a VNC error.
#
# To customize build options, see the following comment lines (next to ENV commands)


# To connect to a running container with a new shell, use
# docker exec -it "id or name of the container" bash

# System environment variables
ENV DEBIAN_FRONTEND noninteractive
ENV DISPLAY :0

# Sirius build default variables
# To override them, use the -e option in docker run command line
# docker run -p 32001:5900 -e PLATFORM=photon -e GOAL="verify" sirius/sirius-build:1.0
ENV BUILD_FOLDER target
ENV PLATFORM photon
ENV GOAL package
ENV TESTS_SUITES junit,swtbot-sequence,swtbot
ENV USE_P2_MIRRORS true
ENV FAIL_AT_END false

# We need to create a proper user to ease the configuration of maven
RUN addgroup --system xusers \
    && useradd -g xusers -m -d /sirius sirius

# Add package repository for Microsoft fonts
RUN echo "deb http://security.debian.org/ stretch/updates contrib non-free" >> /etc/apt/sources.list \
    && echo "deb http://ftp.debian.org/debian/ stretch contrib non-free" >> /etc/apt/sources.list \
    && echo ttf-mscorefonts-installer msttcorefonts/accepted-mscorefonts-eula select true | debconf-set-selections

# This is needed for the configuration of installed packets
RUN apt-get update && apt-get install -y --no-install-recommends apt-utils \
    # Clean up apt
    && apt-get autoremove -y \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Hack to make java installation work on slim version
# see https://github.com/debuerreotype/debuerreotype/issues/10
RUN mkdir -p /usr/share/man/man1

RUN apt-get update && apt-get install -y --no-install-recommends \
    # Install dev tools (Java/Maven/Git)
    openjdk-8-jre git maven \
    # Install fonts
    xfonts-base xfonts-scalable xfonts-100dpi xfonts-75dpi \
    fonts-liberation fonts-liberation2 fonts-freefont-ttf \
    fonts-dejavu fonts-dejavu-core fonts-dejavu-extra \
    # Install VNC
    tigervnc-standalone-server tigervnc-common \
    # Install Window Manager and UI elements (GTK and X server)
    # There is a known issue with SWT making noise in the build logs by displaying this warning :
    # (SWT:917): Gtk-WARNING **: Allocating size to GtkFrame 0x7f3228a387f0 without calling gtk_widget_get_preferred_width/height()
    # and
    # (SWT:917): Gtk-WARNING **: Negative content width -3 (allocation 10, extents 6x7) while allocating gadget (node button, owner GtkButton)
    # It seems to happen only with GTK 3.20+ and it as been partially fixed in SWT 4.8 https://bugs.eclipse.org/bugs/show_bug.cgi?id=486068
    # Since fixing versions of packages with apt is not offically supported, we cannot use GTK pre 3.20 who didn't have the issue
    metacity libgtk-3-0 x11-xserver-utils libgl1-mesa-dri \
    # Clean up apt
    && apt-get autoremove -y \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# The installation of this package broke sometimes without reasons (failing downloads from soureforge).
# There is no real workaround for now, see https://bugs.launchpad.net/ubuntu/+source/msttcorefonts/+bug/1654573
# and https://bugs.launchpad.net/ubuntu/+source/apt/+bug/1651923
RUN apt-get update && apt-get install -y --no-install-recommends ttf-mscorefonts-installer \
    # Clean up apt
    && apt-get autoremove -y \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Create the folder used to store socket and add the appropriate rights
RUN mkdir /tmp/.X11-unix && chmod 1777 /tmp/.X11-unix

# Change to Sirius user
USER sirius

# Create a passwor for VNC. Default is '123456'
# To change it, change the value before building the container
# or connect to the container and run the vncpasswd command
RUN mkdir /sirius/.vnc/
RUN echo "123456" | vncpasswd -f > /sirius/.vnc/passwd
RUN chmod 600 /sirius/.vnc/passwd

# Create the .Xauthority file
RUN XAUTH=/sirius/.Xauthority && touch $XAUTH

# Create a custom vnc xstartup file
RUN echo "#!/bin/sh\n[ -x /etc/vnc/xstartup ] && exec /etc/vnc/xstartup\n[ -r $HOME/.Xresources ] && xrdb $HOME/.Xresources\nXvnc \$DISPLAY -geometry 1440x900 -depth 16 -dpi 100 -PasswordFile /sirius/.vnc/passwd &\nsleep 2\nxsetroot -solid grey\nvncconfig -iconic &\nxhost +\nmetacity --replace --sm-disable --display=\$DISPLAY &" \
    > /sirius/.vnc/xstartup.sh
RUN chmod 755 /sirius/.vnc/xstartup.sh

# Mounting points
VOLUME [ "/sirius/.m2", "/sirius/git" ]

# Internal container ports to expose
EXPOSE 5900/tcp

WORKDIR /sirius
COPY start-wrapper.sh .
CMD ["sh", "-c", "./start-wrapper.sh"]
