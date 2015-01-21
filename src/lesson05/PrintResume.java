package lesson05;

import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.model.SectionType;
import ru.javawebinar.webapp.model.TextSection;

/**
 * Created by vad on 20.01.2015.
 */
public class PrintResume {
    public static void main(String[] args) {

        /*
        http://gkislin.ru/ru/cv.html
         */
        Resume p = new Resume( "Кислин Григорий", "Россия, г. Санкт-Петербург");
        p.addContact(ContactType.HOME_PAGE, "http://gkislin.ru");
        // Проживание: Россия, г. Санкт-Петербург
        p.addContact(ContactType.MOBILE, "+7 (921) 855 0482");
        p.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        p.addContact(ContactType.SKYPE, "grigory.kislin");

        p.addSection(SectionType.OBJECTIVE,new TextSection("Архитектор/ Технический лидер/ Старший Scala/Java разработчик",""));

        p.addSection(SectionType.ACHIEVEMENT,new TextSection( "Разработка и проведение Java тренингов", ""));
        p.addSection(SectionType.ACHIEVEMENT, new TextSection("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. ", ""));

        p.addSection(SectionType.QUALIFICATIONS, new TextSection("JEE AS...", ""));
        p.addSection(SectionType.QUALIFICATIONS,new TextSection( "Frameworks...", ""));

        p.addSection(SectionType.EXPERIENCE,new TextSection( "RIT Center", "Java архитектор"));
        p.addSection(SectionType.EXPERIENCE,new TextSection( "Basis Capital", "Системный архитектор"));

        p.addSection(SectionType.EDUCATION,new TextSection( "Luxoft", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML."));
        p.addSection(SectionType.EDUCATION,new TextSection( "Институт Точной Механики и Оптики (Технический университет)",
                "Аспирантура"));

/*
Опыт работы
        RIT Center
        04/2012- сейчас:	Java архитектор

        Basis Capital
06/2011- 04/2012:	Системный архитектор

образование
Luxoft
2011:	Курс "Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML."

Институт Точной Механики и Оптики (Технический университет)
1993-1996:	Аспирантура
1987-1993:	Инженер

*/

        p.print();
    }


}
