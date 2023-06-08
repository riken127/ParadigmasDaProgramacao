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
private boolean isProjectInEdition(String name, String description, String[] tags) {
        for (int i = 0; i < this.projectCounter; i++) {
            if (this.projectList[i].getName().equals(name) && this.projectList[i].getDescription().equals(description)) {
                int counter = 0;
                String[] temporaryProjectTags = this.projectList[i].getTags();
                for (int j = 0; j < tags.length && j < temporaryProjectTags.length; j++) {
                    if (tags[j].equals(temporaryProjectTags[j])) {
                        counter++;
                    }
                }
                if (counter == temporaryProjectTags.length) {
                    return true;
                }
            }
        }
        return false;
}
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
    if (isProjectInEdition(name, description, tags)) {
        throw new IllegalArgumentException("The given project is already present in the edition.");
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

            LocalDate startAt = LocalDate.ofYearDay(2023, startAtDayOfYear + 1);
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
                if (projectList[i].getName().equals(name)) {
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
        for (int i = position; i < this.projectCounter; i++) {
            this.projectList[i] = this.projectList[i + 1];
        }
        this.projectCounter--;
    }

    @Override
    public Project getProject(String name) throws IllegalArgumentException{
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
        int numberOfProjectsWithTag = 0;
        
        for (int i = 0; i < this.projectCounter; i++) {
            if (this.projectList[i] != null) {
                if (projectList[i].hasTag(tag)) {
                    numberOfProjectsWithTag++;
                }
            }
        }

        Project[] projectsWithTag = new Project[numberOfProjectsWithTag];
        
        int counter = 0;
        
        for (int i = 0; i < this.projectCounter; i++) {
            if (this.projectList[i] != null) {
                if (projectList[i].hasTag(tag)) {
                    projectsWithTag[counter++] = this.projectList[i];
                }
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
        for (int i = 0; i < this.projectCounter; i++) {
            if (this.projectList[i] != null) {
                if (this.projectList[i].getParticipant(email) != null) {
                    count++;
                }
            }
        }

        Project[] projectsOfParticipant = new Project[count];
        int index = 0;
        for (int i = 0; i < this.projectCounter; i++) {
            if (this.projectList[i] != null) {
                if (this.projectList[i].getParticipant(email) != null) {
                    projectsOfParticipant[index++] = this.projectList[i];
                }
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

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Edition)) {
            return false;
        }
        Edition temporaryEdition = (Edition) object;

        if (this == temporaryEdition) {
            return true;
        }

        return  (temporaryEdition.getName().equals(this.getName()) &&
            temporaryEdition.getProjectTemplate().equals(this.getProjectTemplate()) &&
            temporaryEdition.getStatus() == this.getStatus() &&
            temporaryEdition.getEnd().equals(this.getEnd()) &&
            temporaryEdition.getStart().equals(this.getStart()));
    }
}
