package com.example.android.miwok;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Class to represent the grade scores of a person
 * It allows to set and to get grades in many disciplines and it gives a string representation
 */
public class ReportCard {

    private String mperson;

    private Hashtable<String, Integer> disciplinesGrades;

    /**
     * Public constructor; it check that disciplines and grades have the same length
     * It also check that grades are in the range 0 to 30
     * @param person String with person's name
     * @param disciplines ArrayList with disciplines name
     * @param grades ArrayList with disciplines grades 0 to 30
     */
    public ReportCard(String person, ArrayList<String> disciplines, ArrayList<Integer> grades){
        // get ArrayList sizes and check they have the same lenght
        int lenDisciplines = disciplines.size();
        int lenGrades = grades.size();
        Assert.assertEquals(lenDisciplines, lenGrades);

        // initialize class variables
        mperson = person;
        for (int i=0; i<lenGrades; i++) {
            Integer actualGrade = grades.get(i);
            // check that the grade is a 0 to 30 integer
            Assert.assertTrue(actualGrade <= 30 & actualGrade >= 0 );
            disciplinesGrades.put(disciplines.get(i), actualGrade);
        }
    }

    public Integer getGrade(String discipline) {
        if (disciplinesGrades.containsKey(discipline)) {
            return disciplinesGrades.get(discipline);
        } else {
            return -99;
        }
    }

    public void setGrade(String discipline, Integer grade) {
        disciplinesGrades.put(discipline, grade);
    }
}
