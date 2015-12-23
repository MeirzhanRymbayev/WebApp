package kz.epam.mrymbayev.model;

import java.sql.Date;
import java.util.List;

public class Voucher extends BaseEntity{

    private String type;
    private Integer cost;
    private String hotel;
    private String country;
    private String dayNightAmount;
    private String transport;
    private int localeId;
    private boolean status;
    private String folderName;
    private List<String> fileNames;
    private Date startDate;
    private Date endDate;

    public Voucher() {
    }

    public Voucher(Long id, String type, Integer cost) {
        super(id);
        this.type = type;
        this.cost = cost;
    }
    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDayNightAmount() {
        return dayNightAmount;
    }

    public void setDayNightAmount(String dayNightAmount) {
        this.dayNightAmount = dayNightAmount;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public int getLocaleId() {
        return localeId;
    }

    public void setLocaleId(int localeId) {
        this.localeId = localeId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Voucher{" +
                "type='" + type + '\'' +
                ", cost='" + cost + '\'' +
                ", hotel='" + hotel + '\'' +
                ", country='" + country + '\'' +
                ", dayNightAmount='" + dayNightAmount + '\'' +
                ", transport='" + transport + '\'' +
                ", localeId='" + localeId + '\'' +
                '}';
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
