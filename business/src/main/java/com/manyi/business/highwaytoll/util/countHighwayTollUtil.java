package com.manyi.business.highwaytoll.util;

import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;

import java.util.*;

/**
 * @Description: 通行费计算
 * @author zhaoyuxin
 * @version: 1.0.0 on 2015/6/19.
 * @reviewer:
 */
public class countHighwayTollUtil {

    //计算车货总质量的设备误差率
    public  static final double Rate_err= 0.05;
    //轴数：2
    public static final int AXIS_NUM_2 =2;
    //轴数：3
    public static final int AXIS_NUM_3 =3;
    //轴数：4
    public static final int AXIS_NUM_4 =4;
     //轴数：5
    public static final int AXIS_NUM_5 =5;
    //轴数：6
    public static final int AXIS_NUM_6 =6;
    //车货重量：5吨
    public static final double NORMALWEIGHT_1 =5;
    //车货重量：10吨
    public static final double NORMALWEIGHT_2 =10;
    //车货重量：49
    public static final double NORMALWEIGHT_3 =49;
    //两轴货车额定载荷
    public static final double RATEDLOAD_2 =17;
    //三轴货车额定载荷
    public static final double RATEDLOAD_3 =27;
    //四轴货车额定载荷
    public static final double RATEDLOAD_4 =37;
    //五轴货车额定载荷
    public static final double RATEDLOAD_5 =43;
    //六轴货车额定载荷
    public static final double RATEDLOAD_6 =49;
    //超限率
    public static final double OVERRUN_RATE_1 =0;
    //超限率
    public static final double OVERRUN_RATE_2 =0.3;
    //超限率
    public static final double OVERRUN_RATE_3 =1;


