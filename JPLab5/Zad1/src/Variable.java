public class Variable {
    float number;
    String name;
    public Variable(float number, String name){
        this.number = number;
        try{
            int x = Integer.parseInt(name);
            float value = number * x;
            this.number = value;
            this.name = "";
        }
        catch (NumberFormatException e){
            this.name = name;
        }

    }

    @Override
    public String toString() {
        return  number + name;
    }
}
