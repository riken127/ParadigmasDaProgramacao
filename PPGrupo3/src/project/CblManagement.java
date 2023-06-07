package project;

import exceptions.EditionAlreadyInListException;
import exceptions.InvalidIndexException;
import exceptions.InvalidProjectNameException;
import ma02_resources.participants.Participant;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import ma02_resources.project.Submission;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

public class CblManagement implements CblManagementInterface{
    private static final int INITIAL_EDITION_LIST_SIZE = 10;
    private static final int EDITION_LIST_MULTIPLIER = 2;
    private Edition[] editionList;
    private int editionCounter; 
    
    public CblManagement () {
        this.editionList = new Edition[INITIAL_EDITION_LIST_SIZE];
        this.editionCounter = 0;
    }

    private void expandEditionListArray() {
        Edition[] temporaryEditionList = new Edition[this.editionList.length];
        for (int i = 0; i < this.editionList.length; i++) {
            temporaryEditionList[i] = this.editionList[i];
        }
        this.editionList = new Edition[this.editionList.length * this.EDITION_LIST_MULTIPLIER];
        for (int i = 0; i < this.editionList.length; i++) {
            this.editionList[i] = temporaryEditionList[i];
        }
    }
    
    @Override
    public void addEdition(Edition newCblEdition) throws EditionAlreadyInListException {
        for (int i = 0; i < this.editionCounter; i++ ) {
            if (this.editionList[i].equals(newCblEdition)) {
                throw new EditionAlreadyInListException("The given CBL edition is already present.\n");
            }
        }
        if (newCblEdition.getStatus() == Status.ACTIVE) {
            newCblEdition.setStatus(Status.INACTIVE);
        }
        if (this.editionList.length == editionCounter) {
            expandEditionListArray();
            this.editionList[this.editionCounter++] = newCblEdition;
        }
        this.editionList[editionCounter++] = newCblEdition;
        if (this.editionCounter == 1) {
            this.editionList[editionCounter - 1].setStatus(Status.ACTIVE);
        }
    }


    @Override
    public void removeEdition(int index) throws  InvalidIndexException{
        if (editionList[index] == null) {
            throw new InvalidIndexException("The given index is already null.\n");
        }else {
            int i;
            for (i = index; i < this.editionList.length - 1; i++) {
                this.editionList[i] = this.editionList[i + 1];
            }
            this.editionList[i] = null;
        }
    }

    @Override
    public Edition returnEdition(int index) {
        if (this.editionCounter - 1 <= index) {
            return this.editionList[index];
        }
            return null;
    }

    @Override
    public void addProjectToEdition(int editionIndex, String name, String description, String[] tags) throws InvalidIndexException, IOException, ParseException {
        Edition currentEdition = this.editionList[editionIndex];
        if (currentEdition == null) {
            throw new InvalidIndexException("The given edition index is null.\n");
        }
        try {
            currentEdition.addProject(name, description, tags);
        } catch(ParseException e) {
            throw new ParseException("An error has occurred while parsing the json template.", 0);
        } catch (IOException e) {
            throw new IOException("An error has occurred while trying to read the json template.");
        }
    }

    @Override
    public void setActiveEdition(int index) throws InvalidIndexException {
        if (this.editionList[index] == null) {
            throw new InvalidIndexException("The given edition index in null.\n");
        }
        for (int i = 0; i < this.editionCounter; i++) {
            if (i == index) {
                this.editionList[i].setStatus(Status.ACTIVE);
            }else if (this.editionList[i].getStatus() == Status.ACTIVE){
                this.editionList[i].setStatus(Status.INACTIVE);
            }
        }

    }

    @Override
    public Edition[] returnEditionsWithProjectsMissingSubmissionsInTasks() {
        int counter = 0;
        Edition[] missingSubmissionsArray;
        for (Edition editionArray : this.editionList) {
                Project[] temporaryProjectArray = editionArray.getProjects();
                int projectsWithMissingSubmissions = 0;
                for (Project project : temporaryProjectArray) {
                    if (!project.isCompleted()) {
                        projectsWithMissingSubmissions++;
                    }
                }
                if (projectsWithMissingSubmissions > 0) {
                    counter++;
                }
        }
        missingSubmissionsArray = new Edition[counter];
        counter = 0;
        for (Edition editionArray : this.editionList) {
            Project[] temporaryProjectArray = editionArray.getProjects();
            int projectsWithMissingSubmissions = 0;
            for (Project project : temporaryProjectArray) {
                if (!project.isCompleted()) {
                    projectsWithMissingSubmissions++;
                }
            }
            if (projectsWithMissingSubmissions > 0) {
                missingSubmissionsArray[counter++] = editionArray;
            }
        }
        return missingSubmissionsArray;
    }

    @Override
    public Project[] returnProjectsWithMissingSubmissions(int index) throws InvalidIndexException{
        if (this.editionList[index] == null)  {
            throw new InvalidIndexException("The given index is null.\n");
        }
        Project[] projectArray = this.editionList[index].getProjects();
        int counter = 0;
        Project[] projectsWithMissingSubmissions;

        for (int i = 0; i < projectArray.length; i++) {
            if (!projectArray[i].isCompleted()) {
                counter++;
            }
        }
        if (counter == 0) {
            return null;
        }
        projectsWithMissingSubmissions = new Project[counter];
        counter = 0;

        for (int i = 0; i < projectArray.length; i++) {
            if (!projectArray[i].isCompleted()) {
                projectsWithMissingSubmissions[counter++] = projectArray[i];
            }
        }
        return projectsWithMissingSubmissions;
    }

