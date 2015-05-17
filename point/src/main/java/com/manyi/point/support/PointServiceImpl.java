package com.manyi.point.support;

import com.manyi.base.entity.OpEvent;
import com.manyi.base.entity.State;
import com.manyi.base.entity.Type;
import com.manyi.base.exception.BusinessException;
import com.manyi.point.PointService;
import com.manyi.point.bean.*;
import com.manyi.point.support.dao.ExpDao;
import com.manyi.point.support.dao.GrowthLevelDao;
import com.manyi.point.support.entity.DimensionExp;
import com.manyi.point.support.entity.GrowthLevels;
import com.manyi.point.support.entity.PointLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manyi.point.support.dao.PointDao;
import org.wltea.expression.ExpressionEvaluator;
import org.wltea.expression.datameta.Variable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/9.
 */
@Service("pointService")
public class PointServiceImpl implements PointService
{
    public static int SUCCESS= 0;
    @Autowired
    private PointDao pointDao;

    @Autowired
    private GrowthLevelDao levelDao;

    @Autowired
    private ExpDao expDao ;

    @Override
    public int addPointAndGrow(PointParam request) throws BusinessException {

        String eventCode = request.getEventCode();
        String serviceId = request.getServiceId();
       // String remarks = request.getRemarks();
        String opUserId = request.getOpUserId();
        int userId = request.getUserId() ;

        OpEvent event = pointDao.getEventByCode(eventCode);
        if(null == event)
        {
            Type type=Type.POINTEVENT_NOT_FOUND;
               throw new BusinessException(type);
        }

        int growth = event.getEventGrowth();
        int pointVal = event.getEventPoint();

         Point point = pointDao.getPointByUser(serviceId,userId);
        List<GrowthLevel> list = levelDao.getLevelList(serviceId);
        GrowthLevels levels = new GrowthLevels();
        levels.setGrowthLevelList(list);
        if(null == point)
        {
            point = new Point();
            point.setGrowth(growth);
            point.setPoint(pointVal);
            point.setServiceId(serviceId);
            point.setState(State.NORMAL);

            point.setUserId(userId);
            point.setLevel(levels.getLevelByGrowth(growth));
            pointDao.creatPoint(point);

        }
        else
        {
            point.setGrowth(point.getGrowth() + growth);
            point.setPoint(point.getPoint() + pointVal);
            point.setLevel(levels.getLevelByGrowth(point.getGrowth()));
            pointDao.updatePoint(point);
        }

        addGrowthLog(request,growth,point.getId());
        addPointLog(request,pointVal,point.getId());
        return SUCCESS;
    }

    @Override
    public int addPoint(PointParam request) throws BusinessException {



        String eventCode = request.getEventCode();
        String serviceId = request.getServiceId();
        String opUserId = request.getOpUserId();
        int userId = request.getUserId() ;

//        OpEvent event = pointDao.getEventByCode(eventCode);
//        if(null == event)
//        {
//            Type type=Type.POINTEVENT_NOT_FOUND;
//            throw new BusinessException(type);
//        }
//
//        int pointVal = event.getEventPoint();

        int pointVal = getPointByExp(eventCode,request);
        Point point = pointDao.getPointByUser(serviceId,userId);
        List<GrowthLevel> list = levelDao.getLevelList(serviceId);
        GrowthLevels levels = new GrowthLevels();
        levels.setGrowthLevelList(list);

        if(null == point)
        {
            point = new Point();
            point.setPoint(pointVal);
            point.setServiceId(serviceId);
            point.setState(State.NORMAL);
            point.setUserId(userId);
            point.setLevel(0);
            pointDao.creatPoint(point);

        }
        else
        {
            point.setPoint(point.getPoint() + pointVal);
          //  point.setLevel(levels.getLevelByGrowth(point.getGrowth()));
            pointDao.updatePoint(point);
        }
        addPointLog(request,pointVal,point.getId());
        return SUCCESS;
    }

    private int getPointByExp(String eventCode, PointParam request) throws BusinessException {


        List<DimensionExp> explist=  expDao.getExpList(eventCode);
        //假设当前会员等级为1
        String currentLevel = "1" ;
        if( null == explist || explist.size() == 0)
        {
            throw new BusinessException(Type.NO_EXP);
        }
         DimensionExp currentExp = null;
         for (DimensionExp exp :  explist)
         {
             String level = exp.getPreferentialCode();
             if(null == level || "".equals( level))
             {
                 currentExp = exp;
             }
             if(level.equals(currentLevel))
             {
                 currentExp = exp;
                 break;
             }

         }
          if (null == currentExp)
          {
            throw new BusinessException(Type.NO_EXP);
          }
        int point = CalPoint(currentExp.getExp(),request.getParams());
        return point;
    }

    private int CalPoint(String exp, Map<String, Float> map) {

        List<Variable> variables = new ArrayList<Variable>();
        for (Map.Entry<String, Float> entry : map.entrySet()) {
            variables.add(Variable.createVariable(entry.getKey(),entry.getValue()));
        }

        Object evaluate;
        if(null != variables && variables.size() != 0)
        {
             evaluate = ExpressionEvaluator.evaluate(exp, variables);
        }

        else
        {
            evaluate =   ExpressionEvaluator.evaluate(exp);
        }

        return  (int)Float.parseFloat(evaluate.toString());
    }


