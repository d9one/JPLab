import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equation {
    List<Variable>left = new ArrayList<>();
    List<Variable>right = new ArrayList<>();

    public Equation(){
    }
    public void addToLeft(Variable variable){
        left.add(variable);
    }
    public void addToRight(Variable variable){
        right.add(variable);
    }

    public void flipVariable(Variable variable){
        Variable help = variable;
        float divisor = variable.number;

        if(left.contains(variable)){
            for (Variable x:left) {
                if(x!=help){
                    x.number *=-1;
                    right.add(x);
                }
            }
            left = new ArrayList<>();
            left.add(help);
        }

        if(right.contains(variable)){
            for (Variable x:right) {
                if(x!=help){
                    x.number *=-1;
                    left.add(x);
                }
            }
            right = new ArrayList<>();
            right.add(help);
        }
        for (Variable x: right) {
            x.number /= divisor;
        }
        for (Variable x: left) {
            x.number /= divisor;
        }
    }
    public void simplify(){

        for (Variable x: left) {
            x.number *= -1;
        }
        right.addAll(left);
        left = new ArrayList<>();

        for (int i=0; i < right.size(); i++) {
            Variable y = right.get(i);
            List<Variable> toRemove = new ArrayList<>();

            for (int j=i+1; j < right.size(); j++) {
                Variable x = right.get(j);
                if(Objects.equals(x.name, y.name)){
                    toRemove.add(x);
                    y.number += x.number;
                }
            }

            right.removeAll(toRemove);
        }

    }

    @Override
    public String toString() {
        return left + "=" + right;
    }
}
