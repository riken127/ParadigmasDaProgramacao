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
import ma02_resources.project.Project;
import ma02_resources.project.Task;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;
import participants.FacilitatorImpl;
import participants.ParticipantImpl;
import participants.PartnerImpl;
import participants.StudentImpl;

public class ProjectImpl implements Project {

    private final int MAX_PARTICIPANTS;
    private final int MAX_STUDENTS;
    private final int MAX_PARTNERS;
    private final int MAX_FACILITATORS;
    private final int MAX_TASKS;
    private final int MAX_TAGS;

    private final String name;
    private final String description;
    private final Participant[] participantList;
    private final Task[] taskList;
    private String[] tagList;
    private int tagCounter;
    private int participantCounter;
    private int taskCounter;

    public ProjectImpl(String name, String description, int maxTasks, int maxPartners, int maxStudents, int maxFacilitators, int maxTags) {
        this.MAX_FACILITATORS = maxFacilitators;
        this.MAX_PARTNERS = maxPartners;
        this.MAX_STUDENTS = maxStudents;
        this.MAX_PARTICIPANTS = maxFacilitators + maxPartners + maxStudents;
        this.MAX_TASKS = maxTasks;
        this.MAX_TAGS = maxTags;
        this.name = name;
        this.description = description;
        this.participantList = new Participant[this.MAX_PARTICIPANTS];
        this.participantCounter = 0;
        this.taskList = new Task[this.MAX_TASKS];
        this.taskCounter = 0;
        this.tagList = new String[this.MAX_TAGS];
        this.tagCounter = 0;
    }

    public ProjectImpl(String name, String description, String[] tags) {
        this.MAX_FACILITATORS = 10;
        this.MAX_PARTNERS = 5;
        this.MAX_STUDENTS = 20;
        this.MAX_PARTICIPANTS = this.MAX_FACILITATORS + this.MAX_PARTNERS + this.MAX_STUDENTS;
        this.MAX_TASKS = 20;
        this.MAX_TAGS = 10;
        this.name = name;
        this.description = description;
        this.participantList = null;
        this.participantCounter = 0;
        this.taskList = new Task[this.MAX_TASKS];
        this.taskCounter = 0;
        this.addTagsToObject(tags);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getNumberOfParticipants() {
        return this.participantCounter;
    }

    @Override
    public int getNumberOfStudents() {
        int studentCounter = 0;
        for (Participant participant : this.participantList) {
            if (participant instanceof StudentImpl) {
                studentCounter++;
            }
        }
        return studentCounter;
    }

    @Override
    public int getNumberOfPartners() {
        int partnersCounter = 0;
        for (int i = 0; i < this.participantCounter; i++) {
            if (participantList[i] != null) {
                if (participantList[i] instanceof PartnerImpl) {
                    partnersCounter++;
                }
            }
        }
        return partnersCounter;
    }

    @Override
    public int getNumberOfFacilitators() {
        int facilitatorsCounter = 0;
        for (int i = 0; i < this.participantCounter; i++) {
            if (participantList[i] != null) {
                if (participantList[i] instanceof FacilitatorImpl) {
                    facilitatorsCounter++;
                }
            }
        }
        return facilitatorsCounter;
    }

    @Override
    public int getNumberOfTasks() {
        return this.taskCounter;
    }

    @Override
    public int getMaximumNumberOfTasks() {
        return this.MAX_TASKS;
    }

    @Override
    public long getMaximumNumberOfParticipants() {
        return this.MAX_PARTICIPANTS;
    }

    @Override
    public int getMaximumNumberOfStudents() {
        return this.MAX_STUDENTS;
    }

    @Override
    public int getMaximumNumberOfPartners() {
        return this.MAX_PARTNERS;
    }

    @Override
    public int getMaximumNumberOfFacilitators() {
        return this.MAX_FACILITATORS;
    }

    @Override
    public void addParticipant(Participant participant) throws IllegalNumberOfParticipantType, ParticipantAlreadyInProject {
        if (participant == null) {
            throw new IllegalArgumentException("The given argument is null.");
        }
        if (participant instanceof StudentImpl) {
            if (this.getNumberOfStudents() == this.MAX_STUDENTS) {
                throw new IllegalNumberOfParticipantType("The max number of students was achieved");
            }
        }
        if (participant instanceof PartnerImpl) {
            if (this.getNumberOfPartners() == this.MAX_PARTNERS) {
                throw new IllegalNumberOfParticipantType("The max number of partners was achieved");
            }
        }
        if (participant instanceof FacilitatorImpl) {
            if (this.getNumberOfFacilitators() == this.MAX_FACILITATORS) {
                throw new IllegalNumberOfParticipantType("The max number of facilitators was achieved");
            }
        }
        for (Participant participantIndex : this.participantList) {
            if (participantIndex instanceof ParticipantImpl) {
                ParticipantImpl temporaryParticipant = (ParticipantImpl) participantIndex;
                if (temporaryParticipant.equals(participant)) {
                    throw new ParticipantAlreadyInProject("The participant is already in the project");
                }
            }
        }
        this.participantList[this.participantCounter++] = participant;
    }

    @Override
    public Participant removeParticipant(String email) {
        Participant removedParticipant = null;
        for (int i = 0; i < this.participantCounter; i++) {
            if (this.participantList[i].getEmail().equals(email)) {
                removedParticipant = this.participantList[i];
                for (int j = i; j < this.participantCounter - 1; j++) {
                    this.participantList[j] = this.participantList[j + 1];
                }
                this.participantList[--this.participantCounter] = null;
                break;
            }
        }
        return removedParticipant;
    }

    @Override
    public Participant getParticipant(String email) {
        for (int i = 0; i < this.participantCounter; i++) {
            if (this.participantList[i].getEmail().equals(email)) {
                return participantList[i];
            }
        }
        return null;
    }

    public void addTag(String tag) throws ParticipantAlreadyInProject {
        if (tag == null) {
            throw new IllegalArgumentException("The given argument is null.");
        }
        if (this.hasTag(tag)) {
            throw new ParticipantAlreadyInProject("The tag is already in the project");
        }

        this.tagList[this.tagCounter++] = tag;
    }

     private void addTagsToObject(String[] string) {
        this.tagList = new String[string.length];
        System.arraycopy(string, 0, tagList, 0, string.length);
        this.tagCounter = string.length;
    }

    @Override
    public String[] getTags() {
        return this.tagList;
    }

    @Override
    public boolean hasTag(String tag) {
        for (String tags : this.tagList) {
            if (tags.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addTask(Task task) throws IllegalNumberOfTasks, TaskAlreadyInProject {
        if (this.taskCounter == this.MAX_TASKS) {
            throw new IllegalNumberOfTasks("The max number of tasks was achieved.");
        }
        if (getTask(task.getTitle()) != null) {
            throw new TaskAlreadyInProject("The given task is already in the project.");
        }

        this.taskList[this.taskCounter++] = task;
    }

    @Override
    public Task getTask(String string) {
        for (int i = 0; i < this.taskCounter; i++) {
            if (taskList[i].getTitle().equals(string)) {
                return taskList[i];
            }
        }
        return null;
    }

    @Override
    public Task[] getTasks() {
        return this.taskList;
    }

    @Override
    public boolean isCompleted() {
        for (int i = 0; i < this.taskCounter; i++) {
            if (this.taskList[i] == null || this.taskList[i].getNumberOfSubmissions() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Project)) {
            return false;
        }
        ProjectImpl temporaryProject = (ProjectImpl) obj;
        return (temporaryProject.getName().equals(this.getName())
                && temporaryProject.getDescription().equals(this.getDescription()));
    }

}
