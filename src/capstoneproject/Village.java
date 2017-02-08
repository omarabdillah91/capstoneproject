/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstoneproject;

/**
 *
 * @author omaabdillah
 */
public class Village {
    public String city = "";
    public String district = "";
    public String village = "";
    public String postal_code = "";
    
    public Village (String c, String d, String v, String p) {
        this.city = c;
        this.district = d;
        this.village = v;
        this.postal_code = p;
    }
    
}
