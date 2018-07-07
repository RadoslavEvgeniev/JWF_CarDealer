package app.cardealer.models.view;

public class CustomerEditViewModel {

    private Long id;
    private String name;
    private String birthDateString;

    public CustomerEditViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setBirthDateString(String birthDateString) {
        this.birthDateString = birthDateString;
    }
}
