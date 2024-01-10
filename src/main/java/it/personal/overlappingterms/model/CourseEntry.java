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
    private final String courseName;
    private Integer courseYear;
    private final Integer courseSemester;
    private final boolean isMandatory;
    private final List<LocalDate> examTermsDates;

    /**
     * Creates an entry for a course.
     * @param courseName Course name.
     * @param courseSemester Semester of the course.
     * @param isMandatory true if the course is mandatory, false otherwise.
     * @param examTermsDates Dates for the exam calls.
     */
    public CourseEntry (String courseName, Integer courseSemester, boolean isMandatory, List<LocalDate> examTermsDates) {
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.isMandatory = isMandatory;
        this.examTermsDates = examTermsDates;
    }

    /**
     * Creates an entry for a course, specifying also the year of the course.
     * @param courseName Course name.
     * @param courseYear Year of the course.
     * @param courseSemester Semester of the course.
     * @param isMandatory true if the course is mandatory, false otherwise.
     * @param examTermsDates Dates for the exam calls.
     */
    public CourseEntry(String courseName, Integer courseYear, Integer courseSemester, boolean isMandatory, List<LocalDate> examTermsDates) {
        this(courseName, courseSemester, isMandatory, examTermsDates);
        this.courseYear = courseYear;
    }

    /**
     * @return the course name.
     */
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
