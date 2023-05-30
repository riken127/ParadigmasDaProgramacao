/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppgrupo3;

import cbl.participants.Contact;
import cbl.participants.Facilitator;
import cbl.participants.Instituition;

/**
 *
 * @author User
 */
public class FacilitatorImpl extends ParticipantImpl implements Facilitator {

    private String areaOfExpertise;

    public FacilitatorImpl(String name, String email, Instituition instituition, Contact contact, String areaOfExpertise) {
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
    public void setInstituition(Instituition instn) {
        super.setInstituition(instn);
    }

    @Override
    public void setContact(Contact cntct) {
        super.setContact(cntct);
    }
    
}
