package it.personal.overlappingterms.model;

import java.time.LocalDate;
import java.util.List;

/**
 * This class represents an entry of the table that represents,
 * for each course, the name of the course, its temporal location
 * (year & semester), if the course is mandatory or optional and
 * a set of dates that are the exam terms for the course.
 */
public class CourseEntry {
    private String courseName;
    private Integer courseYear;
    private Integer courseSemester;
    private boolean isMandatory;
    private List<LocalDate> examTermsDates;

    // Differentiate the courses per year.
    public CourseEntry(String courseName, Integer courseYear, Integer courseSemester, boolean isMandatory, List<LocalDate> examTermsDates) {
        this.courseName = courseName;
        this.courseYear = courseYear;
        this.courseSemester = courseSemester;
        this.isMandatory = isMandatory;
        this.examTermsDates = examTermsDates;
    }

    // Year is not asked
    public CourseEntry(String courseName, Integer courseSemester, boolean isMandatory, List<LocalDate> examTermsDates) {
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.isMandatory = isMandatory;
        this.examTermsDates = examTermsDates;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getCourseYear() {
        return courseYear;
    }

    public Integer getCourseSemester() {
        return courseSemester;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public List<LocalDate> getExamTermsDates() {
        return examTermsDates;
    }

    @Override
    public String toString() {
        return "CourseEntry{" +
                "courseName='" + courseName + '\'' +
                ", courseYear=" + courseYear +
                ", courseSemester=" + courseSemester +
                ", isMandatory=" + isMandatory +
                ", examTermsDates=" + examTermsDates +
                '}';
    }
}
