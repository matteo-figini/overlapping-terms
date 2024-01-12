package it.personal.overlappingterms.model;

import java.time.LocalDate;

public class ExamDate {
    private final LocalDate examDate;
    private final String examSlot;

    public ExamDate (LocalDate examDate, String examSlot) {
        this.examDate = examDate;
        this.examSlot = examSlot;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public String getExamSlot() {
        return examSlot;
    }

    @Override
    public java.lang.String toString() {
        return examDate + " (" + examSlot + ")";
    }
}
