package com.note.data.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.note.data.bean.DemoReq;
import com.note.data.bean.NodeBean;
import com.note.data.bean.NodeLineBean;
import com.note.data.bean.TaskBean;
import com.note.data.service.CronService;
import com.note.data.service.DataService;
import com.note.data.utils.CronUtil;
import com.note.data.utils.ReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private CronService cronService;

//    @Override
//    public void ABShuntNode() {
//        //读取文件中的json数据
//        String jsonData = ReadFile.readFileContent("D:\\data\\AB分流.txt");
//        //AB分流控件数据整合
//        JsonObject data = ABShuntData(jsonData);
//
//        Node node = new Gson().fromJson(data, Node.class);
//        System.out.println(node);
//    }


    @Override
    public TaskBean task() {
        TaskBean taskBean = new TaskBean();
        String jsonData = ReadFile.readFileContent("D:\\data\\流程信息.json");
        JsonObject jsonObject = new Gson().fromJson(jsonData, JsonObject.class);

        taskBean.setJobId(jsonObject.get("jobId").getAsString());
        taskBean.setJobName(jsonObject.get("jobName").getAsString());
        taskBean.setJobType(jsonObject.get("jobType").getAsString());

        taskBean.setJobDescription(jsonObject.get("jobDescription").getAsString());
        taskBean.setJobVersion(jsonObject.get("jobVersion").getAsString());
        taskBean.setTriggerWay(jsonObject.get("triggerWay").getAsString());
        taskBean.setStartTime(jsonObject.get("startTime").getAsString());
        taskBean.setEndTime(jsonObject.get("endTime").getAsString());
        taskBean.setJobState(jsonObject.get("jobState").getAsString());
        taskBean.setCreateBy(jsonObject.get("createBy").getAsString());
        taskBean.setApprovalTime(jsonObject.get("approvalTime").getAsString());

        //获取所有控件信息
        JsonArray nodeList = jsonObject.get("nodeList").getAsJsonArray();

        //整理控件信息
        List<NodeBean> nodeBeanList = new ArrayList<>();
        for (JsonElement jsonElement : nodeList) {
            JsonObject node = jsonElement.getAsJsonObject();
            String nodeTypeID = node.get("nodeTypeID").getAsString();
            //校验是否为开始控件
            if (nodeTypeID.equals("NID_START")) {
                NodeBean nodeBean = beginData(node);
                nodeBeanList.add(nodeBean);
            }
            //校验是否为条件控件
            if (nodeTypeID.equals("NID_CONDITION")) {
                NodeBean nodeBean = conditionData(node);
                nodeBeanList.add(nodeBean);
            }
            //校验是否为结束控件
            if (nodeTypeID.equals("NID_END")) {
                NodeBean nodeBean = endData(node);
                nodeBeanList.add(nodeBean);
            }
            //校验是否为A/B控件
            if (nodeTypeID.equals("NID_A/B")) {
                NodeBean nodeBean = ABShuntData(node);
                nodeBeanList.add(nodeBean);
            }
            //校验是否为等待控件
            if (nodeTypeID.equals("NID_WAIT")) {
                NodeBean nodeBean = waitData(node);
                nodeBeanList.add(nodeBean);
            }
            //校验是否为push控件
            if (nodeTypeID.equals("NID_PUSH")) {
                NodeBean nodeBean = pushData(node);
                nodeBeanList.add(nodeBean);
            }
            //校验是否为短信控件
            if (nodeTypeID.equals("NID_NOTE")) {
                NodeBean nodeBean = smsData(node);
                nodeBeanList.add(nodeBean);
            }
        }
        taskBean.setNodeBeanList(nodeBeanList);

        //关系结构
        List<NodeLineBean> nodeLineBeanList = new ArrayList<>();
        JsonArray lineList = jsonObject.get("lineList").getAsJsonArray();
        for (JsonElement jsonElement : lineList) {
            JsonObject line = jsonElement.getAsJsonObject();
            NodeLineBean nodeLineBean = new NodeLineBean();
            nodeLineBean.setFrom(line.get("from").getAsString());
            nodeLineBean.setTo(line.get("to").getAsString());
            nodeLineBean.setPinName(line.get("pinName").getAsString());
            nodeLineBeanList.add(nodeLineBean);
        }
        taskBean.setNodeLineBeanList(nodeLineBeanList);

        return taskBean;
    }

    private NodeBean smsData(JsonObject node) {
        NodeBean nodeBean = new NodeBean();
        Map hashMap = new Gson().fromJson(node, HashMap.class);
        nodeBean.setNodeInfo(hashMap);

        //将控件信息转换为jsonObject
        JsonObject nodeJson = node.getAsJsonObject();
        //控件id
        nodeBean.setId(nodeJson.get("id").getAsString());
        nodeBean.setCaption(nodeJson.get("caption").getAsString());
        nodeBean.setNodeTypeID(nodeJson.get("nodeTypeID").getAsString());
        nodeBean.setInfo(nodeJson.get("info").getAsString());

        //端点信息
        JsonArray fixedOutput = nodeJson.get("output").getAsJsonObject().get("fixedOutput").getAsJsonArray();
        List<String> pinNameList = new ArrayList<>();
        for (JsonElement jsonElement : fixedOutput) {
            String pinName = jsonElement.getAsJsonObject().get("pinName").getAsString();
            pinNameList.add(pinName);
        }
        nodeBean.setPinNameList(pinNameList);
        //参数整合
        Map<String, Object> map = new HashMap<>();
        JsonArray parametersList = nodeJson.get("parameters").getAsJsonArray();
        map.put("parameters", parametersList);
        nodeBean.setParams(map);
        return nodeBean;
    }

    private NodeBean pushData(JsonObject node) {
        NodeBean nodeBean = new NodeBean();
        Map hashMap = new Gson().fromJson(node, HashMap.class);
        nodeBean.setNodeInfo(hashMap);

        //将控件信息转换为jsonObject
        JsonObject nodeJson = node.getAsJsonObject();
        //控件id
        nodeBean.setId(nodeJson.get("id").getAsString());
        nodeBean.setCaption(nodeJson.get("caption").getAsString());
        nodeBean.setNodeTypeID(nodeJson.get("nodeTypeID").getAsString());
        nodeBean.setInfo(nodeJson.get("info").getAsString());

        //端点信息
        JsonArray fixedOutput = nodeJson.get("output").getAsJsonObject().get("fixedOutput").getAsJsonArray();
        List<String> pinNameList = new ArrayList<>();
        for (JsonElement jsonElement : fixedOutput) {
            String pinName = jsonElement.getAsJsonObject().get("pinName").getAsString();
            pinNameList.add(pinName);
        }
        nodeBean.setPinNameList(pinNameList);
        //参数整合
        Map<String, Object> map = new HashMap<>();
        JsonArray parametersList = nodeJson.get("parameters").getAsJsonArray();
        map.put("parameters", parametersList);
        nodeBean.setParams(map);
        return nodeBean;
    }

    //等待控件
    private NodeBean waitData(JsonObject node) {
        NodeBean nodeBean = new NodeBean();
        Map hashMap = new Gson().fromJson(node, HashMap.class);
        nodeBean.setNodeInfo(hashMap);

        //将控件信息转换为jsonObject
        JsonObject nodeJson = node.getAsJsonObject();
        //控件id
        nodeBean.setId(nodeJson.get("id").getAsString());
        nodeBean.setCaption(nodeJson.get("caption").getAsString());
        nodeBean.setNodeTypeID(nodeJson.get("nodeTypeID").getAsString());
        nodeBean.setInfo(nodeJson.get("info").getAsString());

        //端点信息
        JsonArray fixedOutput = nodeJson.get("output").getAsJsonObject().get("fixedOutput").getAsJsonArray();
        List<String> pinNameList = new ArrayList<>();
        for (JsonElement jsonElement : fixedOutput) {
            String pinName = jsonElement.getAsJsonObject().get("pinName").getAsString();
            pinNameList.add(pinName);
        }
        nodeBean.setPinNameList(pinNameList);
        //参数整合
        Map<String, Object> map = new HashMap<>();
        JsonArray parametersList = nodeJson.get("parameters").getAsJsonArray();
        map.put("parameters", parametersList);
        nodeBean.setParams(map);
        return nodeBean;
    }

    //ab控件
    private NodeBean ABShuntData(JsonObject node) {
        NodeBean nodeBean = new NodeBean();
        Map hashMap = new Gson().fromJson(node, HashMap.class);
        nodeBean.setNodeInfo(hashMap);

        //将控件信息转换为jsonObject
        JsonObject nodeJson = node.getAsJsonObject();
        //控件id
        nodeBean.setId(nodeJson.get("id").getAsString());
        nodeBean.setCaption(nodeJson.get("caption").getAsString());
        nodeBean.setNodeTypeID(nodeJson.get("nodeTypeID").getAsString());
        nodeBean.setInfo(nodeJson.get("info").getAsString());

        //端点信息
        JsonArray fixedOutput = nodeJson.get("output").getAsJsonObject().get("fixedOutput").getAsJsonArray();
        List<String> pinNameList = new ArrayList<>();
        for (JsonElement jsonElement : fixedOutput) {
            String pinName = jsonElement.getAsJsonObject().get("pinName").getAsString();
            pinNameList.add(pinName);
        }
        nodeBean.setPinNameList(pinNameList);
        Map<String, Object> map = new HashMap<>();
        JsonArray parametersList = nodeJson.get("parameters").getAsJsonArray();

        map.put("parameters", parametersList);
        nodeBean.setParams(map);
        return nodeBean;
    }

    private NodeBean endData(JsonObject node) {
        NodeBean nodeBean = new NodeBean();
        //将控件信息转换为jsonObject
        JsonObject nodeJson = node.getAsJsonObject();
        //控件id
        nodeBean.setId(nodeJson.get("id").getAsString());
        nodeBean.setCaption(nodeJson.get("caption").getAsString());
        nodeBean.setNodeTypeID(nodeJson.get("nodeTypeID").getAsString());
        nodeBean.setInfo(nodeJson.get("info").getAsString());

        return nodeBean;
    }

    //条件控件数据整合
    private NodeBean conditionData(JsonObject node) {
        NodeBean nodeBean = new NodeBean();

        Map hashMap = new Gson().fromJson(node, HashMap.class);
        nodeBean.setNodeInfo(hashMap);

        //将控件信息转换为jsonObject
        JsonObject nodeJson = node.getAsJsonObject();
        //控件id
        nodeBean.setId(nodeJson.get("id").getAsString());
        nodeBean.setCaption(nodeJson.get("caption").getAsString());
        nodeBean.setNodeTypeID(nodeJson.get("nodeTypeID").getAsString());
        nodeBean.setInfo(nodeJson.get("info").getAsString());

        //端点信息
        JsonArray fixedOutput = nodeJson.get("output").getAsJsonObject().get("fixedOutput").getAsJsonArray();
        List<String> pinNameList = new ArrayList<>();
        for (JsonElement jsonElement : fixedOutput) {
            String pinName = jsonElement.getAsJsonObject().get("pinName").getAsString();
            pinNameList.add(pinName);
        }
        nodeBean.setPinNameList(pinNameList);

        Map<String, Object> map = new HashMap<>();
        JsonElement parameters = nodeJson.get("parameters");
        JsonArray parametersList = parameters.getAsJsonArray();
        JsonObject data = parametersList.get(1).getAsJsonObject().get("data").getAsJsonObject();
        map.put("data", data);
        nodeBean.setParams(map);
        return nodeBean;
    }

    //开始控件数据整合
    private NodeBean beginData(JsonObject node) {
        NodeBean nodeBean = new NodeBean();
        //将控件信息转换为jsonObject
        JsonObject nodeJson = node.getAsJsonObject();
        //控件id
        nodeBean.setId(nodeJson.get("id").getAsString());
        nodeBean.setCaption(nodeJson.get("caption").getAsString());
        nodeBean.setNodeTypeID(nodeJson.get("nodeTypeID").getAsString());
        nodeBean.setInfo(nodeJson.get("info").getAsString());

        //端点信息
        JsonArray fixedOutput = nodeJson.get("output").getAsJsonObject().get("fixedOutput").getAsJsonArray();
        List<String> pinNameList = new ArrayList<>();
        for (JsonElement jsonElement : fixedOutput) {
            String pinName = jsonElement.getAsJsonObject().get("pinName").getAsString();
            pinNameList.add(pinName);
        }
        nodeBean.setPinNameList(pinNameList);

        Map<String, Object> map = new HashMap<>();
        JsonElement parameters = nodeJson.get("parameters");
        JsonArray parametersList = parameters.getAsJsonArray();
        //获取触发时间
        String triggerTime = parametersList.get(3).getAsJsonObject().get("defaultValue").getAsString();
        map.put("triggerTime", triggerTime);

        //获取触发周期
        JsonObject PTYPE_SELECT = parametersList.get(4).getAsJsonObject();
        //获取选中的周期类型id
        Integer defaultValue = PTYPE_SELECT.get("defaultValue").getAsInt();

        //执行日期
        String selectedList = PTYPE_SELECT.get("selectedList").getAsString();
        map.put("selectedList", selectedList);

        //生成cron
        DemoReq demoReq = new DemoReq();
        demoReq.setTriggerTime(triggerTime);
        demoReq.setChoice(defaultValue);
        demoReq.setDayValue(selectedList);
        String cron = CronUtil.getCron(demoReq);
        map.put("cron", cron);

        nodeBean.setParams(map);
        return nodeBean;
    }
}
