package com.company;

public class ValueContainer {
    private int v0, v1, v2;

    public int getV0() {
        return v0;
    }

    public int getV1() {
        return v1;
    }

    public int getV2() {
        return v2;
    }

    public boolean compare(ValueContainer c){
        return !(
                (c.getV0() == v0 && c.getV1() == v1 && c.getV2() == v2)||
                        (c.getV0() == v0 && c.getV1() == v2 && c.getV2() == v1)||
                        (c.getV0() == v1 && c.getV1() == v0 && c.getV2() == v2)||
                        (c.getV0() == v1 && c.getV1() == v2 && c.getV2() == v0)||
                        (c.getV0() == v2 && c.getV1() == v1 && c.getV2() == v0)||
                        (c.getV0() == v2 && c.getV1() == v0 && c.getV2() == v1)
        );
    }

    public void print(){
        System.out.println(v0 + ", " + v1 + ", " + v2);
    }

    public ValueContainer(int v0, int v1,int v2){
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
    }
}
