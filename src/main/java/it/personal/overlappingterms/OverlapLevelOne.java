package it.personal.overlappingterms;

import it.personal.overlappingterms.model.CourseEntry;
import it.personal.overlappingterms.model.OverlappingExams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * The first level (the strictest one) returns all and only the exams belonging to the same year and the same semester,
 * that are set as "mandatory" and have a distance of two days or less between the first exam term and the second exam
 * term.
 */
public class OverlapLevelOne extends OverlapLevel {
    final int maximumDaysForOverlapping;

    public OverlapLevelOne () {
        this.maximumDaysForOverlapping = 2;
    }

    public OverlapLevelOne (int maximumDaysForOverlapping) {
        this.maximumDaysForOverlapping = maximumDaysForOverlapping;
    }

    @Override
    protected boolean areOverlappingCourses (CourseEntry examOne, CourseEntry examTwo, int examCall) {
        LocalDate dateOfFirstExam = examOne.getExamTermsDates().get(examCall - 1).getExamDate();
        LocalDate dateOfSecondExam = examTwo.getExamTermsDates().get(examCall - 1).getExamDate();
        long daysOfDifference = Math.abs(DAYS.between(dateOfFirstExam, dateOfSecondExam));

        return (!examOne.getCourseName().equals(examTwo.getCourseName()) &&
                examOne.isMandatory() && examTwo.isMandatory() &&
                examOne.getCourseYear().equals(examTwo.getCourseYear()) &&
                examOne.getCourseSemester().equals(examTwo.getCourseSemester()) &&
                daysOfDifference <= (long) maximumDaysForOverlapping);
    }
}
