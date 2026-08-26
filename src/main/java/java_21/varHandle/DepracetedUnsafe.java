package java_21.varHandle;

import java_21.RecordPattern;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class DepracetedUnsafe {

/*
    static final VarHandle VH_FOO_FIELD_I;

    static {
        try {
            VH_FOO_FIELD_I = MethodHandles.lookup().
                    in(RecordPattern.class).
                    findVarHandle(RecordPattern.class, "i", int.class);
        } catch (Exception e) {
            throw new Error(e);
        }


    }

    public static void handle () {
        /// https://openjdk.org/jeps/454
       //  On-Heap and Off-Heap Memory for native applicaiton

    }*/



    public static void main (String[] args){

        // volatile memory order SEE THE MOST UPDATE VALUE OF THE VARIABLE
        String name = "Hello memory access";
        VarHandle.AccessMode[] varHandle =  VarHandle.AccessMode.values();
        System.out.println(varHandle);
    }





}
