package by.katerinachikova.soapwebservice.service.models;

public class AbstractModel implements Model {
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
