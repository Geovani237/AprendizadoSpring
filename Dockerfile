FROM eclipse-temurin:17-jre

#Define o diretório de trabalho onde as instruções seguintes serão rodadas
WORKDIR /app

#Recebe origem e o destino
COPY target/*.jar /app/api.jar
COPY wait-for-it.sh /wait-for-it.sh

#Tornando o wait-for-it.sh executavel
RUN chmod +x /wait-for-it.sh

#Essa exposição é mais para documentação
EXPOSE 8080

#Comando padrão que vai ser rodado quando o container ser iníciado
CMD ["java", "-jar", "api.jar"]
