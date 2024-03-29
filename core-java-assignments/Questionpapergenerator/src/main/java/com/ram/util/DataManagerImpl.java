package com.ram.util;

import com.ram.bean.Criteria;
import com.ram.bean.Question;
import com.ram.bean.Category;
import com.ram.bean.Complexity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

// Override and implement all the methods of DataManager Interface here
public class DataManagerImpl implements DataManager {


    @Override
    public List<Question> populateData() throws SQLException, ClassNotFoundException {
        List<Question> questions = new ArrayList<>();
        DBConnectionUtil db = new DatabaseConnectionManager();
        Connection con = db.getConnection();
        Statement st = con.createStatement();
        String sqlQuery = "select * from questionBank";
        ResultSet rs = st.executeQuery(sqlQuery);
        while (rs.next()) {
            Question q = new Question();
            q.setSrno(rs.getInt(1));
            q.setQuestion(rs.getString(2));
            q.setOption1(rs.getString(3));
            q.setOption2(rs.getString(4));
            q.setOption3(rs.getString(5));
            q.setOption4(rs.getString(6));
            q.setCorrectAns(rs.getString(7));
            q.setType(Category.valueOf(rs.getString(8)));
//            System.out.println(Category.valueOf(rs.getString("Category")));

            q.setComplexity(Complexity.valueOf(rs.getString(9)));
            questions.add(q);
        }
        db.closeConnection();
        return questions;
    }

    @Override
    public List<Question> getQuestionByCategory(Category category, List<Question> questionsList) {
        List<Question> lCategory = new ArrayList<>();
        for (Question q1 : questionsList) {
//            System.out.println(q1.getSrno() + "="+q1.getCategory());
            if (q1.getCategory() == category) {
                lCategory.add(q1);
            }
        }

        return lCategory;
    }

    @Override
    public List<Question> getQuestionByComplexity(Complexity complexity, List<Question> questionsList) {
//        List<Question>lComplexity = new ArrayList<>();
//        for(Question q2 : questionsList){
//            if(q2.getComplexity()==complexity){
//                lComplexity.add(q2);
//            }
//        }
//        return lComplexity;
        return questionsList.stream()
                .filter((Question q) -> q.getComplexity() == complexity)
                .collect(Collectors.toList());

    }

    @Override
    public Set<Question> generateQuestionPaper(List<Question> list, List<Criteria> template) {
        Set<Question> finalQuestionPaperSet = new HashSet<>();
        for (Criteria criteria : template) {
            final List<Question> questionsByCategory = getQuestionByCategory(criteria.getCategory(), list);
            List<Question> questionsByCategoryAndComplexity = getQuestionByComplexity(criteria.getComplexity(), questionsByCategory);
            for (int i = 0; i < criteria.getNoOfQuestion() && !questionsByCategoryAndComplexity.isEmpty(); i++) {
                int index = (int) ((Math.random()) * questionsByCategoryAndComplexity.size());
                final Question question = questionsByCategoryAndComplexity.remove(index);
                final boolean isAdded = finalQuestionPaperSet.add(question);
                //Duplicate check might require in case someone sends the duplicate Criteria with different noOfQuestions.
                if (!isAdded) {
                    i--;
                }
            }
        }
        return finalQuestionPaperSet;
    }

    @Override
    public void sortByCategory(List<Question> questionList) {
        Collections.sort(questionList, new Comparator<Question>() {
            @Override
            public int compare(Question question1, Question question2) {
                return question1.getCategory().compareTo(question2.getCategory());
            }
        });
    }

    @Override
    public void sortByComplexity(List<Question> questionList) {
//        questionList.sort(Comparator.comparing(Question::getComplexity));
        Collections.sort(questionList, new Comparator<Question>() {
            @Override
            public int compare(Question question1, Question question2) {
                return question1.getComplexity().name().compareTo(question2.getComplexity().name());
            }
        });
    }
}