package it.personal.overlappingterms.controller;

import it.personal.overlappingterms.model.CourseEntry;
import it.personal.overlappingterms.model.OverlappingExams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class OverlappingTerms {
    public static void main(String[] args) {

        Integer yearsConsidered = 1;
        Integer examTermsConsidered = 2;
        Integer maxDistanceForOverlapping = 2;

        List<CourseEntry> courseEntries;
        List<OverlappingExams> overlappingExamsList = new ArrayList<>();

        // Read file from CSV file
        courseEntries = InputController.readCourseEntriesFromCSV(true, 2);
        System.out.println(courseEntries.size() + " entries have been read from the file.");

        // Process the data structure and fill the overlapping list
        int overlappingDates = 0;
        for (int year = 1; year <= yearsConsidered; year++) {
            for (int examCall = 0; examCall < examTermsConsidered; examCall++) {
                for (int i = 0; i < courseEntries.size(); i++) {
                    LocalDate dateOfFirstExam = courseEntries.get(i).getExamTermsDates().get(examCall);

                    for (int j = i + 1; j < courseEntries.size(); j++) {
                        LocalDate dateOfSecondExam = courseEntries.get(j).getExamTermsDates().get(examCall);
                        long daysOfDifference = Math.abs(DAYS.between(dateOfFirstExam, dateOfSecondExam));
                        if ( daysOfDifference <= (long) maxDistanceForOverlapping) {
                            overlappingDates++;
                            // TODO: instantiate the overlappingExams object
                            OverlappingExams overlappingExams = new OverlappingExams(
                                    courseEntries.get(i).getCourseName(),
                                    courseEntries.get(i).isMandatory(),
                                    courseEntries.get(j).getCourseName(),
                                    courseEntries.get(j).isMandatory(),
                                    daysOfDifference,
                                    year,
                                    (courseEntries.get(i).getCourseSemester() == courseEntries.get(j).getCourseSemester())
                            );
                            overlappingExamsList.add(overlappingExams);
                        }
                    }
                }
            }
        }

        // Print the list on a file
        for (OverlappingExams entry : overlappingExamsList) {
            System.out.println(entry);
        }
    }
}
