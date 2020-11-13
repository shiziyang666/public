package com.note.data.bean;

import lombok.Data;

import java.util.List;

/**
 * @author shiziyang
 * @date 2020/11/01 10:32
 */
@Data
public class NodeTree {

    //当前节点
    private NodeBean currentNode;

    //当前节点的to节点
    private List<NodeBean> toList;

}
