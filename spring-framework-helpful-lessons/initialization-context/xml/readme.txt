Конфигурирование с помощью XML заключается в создании xml файла
(традиционно носящего названия вида «context.xml», «applicationContext.xml» и т.д.),
описывающего Spring beans, процесс их создания и взаимосвязи между ними.
Поэтому в первую очередь убедимся, что в коде отсутствуют аннотации @Service и @Inject
и сразу после их удаления напишем им замену в xml

Файл «/resource/applicationContext.xml» заменяет и класс ContextConfiguration и аннотации.

Xml конфигурация представляет собой централизованное описание приложения, которое может хранится отдельно от кода,
позволяя менять структуру приложения без пересборки.
С помощью xml  можно использовать в качестве Spring beans сторонний код.