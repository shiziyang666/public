//package com.note.data.utils;
//
//import com.cdp.mybatis.bean.TLine;
//import org.springframework.util.CollectionUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 校验画布连线关系
// * shiziyang
// */
//public class CheckConnectionUtil {
//
//    //画布不完整
//    private final static Integer CODE_1 = 1;
//    //画布没问题
//    private final static Integer CODE_0 = 0;
//
//    /**
//     * 校验连线关系（shiziyang）
//     *
//     * @param tLineList   连线关系集合
//     * @param startNodeId 开始控件id
//     * @param endIdList   结束控件id集合
//     * @return code 1:画布不完整，0：画布没问题
//     */
//    public static Integer check(List<TLine> tLineList, String startNodeId, List<String> endIdList) {
//        //id集合
//        List<String> idList = new ArrayList<>();
//        idList.add(startNodeId);
//        //返回值code 1:画布不完整，0：画布没问题
//        Integer code = null;
//        while (true) {
//            List<String> finalIdList = idList;
//
//            //校验每一个toId是否有下级节点，如果没有画布不完整
//            for (String id : finalIdList) {
//                //获取此id下层的toId
//                List<String> toIdList = tLineList.stream()
//                        .filter(
//                                tLine -> tLine.getCfrom().equals(id)
//                        ).map(TLine::getCto).collect(Collectors.toList());
//                //如果下层没值说明画布不完整
//                if (CollectionUtils.isEmpty(toIdList)) {
//                    code = CODE_1;
//                    break;
//                }
//            }
//            //画布不完整
//            if (code == CODE_1) {
//                break;
//            }
//            //获取下层的所有id，并去掉所有的结束节点id
//            idList = tLineList.stream()
//                    .filter(
//                            tLine -> finalIdList.contains(tLine.getCfrom()) && !endIdList.contains(tLine.getCto())
//                    ).map(TLine::getCto).collect(Collectors.toList());
//            if (CollectionUtils.isEmpty(idList)) {
//                code = CODE_0;
//                break;
//            }
//        }
//
//        return code;
//    }
//}
