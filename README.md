# Программа контроля за сведениями о студентах и их успеваемости

Пакеты:
controller содержит контроллер;
item - класс студента;
load - SAX-парсер;
menu - слушатели для добавления нового студента, удаления студента, загрузки списка студентов из файла, сохраниения списка студентов в файл, поиска студентов по заданным параметрам;
save - DOM-парсер;
view - класс для таблицы студентов и для окна.

Согласно варианту задания, пользователь имеет возможность осуществлять поиск(удаление) студентов:
по имени;
по номеру группы;
по числу пропусков определенного типа (по болезни, другая причина, без уважительной причины);
по общему числу пропусков.

При этом имеется возможность устанавливать сразу несколько фильтров одновременно, в результате чего поиск будет иметь вид объединения результатов всех параметров для поиска.

Номер группы студента - это целое число.
