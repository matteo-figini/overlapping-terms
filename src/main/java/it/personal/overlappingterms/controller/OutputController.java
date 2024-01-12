package it.personal.overlappingterms.controller;

import it.personal.overlappingterms.model.OverlappingExams;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputController {

    public static int writeOverlappingExamsToFile (String path, List<OverlappingExams> overlappingExamsList) {
        int linesWritten = 0;
        try (FileWriter writer = new FileWriter(path)) {
            for (OverlappingExams overlappingExams : overlappingExamsList) {
                writer.write(overlappingExams.toString() + "\n");
                linesWritten++;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesWritten;
    }
}
