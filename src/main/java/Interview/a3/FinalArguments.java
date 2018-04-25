package Interview.a3;

public class FinalArguments {
    void with(final Gizmo g){// 一旦这样做了之后就无法 在方法中更改其参数类型
//        g = new Gizmo();//无法更改其类型
    }

    void without(Gizmo g){
        g = new Gizmo();
    }
}

class Gizmo{
    public void spin(){}
}
