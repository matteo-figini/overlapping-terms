package it.personal.overlappingterms;

import it.personal.overlappingterms.model.CourseEntry;
import it.personal.overlappingterms.model.OverlappingExams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * The third level returns all and only the exams, mandatory or not, regardless of the year and the semester,
 * that are scheduled in the same date and in the same time slot.
 */
public class OverlapLevelThree extends OverlapLevel {

    public OverlapLevelThree () {}

    @Override
    protected boolean areOverlappingCourses (CourseEntry examOne, CourseEntry examTwo, int examCall) {
        LocalDate dateOfFirstExam = examOne.getExamTermsDates().get(examCall - 1).getExamDate();
        String timeSlotFirstExam = examOne.getExamTermsDates().get(examCall - 1).getExamSlot();
        LocalDate dateOfSecondExam = examTwo.getExamTermsDates().get(examCall - 1).getExamDate();
        String timeSlotSecondExam = examTwo.getExamTermsDates().get(examCall - 1).getExamSlot();

        return (!examOne.getCourseName().equals(examTwo.getCourseName()) &&
                dateOfFirstExam.equals(dateOfSecondExam) &&
                timeSlotFirstExam.equals(timeSlotSecondExam));
    }
}
