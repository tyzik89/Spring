# Spring
Изучение фреймворка Spring

Spring framework предлагает образцовую модель для разработки современных enterprise java приложений. 
Ключевой элемент Spring — инфрастуктурная поддержка на всех уровнях: 
Spring предоставляют готовые низкоуровневые решения, позволяя разработчику сфокусироваться на его непосредственных задачах.

Spring Framework — многофункциональный фреймворк для Java, состоящий из нескольких крупных модулей 
и предоставляющий различные сервисы java разработчикам.

Центральная концепция фреймворка — IoC контейнер, управляющий объектами, 
и конфигурационный контекст (context), описывающий приложение и дополнительную функциональность.

========================================================

Spring Boot — платформа, нацеленная на упрощение разработки приложений с использованием Spring технологий. Spring boot использует принцип соглашения по конфигурации и, предполагая во многих случаях значения по умолчанию, самостоятельно настраивает используемые фреймворки Spring. Кроме того, Spring boot автоматически, при старте приложения, определяет используемые фреймворки, библиотеки и технологии и настраивает их.
Конфигурация Spring Boot по умолчанию располагается в файлах application.properties или application.yml, которые могут располагаться в следующих местах:

1. Каталог config текущего каталога
2. Текущий каталог
3. Пакет config в classpath
4. Корень classpath

Также Spring boot является платформой для разработки приложений и микросервисов на основе Spring, а логическим концом разработки приложения (как это видит разработчик), является его поставка. В обычном приложении приходится настраивать maven для сборки jar/war/ear файла и вручную контролировать сборку. Spring boot облегчает и автоматизирует этот процесс.