    @Override
    public int addGrowth(PointParam request) throws BusinessException {
        String eventCode = request.getEventCode();
        String serviceId = request.getServiceId();
        String opUserId = request.getOpUserId();
        int userId = request.getUserId() ;

        OpEvent event = pointDao.getEventByCode(eventCode);
        if(null == event)
        {
            Type type=Type.POINTEVENT_NOT_FOUND;
            throw new BusinessException(type);
        }

        int growth = event.getEventGrowth();

        Point point = pointDao.getPointByUser(serviceId,userId);
        List<GrowthLevel> list = levelDao.getLevelList(serviceId);
        GrowthLevels levels = new GrowthLevels();
        levels.setGrowthLevelList(list);

        if(null == point)
        {
            point = new Point();
            point.setGrowth(growth);
            point.setPoint(0);
            point.setServiceId(serviceId);
            point.setState(State.NORMAL);
            point.setUserId(userId);
            point.setLevel(levels.getLevelByGrowth(growth));
            pointDao.creatPoint(point);

        }
        else
        {
            point.setGrowth(point.getGrowth() + growth);
            point.setPoint(point.getPoint());
            point.setLevel(levels.getLevelByGrowth(point.getGrowth()));
            pointDao.updatePoint(point);
        }

        addGrowthLog(request,growth,point.getId());
        return SUCCESS;
    }

    @Override
    public int usePoint(UsePointParam usePoint) throws BusinessException {

        String eventCode = UsePointParam.eventCode;
        String serviceId = usePoint.getServiceId();
        String opUserId = usePoint.getOpUserId();
        int userId = usePoint.getUserId() ;

        OpEvent event = pointDao.getEventByCode(eventCode);
        if(null == event)
        {
            Type type=Type.POINTEVENT_NOT_FOUND;
            throw new BusinessException(type);
        }


        Point point = pointDao.getPointByUser(serviceId,userId);
//        List<GrowthLevel> list = levelDao.getLevelList(serviceId);
//        GrowthLevels levels = new GrowthLevels();
//        levels.setGrowthLevelList(list);

        if(null == point)
        {
            throw new BusinessException(Type.NO_POINT_USE);

        }
        else
        {
            if(point.getPoint() < usePoint.getUsePoint())
            {
                throw new BusinessException(Type.NOT_ENOUGH_POINT);
            }
            point.setPoint(point.getPoint()-usePoint.getUsePoint());
          //  point.setLevel(levels.getLevelByGrowth(point.getGrowth()));
            pointDao.updatePoint(point);
        }

        addPointLog(usePoint, usePoint.getUsePoint(), point.getId());
        return SUCCESS;
    }

    @Override
    /**
     * 操作员使用的接口
     */
    public int updatePoint(UpdatePointParam request) throws BusinessException {

        String eventCode = UpdatePointParam.eventCode;
        String serviceId = request.getServiceId();
        String opUserId = request.getOpUserId();
        int userId = request.getUserId() ;
        int changeVal = 0 ;
        OpEvent event = pointDao.getEventByCode(eventCode);
        if(null == event)
        {
            Type type=Type.POINTEVENT_NOT_FOUND;
            throw new BusinessException(type);
        }


        Point point = pointDao.getPointByUser(serviceId,userId);

        if(null == point)
        {
            throw new BusinessException(Type.NO_POINT_USE);

        }
        else
        {
            if(request.getNewPoint() < 0)
            {
                throw new BusinessException(Type.REQUEST_AUTH);
            }
            request.setRemarks("变更积分：" + point.getPoint()+" 到 " + request.getNewPoint());
            changeVal = request.getNewPoint() - point.getPoint()   ;
            point.setPoint(request.getNewPoint());
            pointDao.updatePoint(point);
        }

        addPointLog(request, changeVal, point.getId());
        return SUCCESS;
    }

    @Override
    /**
     * 操作员使用的接口
     */
    public int updateGrowth(UpdateGrowthParam request) throws BusinessException {
        String eventCode = UpdateGrowthParam.eventCode;
        String serviceId = request.getServiceId();
        String opUserId = request.getOpUserId();
        int userId = request.getUserId() ;
        int changeVal = 0 ;
        OpEvent event = pointDao.getEventByCode(eventCode);
        if(null == event)
        {
            Type type=Type.POINTEVENT_NOT_FOUND;
            throw new BusinessException(type);
        }


        Point point = pointDao.getPointByUser(serviceId,userId);
        List<GrowthLevel> list = levelDao.getLevelList(serviceId);
        GrowthLevels levels = new GrowthLevels();
        levels.setGrowthLevelList(list);

        if(null == point)
        {
            throw new BusinessException(Type.NO_POINT_USE);

        }
        else
        {
            if(request.getNewGrowth() < 0)
            {
                throw new BusinessException(Type.REQUEST_AUTH);
            }
            request.setRemarks("变更成长值：" + point.getGrowth()+" 到 " + request.getNewGrowth());
            changeVal = request.getNewGrowth() - point.getGrowth();
            point.setGrowth(request.getNewGrowth());
            point.setLevel(levels.getLevelByGrowth(point.getGrowth()));
            pointDao.updatePoint(point);
        }
        addGrowthLog(request, changeVal, point.getId());
        return SUCCESS;
    }

