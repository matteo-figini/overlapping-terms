package it.personal.overlappingterms;

import it.personal.overlappingterms.model.CourseEntry;
import it.personal.overlappingterms.model.OverlappingExams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * The fourth level returns all and only the exams, mandatory or not, regardless of the year and the semester,
 * that are scheduled with a distance in days between the first exam term and the second exam term lower or equal
 * than a value specified by the user (default = 2).
 */
public class OverlapLevelFour extends OverlapLevel {
    final int maximumDaysForOverlapping;

    public OverlapLevelFour() {
        this.maximumDaysForOverlapping = 2;
    }

    public OverlapLevelFour(int maximumDaysForOverlapping) {
        this.maximumDaysForOverlapping = maximumDaysForOverlapping;
    }

    @Override
    protected boolean areOverlappingCourses (CourseEntry examOne, CourseEntry examTwo, int examCall) {
        LocalDate dateOfFirstExam = examOne.getExamTermsDates().get(examCall - 1).getExamDate();
        LocalDate dateOfSecondExam = examTwo.getExamTermsDates().get(examCall - 1).getExamDate();
        long daysOfDifference = Math.abs(DAYS.between(dateOfFirstExam, dateOfSecondExam));

        return (!examOne.getCourseName().equals(examTwo.getCourseName()) &&
                daysOfDifference <= maximumDaysForOverlapping);
    }
}
