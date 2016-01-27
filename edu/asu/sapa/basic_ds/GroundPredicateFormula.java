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
public class GroundPredicateFormula {
        boolean isPred,isDerivedPred;
        int id;//the pred id
        boolean isAnd,isOR,isNOT,isForAll,isExists,isImplies,isXOR;
        int paramId[];
        int numParams;
        ArrayList pf;
        ArrayList parameterList;
        
        public void ground(PredicateFormula pf1,ArrayList paramnames, ArrayList paramValues,Grounding g){
            if(pf==null)pf = new ArrayList();
            if(pf1.isPred==true){
                isPred = true;
                String predsig = new String(pf1.p.getName());
                for(int i=0; i<paramValues.size(); i++)
                    predsig += "*"+(String)paramValues.get(i);
                id = g.getPredIndex(predsig);
            }
            else
            if(pf1.isDerivedPred==true){
                isDerivedPred = true;
                String dpredsig = new String(pf1.p.getName());
                for(int i=0; i<paramValues.size(); i++)
                    dpredsig += (String)paramValues.get(i);
                id = g.getDPredIndex(dpredsig);
            }
            if(pf1.isAND==true){
                isAnd = true;
                pf = new ArrayList();
                for(int i=0; i<pf1.pf.size(); i++){
                    PredicateFormula pfi =(PredicateFormula) pf1.pf.get(i);
                    GroundPredicateFormula gpfi = new GroundPredicateFormula();
                    gpfi.ground(pfi, paramnames, paramValues, g);
                    pf.add(gpfi);
                }
            }
            else
            if(pf1.isOR==true){
                isOR = true;
                pf = new ArrayList();
                for(int i=0; i<pf1.pf.size(); i++){
                    PredicateFormula pfi =(PredicateFormula) pf1.pf.get(i);
                    GroundPredicateFormula gpfi = new GroundPredicateFormula();
                    gpfi.ground(pfi, paramnames, paramValues, g);
                    pf.add(gpfi);
                }
            }
            else
            if(pf1.isNOT==true){
                isNOT = true;
                pf = new ArrayList();
                if(pf1.pf.size()>1){
                    System.out.println("More than one predform in NOT... something wrong");
                    System.exit(-1);
                }
                PredicateFormula pf0 =(PredicateFormula) pf1.pf.get(0);
                GroundPredicateFormula gpf0 = new GroundPredicateFormula();
                gpf0.ground(pf0, paramnames, paramValues, g);
                pf.add(gpf0);
            }
            else
            if(pf1.isForAll==true){
                isForAll = true;
                int size = pf1.numPara();
		int[] index = new int[size], numObj = new int[size];
		ArrayList[] objSet = new ArrayList[size];
		String type;
                if(size==0){
                    System.out.println("Forall specified without parameters... something wrong");
                    System.exit(-1);
                }
                parameterList = new ArrayList();
                g.getObjectSet(pf1.getParaTypes(), parameterList, id);
                for(int i=0; i<parameterList.size(); i++){
                    ArrayList params = (ArrayList)parameterList.get(i);
                    GroundPredicateFormula gpfi = new GroundPredicateFormula();
                    PredicateFormula ptemp = (PredicateFormula)pf1.pf.get(0);
                    gpfi.ground(ptemp,pf1.parameters,params,g);
                    pf.add(gpfi);
                }
            }
            else
            if(pf1.isExists==true){
                isExists = true;
                int size = pf1.numPara();
		int[] index = new int[size], numObj = new int[size];
		ArrayList[] objSet = new ArrayList[size];
		String type;
                if(size==0){
                    System.out.println("Exists specified without parameters... something wrong");
                    System.exit(-1);
                }
                parameterList = new ArrayList();
                if(pf1.getParaTypes().size()>0){
                g.getObjectSet(pf1.getParaTypes(), parameterList, id);

                for(int i=0; i<parameterList.size(); i++){
                    ArrayList params = (ArrayList)parameterList.get(i);
                    GroundPredicateFormula gpfi = new GroundPredicateFormula();
                    PredicateFormula ptemp = (PredicateFormula)pf1.pf.get(0);
                    gpfi.ground(ptemp,pf1.parameters,params,g);
                    pf.add(gpfi);
                }
                }
            }
            else
            if(pf1.isImplies==true){
                isImplies = true;
                pf = new ArrayList();
                if(pf1.pf.size()>2){
                    System.out.println("More than two predform in IMPLIES... something wrong");
                    System.exit(-1);
                }
                PredicateFormula pf0 =(PredicateFormula) pf1.pf.get(0);
                PredicateFormula pf2 =(PredicateFormula) pf1.pf.get(1);
                GroundPredicateFormula gpf0 = new GroundPredicateFormula();
                GroundPredicateFormula gpf1 = new GroundPredicateFormula();
                gpf0.ground(pf0, paramnames, paramValues, g);
                gpf1.ground(pf2, paramnames, paramValues, g);
                pf.add(gpf0);
                pf.add(gpf1);
            }
            else
            if(pf1.isXOR==true){
                isXOR = true;
                pf = new ArrayList();
                if(pf1.pf.size()>2){
                    System.out.println("More than two predform in XOR... something wrong");
                    System.exit(1);
                }
                PredicateFormula pf0 =(PredicateFormula) pf1.pf.get(0);
                PredicateFormula pf2 =(PredicateFormula) pf1.pf.get(1);
                GroundPredicateFormula gpf0 = new GroundPredicateFormula();
                GroundPredicateFormula gpf1 = new GroundPredicateFormula();
                gpf0.ground(pf0, paramnames, paramValues, g);
                gpf1.ground(pf2, paramnames, paramValues, g);
                pf.add(gpf0);
                pf.add(gpf1);
            }

        }

