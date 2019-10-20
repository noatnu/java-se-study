package core.orientedObject.final_;

public class FinalDemoB {
    private String name;

    public FinalDemoB(String name) {
        this.name = name;
    }

    public final void play(){
        System.out.println("FinalDemoB.play");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
