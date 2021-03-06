package kz.epam.mrymbayev.model;

import java.sql.Date;
import java.util.List;

public class Voucher extends BaseEntity {

    private String type;
    private int cost;
    private String hotel;
    private String country;
    private String dayNightAmount;
    private String transport;
    private int localeId;
    private boolean hot;
    private String folderName;
    private List<String> fileNames;
    private Date startDate;
    private Date endDate;
    private int quantity;
    private double discount;

    public Voucher() {
    }

    public Voucher(long id, String type, Integer cost) {
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

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "type='" + type + '\'' +
                ", cost=" + cost +
                ", hotel='" + hotel + '\'' +
                ", country='" + country + '\'' +
                ", dayNightAmount='" + dayNightAmount + '\'' +
                ", transport='" + transport + '\'' +
                ", localeId=" + localeId +
                ", hot=" + hot +
                ", folderName='" + folderName + '\'' +
                ", fileNames=" + fileNames +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", quantity=" + quantity +
                ", discount=" + discount +
                '}';
    }
}
