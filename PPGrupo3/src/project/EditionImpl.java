/*
* Nome: <João Pedro Salgado Pereira>
* Número: <8220102>
* Turma: <LEI1T4>
*
* Nome: <José Henrique Noronha Oliveira e Silva>
* Número: <8220343>
* Turma: <LEI1T4>
*/
package project;

import ma02_resources.participants.Participant;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import ma02_resources.project.Task;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.TaskAlreadyInProject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
public class EditionImpl implements Edition {
    private static final int INITIAL_PROJECT_ARRAY_SIZE = 10;
    private String name;
    private LocalDate start;
    private LocalDate end;
    private String projectTemplate;
    private Status status;
    private Project[] projectList;
    private int projectCounter;

    public EditionImpl(String name, LocalDate start, LocalDate end, String projectTemplate, Status status) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.projectTemplate = projectTemplate;
        this.status = status;
        this.projectList = new Project[INITIAL_PROJECT_ARRAY_SIZE];
        this.projectCounter = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LocalDate getStart() {
        return this.start;
    }

    @Override
    public String getProjectTemplate() {
        return this.projectTemplate;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }
/*
    @Override
    public void addProject(String name, String description, String[] tags) throws IOException, ParseException {
        String fileName = projectTemplate;
        if (name == null || name.equals("") || description == null || description.equals("") || tags == null) {
            throw new IllegalArgumentException("A null item was found in the given template.");
        }
        for (String tag : tags) {
            if (tag.equals("") || tag.equals(" ")) {
                throw new IllegalArgumentException("A empty tag was found in the given template.");
            }
        }
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileName));

            int getNumberOfFacilitators = ((Long) jsonObject.get("number_of_facilitators")).intValue();
            int numberOfStudents = ((Long) jsonObject.get("number_of_students")).intValue();
            int numberOfPartners = ((Long) jsonObject.get("number_of_partners")).intValue();

            JSONArray tasksArray = (JSONArray) jsonObject.get("tasks");
            ProjectImpl project = new ProjectImpl(name, description, tags);

                for (Object taskObj : tasksArray) {
                    JSONObject taskJson = (JSONObject) taskObj;
                    String title = (String) taskJson.get("title");
                    String taskDescription = (String) taskJson.get("description");
                    int startAtDayOfYear = ((Long) taskJson.get("start_at")).intValue();
                    int duration = ((Long) taskJson.get("duration")).intValue();

                    LocalDate startAt = LocalDate.ofYearDay(2023, startAtDayOfYear);
                    LocalDate endAt = startAt.plusDays((long) duration);

                    Task task = new TaskImpl(startAt, endAt, title, description);
                    project.addTask(task);
                }
            this.projectList[this.projectCounter++] = project;
        } catch (IOException exception) {
            throw new IOException("Error while reading the json file.");
        } catch (org.json.simple.parser.ParseException e) {
            throw new java.text.ParseException("Error while parsing.", 0);
        } catch ( TaskAlreadyInProject | IllegalNumberOfTasks e) {
            throw new RuntimeException(e);
        }

    }
*/
@Override
public void addProject(String name, String description, String[] tags) throws IOException, ParseException {
    String fileName = projectTemplate;
    if (name == null || name.isEmpty() || description == null || description.isEmpty() || tags == null) {
        throw new IllegalArgumentException("A null item was found in the given template.");
    }
    for (String tag : tags) {
        if (tag.isEmpty() || tag.trim().isEmpty()) {
            throw new IllegalArgumentException("An empty tag was found in the given template.");
        }
    }
    try {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileName));

        JSONArray tasksArray = (JSONArray) jsonObject.get("tasks");
        ProjectImpl project = new ProjectImpl(name, description, tags);

        for (Object taskObj : tasksArray) {
            JSONObject taskJson = (JSONObject) taskObj;
            String title = (String) taskJson.get("title");
            String taskDescription = (String) taskJson.get("description");
            int startAtDayOfYear = ((Long) taskJson.get("start_at")).intValue();
            int duration = ((Long) taskJson.get("duration")).intValue();

            LocalDate startAt = LocalDate.ofYearDay(2023, startAtDayOfYear);
            LocalDate endAt = startAt.plusDays(duration);

            Task task = new TaskImpl(startAt, endAt, title, taskDescription);
            project.addTask(task);
        }
        this.projectList[this.projectCounter++] = project;
    } catch (IOException exception) {
        throw new IOException("Error while reading the JSON file.");
    } catch (org.json.simple.parser.ParseException e) {
        throw new java.text.ParseException("Error while parsing the JSON.", 0);
    } catch (TaskAlreadyInProject | IllegalNumberOfTasks e) {
        throw new RuntimeException(e);
    }
}

    private int findProject(String name) {
        for (int i = 0; i < this.projectCounter; i++) {
            if (projectList[i] != null) {
                if (this.projectList[i].getName().equals(name)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    @Override
    public void removeProject(String name) {
        int position = findProject(name);
        if (position == -1) {
            throw new IllegalArgumentException("The given project name was not found ");
        }
        for (int i = position; i < this.projectCounter - 1; i++) {
            this.projectList[i] = this.projectList[i + 1];
        }
    }

    @Override
    public Project getProject(String name) {
        int position = findProject(name);
        if (position == -1) {
            throw new IllegalArgumentException("The given project name was not found.");
        }
        return this.projectList[position];
    }

    @Override
    public Project[] getProjects() {
        return this.projectList;
    }


    @Override
    public Project[] getProjectsByTag(String tag) {
        if (tag == null || tag.isEmpty()) {
            throw new IllegalArgumentException("Tag cannot be null or empty.");
        }
        int count = 0;
        for (Project project : this.projectList) {
            if (project.hasTag(tag)) {
                count++;
            }
        }

        Project[] projectsWithTag = new Project[count];
        int index = 0;
        for (Project project : this.projectList) {
            if (project.hasTag(tag)) {
                projectsWithTag[index++] = project;
            }
        }

        return projectsWithTag;
    }

    @Override
    public Project[] getProjectsOf(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }

        int count = 0;
        for (Project project : this.projectList) {
            Participant participant = project.getParticipant(email);
            if (participant != null) {
                count++;
            }
        }

        Project[] projectsOfParticipant = new Project[count];
        int index = 0;
        for (Project project : this.projectList) {
            Participant participant = project.getParticipant(email);
            if (participant != null) {
                projectsOfParticipant[index++] = project;
            }
        }

        return projectsOfParticipant;
    }

    @Override
    public int getNumberOfProjects() {
        return this.projectCounter;
    }

    @Override
    public LocalDate getEnd() {
        return this.end;
    }

}
