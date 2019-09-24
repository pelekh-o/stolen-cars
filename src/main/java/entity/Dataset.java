package entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Immutable
@Table(name = "dataset", schema = "stolen_cars")
public class Dataset implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DATASETID", unique = true, nullable = false)
    private int datasetid;
    @Column(name = "CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "UPDATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    @Column(name = "URL")
    private String url;
    @Column (name = "CARSCOUNT")
    private int carscount;

    public Dataset() {
        created = updated = new Date();
    }

    public Dataset(String url, int carscount) {
        this.created = updated = new Date();
        this.url = url;
        this.carscount = carscount;
    }

    public int getDatasetid() {
        return datasetid;
    }

    public void setDatasetid(int datasetid) {
        this.datasetid = datasetid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCarscount() {
        return carscount;
    }

    public void setCarscount(int carscount) {
        this.carscount = carscount;
    }

    @Override
    public String toString() {
        return "Dataset{" +
                "datasetid=" + datasetid +
                ", created=" + created +
                ", url='" + url + '\'' +
                ", carscount=" + carscount +
                '}';
    }
}
