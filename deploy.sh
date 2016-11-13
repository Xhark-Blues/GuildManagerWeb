echo -ne "Compilando...\t"
javac WEB-INF/src/Regvlet.java WEB-INF/src/DAO.java WEB-INF/src/User.java -d WEB-INF/classes/
echo -e "\033[0;32m [OK] \033[0m"

echo -ne "Empaquetando...\t"
rm -f WEB-INF/src/*.class
jar -cvf GMW.war * &> /dev/null
echo -e "\033[0;32m [OK] \033[0m"

echo -ne "Instalando...\t"
cp GMW.war ~/Universidad/DAW/apache-tomcat-7.0.72/webapps/GMW.war
echo -e "\033[0;32m [OK] \033[0m"
