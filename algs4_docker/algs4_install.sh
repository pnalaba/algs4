cd /usr/local
mkdir algs4
chmod 755 algs4
cd algs4
pwd
curl -O "https://algs4.cs.princeton.edu/code/algs4.jar"
curl -O "https://algs4.cs.princeton.edu/linux/{javac,java}-{algs4,cos226,coursera}"
chmod 755 {javac,java}-{algs4,cos226,coursera}
mv {javac,java}-{algs4,cos226,coursera} /usr/local/bin

curl -O "https://algs4.cs.princeton.edu/linux/{drjava.jar,drjava,.drjava}"
chmod 755 drjava
mv drjava /usr/local/bin
mv .drjava ~

curl -O "https://algs4.cs.princeton.edu/linux/findbugs.{zip,xml}"
curl -O "https://algs4.cs.princeton.edu/linux/findbugs-{algs4,cos226,coursera}"
unzip findbugs.zip 
chmod 755 findbugs-{algs4,cos226,coursera}
mv findbugs-{algs4,cos226,coursera} /usr/local/bin

curl -O "https://algs4.cs.princeton.edu/linux/pmd.{zip,xml}"
curl -O "https://algs4.cs.princeton.edu/linux/pmd-{algs4,cos226,coursera}"
unzip pmd.zip
chmod 755 pmd-{algs4,cos226,coursera}
mv pmd-{algs4,cos226,coursera} /usr/local/bin

curl -O "https://algs4.cs.princeton.edu/linux/checkstyle.zip"
curl -O "https://algs4.cs.princeton.edu/linux/checkstyle-suppressions.xml"
curl -O "https://algs4.cs.princeton.edu/linux/checkstyle-{algs4,cos226,coursera}.xml"
curl -O "https://algs4.cs.princeton.edu/linux/checkstyle-{algs4,cos226,coursera}"
unzip checkstyle.zip
chmod 755 checkstyle-{algs4,cos226,coursera}
mv checkstyle-{algs4,cos226,coursera} /usr/local/bin








