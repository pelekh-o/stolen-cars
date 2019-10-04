package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "car_info", schema = "stolen_cars")
public class CarInfo {
    private Integer carinfoId;
    private String regAddrKoatuu;
    private String operationCode;
    private String operationName;
    private Date regdate;
    private String departmentCode;
    private String brand;
    private String model;
    private Integer makeYear;
    private String color;
    private String kind;
    private String body;
    private String fuel;
    private Integer capacity;
    private Integer ownWeight;
    private Integer totalWeight;
    private String vehicleNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carinfo_id", unique = true, nullable = false)
    public Integer getCarinfoId() {
        return carinfoId;
    }

    public void setCarinfoId(Integer carinfoId) {
        this.carinfoId = carinfoId;
    }

    @Basic
    @Column(name = "reg_addr_koatuu")
    public String getRegAddrKoatuu() {
        return regAddrKoatuu;
    }

    public void setRegAddrKoatuu(String regAddrKoatuu) {
        this.regAddrKoatuu = regAddrKoatuu;
    }

    @Basic
    @Column(name = "operation_code")
    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    @Basic
    @Column(name = "operation_name")
    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    @Basic
    @Column(name = "regdate")
    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    @Basic
    @Column(name = "department_code")
    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "make_year")
    public Integer getMakeYear() {
        return makeYear;
    }

    public void setMakeYear(Integer makeYear) {
        this.makeYear = makeYear;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "kind")
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Basic
    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "fuel")
    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    @Basic
    @Column(name = "capacity")
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "own_weight")
    public Integer getOwnWeight() {
        return ownWeight;
    }

    public void setOwnWeight(Integer ownWeight) {
        this.ownWeight = ownWeight;
    }

    @Basic
    @Column(name = "total_weight")
    public Integer getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
    }

    @Basic
    @Column(name = "vehicle_number")
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vechicleNumber) {
        this.vehicleNumber = vechicleNumber;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "carinfoId=" + carinfoId +
                ", regAddrKoatuu='" + regAddrKoatuu + '\'' +
                ", operationCode=" + operationCode +
                ", operationName='" + operationName + '\'' +
                ", regdate=" + regdate +
                ", departmentCode='" + departmentCode + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", makeYear=" + makeYear +
                ", color='" + color + '\'' +
                ", kind='" + kind + '\'' +
                ", body='" + body + '\'' +
                ", fuel='" + fuel + '\'' +
                ", capacity=" + capacity +
                ", ownWeight=" + ownWeight +
                ", totalWeight=" + totalWeight +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarInfo carInfo = (CarInfo) o;
        return Objects.equals(regAddrKoatuu, carInfo.regAddrKoatuu) &&
                Objects.equals(operationCode, carInfo.operationCode) &&
                Objects.equals(operationName, carInfo.operationName) &&
                Objects.equals(regdate, carInfo.regdate) &&
                Objects.equals(departmentCode, carInfo.departmentCode) &&
                Objects.equals(brand, carInfo.brand) &&
                Objects.equals(model, carInfo.model) &&
                Objects.equals(makeYear, carInfo.makeYear) &&
                Objects.equals(color, carInfo.color) &&
                Objects.equals(kind, carInfo.kind) &&
                Objects.equals(body, carInfo.body) &&
                Objects.equals(fuel, carInfo.fuel) &&
                Objects.equals(capacity, carInfo.capacity) &&
                Objects.equals(ownWeight, carInfo.ownWeight) &&
                Objects.equals(totalWeight, carInfo.totalWeight) &&
                Objects.equals(vehicleNumber, carInfo.vehicleNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regAddrKoatuu, operationCode, operationName, regdate, departmentCode, brand, model, makeYear, color, kind, body, fuel, capacity, ownWeight, totalWeight, vehicleNumber);
    }
}
