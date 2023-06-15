package urise.webapp.storage;

import urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "Name Surname");
        resume.addContact(ContactType.NUMBER, "+7(921) 855-0482 ");
        resume.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");
        TextSection position =
                new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям ");
        resume.addSection(SectionType.OBJECTIVE, position);
        TextSection personal =
                new TextSection("Аналитический склад ума, сильная логика, " +
                        "креативность, инициативность. Пурист кода и архитектуры. ");
        resume.addSection(SectionType.PERSONAL, personal);
        List<String> achievments = new ArrayList<>();
        achievments.add("Организация команды и успешная реализация Java проектов для " +
                "сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, " +
                "система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, " +
                "многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        achievments.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
                "Более 3500 выпускников. ");
        achievments.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk. ");
        achievments.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке:" +
                " Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                "интеграция CIFS/SMB java сервера. ");
        achievments.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring," +
                "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга. ");
        achievments.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных " +
                "сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации " +
                "о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и " +
                "мониторинга системы по JMX (Jython/ Django). ");
        achievments.add("Реализация протоколов по приему платежей всех основных платежных системы России" +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        ListSection achievment = new ListSection(achievments);
        resume.addSection(SectionType.ACHIEVEMENT, achievment);
        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 ");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce ");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python)," +
                "Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB ");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy ");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts ");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor," +
                "MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), " +
                "Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, " +
                "JUnit, Selenium (htmlelements). ");
        qualifications.add("Python: Django. ");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js ");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka ");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS," +
                "JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX," +
                "Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT. ");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix\n" +
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, " +
                "iReport, OpenCmis, Bonita, pgBouncer ");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA," +
                "шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования ");
        qualifications.add("Родной русский, английский \"upper intermediate\"");
        ListSection qualification = new ListSection(qualifications);
        resume.addSection(SectionType.QUALIFICATIONS, qualification);
        Period javaops = new Period("Java Online Projects",
                "Создание, организация и проведение Java онлайн проектов и стажировок.",
                LocalDate.of(2013, 10, 1), LocalDate.now());
        Link link = new Link("JavaOps", "https://javaops.ru/");
        List<Period> periods = new ArrayList<>();
        periods.add(javaops);
        Organization jops = new Organization(periods, link, "JavaOPS");
        List<Organization> organizations = new ArrayList<>();
        organizations.add(jops);
        resume.addSection(SectionType.EXPERIENCE,new OrganizationSection(organizations));
        Period coursera = new Period("Coursera", "\n" +
                "'Functional Programming Principles in Scala' by Martin Odersky",
                LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1));
        Link linkCoursera = new Link("Coursera", "https://www.coursera.org/course/progfun");
        List<Period> periodED = new ArrayList<>();
        periodED.add(coursera);
        Organization ed = new Organization(periodED, linkCoursera, "Coursera");
        List<Organization> educations = new ArrayList<>();
        educations.add(ed);
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(educations));
        resume.printAllResume();
    }
}
