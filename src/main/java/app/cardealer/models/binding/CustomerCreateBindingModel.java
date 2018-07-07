package app.cardealer.models.binding;

public class CustomerCreateBindingModel {

    private String name;
    private String birthDateString;

    public CustomerCreateBindingModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDateString() {
        return this.birthDateString;
    }

    public void setBirthDateString(String birthDate) {
        this.birthDateString = birthDate;
    }
}
