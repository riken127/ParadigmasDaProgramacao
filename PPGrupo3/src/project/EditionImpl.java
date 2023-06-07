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

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

import ma02_resources.participants.Participant;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Status;

public class EditionImpl implements Edition {

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

    @Override
    public void addProject(String name, String description, String[] tags) throws IOException, ParseException {
        if (name == null || name.equals("") || description == null || description.equals("") || tags == null) {
            throw new IllegalArgumentException("A null item was found in the given template.");
        }
        for (String tag : tags) {
            if (tag.equals("") || tag.equals(" ")) {
                throw new IllegalArgumentException("A empty tag was found in the given template.");
            }
        }

        this.projectList[this.projectCounter++] = new ProjectImpl(name, description, tags);
    }

    private int findProject(String string) {
        for (int i = 0; i < this.projectCounter; i++) {
            if (projectList[i] != null) {
                if (this.projectList[i].getName().equals(string)) {
                    return i;
                }
            }
        }
        return -1;
    }
    @Override
    public void removeProject(String string) {
        int position = findProject(string);
        if (position == -1) {
            throw new IllegalArgumentException("The given project name was not found ");
        }
        for (int i = position; i < this.projectCounter - 1; i++) {
            this.projectList[i] = this.projectList[i + 1];
        }
    }

    @Override
    public Project getProject(String string) {
        int position = findProject(string);
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
    public Project[] getProjectsByTag(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("Tag cannot be null or empty.");
        }
        int count = 0;
        for (Project project : this.projectList) {
            if (project.hasTag(string)) {
                count++;
            }
        }

        Project[] projectsWithTag = new Project[count];
        int index = 0;
        for (Project project : this.projectList) {
            if (project.hasTag(string)) {
                projectsWithTag[index++] = project;
            }
        }

        return projectsWithTag;
    }

    @Override
    public Project[] getProjectsOf(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }

        int count = 0;
        for (Project project : this.projectList) {
            Participant participant = project.getParticipant(string);
            if (participant != null) {
                count++;
            }
        }

        Project[] projectsOfParticipant = new Project[count];
        int index = 0;
        for (Project project : this.projectList) {
            Participant participant = project.getParticipant(string);
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
