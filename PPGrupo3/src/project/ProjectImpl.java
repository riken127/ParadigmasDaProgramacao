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
import participants.PartnerImpl;
import participants.StudentImpl;

public class ProjectImpl implements Project {

    private static final int MAX_PARTICIPANTS = 20;
    private String name;
    private String description;
    private Participant[] participantList;
    private int participantCounter;

    public ProjectImpl(String name, String description) {
        this.name = name;
        this.description = description;
        this.participantList = new Participant[MAX_PARTICIPANTS];
        this.participantCounter = 0;
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
        for (Participant participant : this.participantList) {
            if (participant instanceof PartnerImpl) {
                partnersCounter++;
            }
        }
        return partnersCounter;
    }

    @Override
    public int getNumberOfFacilitators() {
        int facilitatorsCounter = 0;
        for (Participant participant : this.participantList) {
            if (participant instanceof FacilitatorImpl) {
                facilitatorsCounter++;
            }
        }
        return facilitatorsCounter;
    }

    @Override
    public int getNumberOfTasks() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getMaximumNumberOfTasks() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public long getMaximumNumberOfParticipants() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getMaximumNumberOfStudents() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getMaximumNumberOfPartners() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getMaximumNumberOfFacilitators() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addParticipant(Participant p) throws IllegalNumberOfParticipantType, ParticipantAlreadyInProject {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Participant removeParticipant(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Participant getParticipant(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String[] getTags() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean hasTag(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addTask(Task task) throws IllegalNumberOfTasks, TaskAlreadyInProject {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Task getTask(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Task[] getTasks() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isCompleted() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
