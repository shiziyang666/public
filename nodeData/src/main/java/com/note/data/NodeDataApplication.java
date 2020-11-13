package com.note.data;

import com.note.data.bean.TaskBean;
import com.note.data.service.CronService;
import com.note.data.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NodeDataApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NodeDataApplication.class, args);
    }

    @Autowired
    private DataService dataService;

    @Autowired
    private CronService cronService;

    @Override
    public void run(String... args) throws Exception {

        TaskBean task = dataService.task();
        System.out.println(task);

//        TaskBean info = DataInfo.getInfo();
//        List<NodeBean> nodeBeanList = info.getNodeBeanList();
//        List<NodeLineBean> nodeLineBeanList = info.getNodeLineBeanList();
//        List<List<NodeTree>> list = FormatTree.getList(nodeBeanList, nodeLineBeanList);
//        System.out.println(list);
    }


}
