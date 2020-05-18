package com.chen.mywebflux.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chen.mywebflux.db.entity.SevenStarHistory;
import com.chen.mywebflux.db.mapper.SevenStarHistoryMapper;
import com.chen.mywebflux.util.ToolUtil;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: Aaron chen
 * @Date: 2020/5/18 3:04
 */
@Service
@Slf4j
public class SevenStarService {

    @Autowired
    SevenStarHistoryMapper sevenStarHistoryMapper;

    HashMap<String,Node> drawNumMap = new HashMap<>(100);
    List<UnopenedNode> nodeList = new LinkedList<>();
    {
        for(int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                drawNumMap.put(i+","+j,new Node(i+","+j,0));
                nodeList.add(new UnopenedNode(i+","+j,null,null,null));
            }
        }
    }

    @Data
    class Node{
        private String drawNum;
        private Integer num;

        public Node(String drawNum,Integer num){
            this.drawNum = drawNum;
            this.num = num;
        }
    }

    @Data
    class UnopenedNode{
        private String drawNum;
        private String date;
        private String period;
        private String unopenedPeriod;
        private Long day;

        public UnopenedNode(String drawNum,String date,String period,String unopenedPeriod){
            this.date = date;
            this.period = period;
            this.unopenedPeriod = unopenedPeriod;
            this.drawNum = drawNum;
        }

        public UnopenedNode(){}

        class UnopenedNodeDay implements Comparator<UnopenedNode>{
            @Override
            public int compare(UnopenedNode o1, UnopenedNode o2) {
                return o2.getDay().compareTo(o1.day);
            }
        }
    }

    public String statisticsDrawNum(String drawTime,Integer type,Integer size){
        List<SevenStarHistory> starHistories = sevenStarHistoryMapper.selectByTime(drawTime);
        for (SevenStarHistory starHistory:starHistories) {
            String drawNumKey = starHistory.getTbDrawNum().substring(0,1)+","+starHistory.getTbDrawNum().substring(3,4);
            Node node = drawNumMap.get(drawNumKey);
            node.setNum(node.num++);
            drawNumMap.put(drawNumKey,node);
        }
        log.info("总数:"+drawNumMap.size());
        return JSON.toJSONString(drawNumMap);
    }

    public String statisticsUnopened(){
        for(UnopenedNode unopenedNode : nodeList){
            SevenStarHistory sevenStarHistory = sevenStarHistoryMapper.selectByTbHeadTail(unopenedNode.getDrawNum());
            unopenedNode.setDate(sevenStarHistory.getTbDrawTime());
            unopenedNode.setPeriod(sevenStarHistory.getTbPeriodsNum());
            long day = ToolUtil.getDaySub(sevenStarHistory.getTbDrawTime()
                    ,ToolUtil.dataFormat("yyyy-MM-dd").format(new Date()));
            unopenedNode.setUnopenedPeriod("已经有"+day+"天没有开"+unopenedNode.getDrawNum());
            unopenedNode.setDay(day);

        }
        Collections.sort(nodeList,new UnopenedNode().new UnopenedNodeDay());
        log.info(nodeList.toString());
        return JSON.toJSONString(nodeList);
    }

}
;