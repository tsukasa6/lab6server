package core.stored;

import core.utils.IDGenerator;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

public class Worker implements Comparable<Worker>, Serializable {
    // Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private int id;
    // Поле не может быть null, Строка не может быть пустой
    private String name;
    // Поле не может быть null
    private Coordinates coordinates;
    // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Date creationDate;
    // Поле не может быть null, Значение поля должно быть больше 0
    private Long salary;
    // Поле не может быть null
    private ZonedDateTime startDate;
    // Поле может быть null
    private Date endDate;
    // Поле может быть null
    private Status status;
    // Поле не может быть null
    private Organization organization;

    public Worker(String name, Coordinates coordinates, Long salary, Status status, Organization organization) {
        this.id = IDGenerator.generateId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.salary = salary;
        this.startDate = ZonedDateTime.now();
        this.endDate = new Date();
        this.status = status;
        this.organization = organization;
    }

    public Worker() {}

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Long getSalary() {
        return this.salary;
    }

    public ZonedDateTime getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public Status getStatus() {
        return this.status;
    }

    public Organization getOrganization() {
        return this.organization;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public void setStartDate(ZonedDateTime startDate) { this.startDate = startDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public int compareTo(Worker worker) {
        return Integer.compare(this.id, worker.getId());
    }
}