    //10＜x≤49 计算公式 X代表高速公路车货不超限质量，S代表里程
    public static final String NORMAL_FEE_FORMULA_3 = "((-0.8/(39*39))*X*X+(78.4/(39*39))*X+512.8/(39*39))*S";
    //5＜x≤10 计算公式 X代表高速公路车货不超限质量，S代表里程
    public static final String NORMAL_FEE_FORMULA_2 ="0.08*X*S";
    //x 5,按5吨 计算公式 X代表高速公路车货不超限质量，S代表里程
    public static final String NORMAL_FEE_FORMULA_1 ="0.08*5*S";
    //超限率区间 0%<m≤30%
    public static final String OVERRUN_FEE_FORMULA_1 ="((20*m+3)/3.0)*0.08*C*S";
    //超限率区间 30%<m≤100%
    public static final String OVERRUN_FEE_FORMULA_2 ="((30*m+12)/7.0)*0.08*C*S";
    //超限率区间 100%<m
    public static final String OVERRUN_FEE_FORMULA_3 ="6*0.08*C*S";
    /**
     *通行费计算
     * @param axisNum 轴数
     * @param realTotalWeight 实际总重量
     * @param mileage 里程数
     * @return
     */
    public static double getTotalFee(int axisNum, double realTotalWeight,double mileage){
        System.out.println(axisNum+"--"+realTotalWeight+"--"+mileage);
        //车货总质量＝实际车货总质量×（1－Rate_err）
        double totalWight= realTotalWeight*(1-Rate_err);
        //是否超限
        boolean isOverrun=false;
        //超限重量
        double normlWeight=totalWight;
        //超限重量
        double overrun=0;
        //超限率
        double overrunRate=0;
        //总费用
        double totalFee=0;
        //正常费用
        double normalFee=0;
        //超限费用
        double overrunFee=0;
        switch (axisNum){
            case AXIS_NUM_2:
                if(totalWight> RATEDLOAD_2) {
                    isOverrun=true;
                    overrun = totalWight - RATEDLOAD_2;
                    overrunRate = totalWight / RATEDLOAD_2 - 1;
                    normlWeight= RATEDLOAD_2;
                }
                break;
            case AXIS_NUM_3:
                if(totalWight> RATEDLOAD_3) {
                    isOverrun=true;
                    overrun = totalWight - RATEDLOAD_3;
                    overrunRate = totalWight / RATEDLOAD_3 - 1;
                    normlWeight= RATEDLOAD_3;
                }
                break;
            case AXIS_NUM_4:
                if(totalWight> RATEDLOAD_4) {
                    isOverrun=true;
                    overrun = totalWight - RATEDLOAD_4;
                    overrunRate = totalWight / RATEDLOAD_4 - 1;
                    normlWeight= RATEDLOAD_4;
                }
                break;
            case AXIS_NUM_5:
                if(totalWight> RATEDLOAD_5) {
                    isOverrun=true;
                    overrun = totalWight - RATEDLOAD_5;
                    overrunRate = totalWight / RATEDLOAD_5 - 1;
                    normlWeight= RATEDLOAD_5;
                }
                break;
            case AXIS_NUM_6:
                if(totalWight> RATEDLOAD_6) {
                    isOverrun=true;
                    overrun = totalWight - RATEDLOAD_6;
                    overrunRate = totalWight / RATEDLOAD_6 - 1;
                    normlWeight= RATEDLOAD_6;
                }
                break;
            default:
                if(totalWight> RATEDLOAD_6) {
                    isOverrun=true;
                    overrun = totalWight - RATEDLOAD_6;
                    overrunRate = totalWight / RATEDLOAD_6 - 1;
                    normlWeight= RATEDLOAD_6;
                }
        }
        //参数map
        Map normalmap = new HashMap();
        normalmap.put("X",normlWeight);
        normalmap.put("S",mileage);
        //不超限车货总质量 10＜x≤49
        if((normlWeight<= NORMALWEIGHT_3)&&(normlWeight> NORMALWEIGHT_2)){
            normalFee=getValueFromFormula(NORMAL_FEE_FORMULA_3,normalmap);
        }
        //不超限车货总质量5＜x≤10
        else if((normlWeight<= NORMALWEIGHT_2)&&(normlWeight> NORMALWEIGHT_1)){
            normalFee=getValueFromFormula(NORMAL_FEE_FORMULA_2,normalmap);
        }
        //不超限车货总质量  x<= 5,按5吨
        else if(normlWeight<= NORMALWEIGHT_1){
            normalFee=getValueFromFormula(NORMAL_FEE_FORMULA_1,normalmap);
        }
        //如果超限，计算超限费用
        if(isOverrun==true){
            Map overrunMap = new HashMap();
            overrunMap.put("m",overrunRate);
            overrunMap.put("C",overrun);
            overrunMap.put("S",mileage);
            //超限率区间 0%<m≤30%
            if((overrunRate<= OVERRUN_RATE_2)&&(overrunRate> OVERRUN_RATE_1)){
                overrunFee=getValueFromFormula(OVERRUN_FEE_FORMULA_1,overrunMap);
            }
            //超限率区间 30%<m≤100%
            else if((overrunRate<= OVERRUN_RATE_3)&&(overrunRate> OVERRUN_RATE_2)){
                overrunFee=getValueFromFormula(OVERRUN_FEE_FORMULA_2,overrunMap);
            }
            //超限率区间 100%<m
            else if(overrunRate> OVERRUN_RATE_3){
                overrunFee=getValueFromFormula(OVERRUN_FEE_FORMULA_3,overrunMap);
            }
        }
        totalFee=normalFee+overrunFee;
        return totalFee;
    }


    /**
     * 参数带入公式计算
     * @param exp
     * @param map
     * @return
     */
    private static double getValueFromFormula(String exp, Map<String, Double> map) {
        Object evaluate=null;
        if(null == map)
        {
            return  Double.parseDouble(ExpressionEvaluator.evaluate(exp).toString());
        }
        List<Variable> variables = new ArrayList<Variable>();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            variables.add(Variable.createVariable(entry.getKey(),entry.getValue()));
        }
        if(variables!= null  && (!variables.isEmpty()))
        {
            evaluate = ExpressionEvaluator.evaluate(exp, variables);
        }
        else
        {
            evaluate =   ExpressionEvaluator.evaluate(exp);
        }
        return  Double.parseDouble(evaluate.toString());
    }

}
