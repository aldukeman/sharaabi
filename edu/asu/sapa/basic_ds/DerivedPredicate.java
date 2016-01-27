/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.asu.sapa.basic_ds;
import java.util.ArrayList;
import java.util.Hashtable;
/**
 *
 * @author AIDB
 */


public class DerivedPredicate extends Predicate {
    String name; //Name of this predicate

    ArrayList parameters; // List of all objects used in this action
    Hashtable para_map; // Map each objec with its type: obj->type
    PredicateFormula precondition;

    public void putPF(PredicateFormula p){
        precondition = p;
    }
    public int predSize(){
        if (parameters != null) {
            return parameters.size();
        }
        return 0;
    }
     /** Add a set of parameters and their (same) type of this action */
    public void putPara(ArrayList paras, String type) {
	String para;

	for(int i = 0; i < paras.size(); i++) {
	    para = (String) paras.get(i);
	    parameters.add(para);
	    para_map.put(para, type);
	}
    }

    public int numPara() {
	return parameters.size();
    }

    public String getPara(int index) {
	return (String) parameters.get(index);
    }

    public ArrayList getAllPara() {
	return parameters;
    }

    public ArrayList getParaTypes(){
        ArrayList pTypes = new ArrayList();
        for(int i=0; i<parameters.size(); i++){
            pTypes.add((String)para_map.get((String)parameters.get(i)));
        }
        return pTypes;
    }

    public String getParaType(Object obj) {
	return (String) para_map.get(obj);
    }

}
