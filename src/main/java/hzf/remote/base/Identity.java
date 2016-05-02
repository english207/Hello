package hzf.remote.base;

/**
 * Created by WTO on 2016/5/1 0001.
 *
 */
public enum Identity
{
    NoBody("NoBody"),
    Mediator("Mediator"),
    Leader("Leader"),
    Worker("Worker");

    private String type;
    Identity(String type)
    {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Identity{" +
                "type='" + type + '\'' +
                '}';
    }
}
