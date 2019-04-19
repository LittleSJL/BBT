/**
 * Created by leptop on 2019/3/10.
 */
package com.example.bbt_true_app;

public class Mission {
    private String gaishu;

    private String juti_xuqiu;

    private String yonghu;

    private String number_liulan;

    public Mission(String gaishu,String juti_xuqiu,String yonghu,String number_liulan)
    {
        this.gaishu = gaishu;
        this.juti_xuqiu = juti_xuqiu;
        this.yonghu = yonghu;
        this.number_liulan = number_liulan;
    }

    public String getGaishu() {
        return gaishu;
    }

    public String getJuti_xuqiu() {
        return juti_xuqiu;
    }

    public String getYonghu() {
        return yonghu;
    }

    public String getNumber_liulan() {
        return number_liulan;
    }

    public void setNumber_liulan(String number_liulan) {
        this.number_liulan = number_liulan;
    }
}
