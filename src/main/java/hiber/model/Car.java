package hiber.model;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @Column
    private Long id;

    @Column
    private String model;

    @Column
    private int series;

    @OneToOne
    @MapsId
    private User user;

    public Car() {

    }

    public Car(String model, int series, User user) {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", series=" + series +
                ", user=" + user +
                '}';
    }
}
