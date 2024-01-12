package it.personal.overlappingterms.model;

import java.util.List;

public class OverlappingCourse {
    private final String courseName;
    private Integer courseYear;
    private final Integer courseSemester;
    private final boolean isMandatory;
    private final ExamDate examTermsDate;

    /**
     * Creates an entry for a course.
     * @param courseName Course name.
     * @param courseSemester Semester of the course.
     * @param isMandatory true if the course is mandatory, false otherwise.
     * @param examTermsDate Dates for the exam calls.
     */
    public OverlappingCourse (String courseName, Integer courseSemester, boolean isMandatory, ExamDate examTermsDate) {
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.isMandatory = isMandatory;
        this.examTermsDate = examTermsDate;
    }

    /**
     * Creates an entry for a course, specifying also the year of the course.
     * @param courseName Course name.
     * @param courseYear Year of the course.
     * @param courseSemester Semester of the course.
     * @param isMandatory true if the course is mandatory, false otherwise.
     * @param examTermsDate Dates for the exam calls.
     */
    public OverlappingCourse (String courseName, Integer courseYear, Integer courseSemester, boolean isMandatory, ExamDate examTermsDate) {
        this(courseName, courseSemester, isMandatory, examTermsDate);
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

    public ExamDate getExamTermsDates() {
        return examTermsDate;
    }

    @Override
    public String toString() {
        return "[" + courseName +
                (isMandatory ? " (obbligatorio)" : "") + ": " +
                courseYear + "° anno, " +
                courseSemester + "° semestre, " +
                "data: " + examTermsDate + "]";
    }
}