        public boolean isTrue(GState g){
            if(isPred==true){
                if(g.containPred(new Integer(id)))return true;
                else return false;
            }
            if(isDerivedPred==true){
                return g.isTrue(id);
            }
            if(isAnd==true){
                for(int i=0; i<pf.size(); i++){
                    GroundPredicateFormula pfi =(GroundPredicateFormula) pf.get(i);
                    if(pfi.isTrue(g)==false)return false;
                }
                return true;
            }
            if(isOR==true){
                for(int i=0; i<pf.size(); i++){
                    GroundPredicateFormula pfi =(GroundPredicateFormula) pf.get(i);
                    if(pfi.isTrue(g)==true)return true;
                }
                return false;
            }
            
            if(isNOT==true){
                if(g.containPred(new Integer(id)))return false;
                else return true;
            }
            
            if(isForAll==true){               
                for(int i=0; i<pf.size(); i++){
                    GroundPredicateFormula pfi =(GroundPredicateFormula) pf.get(i);
                    if(pfi.isTrue(g)==false)return false;
                }
                return true;
            }
            
            if(isExists==true){
                for(int i=0; i<pf.size(); i++){
                    GroundPredicateFormula pfi =(GroundPredicateFormula) pf.get(i);
                    if(pfi.isTrue(g)==true)return true;
                }
                return false;
            }
            
            if(isImplies==true){                
                if(pf.size()>2){
                    System.out.println("More than two predform in IMPLIES... something wrong");
                    System.exit(-1);
                }
                GroundPredicateFormula pf0 =(GroundPredicateFormula) pf.get(0);
                GroundPredicateFormula pf2 =(GroundPredicateFormula) pf.get(1);
                if(pf0.isTrue(g)==false)return true;
                if(pf2.isTrue(g))return true;
            }
            
            if(isXOR==true){
               
                if(pf.size()>2){
                    System.out.println("More than two predform in XOR... something wrong");
                    System.exit(1);
                }
                GroundPredicateFormula pf0 =(GroundPredicateFormula) pf.get(0);
                GroundPredicateFormula pf2 =(GroundPredicateFormula) pf.get(1);
                if((pf0.isTrue(g)&& !(pf2.isTrue(g))) ||(pf2.isTrue(g)&& !(pf0.isTrue(g))))return true;
            }
            return false;
        }
       
}
