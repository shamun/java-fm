/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package figoo.classes;


/**
 *
 * @author Lada Riha
 */
public class Session {
private String []  rootsL;
private String []  rootsR;

    /**
     * @return the roots
     */
    public String [] getRootsL() {
        return rootsL;
    }

    /**
     * @param roots the roots to set
     */
    public void setRootsL(String [] roots) {
        this.rootsL = roots;
    }

    /**
     *
     * @param t
     * @param i
     */
    public void setRootL(String t, int i){
    this.rootsL[i]=t;
    }

    /**
     *
     * @param selectedIndex
     * @return
     */
    public String getRootL(int selectedIndex) {
        return  this.rootsL[selectedIndex];
    }

    /**
     *
     * @return
     */
    public String [] getRootsR() {
        return rootsR;
    }

    /**
     * @param roots the roots to set
     */
    public void setRootsR(String [] roots) {
        this.rootsR = roots;
    }

    /**
     *
     * @param t
     * @param i
     */
    public void setRootR(String t, int i){
    this.rootsR[i]=t;
    }

    /**
     *
     * @param selectedIndex
     * @return
     */
    public String getRootR(int selectedIndex) {
        return  this.rootsR[selectedIndex];
    }


}
