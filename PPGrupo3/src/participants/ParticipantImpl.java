/*
* Nome: <João Pedro Salgado Pereira>
* Número: <8220102>
* Turma: <LEI1T4>
*
* Nome: <José Henrique Noronha Oliveira e Silva>
* Número: <8220343>
* Turma: <LEI1T4>
*/
package participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Participant;

public class ParticipantImpl implements Participant{

    private String name;
    private String email;
    private InstituitionImpl institution;
    private Contact contact;

    public ParticipantImpl(String name, String email, InstituitionImpl institution, Contact contact) {
        this.name = name;
        this.email = email;
        this.institution = institution;
        this.contact = contact;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public Contact getContact() {
        return this.contact;
    }

    @Override
    public Instituition getInstituition() {
        return this.institution;
    }

    @Override
    public void setContact(Contact cntct) {
        this.contact = cntct;
    }

    @Override
    public void setInstituition(Instituition instn) {
        this.institution = (InstituitionImpl) instn;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Participant)) {
            return false;
        }
        ParticipantImpl temporaryParticipant = (ParticipantImpl) obj;
        if (temporaryParticipant == this) {
            return true;
        }

        return (temporaryParticipant.getInstituition().equals(this.getInstituition()) &&
                temporaryParticipant.getContact().equals(this.getContact()) &&
                temporaryParticipant.getEmail().equals(this.getEmail()) &&
                temporaryParticipant.getName().equals(this.getName()));
    }
}
