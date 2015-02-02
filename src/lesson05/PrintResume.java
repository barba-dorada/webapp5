package lesson05;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.javawebinar.webapp.model.MultiTextSection;
import ru.javawebinar.webapp.model.Resume;
import ru.javawebinar.webapp.model.TextSection;

import static ru.javawebinar.webapp.model.ContactType.*;
import static ru.javawebinar.webapp.model.SectionType.*;

/**
 * Created by vad on 20.01.2015.
 */
public class PrintResume {
    public static void main(String[] args) {
        Integer i3 = new Integer(3);
        Integer i4 = new Integer(3);

        System.out.println(i3 == i4);

        /*
        http://gkislin.ru/ru/cv.html
         */
        Resume p = new Resume("Кислин Григорий", "Россия, г. Санкт-Петербург");
        p.addContact(HOME_PAGE, "http://gkislin.ru");
        // Проживание: Россия, г. Санкт-Петербург
        p.addContact(MOBILE, "+7 (921) 855 0482");
        p.addContact(MAIL, "gkislin@yandex.ru");
        p.addContact(SKYPE, "grigory.kislin");

        p.addSection(OBJECTIVE, new TextSection("Архитектор/ Технический лидер/ Старший Scala/Java разработчик"));

        p.addSection(ACHIEVEMENT, mts(
                "Разработка и проведение Java тренингов",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. "));

        p.addSection(QUALIFICATIONS, mts("JEE AS...", "Frameworks..."));

        p.addSection(EXPERIENCE, mts("RIT Center", "Java архитектор",
                "Basis Capital", "Системный архитектор"));

        p.addSection(EDUCATION, mts("Luxoft",
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.",
                "Институт Точной Механики и Оптики (Технический университет)", "Аспирантура"));



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
        //    testGSON(p);


    }

    protected static void testGSON(Resume p) {
        //DwarvesBand band = BandUtil.createBand();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(p);
        System.out.println(json);

        Resume r2 = gson.fromJson(json, Resume.class);
        r2.print();
    }

    static MultiTextSection mts(String... args) {
        MultiTextSection sec = new MultiTextSection(args);
        return sec;
    }


}
