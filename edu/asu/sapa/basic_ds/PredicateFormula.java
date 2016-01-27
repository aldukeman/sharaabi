/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.asu.sapa.basic_ds;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
/**
 *
 * @author AIDB
 */
public class PredicateFormula {
        boolean isPred,isDerivedPred;
        Predicate p;
        DerivedPredicate dp;
        boolean isAND,isOR,isNOT,isForAll,isExists,isImplies,isXOR;
        ArrayList parameters; // List of all objects used in this specification
        Hashtable para_map; // Map each objec with its type: obj->type
        ArrayList pf;

        
        public void setPredicate(Predicate p1){
            isPred = true;
            p = p1;
        }

      
        public void isNOTTrue(){
            isNOT = true;
        }
        public void setPredForm(PredicateFormula pf1){
            if(pf == null){
                pf = new ArrayList();
            }
            pf.add(pf1);
        }
        public void ORAND(String s){
            if(s.matches("or")) isOR = true;
            else isAND = true;
        }
        public void eitherimplies(String s){
            if(s.matches("either")) isXOR = true;
            else isImplies = true;
        }
        public void faexist(String s){
            if(s.matches("exists")) isExists = true;
            else isForAll = true;
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
        //for(int i=0; i<parameters.size(); i++){
            //pTypes.add((String)para_map.get((String)parameters.get(i)));
        //}
        Set ke = (Set)para_map.keySet();
        Iterator e = ke.iterator();
        while(e.hasNext()){
            pTypes.add(e.next());
        }
        return pTypes;
    }

    public String getParaType(Object obj) {
	return (String) para_map.get(obj);
    }

    public void putParaFE(ArrayList a, String type){
        if(a.size()!=0){
            System.out.println("TYpe"+type+"Params"+a);
            if(parameters==null)parameters = new ArrayList();
        parameters.addAll(a);
        if(para_map==null)para_map = new Hashtable();
        para_map.put(type, a);
        }
    }

}
