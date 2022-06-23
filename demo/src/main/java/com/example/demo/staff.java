package com.example.demo;


public abstract class staff {
    private String staffMemberName;
    private int staffVacationDays = 30;
    private int staffOvertimeDays = 0;

    public String getStaffMemberName() {
        return staffMemberName;
    }

    public int getStaffVacationDays() {
        return staffVacationDays;
    }

    public int getStaffOvertimeDays() {
        return staffOvertimeDays;
    }

    public void setStaffMemberName(String staffMemberName) {
        this.staffMemberName = staffMemberName;
    }

    public void setStaffVacationDays(int staffVacationDays) {
        this.staffVacationDays = staffVacationDays;
    }

    public void setStaffOvertimeDays(int staffOvertimeDays) {
        this.staffOvertimeDays = staffOvertimeDays;
    }

    public int staffCalculateVDays() {

        return 0;
    }

}