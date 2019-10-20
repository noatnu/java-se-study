package other.nio.channel;

public enum PathEnum {
    PATH_ENUM("/home/zhou/CS/ssh/config/");
    private String var;

    PathEnum(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }
}
