package kz.epam.mrymbayev.model;

public abstract class BaseEntity {

    protected long id;

    public BaseEntity(long id){
        this.id = id;
    }

    public BaseEntity(){

    }

    public Long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    /*
     * If method returns true - use update
     *
     */
    public boolean isPersisted(){
        return id != 0L;
    }

    /*
     * If method returns true - use insert
     *
     */
    public boolean isNotPersisted() {
        return id == 0L;
    }

}
