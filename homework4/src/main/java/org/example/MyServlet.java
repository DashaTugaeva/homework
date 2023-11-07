package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/*"})
public class MyServlet extends HttpServlet {
    public MyArrayList<Person> personsList;

    public void setPersons() throws IOException {
        StudentService studentService = new StudentService();
        studentService.setPersons(new FileDataLoader());
        personsList = studentService.getPersons();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-16");
        if(personsList == null) setPersons();
        MyArrayList<Person> persons = personsList;
        try (var output = resp.getWriter()) {
            int group = Integer.parseInt(req.getParameter("group"));
            for (int i = 0; i < persons.size; i++) {
                if (persons.get(i).getGroup() == group) {
                    output.write("Average rating = %s for student %s in %d group.\n".
                            formatted(String.format("%.2f", persons.get(i).getAverageScore()),
                            persons.get(i).getName(), persons.get(i).getGroup()));
                }
                output.flush();
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var mapper = new ObjectMapper();
        PersonInfo personInfo = null;
        try(var input = req.getInputStream()){
            personInfo = mapper.readValue(new String(input.readAllBytes()), PersonInfo.class);
        }
        if(personsList == null) setPersons();
        MyArrayList<Person> persons = personsList;
        Person person = null;
        for (int i = 0; i < persons.size; i++) {
            if(persons.get(i).getName().equals(personInfo.name) && persons.get(i).getGroup() == personInfo.group){
                person = persons.get(i);
            }
        }
        try(var output = resp.getWriter()){
            resp.setContentType("application/json");
            output.write("Grade before the change\n");
            output.write(mapper.writeValueAsString(person) + "\n");
            person = selectLesson(personInfo.lesson, person, personInfo.grade);
            output.write("Grade after the change\n");
            output.write(mapper.writeValueAsString(person));
            output.flush();
        }
    }

    public Person selectLesson(String nameLesson, Person person, int grade){
        switch (nameLesson) {
            case "physics" -> person.setPhysicsScore(grade);
            case "mathematics" -> person.setMathematicsScore(grade);
            case "rus" -> person.setRusScore(grade);
            case "literature" -> person.setLiteratureScore(grade);
            case "geometry" -> person.setGeometryScore(grade);
            case "informatics" -> person.setInformaticsScore(grade);
        }
        return person;
    }



}
