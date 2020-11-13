package com.note.data.bean;

import lombok.Data;

@Data
public class NodeLineBean {
    private String from;
    private String to;
    private String pinName;

    public NodeLineBean() {
    }

    public NodeLineBean(String from, String to, String pinName) {
        this.from = from;
        this.to = to;
        this.pinName = pinName;
    }
}
