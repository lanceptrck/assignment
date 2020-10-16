package com.patrick.assignment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataTransferObject {

    List<SummaryDTO> summary;

    public DataTransferObject(List<SummaryDTO> summaryDTOList) {
        this.summary = summaryDTOList;
    }

    public List<SummaryDTO> getSummary() {
        return summary;
    }

    public void setSummary(List<SummaryDTO> summary) {
        this.summary = summary;
    }
}
