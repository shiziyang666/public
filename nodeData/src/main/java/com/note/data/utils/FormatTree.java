package com.note.data.utils;

import com.note.data.bean.NodeBean;
import com.note.data.bean.NodeLineBean;
import com.note.data.bean.NodeTree;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 格式化树
 */
public class FormatTree {

    public static List<List<NodeTree>> getList(List<NodeBean> nodeListAll, List<NodeLineBean> lineList) {

        //开始控件
        List<NodeBean> beginNode = nodeListAll.stream()
                .filter(
                        aa -> aa.getNodeTypeID().equals("NID_START")
                ).collect(Collectors.toList());
        //开始
        List<String> nodeArray2 = beginNode.stream().map(NodeBean::getId).collect(Collectors.toList());
        //id集合
        List<String> idList = new ArrayList<>();
        idList.addAll(nodeArray2);
        //最终集合
        List<List<NodeTree>> response = new ArrayList<>();
        while (true) {
            List<String> finalIdList = idList;
            //此层最终集合
            List<NodeTree> nodeMapList = new ArrayList<>();
            for (String id : idList) {
                //获取此层节点id总和
                List<String> toList = lineList.stream()
                        .filter(
                                aa -> id.equals(aa.getFrom())
                        ).map(NodeLineBean::getTo).collect(Collectors.toList());

                NodeTree nodeMap = new NodeTree();
                //获取自己的id
                List<NodeBean> currentList = nodeListAll.stream()
                        .filter(
                                aa -> aa.getId().equals(id)
                        ).collect(Collectors.toList());
                nodeMap.setCurrentNode(currentList.get(0));
                //获取子id集合
                List<NodeBean> childList = nodeListAll.stream()
                        .filter(
                                aa -> toList.contains(aa.getId())
                        ).map(bb -> {
                            //获取pinName
                            NodeLineBean nodeLineBean = lineList.stream().filter(cc -> cc.getTo().equals(bb.getId())).collect(Collectors.toList()).get(0);
                            bb.setPinName(nodeLineBean.getPinName());
                            return bb;
                        }).collect(Collectors.toList());
                nodeMap.setToList(childList);

                nodeMapList.add(nodeMap);
            }
            response.add(nodeMapList);
            //获取下层的所有id
            idList = lineList.stream()
                    .filter(
                            aa -> finalIdList.contains(aa.getFrom())
                    ).map(NodeLineBean::getTo).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(idList)) {
                break;
            }
        }

        System.out.println(response);
        return response;
    }
}
