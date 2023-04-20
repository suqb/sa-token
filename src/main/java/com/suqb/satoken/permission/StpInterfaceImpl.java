package com.suqb.satoken.permission;

import cn.dev33.satoken.stp.StpInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suqb 2023/4/20
 * 权限/角色获取工具类
 * 支持权限通配符 *
 *     当某个用户的权限仅为 * 时，属于上帝权限
 *  将权限精确到按钮？
 *     1. 在登录时，把当前账号拥有的所有权限码一次性返回给前端。
 *     2. 前端将权限码集合保存在localStorage或其它全局状态管理对象中。
 *     3. 在需要权限控制的按钮上，使用 js 进行逻辑判断，例如在Vue框架中我们可以使用如下写法：
 *         <button v-if="arr.indexOf('user.delete') > -1">删除按钮</button>
 */
@Component
@Slf4j
public class StpInterfaceImpl implements StpInterface {

    static {
        log.info("正在初始化权限查询器>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    /**
     * 获取权限集合
     * @param loginId 调用login方法时传入的id
     * @param loginType 不知道是什么东西，但是获取token时会返回一个login
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        ArrayList<String> pList = new ArrayList<>(20);
        pList.add("user.add");
        pList.add("user.delete");
        pList.add("user.update");

        // 支持通配符
        pList.add("*.get");
        return pList;
    }

    /**
     * 获取角色
     * @param loginId 调用login方法时传入的id
     * @param loginType 不知道是什么东西，但是获取token时会返回一个login
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        ArrayList<String> pList = new ArrayList<>(20);
        pList.add("admin");
        pList.add("employee");
        return pList;
    }
}
