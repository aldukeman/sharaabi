/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.asu.sapa.basic_ds;
import java.util.ArrayList;
import edu.asu.sapa.parsing.Grounding;
import edu.asu.sapa.complex_ds.GState;
/**
 *
 * @author AIDB
 */
public class GroundDerivedPredicate {
    int id = -1; // Unique ID for a ground derived predicate
    GroundPredicateFormula gf;
    public int getID(){
        return id;
    }
    public void setID(int i){
        id = i;
    }
    void addGPF(GroundPredicateFormula g){
        gf = g;
    }
    public void processParams(ArrayList params,DerivedPredicate dp, GroundPredicateFormula gf1,Grounding g){
        PredicateFormula pf = dp.precondition;
        ArrayList paramNames = dp.parameters;
        gf1.ground(pf,paramNames,params,g);
        addGPF(gf1);
    }
    public boolean isTrue(GState g){
        return(gf.isTrue(g));
    }    
}