    @Override
    public int returnNumberOfProjects(int index) throws InvalidIndexException{
        if (this.editionList[index] == null) {
            throw new InvalidIndexException("The given index is null.\n");
        }
        return (this.editionList[index].getProjects().length);

    }

    @Override
    public int returnNumberOfEditions() {
        return this.editionCounter;
    }

    @Override
    public String returnProjectProgress(int index, String string) throws InvalidIndexException {
        String s  = "\t\tProject progress\n";
        if (this.editionList[index] == null) {
            throw new InvalidIndexException("The given index is null.\n");
        }

        Project project = this.editionList[index].getProject(string);
        if (project == null) {
            throw new InvalidIndexException("The given project name is invalid.\n");
        }
        s += "\t\tCurrent number of participants:\t" + project.getNumberOfParticipants() + "/" + project.getMaximumNumberOfParticipants();
        s += "\n\t\tCurrent number of partners:\t" + project.getNumberOfPartners() + "/" + project.getMaximumNumberOfPartners();
        s += "\n\t\tCurrent number of students:\t" + project.getNumberOfStudents() + "/" + project.getMaximumNumberOfStudents();
        s += "\n\t\tCurrent number of tasks:\t" + project.getNumberOfTasks() + "/" + project.getMaximumNumberOfTasks();
        s += "\n\t\tCurrent project state: \t" + project.isCompleted();
        return s;
    }

    @Override
    public String returnEditionProgress(int index) throws InvalidIndexException{
        String s = "\t\tEdition progress\n";
        if (this.editionList[index] == null) {
            throw new InvalidIndexException("The given index is null.\n");
        }
        Project[] projects = this.editionList[index].getProjects();
        s += "\t\tEdition name:\t" + this.editionList[index].getName();
        s += "\n\t\tStarts at/ Started at:\t" + this.editionList[index].getStart();
        s += "\n\t\tEnds at/ Ended at:\t" + this.editionList[index].getEnd();
        s += "\n\t\tCurrent edition status:\t" + this.editionList[index].getStatus();
        if (projects == null) {
            s += "\t\tNo projects available.\n";
        }else {
            s += "\n\t\tNumber of projects:\t" + this.editionList[index].getProjects().length;
            s += "\n\t\tRatio of completed projects:\t" + (double) (this.getNumberOfCompletedProjects(index) / this.editionList[index].getProjects().length) * 100 + "%\n";
        }

        return s;
    }
    private int getNumberOfCompletedProjects(int index) {
        Project[] projects = this.editionList[index].getProjects();
        int i = 0;

        for (int j = 0; j <projects.length; j++) {
            if (projects[j] != null) {
                if (projects[j].isCompleted()) {
                    i++;
                }
            }
        }
        return i;
    }
    @Override
    public void addProjectSubmissionToActiveEdition(Submission sbmsn, String studentEmail, String projectName, String title) throws InvalidIndexException , InvalidProjectNameException {
        int index = findActiveEdition();
        if (index == -1) {
            throw new InvalidIndexException("No active edition was found.");
        }
        Edition currentActiveEdition = this.editionList[index];

        Project currentProject = currentActiveEdition.getProject(projectName);
        if (currentProject == null) {
            throw new InvalidProjectNameException("The given project name was not found.");
        }
        if (currentProject.getParticipant(studentEmail) != null) {
            if (currentProject.getTask(title) != null) {
                currentProject.getTask(title).addSubmission(sbmsn);
            }else {
                throw new InvalidIndexException("The given task title is invalid.");
            }
        }else {
            throw new InvalidIndexException("The given student email is not present.");
        }
    }

    private int findActiveEdition() {
        for (int i = 0; i < this.editionCounter; i++) {
            if (this.editionList[i].getStatus() == Status.ACTIVE) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String listEditionInformation() {
        String s = "\t\tEditions Information";
        s += "\n\t\t----------------------------------";
        for (Edition edition : this.editionList) {
            s += "\n\t\tEdition Name: " + edition.getName();
            s += "\n\t\tStart Date:" + edition.getStart();
            s += "\n\t\tProject Template: " + edition.getProjectTemplate();
            s += "\n\t\tStatus: " + edition.getStatus();
            s += "\n\t\t----------------------------------";
        }
        return s;
    }

    @Override
    public String listProjectInformationByEdition() {
        String s = "\t\t\tProjects Information By Edition";
        s += "\n\t\t----------------------------------";
        for (Edition edition : this.editionList) {
            s += "\n\t\tEdition Name: \t"+ edition.getName();
            s += "\n\t\t----------------------------------";

            Project[] projects = edition.getProjects();

            for (Project project: projects) {
                s += "\n\t\t\tProject Name:\t" + project.getName();
                s += "\n\t\t\tDescription:\t" + project.getDescription();
                                        /*
                                            Não se pode usar java.utils, tenho de criar
                                            um toString para o tags.
                                         */
                s += "\n\t\t\tTags " + Arrays.toString(project.getTags());
                s += "\n\t\t\t----------------------------------";
            }
        }
        return s;
    }

    @Override
    public String listProjectStatusByEdition() {
        String s = "\t\t\tProject Status Summary by Edition";
        s += "\n\t\t\t----------------------------------";

        for (Edition edition: this.editionList) {
            s += "\n\t\t\tEdition Name:\t" + edition.getName();
            s += "\n\t\t\t----------------------------------";

            Project[] projects = edition.getProjects();
            int completed, uncompleted;
            completed = uncompleted = 0;
            for (Project project : projects) {
                boolean state = project.isCompleted();
                if (state) {
                    completed++;
                }else {
                    uncompleted++;
                }
            }
            s += "\n\t\t\t" + completed + "/" + uncompleted + ". (" +
                    (double) ((completed+uncompleted) / completed)*100 + ")";
        }
        return s;
    }
}
