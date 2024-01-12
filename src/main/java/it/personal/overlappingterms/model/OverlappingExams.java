package it.personal.overlappingterms.model;

public class OverlappingExams {

    private final OverlappingCourse firstOverlappingCourse;
    private final OverlappingCourse secondOverlappingCourse;
    private final int examCallWithOverlapping;

    public OverlappingExams(OverlappingCourse firstOverlappingCourse, OverlappingCourse secondOverlappingCourse, int examCallWithOverlapping) {
        this.firstOverlappingCourse = firstOverlappingCourse;
        this.secondOverlappingCourse = secondOverlappingCourse;
        this.examCallWithOverlapping = examCallWithOverlapping;
    }

    public OverlappingCourse getFirstOverlappingCourse() {
        return firstOverlappingCourse;
    }

    public OverlappingCourse getSecondOverlappingCourse() {
        return secondOverlappingCourse;
    }

    public int getExamCallWithOverlapping() {
        return examCallWithOverlapping;
    }

    @Override
    public String toString() {
        return "{ " + examCallWithOverlapping + "° appello => " +
                firstOverlappingCourse + " & " +
                secondOverlappingCourse + " } ";
    }
}
