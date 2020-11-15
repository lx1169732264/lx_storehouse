package com.lx.sys.config;

import com.lx.sys.vo.AjaxResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author lx
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 前端传null  MethodArgumentNotValidException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult jsonErrorHandler(MethodArgumentNotValidException e) {
        return getAjaxResult(e.getBindingResult());
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public AjaxResult jsonErrorHandler(SQLIntegrityConstraintViolationException e) {
        return new AjaxResult(-1, "该记录已存在,请勿重复添加");
    }

    /**
     * Validated验证失败    BindException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public AjaxResult jsonErrorHandler(BindException e) {
        return getAjaxResult(e.getBindingResult());
    }


    private AjaxResult getAjaxResult(BindingResult bindingResult) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError allError : allErrors) {
            Map<String, Object> map = new HashMap<>(8);
            //返回消息
            map.put("defaultMessage", allError.getDefaultMessage());
            //报错对象
            map.put("objectName", allError.getObjectName());
            //报错对象的属性
            FieldError fieldError = (FieldError) allError;
            map.put("field", fieldError.getField());
            list.add(map);
        }
        return AjaxResult.fail("后端数据校验异常", list);
    }
}
