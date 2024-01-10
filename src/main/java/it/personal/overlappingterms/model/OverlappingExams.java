package it.personal.overlappingterms.model;

public class OverlappingExams {
    private String firstOverlappingCourse;
    private boolean firstCourseMandatory;
    private String secondOverlappingCourse;
    private boolean secondCourseMandatory;
    private long daysOfDistanceBetweenCalls;

    private Integer year;
    private boolean sameSemester;

    public String getFirstOverlappingCourse() {
        return firstOverlappingCourse;
    }

    public boolean isFirstCourseMandatory() {
        return firstCourseMandatory;
    }

    public String getSecondOverlappingCourse() {
        return secondOverlappingCourse;
    }

    public boolean isSecondCourseMandatory() {
        return secondCourseMandatory;
    }

    public long getDaysOfDistanceBetweenCalls() {
        return daysOfDistanceBetweenCalls;
    }

    public Integer getYear() {
        return year;
    }

    public boolean isSameSemester() {
        return sameSemester;
    }

    public OverlappingExams(String firstOverlappingCourse,
                            boolean firstCourseMandatory,
                            String secondOverlappingCourse,
                            boolean secondCourseMandatory,
                            long daysOfDistanceBetweenCalls,
                            Integer year,
                            boolean sameSemester) {
        this.firstOverlappingCourse = firstOverlappingCourse;
        this.firstCourseMandatory = firstCourseMandatory;
        this.secondOverlappingCourse = secondOverlappingCourse;
        this.secondCourseMandatory = secondCourseMandatory;
        this.daysOfDistanceBetweenCalls = daysOfDistanceBetweenCalls;
        this.year = year;
        this.sameSemester = sameSemester;
    }

    @Override
    public String toString() {
        return "{ " +
                firstOverlappingCourse +
                (firstCourseMandatory ? " (mandatory)" : "") +
                " & " +
                secondOverlappingCourse +
                (secondCourseMandatory ? " (mandatory)" : "") +
                ": days of distance = " + daysOfDistanceBetweenCalls +
                ", year = "  + year +
                ", " + (sameSemester ? "Same semester" : "Different semesters") + " }";

    }
}
