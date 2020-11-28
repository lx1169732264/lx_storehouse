package com.lx.sys.common;

import com.lx.bus.domain.Goods;
import com.lx.bus.domain.Inport;
import com.lx.bus.service.GoodsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lx
 * @create: 2020-01-04 09:35
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {

    public static final ResultObj IS_LOGIN = new ResultObj(200, "已登陆");
    public static final ResultObj UN_LOGIN = new ResultObj(-1, "未登陆");

    public static final ResultObj DELETE_SUCCESS = new ResultObj(200, "删除成功");
    public static final ResultObj DELETE_ERROR = new ResultObj(-1, "删除失败");


    public static final ResultObj ADD_SUCCESS = new ResultObj(200, "添加成功");
    public static final ResultObj ADD_ERROR = new ResultObj(-1, "添加失败");

    public static final ResultObj UPDATE_SUCCESS = new ResultObj(200, "更新成功");
    public static final ResultObj UPDATE_ERROR = new ResultObj(-1, "更新失败");
    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(200, "分配成功");
    public static final ResultObj DISPATCH_ERROR = new ResultObj(-1, "分配失败");
    public static final ResultObj RESET_SUCCESS = new ResultObj(200, "重置成功");
    public static final ResultObj RESET_ERROR = new ResultObj(-1, "重置成功");

    private Integer code = 200;
    private String msg = "";
    private Object token = "";


    public ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultObj(Goods goods) {
        if (goods.getNumber() > goods.getUppernum()) {
            this.code = -1;
            this.msg = "操作成功!当前库存:" + goods.getNumber() + ", 高于库存预警:" + goods.getUppernum() + ",请出货!";
        } else if (goods.getNumber() < goods.getLowernum()) {
            this.code = -1;
            this.msg = "操作成功!当前库存:" + goods.getNumber() + ", 低于库存预警:" + goods.getLowernum() + ",请补货!";
        } else {
            this.code = 200;
            this.msg = "操作成功";
        }
    }

    public ResultObj(GoodsService goodsService,Inport inport) {
        this(goodsService.getById(inport.getGoodsid()));
    }
}
