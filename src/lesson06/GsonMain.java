package lesson06;

import com.google.gson.*;
import ru.javawebinar.webapp.model.*;
import ru.javawebinar.webapp.util.JsonParser;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by vad on 26.01.2015.
 */
public class GsonMain {
    static Resume R1,R2,R3,R4;
    public static void main(String[] args) {
        before();
        System.out.println(R1);
        System.out.println(R3);
        //Gson gson = new JsonParser();
        StringWriter sw = new StringWriter();
        JsonParser.write(R1, sw);
        Gson g = new Gson();
        GsonBuilder gson = new GsonBuilder();
        //gson.registerTypeAdapter(Section.class, new Sss());
        gson.registerTypeAdapter(LocalDate.class,new DateTimeHelper());
       // .SON.read(sw,Resume.class)
        g=gson.create();
        String s=g.toJson(R1,Resume.class);
        Resume r=g.fromJson(s, Resume.class);
        System.out.println("===="+s);
        System.out.println("===="+r);


    }
    public static void before() {
        R1 = new Resume("Полное Имя1", "location1");
        R1.addContact(ContactType.MAIL, "mail1@ya.ru");
        R1.addContact(ContactType.PHONE, "11111");



        R2 = new Resume("Полное Имя2", "location2");
        R2.addContact(ContactType.SKYPE, "skype2");
        R2.addContact(ContactType.PHONE, "22222");


        R1.addSection(SectionType.EXPERIENCE,new OrganizationSection(
                        new Organization("Organization11", "null",
                                new Organization.Period(LocalDate.of(2005, Month.JANUARY, 1), Organization.Period.NOW, "position1", "content1"),
                                new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2")),
                        new Organization("Organization12", "http://Organization12.ru"))
        );
        R1.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("Institute", null,
                        new Organization.Period(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", "null"),
                        new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                new Organization("Organization12", "http://Organization12.ru")));


        R3 = new Resume("Полное Имя3", "location3");
        R3.addSection(SectionType.OBJECTIVE,new TextSection("позиция 3"));
        R3.addSection(SectionType.ACHIEVEMENT,new MultiTextSection("A1","A2","A3"));
        R3.addSection(SectionType.QUALIFICATIONS,new MultiTextSection("qqqq","wwwww"));
        R4 = new Resume("Полное Имя4", "location4");
//        storage.clear();
//        storage.save(R3);
//        storage.save(R1);
//        storage.save(R2);
    }
}

class Sss implements JsonDeserializer<Section> {

    @Override
    public Section deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}

class DateTimeHelper implements JsonSerializer<LocalDate>,JsonDeserializer<LocalDate>{

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
    }

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }
}