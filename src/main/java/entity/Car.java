package entity;

import javax.persistence.*;
import java.sql.Date;
import java.text.ParseException;

@Entity
@Table(name = "car", schema = "stolen_cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id", unique = true, nullable = false)
    private int CAR_ID;
    @Column(name = "ID", unique = true)
    private String ID;              // received ID from MVS db
    @Column(name = "OVD")
    private String OVD;             // region
    @Column(name = "BRAND")
    private String BRAND;           // make and model
    @Column(name = "COLOR")
    private String COLOR;           // body color
    @Column(name = "VEHICLENUMBER")
    private String VEHICLENUMBER;
    @Column(name = "BODYNUMBER")
    private String BODYNUMBER;
    @Column(name = "CHASSISNUMBER")
    private String CHASSISNUMBER;
    @Column(name = "ENGINENUMBER")
    private String ENGINENUMBER;
    @Column(name = "THEFT_DATA")
    private Date THEFT_DATA;
    @Column(name = "INSERT_DATE")
    private Date INSERT_DATE;

    public Car() {
    }

    public Car(String ID, String OVD, String BRAND, String COLOR, String VEHICLENUMBER, String BODYNUMBER,
               String CHASSISNUMBER, String ENGINENUMBER, Date THEFT_DATA, Date INSERT_DATE) throws ParseException {
        this.ID = ID;
        this.OVD = OVD;
        this.BRAND = BRAND;
        this.COLOR = COLOR;
        this.VEHICLENUMBER = VEHICLENUMBER;
        this.BODYNUMBER = BODYNUMBER;
        this.CHASSISNUMBER = CHASSISNUMBER;
        this.ENGINENUMBER = ENGINENUMBER;
        this.THEFT_DATA = THEFT_DATA;
        this.INSERT_DATE = INSERT_DATE;
    }

    public int getCAR_ID() {
        return CAR_ID;
    }

    public void setCAR_ID(int CAR_ID) {
        this.CAR_ID = CAR_ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOVD() {
        return OVD;
    }

    public void setOVD(String OVD) {
        this.OVD = OVD;
    }

    public String getBRAND() {
        return BRAND;
    }

    public void setBRAND(String BRAND) {
        this.BRAND = BRAND;
    }

    public String getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    public String getVEHICLENUMBER() {
        return VEHICLENUMBER;
    }

    public void setVEHICLENUMBER(String VEHICLENUMBER) {
        this.VEHICLENUMBER = VEHICLENUMBER;
    }

    public String getBODYNUMBER() {
        return BODYNUMBER;
    }

    public void setBODYNUMBER(String BODYNUMBER) {
        this.BODYNUMBER = BODYNUMBER;
    }

    public String getCHASSISNUMBER() {
        return CHASSISNUMBER;
    }

    public void setCHASSISNUMBER(String CHASSISNUMBER) {
        this.CHASSISNUMBER = CHASSISNUMBER;
    }

    public String getENGINENUMBER() {
        return ENGINENUMBER;
    }

    public void setENGINENUMBER(String ENGINENUMBER) {
        this.ENGINENUMBER = ENGINENUMBER;
    }

    public Date getTHEFT_DATA() {
        return THEFT_DATA;
    }

    public void setTHEFT_DATA(Date THEFT_DATA) {
        this.THEFT_DATA = THEFT_DATA;
    }

    public Date getINSERT_DATE() {
        return INSERT_DATE;
    }

    public void setINSERT_DATE(Date INSERT_DATE) {
        this.INSERT_DATE = INSERT_DATE;
    }

    @Override
    public String toString() {
        return "Car{" +
                "ID='" + ID + '\'' +
                ", OVD='" + OVD + '\'' +
                ", BRAND='" + BRAND + '\'' +
                ", COLOR='" + COLOR + '\'' +
                ", VEHICLENUMBER='" + VEHICLENUMBER + '\'' +
                ", BODYNUMBER='" + BODYNUMBER + '\'' +
                ", CHASSISNUMBER='" + CHASSISNUMBER + '\'' +
                ", ENGINENUMBER='" + ENGINENUMBER + '\'' +
                ", THEFT_DATA='" + THEFT_DATA + '\'' +
                ", INSERT_DATE='" + INSERT_DATE + '\'' +
                '}';
    }
}
