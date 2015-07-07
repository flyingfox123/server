package com.manyi.usercenter.user.support.dao;

import com.manyi.usercenter.user.bean.*;
import com.manyi.usercenter.user.support.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Magic on 2015/1/7.
 */
public interface UserDao {

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    BaseUser getUserByName(String username);

    /**
     * 根据传入用户实体查询用户基本信息
     * @param platUser
     * @return
     */
    BaseUser getUser(PlatUser platUser);

    /**
     * 查询所有平台用户
     * @return
     */
    List<PlatUser> queryAllSysUsers();

    /**
     * 查询平台用户
     * @return
     */
    List<PlatUser> querySysUser(PlatUser platUser);

    /**
     * 查询平台用户数量
     * @return
     */
    int querySysUserCount(PlatUser platUser);

    /**
     * 创建基础用户
     * @param baseUser
     */
    int createUser(BaseUser baseUser);

    /**
     * 添加平台用户
     * @param sysUser
     * @return
     */
    int createSysUser(SysUser sysUser);

    /**
     * 修改平台用户状态
     * @return
     */
    int updateSysUserStatus(@Param("userId") long userId, @Param("state") String state);

    /**
     * 修改平台用户昵称
     * @param userId
     * @param name
     */
    void updateSysUserName(@Param("userId")long userId, @Param("name")String name);

    /**
     * 删除基本用户
     * @param id
     * @return
     */
    int deleteUser(Long id);

    /**
     * 删除平台用户
     * @return
     */
    int deleteSysUser(Long id);

    /**
     * 注册个体用户
     * @param individual
     */
    void createIndividual(Individual individual);

    /**
     * 修改个人信息
     * @param individual
     */
    void updateIndividual(Individual individual);

    /**
     * 查询司机用户信息
     * @param loginName
     * @return
     */
    List<Individual> getIndividual(String loginName);

    /**
     * 查询司机用户信息
     * @param individualBean
     * @return
     */
    List<Individual> findIndividual(IndividualBean individualBean);

    /**
     * 查询司机用户数量
     * @param individualBean
     * @return
     */
    int findIndividualCount(IndividualBean individualBean);

    /**
     * 根据ID查询司机信息
     * @param id
     * @return
     */
    Individual getIndividualById(@Param("id")Long id);

    /**
     * 创建企业用户
     * @param corporation
     */
    void createCorporater(Corporation corporation);

    /**
     * 查询企业用户
     * @param corporation
     */
    List<CorpUser> queryCorporationInfo(Corporation corporation);

    /**
     * 查询企业用户数量
     * @param corporation
     */
    int queryCorporationCount(Corporation corporation);

    /**
     * 修改企业用户信息
     * @param corporation
     */
    void updateCorporater(Corporation corporation);

    /**
     * 修改用户密码
     * @param baseUser
     */
    void updatePassword(BaseUser baseUser);

    /**
     * 根据Id获取当前基础用户信息
     * @param id
     * @return
     */
    BaseUser getBaseUserById(@Param("id")Long id);

    /**
     * 查询用户角色
     * @param name
     * @return
     */
    List<String> findRoles(@Param("name")String name);

    /**
     * 查询用户权限
     * @param rolename
     * @return
     */
    List<String> findPermissions(@Param("name")String rolename);

    /**
     * 增加车辆信息
     * @param vehicleBean
     */
    void addVehicle(VehicleBean vehicleBean);

    /**
     * 根据userId查询车辆信息
     * @param vehicleBean
     * @return
     */
    List<Vehicle> findVehicle(VehicleBean vehicleBean);

    /**
     * 更新车辆信息
     * @param vehicleBean
     */
    void updateVehicle(VehicleBean vehicleBean);

    /**
     * 增加地址信息
     * @param addressBean
     */
    void addAddress(AddressBean addressBean);

    /**
     * 查询地址信息
     * @param addressBean
     * @return
     */
    List<Address> findAddress(AddressBean addressBean);

    /**
     * 更新地址
     * @param addressBean
     */
    void updateAddress(AddressBean addressBean);

    /**
     * 删除地址
     * @param addressBean
     */
    void deleteAddress(AddressBean addressBean);

    /**
     * 设置所有地址为非默认
     * @param addressBean
     */
    void setAllAddressNoDef(AddressBean addressBean);
}
