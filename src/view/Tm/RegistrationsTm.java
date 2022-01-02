package view.Tm;

import java.time.LocalDate;

public class RegistrationsTm {
    private String regNo;
    private String sId;
    private String cId;
    private String cName;
    private LocalDate regDate;

    public RegistrationsTm() {
    }

    public RegistrationsTm(String regNo, String sId, String cId, String cName, LocalDate regDate) {
        this.regNo = regNo;
        this.sId = sId;
        this.cId = cId;
        this.cName = cName;
        this.regDate = regDate;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "RegistrationsTm{" +
                "regNo='" + regNo + '\'' +
                ", sId='" + sId + '\'' +
                ", cId='" + cId + '\'' +
                ", cName='" + cName + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
