package com.njfu.entity;

public class Staff {

    private String staff_id;
    private String power;
    private String work;

    public Staff() {
    }

    public Staff(String staff_id, String power, String work) {
        this.staff_id = staff_id;
        this.power = power;
        this.work = work;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staff_id='" + staff_id + '\'' +
                ", power='" + power + '\'' +
                ", work='" + work + '\'' +
                '}';
    }
}
