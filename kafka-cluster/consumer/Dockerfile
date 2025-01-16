FROM openjdk:21

WORKDIR /app

# Копирование JAR-файла в контейнер
COPY consumer/build/libs/*.jar consumer.jar

# Команда для запуска JAR-файла
CMD ["java", "-jar", "consumer.jar"]