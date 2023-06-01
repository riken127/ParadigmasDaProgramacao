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
import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Instituition;

public class FacilitatorImpl extends ParticipantImpl implements Facilitator {

    private String areaOfExpertise;
    public FacilitatorImpl(String name, String email, InstituitionImpl instituition, Contact contact, String areaOfExpertise) {
        super(name, email, instituition, contact);
        this.areaOfExpertise = areaOfExpertise;
    }
       
    @Override
    public String getAreaOfExpertise() {
        return this.areaOfExpertise;
    }

    @Override
    public void setAreaOfExpertise(String string) {
        this.areaOfExpertise = string;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public Contact getContact() {
        return super.getContact();
    }

    @Override
    public Instituition getInstituition() {
        return super.getInstituition();
    }


    @Override
    public void setContact(Contact cntct) {
        super.setContact(cntct);
    }

    @Override
    public void setInstituition(Instituition instn) {
    }
    
}
