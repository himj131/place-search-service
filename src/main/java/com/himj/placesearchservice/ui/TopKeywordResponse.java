package com.himj.placesearchservice.ui;

import com.himj.placesearchservice.domain.TopKeyword;
import lombok.Getter;

import java.util.List;

@Getter
public class TopKeywordResponse {
    private List<TopKeyword> datas;

    public TopKeywordResponse(List<TopKeyword> datas) {
        this.datas = datas;
    }
}