    @Override
    public Point queryUserPoint(String serviceId, int userId)
    {
        return pointDao.getPointByUser(serviceId,userId);
    }

    @Override
    public QueryPointResult queryPoints(QueryPointCondition condition)
    {

        QueryPointResult response = new QueryPointResult();
        if(null != condition && 0 != condition.getPageSize())
        {
            response.setPageNum(condition.getPageNum());
            response.setPageSize(condition.getPageSize());
            response.setPageCount(getPointPageCount(condition));
        }

        List<Point> list =   pointDao.getPoints(condition);
        response.setList(list);
        return  response;
    }

    private int getPointPageCount(QueryPointCondition condition) {

        int count = pointDao.getPointPageCount(condition);
        int pageCount = (count + condition.getPageSize() -1)/condition.getPageSize();
        return pageCount;
    }


    @Override
    public PointLogResult queryUserPointLog(QueryPointLogCondition condition) {

        PointLogResult response = new PointLogResult();
        if(null != condition && 0 != condition.getPageSize())
        {
            response.setPageNum(condition.getPageNum());
            response.setPageSize(condition.getPageSize());
            response.setPageCount(getPointLogPageCount(condition));
        }
        List<PointLogBean> list = pointDao.queryPointLog(condition);
        response.setList(list);
        return  response;

    }



    private int getPointLogPageCount(QueryPointLogCondition condition)
    {
        int count = pointDao.getPointLogPageCount(condition);
        int pageCount = (count + condition.getPageSize() -1)/condition.getPageSize();
        return pageCount;
    }

    @Override
    public PointLogResult queryUserGrowthLog(QueryPointLogCondition condition)
    {

        PointLogResult response = new PointLogResult();
        if(null != condition && 0 != condition.getPageSize())
        {
            response.setPageNum(condition.getPageNum());
            response.setPageSize(condition.getPageSize());
            response.setPageCount(getGrowthLogPageCount(condition));
        }
        List<PointLogBean> list  = pointDao.queryGrowthLog(condition);
        response.setList(list);
        return  response;

    }


    private int getGrowthLogPageCount(QueryPointLogCondition condition)
    {
        int count = pointDao.getGrowthLogPageCount(condition);
        int pageCount = (count + condition.getPageSize() -1)/condition.getPageSize();
        return pageCount;
    }


    /**
     * 增加成长值日志
     * @param request
     * @param growth
     * @param pointId
     */
    private void addGrowthLog(PointParam request,int growth,int pointId)
    {
        String eventCode = request.getEventCode();
        String remarks = request.getRemarks();
        String opUserId = request.getOpUserId();
        PointLog log = new PointLog();
        log.setGrowth(growth);
        log.setEventCode(eventCode);
        log.setOpUserId(opUserId);
        log.setPointId(pointId);
        log.setRemarks(remarks);
       // log.setCreateTime(new Date(System.currentTimeMillis()));
        pointDao.creatGrowthLog(log);
    }


    /**
     * 增加积分日志
     * @param request
     * @param pointVal
     * @param pointId
     */
    private void addPointLog (PointParam request ,int pointVal,int pointId)
    {
        String eventCode = request.getEventCode();
        String remarks = request.getRemarks();
        String opUserId = request.getOpUserId();
        PointLog log = new PointLog();
        log.setPoint(pointVal);
        log.setEventCode(eventCode);
        log.setOpUserId(opUserId);
        log.setPointId(pointId);
        log.setRemarks(remarks);
     //   log.setCreateTime(new Date(System.currentTimeMillis()));
        pointDao.createPointLog(log);
    }

    /**
     * 增加积分日志
     * @param request
     * @param pointVal
     * @param pointId
     */
    private void addPointLog (UsePointParam request ,int pointVal,int pointId)
    {
        String eventCode = UsePointParam.eventCode;
        String remarks = request.getRemarks();
        String opUserId = request.getOpUserId();
        PointLog log = new PointLog();
        log.setPoint(pointVal*(-1));
        log.setEventCode(eventCode);
        log.setOpUserId(opUserId);
        log.setPointId(pointId);
        log.setRemarks(remarks);
   //     log.setCreateTime(new Date(System.currentTimeMillis()));
        pointDao.createPointLog(log);
    }

//    public int deleteTestData()
//    {
//        QueryPointCondition condition = new   QueryPointCondition();
//        condition.setState(State.TEST);
//        QueryPointResult response = queryPoints(condition);
//         if(null == response && null == response.getList() && response.getList().size() == 0 )
//         {
//             return SUCCESS;
//         }
//        for(Point p : response.getList() )
//        {
//            pointDao.deletePointLog(p.getId());
//            pointDao.deleteGrowthLog(p.getId());
//            pointDao.deletePoint(p.getId());
//        }
//        return SUCCESS;
//    }


}
