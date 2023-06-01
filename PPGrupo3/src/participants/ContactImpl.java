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

public class ContactImpl implements Contact{

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String phone;

    public ContactImpl(String street, String city, String state, String zipCode, String country, String phone) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.phone = phone;
    }
    
    
    @Override
    public String getStreet() {
        return this.street;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public String getState() {
        return this.state;
    }

    @Override
    public String getZipCode() {
        return this.zipCode;
    }

    @Override
    public String getCountry() {
        return this.country;
    }

    @Override
    public String getPhone() {
        return this.phone;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof ContactImpl)) {
            return false;
        }
        ContactImpl temporaryContact = (ContactImpl) object;
        
        if (temporaryContact.getCity().equals(this.getCity()) &&
                temporaryContact.getCountry().equals(this.getCountry()) &&
                temporaryContact.getPhone().equals(this.getPhone()) &&
                temporaryContact.getState().equals(this.getState()) &&
                temporaryContact.getStreet().equals(this.getStreet()) &&
                temporaryContact.getZipCode().equals(this.getZipCode())) {
            return true;
        }
        return (temporaryContact == this);
    }
}
