/*
* Nome: <João Pedro Salgado Pereira>
* Número: <8220102>
* Turma: <LEI1T4>
*
* Nome: <José Henrique Noronha Oliveira e Silva>
* Número: <8220343>
* Turma: <LEI1T4>
 */
package ppgrupo3;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import exceptions.EditionAlreadyInListException;
import exceptions.InvalidIndexException;
import ma02_resources.participants.InstituitionType;
import ma02_resources.project.Status;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import participants.ContactImpl;
import participants.InstituitionImpl;
import participants.ParticipantImpl;
import participants.FacilitatorImpl;
import participants.PartnerImpl;
import participants.StudentImpl;
import project.*;

public class PPGrupo3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalNumberOfParticipantType, ParticipantAlreadyInProject {

        ContactImpl contacto = new ContactImpl("Rua dos Cravos", "Guimaraes", "Braga", "4325-324", "Porgual", "91242154");
        InstituitionImpl instituicao = new InstituitionImpl("FM Teck", "FMTek@gmail.com", InstituitionType.COMPANY, contacto, "www.FMTek.com", "Somos uma empresa");
        ParticipantImpl participante = new ParticipantImpl("Joao", "joao@gmail.com", instituicao, contacto);
        FacilitatorImpl facilitador = new FacilitatorImpl("Joao o Facilitador", "joaoF@gmail.com", instituicao, contacto, "Informatica");
        PartnerImpl parceiro = new PartnerImpl("Joao o Parceiro", "joaoP@gmail.com", instituicao, contacto, "vat", "www.Parceiro.com");
        StudentImpl estudante = new StudentImpl("Joao o Aluno 1", "joaoA1@gmail.com", instituicao, contacto, 33);
        StudentImpl estudante2 = new StudentImpl("Joao o Aluno 2", "joaoA2@gmail.com", instituicao, contacto, 33);
        StudentImpl estudante3 = new StudentImpl("Joao o Aluno 3", "joaoA3@gmail.com", instituicao, contacto, 33);

        SubmissionImpl submicao = new SubmissionImpl(LocalDateTime.now(), estudante, "Primeira Submição");
        TaskImpl task = new TaskImpl(LocalDate.now(), LocalDate.of(2022, Month.OCTOBER, 10), "Carro", "Fazer um carro");
        ProjectImpl projeto = new ProjectImpl("Primeiro projeto", "Um projeto pa desemvolver", 4, 8, 2, 5, 3);
        EditionImpl edition = new EditionImpl("Edição 2023", LocalDate.now(), LocalDate.now().plusDays(200), "project_template.json", Status.ACTIVE);
        String[] tags = {"manager", "cbl", "software"};
        try {
            edition.addProject("CBL manager", "Software de gestão de cbl", tags);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        //task.addSubmission(submicao);
        CblManagement cbl = new CblManagement();
        try {
            cbl.addEdition(edition);
            System.out.println(cbl.returnEditionProgress(0));
        } catch (EditionAlreadyInListException | InvalidIndexException e) {
            throw new RuntimeException(e);
        }


        try {
            projeto.addParticipant(estudante);
            projeto.addParticipant(facilitador);
            projeto.addParticipant(parceiro);
            projeto.addParticipant(estudante2);
            //projeto.addParticipant(estudante3);

            projeto.addTask(task);
                        projeto.addTask(task);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        projeto.removeParticipant("joaoA2@gmail.com");

        System.out.println(projeto.isCompleted());
        
        System.out.println("Participantes - " +projeto.getNumberOfParticipants());
        System.out.println("Facilitadores - " +projeto.getNumberOfFacilitators());
        System.out.println("Parceiros - " +projeto.getNumberOfPartners());
        System.out.println("Alunos - " +projeto.getNumberOfStudents());
        System.out.println("Tags - " + projeto.getNumberOfTasks());

    }

}
