package com.manyi.point.support;


import com.manyi.point.bean.PointEvent;
import com.manyi.point.bean.PointParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author LiuKaihua
 * @version 1.0.0 2015-06-11
 * @reviewer
 */
public class StrategyContext {

    @Autowired
   private  PointStrategy strategy;


   public  StrategyContext (PointStrategy strategy)
   {
       this.strategy = strategy;
   }

//    public int getPointByExp(PointParam request) throws BusinessException {
//
//
//        List<DimensionExp> explist=  expDao.getExpList(request.getEventCode());
//        //假设当前会员等级为1
//        // String currentLevel = "1" ;
//        if( null == explist || explist.size() == 0)
//        {
//            throw new BusinessException(Type.NO_EXP);
//        }
//        DimensionExp currentExp = explist.get(0);
//        int point = CalPoint(currentExp.getExp(),request.getParams());
//        return point;
//    }

    private int CalPoint(String exp, Map<String, Float> map) {
        Object evaluate = null;
        if(null == map)
        {
            return  (int)Float.parseFloat( ExpressionEvaluator.evaluate(exp).toString());
        }

        List<Variable> variables = new ArrayList<Variable>();
        for (Map.Entry<String, Float> entry : map.entrySet()) {
            variables.add(Variable.createVariable(entry.getKey(),entry.getValue()));
        }


        if( !StringUtils.isEmpty(variables) || variables.size() != 0)
        {
            evaluate = ExpressionEvaluator.evaluate(exp, variables);
        }

        else
        {
            evaluate =   ExpressionEvaluator.evaluate(exp);
        }

        return  (int)Float.parseFloat(evaluate.toString());
    }

    public int getPointByExp(PointEvent event, PointParam request) {

        if(!strategy.validate(event,request.getUserId()))
        {
            return  0 ;
        }
        else
        {
            int point = CalPoint(event.getExp(),request.getParams());
            return point;
        }
    }
}
