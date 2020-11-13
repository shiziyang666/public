package com.note.data.utils;

import com.note.data.bean.DemoReq;
import com.note.data.bean.TaskScheduleModel;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CronUtil {

    /**
     * 方法摘要：构建Cron表达式
     *
     * @param taskScheduleModel
     * @return String
     */
    public static String createCronExpression(TaskScheduleModel taskScheduleModel) {
        StringBuffer cronExp = new StringBuffer("");

        if (null == taskScheduleModel.getJobType()) {
            System.out.println("执行周期未配置");//执行周期未配置
        }

        if (null != taskScheduleModel.getSecond()
                && null == taskScheduleModel.getMinute()
                && null == taskScheduleModel.getHour()) {
            //每隔几秒
            if (taskScheduleModel.getJobType().intValue() == 0) {
                cronExp.append("0/").append(taskScheduleModel.getSecond());
                cronExp.append(" ");
                cronExp.append("* ");
                cronExp.append("* ");
                cronExp.append("* ");
                cronExp.append("* ");
                cronExp.append("?");
            }

        }

        if (null != taskScheduleModel.getSecond()
                && null != taskScheduleModel.getMinute()
                && null == taskScheduleModel.getHour()) {
            //每隔几分钟
            if (taskScheduleModel.getJobType().intValue() == 4) {
                cronExp.append("* ");
                cronExp.append("0/").append(taskScheduleModel.getMinute());
                cronExp.append(" ");
                cronExp.append("* ");
                cronExp.append("* ");
                cronExp.append("* ");
                cronExp.append("?");
            }

        }

        if (null != taskScheduleModel.getSecond()
                && null != taskScheduleModel.getMinute()
                && null != taskScheduleModel.getHour()) {
            //秒
            cronExp.append(taskScheduleModel.getSecond()).append(" ");
            //分
            cronExp.append(taskScheduleModel.getMinute()).append(" ");
            //小时
            cronExp.append(taskScheduleModel.getHour()).append(" ");

            //每天
            if (taskScheduleModel.getJobType().equals(DemoReq.CHOICEType.day.getCode())) {
                cronExp.append("* ");//日
                cronExp.append("* ");//月
                cronExp.append("?");//周
            }

            //按每周
            else if (taskScheduleModel.getJobType().equals(DemoReq.CHOICEType.week.getCode())) {
                //一个月中第几天
                cronExp.append("? ");
                //月份
                cronExp.append("* ");
                //周
                List<Integer> weeks = taskScheduleModel.getDayOfWeeks();
                boolean weekFlag = true;
                for (Integer week : weeks) {
                    if (weekFlag) {
                        weekFlag = false;
                        cronExp.append(week);
                    } else {
                        cronExp.append(",").append(week);
                    }
                }
//                for (int i = 0; i < weeks.size(); i++) {
//                    if (i == 0) {
//                        cronExp.append(weeks.get(i));
//                    } else {
//                        cronExp.append(",").append(weeks.get(i));
//                    }
//                }

            }

            //按每月
            else if (taskScheduleModel.getJobType().equals(DemoReq.CHOICEType.month.getCode())) {
                //一个月中的哪几天
                List<Integer> days = taskScheduleModel.getDayOfMonths();
                boolean dayFlag = true;
                for (Integer day : days) {
                    if (dayFlag) {
                        dayFlag = false;
                        cronExp.append(day);
                    } else {
                        cronExp.append(",").append(day);
                    }
                }
//                for (int i = 0; i < days.size(); i++) {
//                    if (i == 0) {
//                        cronExp.append(days.get(i));
//                    } else {
//                        cronExp.append(",").append(days.get(i));
//                    }
//                }
                //月份
                cronExp.append(" * ");
                //周
                cronExp.append("?");
            }

        } else {
            System.out.println("时或分或秒参数未配置");//时或分或秒参数未配置
        }
        return cronExp.toString();
    }

    /**
     * 方法摘要：生成计划的详细描述
     *
     * @param taskScheduleModel
     * @return String
     */
    public static String createDescription(TaskScheduleModel taskScheduleModel) {
        StringBuffer description = new StringBuffer("");
        //计划执行开始时间
//      Date startTime = taskScheduleModel.getScheduleStartTime();

        if (null != taskScheduleModel.getSecond()
                && null != taskScheduleModel.getMinute()
                && null != taskScheduleModel.getHour()) {
            //按每天
            if (taskScheduleModel.getJobType().intValue() == 1) {
                description.append("每天");
                description.append(taskScheduleModel.getHour()).append("时");
                description.append(taskScheduleModel.getMinute()).append("分");
                description.append(taskScheduleModel.getSecond()).append("秒");
                description.append("执行");
            }

            //按每周
            else if (taskScheduleModel.getJobType().intValue() == 3) {
                if (taskScheduleModel.getDayOfWeeks() != null && taskScheduleModel.getDayOfWeeks().size() > 0) {
                    String days = "";
                    for (int i : taskScheduleModel.getDayOfWeeks()) {
                        days += "周" + i;
                    }
                    description.append("每周的").append(days).append(" ");
                }
                if (null != taskScheduleModel.getSecond()
                        && null != taskScheduleModel.getMinute()
                        && null != taskScheduleModel.getHour()) {
                    description.append(",");
                    description.append(taskScheduleModel.getHour()).append("时");
                    description.append(taskScheduleModel.getMinute()).append("分");
                    description.append(taskScheduleModel.getSecond()).append("秒");
                }
                description.append("执行");
            }

            //按每月
            else if (taskScheduleModel.getJobType().intValue() == 2) {
                //选择月份
                if (taskScheduleModel.getDayOfMonths() != null && taskScheduleModel.getDayOfMonths().size() > 0) {
                    String days = "";
                    for (int i : taskScheduleModel.getDayOfMonths()) {
                        days += i + "号";
                    }
                    description.append("每月的").append(days).append(" ");
                }
                description.append(taskScheduleModel.getHour()).append("时");
                description.append(taskScheduleModel.getMinute()).append("分");
                description.append(taskScheduleModel.getSecond()).append("秒");
                description.append("执行");
            }

        }
        return description.toString();
    }

    public static String getCron(DemoReq demoReq) {
        TaskScheduleModel taskScheduleModel = new TaskScheduleModel();
        //时间处理
        String triggerTime = demoReq.getTriggerTime();
        if (StringUtils.isEmpty(triggerTime)) {
            triggerTime = "00:00:00";
        }
        String[] times = triggerTime.split(":");
        if (times.length >= 3) {
            taskScheduleModel.setHour(Integer.parseInt(times[0]));
            taskScheduleModel.setMinute(Integer.parseInt(times[1]));
            taskScheduleModel.setSecond(Integer.parseInt(times[2]));
        } else {
            taskScheduleModel.setHour(Integer.parseInt(times[0]));
            taskScheduleModel.setMinute(Integer.parseInt(times[1]));
            taskScheduleModel.setSecond(0);
        }
        //天数处理
        String dayValue = demoReq.getDayValue();
        if (StringUtils.isNotEmpty(dayValue)) {
            String[] days = dayValue.split(",");
            List<Integer> dayIntegerList = Arrays.stream(days).map(aa -> Integer.parseInt(aa)).collect(Collectors.toList());
            if (demoReq.getChoice().equals(DemoReq.CHOICEType.week.getCode())) {
                taskScheduleModel.setDayOfWeeks(dayIntegerList);
            }
            if (demoReq.getChoice().equals(DemoReq.CHOICEType.month.getCode())) {
                taskScheduleModel.setDayOfMonths(dayIntegerList);
            }
        }

        if (demoReq.getChoice().equals(DemoReq.CHOICEType.day.getCode())) {
            taskScheduleModel.setJobType(DemoReq.CHOICEType.day.getCode());
            String cropExp = createCronExpression(taskScheduleModel);
            return cropExp;
        } else if (demoReq.getChoice().equals(DemoReq.CHOICEType.week.getCode())) {
            taskScheduleModel.setJobType(DemoReq.CHOICEType.week.getCode());
            String cropExp = createCronExpression(taskScheduleModel);
            return cropExp;
        } else if (demoReq.getChoice().equals(DemoReq.CHOICEType.month.getCode())) {
            taskScheduleModel.setJobType(DemoReq.CHOICEType.month.getCode());
            String cropExp = createCronExpression(taskScheduleModel);
            return cropExp;
        }

        return null;
    }

    //参考例子
    public static void main(String[] args) {
        DemoReq demoReq = new DemoReq();
        demoReq.setTriggerTime("10:15:45");
        demoReq.setChoice(DemoReq.CHOICEType.week.getCode());
        demoReq.setDayValue("1,2,5");
        String cron = getCron(demoReq);
        System.out.println(cron);

//        //执行时间：每天的12时12分12秒 start
//        TaskScheduleModel taskScheduleModel = new TaskScheduleModel();
//
//        taskScheduleModel.setJobType(0);//按每秒
//        taskScheduleModel.setSecond(30);
//        String cronExp = createCronExpression(taskScheduleModel);
//        System.out.println(cronExp);
//
//        taskScheduleModel.setJobType(4);//按每分钟
//        taskScheduleModel.setMinute(8);
//        String cronExpp = createCronExpression(taskScheduleModel);
//        System.out.println(cronExpp);
//
//        taskScheduleModel.setJobType(1);//按每天
//        Integer hour = 12; //时
//        Integer minute = 12; //分
//        Integer second = 12; //秒
//        taskScheduleModel.setHour(hour);
//        taskScheduleModel.setMinute(minute);
//        taskScheduleModel.setSecond(second);
//        String cropExp = createCronExpression(taskScheduleModel);
//        System.out.println(cropExp + ":" + createDescription(taskScheduleModel));
//        //执行时间：每天的12时12分12秒 end
//
//        taskScheduleModel.setJobType(3);//每周的哪几天执行
//        List<Integer> dayOfWeeks = new ArrayList<>();
//        dayOfWeeks.add(1);
//        dayOfWeeks.add(2);
//        dayOfWeeks.add(3);
//        taskScheduleModel.setDayOfWeeks(dayOfWeeks);
//        cropExp = createCronExpression(taskScheduleModel);
//        System.out.println(cropExp + ":" + createDescription(taskScheduleModel));
//
//        taskScheduleModel.setJobType(2);//每月的哪几天执行
//        List<Integer> dayOfMonths = new ArrayList();
//        dayOfMonths.add(1);
//        dayOfMonths.add(21);
//        dayOfMonths.add(13);
//        taskScheduleModel.setDayOfMonths(dayOfMonths);
//        cropExp = createCronExpression(taskScheduleModel);
//        System.out.println(cropExp + ":" + createDescription(taskScheduleModel));

    }

}
