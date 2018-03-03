package client;

import javax.json.JsonObject;


public class ValueTabPeriph {
    private JsonObject Gyro;
    private JsonObject Acce;
    private JsonObject Gps;
    private JsonObject Capt_cont;
    private JsonObject Capt_son;

    public ValueTabPeriph() {
        Gyro = null;
        Acce = null;
        Gps = null;
        Capt_cont = null;
        Capt_son = null;
    }

    public ValueTabPeriph(JsonObject gyro, JsonObject acce, JsonObject gps, JsonObject capt_cont, JsonObject capt_son) {
        Gyro = gyro;
        Acce = acce;
        Gps = gps;
        Capt_cont = capt_cont;
        Capt_son = capt_son;
    }

    public JsonObject getGyro() {
        return Gyro;
    }

    public void setGyro(JsonObject gyro) {
        Gyro = gyro;
    }

    public JsonObject getAcce() {
        return Acce;
    }

    public void setAcce(JsonObject acce) {
        Acce = acce;
    }

    public JsonObject getGps() {
        return Gps;
    }

    public void setGps(JsonObject gps) {
        Gps = gps;
    }

    public JsonObject getCapt_cont() {
        return Capt_cont;
    }

    public void setCapt_cont(JsonObject capt_cont) {
        Capt_cont = capt_cont;
    }

    public JsonObject getCapt_son() {
        return Capt_son;
    }

    public void setCapt_son(JsonObject capt_son) {
        Capt_son = capt_son;
    }
    public String toString(){
        return ""+this.getGyro()+this.getGps()+this.getAcce()+this.getCapt_cont()+this.getCapt_son();
    }
  /*  public JsonObject CreateGyro(int id, double x,double y,double z){
        JsonObject js = null;
        js.put("id", );
        js.put("name", "gyroscope");
        JSONObject data=new JSONObject();
        data.put("x", x);
        data.put("y", y);
        data.put("z", z);
        js.put("data",data);
        System.out.println(js);
        return js;

    }

    public JSONObject CreatAcce(int id, double x,double y,double z){
        JSONObject js=new JSONObject();
        js.put("id", id);
        js.put("name", "accelerometre");
        JSONObject data=new JSONObject();
        data.put("x", x);
        data.put("y", y);
        data.put("z", z);
        js.put("data",data);
        System.out.println(js);
        return js;
    }

    public JSONObject CreatCaptUltra(int id, double z){
        JSONObject js=new JSONObject();
        js.put("id", id);
        js.put("name", "capteur-ultra-son");
        js.put("z", z);
        System.out.println(js);
        return js;
    }

    public JSONObject CreatGPS(int id, double x,double y,double z, double speed){
        JSONObject js=new JSONObject();
        js.put("id", id);
        js.put("name", "GPS");
        JSONObject data=new JSONObject();
        data.put("x", x);
        data.put("y", y);
        data.put("z", z);
        js.put("data",data);
        js.put("speed", speed);
        System.out.println(js);
        return js;
    }

    public JSONObject CreatCaptContact(int id, boolean n, boolean s, boolean e, boolean w){
        JSONObject js=new JSONObject();
        js.put("id", id);
        js.put("name", "capteur-contact");
        JSONObject data=new JSONObject();
        data.put("n", n);
        data.put("s", s);
        data.put("e", e);
        data.put("w", w);
        js.put("data",data);
        System.out.println(js);
        return js;
    }
*/

}
