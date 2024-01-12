package it.personal.overlappingterms;

import it.personal.overlappingterms.model.CourseEntry;
import it.personal.overlappingterms.model.ExamDate;
import it.personal.overlappingterms.model.OverlappingCourse;
import it.personal.overlappingterms.model.OverlappingExams;

import java.util.ArrayList;
import java.util.List;

public abstract class OverlapLevel {
    public List<OverlappingExams> computeOverlappingExams(List<CourseEntry> courseEntryList, int examCalls) {
        List<OverlappingExams> overlappingExamsList = new ArrayList<>();

        for (int call = 1; call <= examCalls; call++) {
            System.out.println(call + "° EXAM CALL");

            for (int i = 0; i < courseEntryList.size(); i++) {
                CourseEntry examOne = courseEntryList.get(i);
                for (int j = i + 1; j < courseEntryList.size(); j++) {
                    CourseEntry examTwo = courseEntryList.get(j);
                    if (areOverlappingCourses(examOne, examTwo, call)) {
                        OverlappingExams overlappingExams = getOverlappingExams(examOne, call, examTwo);
                        System.out.println(overlappingExams);
                        overlappingExamsList.add(overlappingExams);
                    }
                }
            }
        }
        return  overlappingExamsList;
    }

    private static OverlappingExams getOverlappingExams(CourseEntry examOne, int call, CourseEntry examTwo) {
        ExamDate firstDate = examOne.getExamTermsDates().get(call - 1);
        ExamDate secondDate = examTwo.getExamTermsDates().get(call - 1);
        OverlappingCourse firstCourse = new OverlappingCourse(
                examOne.getCourseName(),
                examOne.getCourseYear(),
                examOne.getCourseSemester(),
                examOne.isMandatory(),
                firstDate
        );
        OverlappingCourse secondCourse = new OverlappingCourse(
                examTwo.getCourseName(),
                examTwo.getCourseYear(),
                examTwo.getCourseSemester(),
                examTwo.isMandatory(),
                secondDate
        );

        return new OverlappingExams(firstCourse, secondCourse, call);
    }

    protected abstract boolean areOverlappingCourses (CourseEntry examOne, CourseEntry examTwo, int examCall);

}
