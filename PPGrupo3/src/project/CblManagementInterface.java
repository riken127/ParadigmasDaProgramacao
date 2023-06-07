package project;
import exceptions.EditionAlreadyInListException;
import exceptions.InvalidIndexException;
import exceptions.InvalidProjectNameException;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Submission;

import java.io.IOException;
import java.text.ParseException;

public interface CblManagementInterface {
    void addEdition(Edition newCblEdition) throws EditionAlreadyInListException;
    void removeEdition(int index) throws InvalidIndexException;
    Edition returnEdition(int index);
    void addProjectToEdition(int editionIndex, String name, String description, String[] tags) throws InvalidIndexException, IOException, ParseException;
    void setActiveEdition(int index) throws InvalidIndexException;

    /**
     * Retorna edições que possuem projetos com submissões em falta em tarefas.
     */
    Edition[] returnEditionsWithProjectsMissingSubmissionsInTasks();
    /**
     * Projetos com submissões em falta de um edição e da edição ativa
     */
    Project[] returnProjectsWithMissingSubmissions(int index) throws InvalidIndexException;
    int returnNumberOfProjects(int index) throws InvalidIndexException;
    int returnNumberOfEditions();
    String returnProjectProgress(int index, String string) throws InvalidIndexException;
    String returnEditionProgress(int index) throws InvalidIndexException;
    void addProjectSubmissionToActiveEdition(Submission submission,  String studentEmail, String projectName, String title) throws InvalidIndexException , InvalidProjectNameException;

    /**
     *  Listagens.
     */
    String listOne();
    String listTwo();
    String listThree();
}
