package com.project.community.community.service;


import com.project.community.community.dto.PagequestionDTO;
import com.project.community.community.dto.QuestionDTO;
import com.project.community.community.mapper.QuestionMapper;
import com.project.community.community.mapper.UserMapper;
import com.project.community.community.model.Question;
import com.project.community.community.model.User;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;



    public PagequestionDTO list(Integer page, Integer size) {
        Integer offsize = size * (page - 1);
        List<Question> questionList = questionMapper.list(offsize,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PagequestionDTO pagequestionDTO = new PagequestionDTO();
        for(Question q : questionList){
            User user = userMapper.findbyId(q.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        Integer totalcount = questionMapper.count();
        pagequestionDTO.setQuestions(questionDTOList);
        pagequestionDTO.setPagination(totalcount,page,size);

        return pagequestionDTO;
    }


    public PagequestionDTO list(Integer id, Integer page, Integer size) {
        Integer offsize = size * (page - 1);
        List<Question> questionList = questionMapper.listByUserid(id,offsize,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PagequestionDTO pagequestionDTO = new PagequestionDTO();
        for(Question q : questionList){
            User user = userMapper.findbyId(q.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        Integer totalcount = questionMapper.countByUserid(id);
        pagequestionDTO.setQuestions(questionDTOList);
        pagequestionDTO.setPagination(totalcount,page,size);

        return pagequestionDTO;
    }

    public QuestionDTO findById(Integer id) {
        Question question = questionMapper.findById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findbyId(question.getCreator());
        questionDTO.setUser(user);


        return questionDTO;
    }

    public void createOrupdate(Question question) {
        if (question.getId()==0){
            questionMapper.create(question);
        }
        else {
            question.setGmt_modified(System.currentTimeMillis());

            questionMapper.update(question);
        }
    }
}
