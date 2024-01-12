package it.personal.overlappingterms;

import it.personal.overlappingterms.model.CourseEntry;
import it.personal.overlappingterms.model.OverlappingExams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * The second level returns all and only the exams, mandatory or not, that belongs to the same year and the same
 * semester and have a distance in days between the first exam term and the second exam term (of the same call)
 * lower or equal to a value specified by the user (default = 2).
 */
public class OverlapLevelTwo extends OverlapLevel {
    final int maximumDaysForOverlapping;

    public OverlapLevelTwo () {
        this.maximumDaysForOverlapping = 2;
    }

    public OverlapLevelTwo (int maximumDaysForOverlapping) {
        this.maximumDaysForOverlapping = maximumDaysForOverlapping;
    }

    @Override
    protected boolean areOverlappingCourses (CourseEntry examOne, CourseEntry examTwo, int examCall) {
        LocalDate dateOfFirstExam = examOne.getExamTermsDates().get(examCall - 1).getExamDate();
        LocalDate dateOfSecondExam = examTwo.getExamTermsDates().get(examCall - 1).getExamDate();
        long daysOfDifference = Math.abs(DAYS.between(dateOfFirstExam, dateOfSecondExam));

        return (!examOne.getCourseName().equals(examTwo.getCourseName()) &&
                examOne.getCourseYear().equals(examTwo.getCourseYear()) &&
                examOne.getCourseSemester().equals(examTwo.getCourseSemester()) &&
                daysOfDifference <= maximumDaysForOverlapping);
    }
}
