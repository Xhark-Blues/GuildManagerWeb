#!/bin/bash

echo -ne "Compilando Hellvlet...\t"
javac WEB-INF/src/servlets/Hellvlet.java -d WEB-INF/classes
echo -e "\033[0;32m [OK] \033[0m"

echo -ne "Compilando Regvlet...\t"
javac WEB-INF/src/servlets/Regvlet.java WEB-INF/src/model/*.java -d WEB-INF/classes/
echo -e "\033[0;32m [OK] \033[0m"

echo -ne "Compilando Storvlet...\t"
javac WEB-INF/src/servlets/Storvlet.java WEB-INF/src/model/*.java -d WEB-INF/classes/
echo -e "\033[0;32m [OK] \033[0m"

echo -ne "Compilando Cartvlet...\t"
javac WEB-INF/src/servlets/Cartvlet.java WEB-INF/src/model/*.java -d WEB-INF/classes/
echo -e "\033[0;32m [OK] \033[0m"

echo -ne "Compilando Confvlet...\t"
javac WEB-INF/src/servlets/Confvlet.java WEB-INF/src/model/*.java -d WEB-INF/classes/
echo -e "\033[0;32m [OK] \033[0m"

echo -ne "Compilando Billvlet...\t"
javac WEB-INF/src/servlets/Billvlet.java WEB-INF/src/model/*.java -d WEB-INF/classes/
echo -e "\033[0;32m [OK] \033[0m"


echo -ne "Empaquetando...\t\t"
rm -f WEB-INF/src/servlets/*.class
rm -f WEB-INF/src/model/*.class

jar -cvf GMW.war * &> /dev/null
echo -e "\033[0;32m [OK] \033[0m"

echo -ne "Instalando...\t\t"
cp GMW.war ~/Universidad/DAW/apache-tomcat-7.0.72/webapps/GMW.war
echo -e "\033[0;32m [OK] \033[0m"

echo -ne "Recargando Tomcat... \t"
touch /home/martin/Universidad/DAW/apache-tomcat-7.0.72/webapps/GMW.war
echo -e "\033[0;32m [OK] \033[0m"

#firefox localhost:8080/GMW
