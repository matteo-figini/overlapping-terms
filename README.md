# OverlappingTerms
This project is a personal project by Matteo Figini that aims to find possible overlapping exams on a given session during exam scheduling at Politecnico di Milano, specially designed for the Industrial and Information Engineering faculty. The program is a simple CLI (Command-Line Interface) program, written in Java language, which reads in input a CSV file of exam dates expressed in a certain format, and - chosen a proper level of overlap - returns all the possible overlaps both in a textual file and in the CLI.

## Input file: a CSV file
As expressed in the program, the input file is a CSV file containing a set of rows, each expressing an exam term for a given course (e.g. Computer Science Eng. BSc, Computer Science Eng. MSc, Biomedical Engineering BSc...). Each row is formatted in the following way:
```
<EXAM_NAMES>;<EXAM_YEAR>;<EXAM_SEMESTER>;<IS_MANDATORY>;<FIRST_CALL_DATE>;<FIRST_CALL_SLOT>(;<EXAM_CALL_DATE>;<EXAM_CALL_SLOT>)*
```
meaning that one can define one or more dates for exam calls and the corresponding slot. Here below a complete explanation of the parts is provided:
- **EXAM_NAMES** corresponds to the name of the exam. In its basic version, the name is nothing but a string that contains the name for the exam; if needed, it is possible to provide some additional information that may be useful for the user. For example, suppose the exam is dispensed to a specific "PSPA - Piano di Studi Preventivamente Approvato" and this information is relevant to understand the level of overlaps. In that case, it is suggested to insert this information between brackets. One possible example derives from the BSc of Mechanical Engineering, in which different exams are provided to different PSPAs:
```
MACCHINE [VA1]
STATISTICS [VA1/E]
PROGETTAZIONE DI SISTEMI MECCANICI [VA2,VA5,VA6]
```
- **EXAM_YEAR** is the year in which the exam is traditionally dispensed. If the course doesn't have a specific year of reference (e.g. in Master's Degrees this is quite common), it is possible to indicate this as "1". Keep in mind that, if a certain course is dispensed for more than one year, there are two possible solutions:
  -  The first trivial solution is duplicating the corresponding row, changing only the year and keeping all other fields the same.
  -  The second solution is to consider all the courses as of the same year, for example by setting "1" for all the courses.
- **EXAM_SEMESTER** (1 or 2) is the semester in which the course is dispensed.
- **IS_MANDATORY** is a flag that just helps the user when checking the overlaps by indicating if the given course is mandatory or not.
- **n_CALL_DATE** represents one date for the given exam in the format __dd/MM/yyyy__;
- **n_CALL_SLOT** represents the slot for the previous date reported. There are 3 different values that may be used to indicate the slot:
  - _F1_ stands for the first-morning slot (typically 08:00 - 11:30);
  - _F2_ stands for the late-morning slot (typically 11:30 - 15:00);
  - _F3_ stands for the afternoon slot (typically 15:00 - 18:30); 
One particular exam may require more than one slot: in this case it is suggested to replicate the row as many times as the number of time slots interested, changing only the slot and keeping all other fields the same.

## Levels of overlap
The program allows one to choose among different levels of overlap.
1. The __first__ level (the strictest one) returns all and only the exams belonging to the same year and the same semester, that are set as "mandatory" and have a distance of two days or less between the first exam term and the second exam term.
2. The __second__ level returns all and only the exams, mandatory or not, that belongs to the same year and the same semester and have a distance in days between the first exam term and the second exam term lower or equal to a value specified by the user.
3. The __third level__ returns all and only the exams, mandatory or not, regardless of the year and the semester, that are scheduled in the same date and in the same time slot.
4. The __fourth level__ returns all and only the exams, mandatory or not, regardless of the year and the semester, that are scheduled with a distance in days between the first exam term and the second exam term lower or equal to a value specified by the user.

The user is asked to provide the following information:
- The name of the input CSV file;
- The name of the output text file;
- The level of overlap desired;
- If needed, the parameter representing the maximum distance in days considered as "overlapping".

### Considerations on the levels of overlap

## Output file and considerations
