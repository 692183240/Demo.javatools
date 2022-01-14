package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;


public class XxlProcess {

    private List<String> processId;

    public List<String> getProcessId() {
        return processId;
    }

    public void setProcessId(List<String> processId) {
        this.processId = processId;
    }
}
