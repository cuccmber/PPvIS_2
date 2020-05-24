package item;

public class Student {
    private String name;
    private int group;
    private int sicknessAbsenceCount;
    private int otherReasonAbsenceCount;
    private int noReasonAbsenceCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getSicknessAbsenceCount(){
        return sicknessAbsenceCount;
    }

    public void setSicknessAbsenceCount(int sicknessAbsenceCount) {
        this.sicknessAbsenceCount = sicknessAbsenceCount;
    }

    public int getOtherReasonAbsenceCount() {
        return otherReasonAbsenceCount;
    }

    public void setOtherReasonAbsenceCount(int otherReasonAbsenceCount) {
        this.otherReasonAbsenceCount = otherReasonAbsenceCount;
    }

    public int getNoReasonAbsenceCount() {
        return noReasonAbsenceCount;
    }

    public void setNoReasonAbsenceCount(int noReasonAbsenceCount){
        this.noReasonAbsenceCount = noReasonAbsenceCount;
    }

    public int getTotalAbsenceCount(){
        return sicknessAbsenceCount + otherReasonAbsenceCount + noReasonAbsenceCount;
    }

}
