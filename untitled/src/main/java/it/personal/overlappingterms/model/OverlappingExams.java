package it.personal.overlappingterms.model;

public class OverlappingExams {
    private String firstOverlappingCourse;
    private boolean firstCourseMandatory;
    private String secondOverlappingCourse;
    private boolean secondCourseMandatory;
    private long daysOfDistanceBetweenCalls;

    private Integer year;
    private boolean sameSemester;

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
        return "OverlappingExams {" +
                "firstOverlappingCourse='" + firstOverlappingCourse + '\'' +
                ", firstCourseMandatory=" + firstCourseMandatory +
                ", secondOverlappingCourse='" + secondOverlappingCourse + '\'' +
                ", secondCourseMandatory=" + secondCourseMandatory +
                ", daysOfDistanceBetweenCalls=" + daysOfDistanceBetweenCalls +
                ", year=" + year +
                ", sameSemester=" + sameSemester +
                '}';
    }
}
