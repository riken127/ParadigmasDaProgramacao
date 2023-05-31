/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package participants;
import ma02_resources.participants.Contact;
import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Instituition;

/**
 *
 * @author User
 */

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
    public InstituitionImpl getInstituition() {
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
