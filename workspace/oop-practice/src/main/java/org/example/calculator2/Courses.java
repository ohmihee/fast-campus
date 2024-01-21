package org.example.calculator2;

import java.util.List;

public class Courses {
    /**
     * 일급 컬렉션이란?
     *
     * */
    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade() {
//        double multipliedCreditAndCourseGrade = 0;
//        for(Course course : courses) {
//            //multipliedCreditAndCourseGrade +=  course.getCredit() * course.getGradToNumber();
//            multipliedCreditAndCourseGrade += course.multiplyCreditAndCourseGrade();
//        }
//        return multipliedCreditAndCourseGrade;

        return courses.stream()
                .mapToDouble(Course::multiplyCreditAndCourseGrade)
                .sum();
    }

    public int calculateTotalCompletedCredit() {
        return courses.stream()
                .mapToInt(Course::getCredit)
                .sum();
    }
}
