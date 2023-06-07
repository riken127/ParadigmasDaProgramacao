package project;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Submission;

public interface CblManagementInterface {
    void addEdition(Edition newCblEdition);
    void removeEdition(int index);
    Edition returnEdition(int index);
    void addProjectToEdition(int index);
    void setActiveEdition(int index);

    /**
     * Retorna edições que possuem projetos com submissões em falta em tarefas.
     */
    Edition[] returnEditionsWithProjectsMissingSubmissionsInTasks();
    /**
     * Projetos com submissões em falta de um edição e da edição ativa
     */
    Project[] returnProjectsWithMissingSubmissions();
    int returnNumberOfProjects(int index);
    int returnNumberOfEditions();
    String returnProjectProgress(int index, String string);
    String returnEditionProgress(int index);
    void addProjectSubmissionToActiveEdition(Submission submission);

    /**
     *  Listagens.
     */
    String listOne();
    String listTwo();
    String listThree();
}
