/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/***********************************************************
     Author: Minh B. Do - Arizona State University
***********************************************************/
package edu.asu.sapa.basic_ds;

/**
 *   Event: Store the class that represent an event.
 *   Event occurs at some time instance and changes
 *   the value of some predicate (T/F) in the state.
 */
public class FunctionEvent extends Event{
    Integer funcID; // ID of this Ground Predicate
    GMySet gs;
    
   
    public float setValue;

    public FunctionEvent() {
		funcID = new Integer(-1);
        time = 0;

    }

    public FunctionEvent(Integer p) {
		funcID = p;
		time = 0;
    }

    

    public FunctionEvent(Integer p, float v, float t,GMySet g) {
		predID = p;
		setValue = v;
		time = t;
                gs = g;
    }

    public GMySet getMySet(){
        return gs;
    }

    public FunctionEvent(FunctionEvent e) {
		funcID = e.funcID;
		setValue = e.setValue;
		time = e.time;
    }

    public void setFunc(Integer p) {
		funcID = p;
    }

    public Integer getFunc() {
		return funcID;
    }

    public void setValue(float v){
        setValue = v;
    }

    public float getValue(){
        return setValue;
    }

    
    /* Set the (continuous) time instance at which this event occurs */
    //@Override
    public void setTime(float f) {
		time = f;
    }

    //@Override
    public float getTime() {
		return time;
    }
}
