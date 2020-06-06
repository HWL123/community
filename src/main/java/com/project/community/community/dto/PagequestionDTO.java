package com.project.community.community.dto;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Data
public class PagequestionDTO {
    private List<QuestionDTO> questions = new ArrayList<>();
    private Boolean prepage;
    private Boolean nextpage;
    private Boolean firstpage;
    private Boolean endpage;
    private Integer totalpage ;

    private Integer page;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalcount, Integer page, Integer size) {

        this.page = page;
        if(totalcount % size == 0){
            totalpage = totalcount / size;
        }
        else{
            totalpage = totalcount / size + 1;
        }
        pages.add(page);
        for(int i =1;i <= 3;i++){
            if(page - i > 0 ){
                pages.add(0,page - i);
            }
            if(page + i <= totalpage ){
                pages.add(page + i);
            }

        }


        if(page == 1){
            prepage = false;
        }
        else{
            prepage = true;
        }
        if(page == totalpage){
            nextpage = false;
        }
        else {
            nextpage = true;
        }
        if(pages.contains(1)){
            firstpage = false;
        }
        else{
            firstpage = true;
        }
        if(pages.contains(totalpage)){
            endpage = false;
        }
        else {
            endpage = true;
        }

    }
}
