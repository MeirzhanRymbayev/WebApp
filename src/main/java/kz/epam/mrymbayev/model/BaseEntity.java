package kz.epam.mrymbayev.model;

public abstract class BaseEntity {

    protected Long id;

    public BaseEntity(Long id){
        this.id = id;
    }

    public BaseEntity(){

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    /*
     * If method returns true - use update
     *
     */
    public boolean isPersisted(){
        return id != null;
    }

    /*
     * If method returns true - use insert
     *
     */
    public boolean isNotPersisted() {
        return id == null;
    }

}
