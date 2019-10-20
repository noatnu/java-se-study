package core.orientedObject.final_;

public class FinalDemoBExtend extends FinalDemoB {

    public FinalDemoBExtend(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}
