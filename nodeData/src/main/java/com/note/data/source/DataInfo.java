package com.note.data.source;

import com.note.data.bean.NodeBean;
import com.note.data.bean.NodeLineBean;
import com.note.data.bean.TaskBean;

import java.util.ArrayList;
import java.util.List;

public class DataInfo {


    public static TaskBean getInfo() {

        TaskBean taskBean = new TaskBean();
//        taskBean.setTaskId("1");
//        taskBean.setTaskName("模拟流程");
//        taskBean.setTaskType("");

        taskBean.setNodeBeanList(getNodeBeanList());
        taskBean.setNodeLineBeanList(getNodeLineBeanList());
        return taskBean;
    }

    private static List<NodeBean> getNodeBeanList() {
        List<NodeBean> nodeBeanList = new ArrayList<>();
//
//        /**
//         * 开始
//         */
//        NodeBean nodeBean = new NodeBean();
//        nodeBean.setId("20201030144957l51q6hhcmf");
//        nodeBean.setCaption("开始");
//        nodeBean.setNodeTypeID("NID_START");
//        nodeBean.setInfo("条件控件根据所配置条件的计算结果，决定下一个执行的节点");
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        nodeBean.setPinNameList(list);
//        Map<String, Object> map = new HashMap<>();
//        map.put("cron", "123456");
//        map.put("triggerTime", "19:11");
//        map.put("triggerPeriod", "1");
//        map.put("selectedList", "1,4");
//        nodeBean.setParams(map);
//        nodeBeanList.add(nodeBean);
//
//
//        /**
//         * 条件
//         */
//        NodeBean nodeBean1 = new NodeBean();
//        nodeBean1.setId("20201101135451qxiemr9trr");
//        nodeBean1.setCaption("条件分支");
//        nodeBean1.setNodeTypeID("NID_CONDITION");
//        nodeBean1.setInfo("用来判断是否满足某个或者某一组条件");
//        List<String> list1 = new ArrayList<>();
//        list1.add("PIN_TRUE");
//        list1.add("PIN_FALSE");
//        nodeBean1.setPinNameList(list1);
//        NodeBean.Params params1 = new NodeBean.Params();
//        Map<String, String> map1 = new HashMap<>();
//        map1.put("data", getData());
//        params1.setMap(map1);
//        nodeBean1.setParams(params1);
//        nodeBeanList.add(nodeBean1);
//
//        /**
//         * AB分流
//         */
//        NodeBean nodeBean2 = new NodeBean();
//        nodeBean2.setId("20201101135453ti3delqmng");
//        nodeBean2.setCaption("AB分流");
//        nodeBean2.setNodeTypeID("NID_CONDITION");
//        nodeBean2.setInfo("按照不同百分比分流");
//        List<String> list2 = new ArrayList<>();
//        list2.add("PIN_TRUE");
//        list2.add("PIN_FALSE");
//        nodeBean2.setPinNameList(list2);
//        NodeBean.Params params2 = new NodeBean.Params();
//        Map<String, String> map2 = new HashMap<>();
//        map2.put("type", "PTYPE_ABSHUNT");
//        map2.put("groups", "[\n" +
//                "                        {\n" +
//                "                            \"name\": \"对照组\",\n" +
//                "                            \"num\": 0\n" +
//                "                        },\n" +
//                "                        {\n" +
//                "                            \"name\": \"实验组\",\n" +
//                "                            \"num\": 0\n" +
//                "                        }\n" +
//                "                    ]");
//        params2.setMap(map2);
//        nodeBean2.setParams(params2);
//        nodeBeanList.add(nodeBean2);
//
//
//        /**
//         * 短信
//         */
//        NodeBean nodeBean3 = new NodeBean();
//        nodeBean3.setId("20201101135452glojpohdz");
//        nodeBean3.setCaption("短信");
//        nodeBean3.setNodeTypeID("NID_NOTE");
//        nodeBean3.setInfo("向目标发送短信");
//        List<String> list3 = new ArrayList<>();
//        list3.add("PIN_TRUE");
//        list3.add("PIN_FALSE");
//        nodeBean3.setPinNameList(list3);
//        NodeBean.Params params3 = new NodeBean.Params();
//        Map<String, String> map3 = new HashMap<>();
//        map3.put("type", "PTYPE_ABSHUNT");
//        map3.put("", "PTYPE_ABSHUNT");
//        params3.setMap(map3);
//        nodeBean2.setParams(params3);
//        nodeBeanList.add(nodeBean3);

        return nodeBeanList;
    }

    private static String getData() {
        return "{\n" +
                "                        \"id\": 0,\n" +
                "                        \"label\": \"且\",\n" +
                "                        \"swtich\": \"myred\",\n" +
                "                        \"children\": [\n" +
                "                            {\n" +
                "                                \"id\": 2,\n" +
                "                                \"label\": \"或\",\n" +
                "                                \"swtich\": \"myred\",\n" +
                "                                \"children\": [\n" +
                "                                    {\n" +
                "                                        \"id\": 5,\n" +
                "                                        \"label\": \"性别=男\",\n" +
                "                                        \"operator\": \"=\",\n" +
                "                                        \"value\": \"性别,男\"\n" +
                "                                    },\n" +
                "                                    {\n" +
                "                                        \"id\": 6,\n" +
                "                                        \"label\": \"账龄>=(30,90)\",\n" +
                "                                        \"operator\": \">=\",\n" +
                "                                        \"value\": \"账龄,(30,90)\"\n" +
                "                                    }\n" +
                "                                ]\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"id\": 3,\n" +
                "                                \"label\": \"且\",\n" +
                "                                \"swtich\": \"myred\",\n" +
                "                                \"children\": [\n" +
                "                                    {\n" +
                "                                        \"id\": 7,\n" +
                "                                        \"label\": \"是否实名，已实名\",\n" +
                "                                        \"operator\": \"\",\n" +
                "                                        \"value\": \"\"\n" +
                "                                    },\n" +
                "                                    {\n" +
                "                                        \"id\": 8,\n" +
                "                                        \"label\": \"\",\n" +
                "                                        \"swtich\": false,\n" +
                "                                        \"operator\": \"\",\n" +
                "                                        \"value\": \"\"\n" +
                "                                    }\n" +
                "                                ]\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }";
    }

    private static List<NodeLineBean> getNodeLineBeanList() {

        List<NodeLineBean> lineBeanList = new ArrayList<>();
        NodeLineBean nodeLineBean1 = new NodeLineBean();
        //开始
        nodeLineBean1.setFrom("20201030144957l51q6hhcmf");
        //ab
        nodeLineBean1.setTo("20201101135453ti3delqmng");
        nodeLineBean1.setPinName("1");
        lineBeanList.add(nodeLineBean1);

        NodeLineBean nodeLineBean2 = new NodeLineBean();
        //ab
        nodeLineBean2.setFrom("20201101135453ti3delqmng");
        //条件
        nodeLineBean2.setTo("20201101135451qxiemr9trr");
        nodeLineBean2.setPinName("PIN_FALSE");
        lineBeanList.add(nodeLineBean2);

        NodeLineBean nodeLineBean3 = new NodeLineBean();
        //ab
        nodeLineBean3.setFrom("20201101135453ti3delqmng");
        //短信
        nodeLineBean3.setTo("20201101135452glojpohdz");
        nodeLineBean3.setPinName("PIN_TRUE");
        lineBeanList.add(nodeLineBean3);

        return lineBeanList;
    }


}
