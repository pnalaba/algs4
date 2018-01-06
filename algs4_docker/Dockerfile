From ubuntu:16.04

RUN apt-get update
RUN apt-get -y install software-properties-common
RUN add-apt-repository -y ppa:webupd8team/java
RUN apt-get update
RUN sh -c '/bin/echo -e "yes" | apt-get install -y oracle-java8-installer'
RUN sh -c '/bin/echo -e "yes" | apt-get install -y oracle-java8-set-default'
RUN javac -version
RUN apt-get install -y curl unzip vim-gnome
RUN apt-get install -y libxext-dev libxrender-dev libxtst-dev
ENV DISPLAY :0
ADD algs4_install.sh /root
RUN chmod +x /root/algs4_install.sh
RUN bash /root/algs4_install.sh

CMD ["/bin/bash"]
